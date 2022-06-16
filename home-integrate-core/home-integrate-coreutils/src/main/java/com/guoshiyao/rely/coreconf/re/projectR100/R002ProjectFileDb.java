/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.coreconf.re.projectR100;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.ab.ProjectCoreConfAb;
import com.guoshiyao.rely.coreconf.utils.HomeCoreConfUtils;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.coreconf.vo.EnvVo;
import com.guoshiyao.rely.coreconf.vo.ModelConfigInfoVo;
import com.guoshiyao.rely.coreconf.vo.ModelConfigPropertiesVo;
import com.guoshiyao.rely.coreconf.vo.ReadMeVo;
import com.guoshiyao.rely.environment.ENV;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.resource.ResourceFindUtils;
import com.guoshiyao.rely.velocity.VelocityUtils;

import java.io.File;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
@RuleAnnotation
public class R002ProjectFileDb implements ProjectCoreConfAb {
    private static HashMap<String, ModelConfigInfoVo> listModelConfigPropertiesVo = new HashMap<>();
    private static HashMap<String, String> thisEnvKeyValues = new HashMap<>();
    private static Setting allEnvSetting = new Setting();

    static {
        List<Map<String, Object>> list = HomeCoreConfUtils.getTableData("model_config_info");
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> stringObjectMap = list.get(i);
            ModelConfigInfoVo inf = BeanUtil.copyProperties(stringObjectMap, ModelConfigInfoVo.class);
            listModelConfigPropertiesVo.put(inf.getCode(), inf);
        }
        if (allEnvSetting.size() == 0) {
            loadAll();
        }
    }

    private static void loadAll() {
        try {
            {
                allEnvSetting = new Setting();
                List<URL> listUrl = ResourceFindUtils.find(StrUtil.format("home-*.ini"));//Line.env.getName()
                for (int i = 0; i < listUrl.size(); i++) {
                    URL url = listUrl.get(i);
                    Setting o = new Setting(url, CharsetUtil.CHARSET_UTF_8, true);
                    allEnvSetting.addSetting(o);
                }
            }
            {
                thisEnvKeyValues.clear();
                List<String> groups = allEnvSetting.getGroups();
                for (int j = 0; j < groups.size(); j++) {
                    String s = groups.get(j);
                    if (s.contains("-" + Line.env.getLocalName() + "-")) {
                        thisEnvKeyValues.putAll(allEnvSetting.getMap(s));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getotherMessageFileContext() {
        Map<String, String> map = new HashMap<>();
        try {
            List<URL> listUrl = ResourceFindUtils.find(StrUtil.format("message-{}.ini"));//Line.env.getName()
            for (int i = 0; i < listUrl.size(); i++) {
                URL url = listUrl.get(i);
                if (url.getPath().contains("-" + Line.i18n)) {
                    String name = StrUtil.subBetween(url.getPath(), "message-", ".ini");
                    String context = FileUtil.readString(url, CharsetUtil.CHARSET_UTF_8);
                    map.put(name, context);
                }
            }
//            return ResourceUtil.readUtf8Str(StrUtil.format("message-{}.xml", Line.i18n));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public String getZoneMessageFileContext() {
        try {
            return ResourceUtil.readUtf8Str(StrUtil.format("message-{}.xml", Line.i18n));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public HashMap<String, String> getEnvAllProperties() {
        return thisEnvKeyValues;
    }

    @Override
    public Map<String, String> getEnvPropertiesByCode(String code) {
        String groupName = StrUtil.format("{}-{}-{}", code, Line.env.getLocalName(), listModelConfigPropertiesVo.get(code).getName_en());
        return allEnvSetting.getMap(groupName);
    }

    @Override
    public boolean getDbInit() {
        String file = Line.projectresourcepath + File.separator + "versionlog" + File.separator + "v1-init";
        return FileUtil.exist(file);
    }

    @Override
    public boolean setDbInit() {
        String file = Line.projectresourcepath + File.separator + "versionlog" + File.separator + "v1-init";
        FileUtil.writeUtf8String("v1-" + DateUtil.now(), file);
        return true;
    }

    @Override
    public void writeReadMes() {
        List<Map<String, Object>> list = HomeCoreConfUtils.getTableData("readme");
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> stringObjectMap = list.get(i);
            ReadMeVo inf = BeanUtil.copyProperties(stringObjectMap, ReadMeVo.class);
            String path = "";
            String context = inf.getReadme();
            if (inf.getType().equals("1")) {
                path = Line.projectcodesourcepath + ClassUtil.getPackagePath(Line.mainClassC) + File.separator + inf.getPath();
            } else if (inf.getType().equals("2")) {
                path = Line.projectresourcepath + File.separator + inf.getPath();
            }
            if (!FileUtil.exist(path)) {
                FileUtil.writeUtf8String(context, path);
            }
        }
    }

    @Override
    public void copyQuasiproduction() {

    }

    @Override
    public void writeModelConfig() {
        if (Line.env != ENV.LOCAL && !Line.isClassModel) {
            LoggerBaseAb.err("-Denv={}以及开发摸下才可进行初始化,系统已停止", ENV.LOCAL.getName());
            System.exit(-1);
        }


        List<EnvVo> listEnv = HomeCoreConfUtils.getEnvs();
        for (EnvVo envvo : listEnv) {
            ENV env = ENV.getEnv(envvo.getEnv_name());
            if (Line.autoUpdate || !ProjectCoreConfUtils.getDbInit() || (env == ENV.LOCAL && Line.isClassModel)) {//数据库未初始化,手动更新配置
                HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> config = HomeCoreConfUtils.getModelConf();
                List<ModelConfigInfoVo> sortx = config.keySet().stream().sorted(Comparator.comparingInt(od -> od.getSort_id()))
                        .collect(Collectors.toList());

                StringBuffer envstr = new StringBuffer("");
                for (int k = 0; k < sortx.size(); k++) {
                    ModelConfigInfoVo modelConfigInfo = sortx.get(k);
                    List<ModelConfigPropertiesVo> listProperties = config.get(modelConfigInfo);
                    if (listProperties != null && listProperties.size() > 0) {
                        envstr.append(StrUtil.format("\n\n\n\n\n\n[{}-{}-{}]\n", modelConfigInfo.getCode(), env.getLocalName(), listModelConfigPropertiesVo.get(modelConfigInfo.getCode()).getName_en()));//格式为 code-env
                        String context = "";
                        for (int i = 0; i < listProperties.size(); i++) {
                            ModelConfigPropertiesVo o = listProperties.get(i);
                            {
                                o.setValue(VelocityUtils.convert(o.getValue(), Line.context));
                                o.setMark(VelocityUtils.convert(o.getMark(), Line.context));
                            }
                            {
                                context += o.getMark() + "\n";
                                context += StrUtil.blankToDefault(o.getBeforesuff(), "");
                                context += o.getKey();
                                context += o.getM();
                                context += o.getValue() + "\n";
                            }
                        }
                        envstr.append(context);//合并参数
                    } else if (StrUtil.isNotBlank(modelConfigInfo.getModel_context())) {
                        String name_en = VelocityUtils.convert(modelConfigInfo.getName_en(), Line.context);
                        String context = VelocityUtils.convert(modelConfigInfo.getModel_context(), Line.context);
                        String file = Line.projectresourcepath + File.separator + name_en;
                        if (!FileUtil.exist(file)) {
                            FileUtil.writeUtf8String(context, file);
                        }
                    }
                }
                {//写入文件
                    String file = Line.projectresourcepath + File.separator + "home-" + env.getLocalName() + ".ini";
                    if (!FileUtil.exist(file)) {
                        FileUtil.writeUtf8String(envstr.toString(), file);
                    }
                }
            }
        }

        if (!ProjectCoreConfUtils.getDbInit()) {//数据库未初始化的情况
            ProjectCoreConfUtils.writeReadMes();
            ProjectCoreConfUtils.copyQuasiproduction();
            ProjectCoreConfUtils.setDbInit();
        }

        loadAll();//重新加载总配置

    }


}
