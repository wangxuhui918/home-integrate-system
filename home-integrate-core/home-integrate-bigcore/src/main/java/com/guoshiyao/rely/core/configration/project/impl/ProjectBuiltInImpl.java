/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.project.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.bean.ConfigMainType;
import com.guoshiyao.rely.core.configration.project.IProjectConf;
import com.guoshiyao.rely.core.configration.vo.ModelConfigInfoVo;
import com.guoshiyao.rely.core.configration.vo.ModelConfigPropertiesVo;
import com.guoshiyao.rely.core.configration.vo.ReadMeVo;
import com.guoshiyao.rely.core.utils.conf.CoreConfUtils;
import com.guoshiyao.rely.core.utils.conf.ProjectConfUtils;
import com.guoshiyao.rely.core.utils.resource.ResourceFindUtils;
import com.guoshiyao.rely.core.utils.velocity.VelocityUtils;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
@RuleInjection
public class ProjectBuiltInImpl implements IProjectConf {
    private static HashMap<String, ModelConfigInfoVo> listModelConfigPropertiesVo = new HashMap<>();
    private static HashMap<String, String> thisEnvKeyValues = new HashMap<>();
    private static Setting allEnvSetting = new Setting();

    static {
        for (int i = 0; i < ConfigMainType.values().length; i++) {
            ModelConfigInfoVo m1 = new ModelConfigInfoVo();
            m1.setCode(ConfigMainType.values()[i].getOld_code());
            m1.setFile_path("");
            m1.setSort_id(ConfigMainType.values()[i].getSort_id());
            m1.setName_en(ConfigMainType.values()[i].getCode());
            m1.setName_cn("");
            m1.setModel_context(ConfigMainType.values()[i].getModel_context());
            m1.setOnly_local(ConfigMainType.values()[i].isOnly_local() ? "1" : "0");
            m1.setUse_uk(ConfigMainType.values()[i].isUse_uk() ? "1" : "0");
            m1.setNeed_format_zone(ConfigMainType.values()[i].isNeed_format_zone() ? "1" : "0");
            listModelConfigPropertiesVo.put(m1.getCode(), m1);
        }
        if (allEnvSetting.size() == 0) {
            loadAll();
        }
    }

    private static void loadAll() {
        try {
            {
                allEnvSetting = new Setting();
                List<URI> listUrl = ResourceFindUtils.findUri(BaseEv.SystemInformation.SYSTEM_EN_NAME + "-*.ini");//Line.env.getName()
                for (int i = 0; i < listUrl.size(); i++) {
                    ILoggerBaseUtils.info("读取到[{}]配置文件", listUrl.get(i).toString());
                    Setting o = new Setting(listUrl.get(i).toURL(), CharsetUtil.CHARSET_UTF_8, true);
                    {//可使用表达式{}
                        List<String> groups = o.getGroups();
                        for (int j = 0; j < groups.size(); j++) {
                            String groupName = groups.get(j);
                            Set<String> keys = o.getMap(groupName).keySet();
                            for (String key : keys) {
                                try {
                                    o.putByGroup(key, groupName, VelocityUtils.convert(o.getByGroup(key, groupName), BaseEv.SettingInformation.context));
                                } catch (Exception e) {
                                    ILoggerBaseUtils.warn("配置[{}]值[{}]转换失败!", key, o.getByGroup(key, groupName));
                                }
                            }
                        }
                    }
                    allEnvSetting.addSetting(o);
                }
            }
            {
                thisEnvKeyValues.clear();
                List<String> groups = allEnvSetting.getGroups();
                ILoggerBaseUtils.info("加载环境[{}]配置", BaseEv.SettingInformation.runEnv);
                for (int j = 0; j < groups.size(); j++) {
                    String s = groups.get(j);
                    if (s.contains("-" + BaseEv.SettingInformation.runEnv + "-")) {
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
                if (!listUri.get(i).toString().contains("-" + BaseEv.SettingInformation.i18n)) {
                    String name = StrUtil.subBetween(listUri.get(i).toString(), "message-", ".xml");
                    String context =
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
            return ResourceUtil.readUtf8Str(StrUtil.format("message-{}.xml", BaseEv.SettingInformation.i18n));
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
        String groupName = StrUtil.format("{}-{}-{}", code, BaseEv.SettingInformation.runEnv, listModelConfigPropertiesVo.get(code).getName_en());
        return allEnvSetting.getMap(groupName);
    }

    @Override
    public boolean getDbInit() {
        String file = BaseEv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + "versionlog" + FileUtil.FILE_SEPARATOR + "v1-init";
        return FileUtil.exist(file);
    }

    @Override
    public boolean setDbInit() {
        String file = BaseEv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + "versionlog" + FileUtil.FILE_SEPARATOR + "v1-init";
        FileUtil.writeUtf8String("v1-" + DateUtil.now(), file);
        return true;
    }

    @Override
    public void writeReadMes() {
        List<ReadMeVo> list = CoreConfUtils.getReadMe();
        for (int i = 0; i < list.size(); i++) {
            ReadMeVo inf = list.get(i);
            String path = "";
            String context = inf.getReadme();
            if (inf.getType().equals("1")) {
                path = BaseEv.WorkDir.projectcodesourcepath + ClassUtil.getPackagePath(BaseEv.WorkDir.mainClassC) + FileUtil.FILE_SEPARATOR + inf.getPath();
            } else if (inf.getType().equals("2")) {
                path = BaseEv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + inf.getPath();
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
        if (!BaseEv.SettingInformation.isClassModel) {
            ILoggerBaseUtils.err("开发摸下才可进行初始化,系统已停止");
            System.exit(-1);
        }
        List<String> envs = new ArrayList<>(Arrays.asList(BaseEv.SettingInformation.configEnv));
        envs.add(BaseEv.SettingInformation.runEnv);

        for (String envName : envs) {
            if (BaseEv.SettingInformation.autoUpdate || !ProjectConfUtils.getDbInit() || ((BaseEv.SettingInformation.UK).equals(envName) && BaseEv.SettingInformation.isClassModel)) {//数据库未初始化,手动更新配置
                HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> config = CoreConfUtils.getModelConf();
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
//                                o.setValue(VelocityUtils.convert(o.getValue(), Line.context));//直接使用原生,读取的时候进行读取
                                o.setMark(VelocityUtils.convert(o.getMark(), BaseEv.SettingInformation.context));
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
                        String name_en = VelocityUtils.convert(modelConfigInfo.getName_en(), BaseEv.SettingInformation.context);
                        String context = VelocityUtils.convert(modelConfigInfo.getModel_context(), BaseEv.SettingInformation.context);
                        String file = BaseEv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + name_en;
                        if (!FileUtil.exist(file)) {
                            FileUtil.writeUtf8String(context, file);
                        }
                    }
                }
                {//写入文件
                    String file = BaseEv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + BaseEv.SystemInformation.SYSTEM_EN_NAME + "-" + envName + ".ini";
                    if (!FileUtil.exist(file)) {
                        FileUtil.writeUtf8String(envstr.toString(), file);
                    }
                }
            }
        }

        if (!ProjectConfUtils.getDbInit()) {//数据库未初始化的情况
            ProjectConfUtils.writeReadMes();
            ProjectConfUtils.copyQuasiproduction();
            ProjectConfUtils.setDbInit();
        }

        loadAll();//重新加载总配置

    }


}
