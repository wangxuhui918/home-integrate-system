/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
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
import com.guoshiyao.rely.base.BaseEv;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.ab.ProjectCoreConfAb;
import com.guoshiyao.rely.coreconf.utils.HomeCoreConfUtils;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.coreconf.vo.ModelConfigInfoVo;
import com.guoshiyao.rely.coreconf.vo.ModelConfigPropertiesVo;
import com.guoshiyao.rely.coreconf.vo.ReadMeVo;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.resource.ResourceFindUtils;
import com.guoshiyao.rely.velocity.VelocityUtils;

import java.io.File;
import java.net.URI;
import java.util.*;
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
                List<URI> listUrl = ResourceFindUtils.findUri(BaseEv.HOME_TAG + "-*.ini");//Line.env.getName()
                for (int i = 0; i < listUrl.size(); i++) {
                    LoggerBaseAb.info("读取到[{}]配置文件", listUrl.get(i).toString());
                    Setting o = new Setting(listUrl.get(i).toURL(), CharsetUtil.CHARSET_UTF_8, true);
                    allEnvSetting.addSetting(o);
                }
            }
            {
                thisEnvKeyValues.clear();
                List<String> groups = allEnvSetting.getGroups();
                LoggerBaseAb.info("加载环境[{}]配置", Line.runEnv);
                for (int j = 0; j < groups.size(); j++) {
                    String s = groups.get(j);
                    if (s.contains("-" + Line.runEnv + "-")) {
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
            List<URI> listUri = ResourceFindUtils.findUri("message-*.xml");//Line.env.getName()
            for (int i = 0; i < listUri.size(); i++) {
                if (listUri.get(i).toString().contains("-" + Line.i18n)) {
                    String name = StrUtil.subBetween(listUri.get(i).toString(), "message-", ".xml");
                    String context =
//                            ResourceUtil.readUtf8Str(listUri.get(i).);
                            FileUtil.readString(listUri.get(i).toURL(), CharsetUtil.CHARSET_UTF_8);
                    map.put(name, context);
                }
            }
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
        String groupName = StrUtil.format("{}-{}-{}", code, Line.runEnv, listModelConfigPropertiesVo.get(code).getName_en());
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
        if (!Line.isClassModel) {
            LoggerBaseAb.err("开发摸下才可进行初始化,系统已停止");
            System.exit(-1);
        }
        List<String> envs = new ArrayList<>(Arrays.asList(Line.configEnv));
        envs.add(Line.runEnv);

        for (String envName : envs) {
            if (Line.autoUpdate || !ProjectCoreConfUtils.getDbInit() || ((Line.UK).equals(envName) && Line.isClassModel)) {//数据库未初始化,手动更新配置
                HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> config = HomeCoreConfUtils.getModelConf();
                List<ModelConfigInfoVo> sortx = config.keySet().stream().sorted(Comparator.comparingInt(od -> od.getSort_id()))
                        .collect(Collectors.toList());

                StringBuffer envstr = new StringBuffer("");
                for (int k = 0; k < sortx.size(); k++) {
                    ModelConfigInfoVo modelConfigInfo = sortx.get(k);
                    List<ModelConfigPropertiesVo> listProperties = config.get(modelConfigInfo);
                    if (listProperties != null && listProperties.size() > 0) {
                        envstr.append(StrUtil.format("\n\n\n\n\n\n[{}-{}-{}]\n", modelConfigInfo.getCode(), envName, listModelConfigPropertiesVo.get(modelConfigInfo.getCode()).getName_en()));//格式为 code-env
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
                    String file = Line.projectresourcepath + File.separator + BaseEv.HOME_TAG+"-" + envName + ".ini";
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
