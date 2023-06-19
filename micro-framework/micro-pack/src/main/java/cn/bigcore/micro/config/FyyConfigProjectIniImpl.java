/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.config.bean.FyyConfigEntryDetailsVo;
import cn.bigcore.micro.config.config.bean.FyyConfigEntryVo;
import cn.bigcore.micro.config.config.bean.FyyConfigFileStructureVo;
import cn.bigcore.micro.config.config.bean.FyyConfigResourceType;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.utils.FyyConfigFrameUtils;
import cn.bigcore.micro.utils.FyyConfigProjectUtils;
import cn.bigcore.micro.utils.resource.FyyResourceFindUtils;
import cn.bigcore.micro.utils.velocity.FyyVelocityUtils;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;

import java.net.URI;
import java.util.*;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme ini文件格式方式, 已经在注册表中注销
 */
@Deprecated
public class FyyConfigProjectIniImpl implements FyyConfigProjectInterface {

    @Override
    public HashMap<String, String> reloadPropertiesValue() {
        HashMap<String, String> thisEnvPropertiesValue = new HashMap<>();
        Setting allEnvSetting = new Setting();
        try {
            List<URI> listUrl = FyyResourceFindUtils.findUri(FyyInitEnv.SystemInformation.SYSTEM_EN_NAME + "-*.ini");//Line.env.getName()
            for (int i = 0; i < listUrl.size(); i++) {
                FyyLogBaseUtils.debug("读取到[{}]配置文件", listUrl.get(i).toString());
                Setting o = new Setting(listUrl.get(i).toURL(), CharsetUtil.CHARSET_UTF_8, true);
                List<String> groups = o.getGroups();
                for (int j = 0; j < groups.size(); j++) {
                    String groupName = groups.get(j);
                    Set<String> keys = o.getMap(groupName).keySet();
                    for (String key : keys) {
                        try {
                            o.putByGroup(key, groupName, FyyVelocityUtils.convert(o.getByGroup(key, groupName), FyyInitEnv.SettingInformation.context));
                        } catch (Exception e) {
                            FyyLogBaseUtils.warn("配置[{}]值[{}]转换失败!", key, o.getByGroup(key, groupName));
                        }
                    }
                }
                allEnvSetting.addSetting(o);
            }

            if (StrUtil.isNotBlank(FyyInitEnv.SettingInformation.runEnv)) {//塞入默认值
                List<String> envs = new ArrayList<>(Arrays.asList(FyyInitEnv.SettingInformation.configEnv));
                envs.add(FyyInitEnv.SettingInformation.runEnv);
                for (String envName : envs) {
                    for (FyyConfigEntryDetailsValues o : FyyConfigEntryDetailsValues.values()) {
                        String groupname = o.getCodeType().name() + "-" + envName;
                        if (!allEnvSetting.containsKey(groupname, o.getKey())) {
                            allEnvSetting.putByGroup(o.getKey(), groupname, o.getValue());
                        }
                    }
                }
            }
            {
                thisEnvPropertiesValue.clear();
                List<String> groups = allEnvSetting.getGroups();
                FyyLogBaseUtils.info("加载环境[{}]配置", FyyInitEnv.SettingInformation.runEnv);
                for (int j = 0; j < groups.size(); j++) {
                    String s = groups.get(j);
                    if (s.contains("-" + FyyInitEnv.SettingInformation.runEnv)) {
                        thisEnvPropertiesValue.putAll(allEnvSetting.getMap(s));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisEnvPropertiesValue;
    }

    @Override
    public Map<String, String> getAllMessageXmlContexts() {
        Map<String, String> map = new HashMap<>();
        try {
            List<URI> listUri = FyyResourceFindUtils.findUri("message-*.xml");//Line.env.getName()
            for (int i = 0; i < listUri.size(); i++) {
                if (!listUri.get(i).toString().contains("-" + FyyInitEnv.SettingInformation.i18n)) {
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
    public String getDefaultMessageXmlContexts() {
        try {
            return ResourceUtil.readUtf8Str(StrUtil.format("message-{}.xml", FyyInitEnv.SettingInformation.i18n));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public HashMap<String, String> getThisEnvPropertiesValue() {
        return reloadPropertiesValue();
    }

    @Override
    public boolean installed() {
        String file = FyyInitEnv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + "install" + FileUtil.FILE_SEPARATOR + "installed.txt";
        return FileUtil.exist(file);
    }

    @Override
    public boolean install() {
        String file = FyyInitEnv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + "install" + FileUtil.FILE_SEPARATOR + "installed.txt";
        FileUtil.writeUtf8String("v1-" + DateUtil.now(), file);
        return true;
    }

    @Override
    public void writeFileStructures() {
        List<FyyConfigFileStructureVo> list = FyyConfigFrameUtils.getFileStructures();
        for (int i = 0; i < list.size(); i++) {
            FyyConfigFileStructureVo inf = list.get(i);
            String path = "";
            String context = inf.getContext();
            if (inf.getResourceType().equals(FyyConfigResourceType.SOURCE_TYPE)) {
                path = FyyInitEnv.WorkDir.projectcodesourcepath + ClassUtil.getPackagePath(FyyInitEnv.WorkDir.mainClassC) + FileUtil.FILE_SEPARATOR + inf.getPath();
            } else if (inf.getResourceType().equals(FyyConfigResourceType.RESOURCE_TYPE)) {
                path = FyyInitEnv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + inf.getPath();
            }
            if (!FileUtil.exist(path)) {
                FileUtil.writeUtf8String(context, path);
            }
        }
    }


    @Override
    public void writeProperties() {
        if (!FyyInitEnv.SettingInformation.isClassModel) {
            FyyLogBaseUtils.err("开发摸下才可进行初始化,系统已停止");
            System.exit(-1);
        }
        List<String> envs = new ArrayList<>(Arrays.asList(FyyInitEnv.SettingInformation.configEnv));
        envs.add(FyyInitEnv.SettingInformation.runEnv);

        for (String envName : envs) {
            if (FyyInitEnv.SettingInformation.autoUpdate || !FyyConfigProjectUtils.installed() || ((FyyInitEnv.SettingInformation.daemonRoot.getDevelop_user_id()).equals(envName) && FyyInitEnv.SettingInformation.isClassModel)) {//数据库未初始化,手动更新配置
                List<FyyConfigEntryVo> listMainConfig = FyyConfigFrameUtils.getPropertiesMain();
                List<String> lineStr = new ArrayList<>();
                for (int k = 0; k < listMainConfig.size(); k++) {
                    FyyConfigEntryVo mainConfig = listMainConfig.get(k);
                    List<FyyConfigEntryDetailsVo> listProperties = FyyConfigFrameUtils.getPropertiesDetails(mainConfig.getConfigName());
                    if (listProperties != null && listProperties.size() > 0) {
                        lineStr.add(StrUtil.format("[{}-{}]", mainConfig.getName(), envName));//格式为 code-env
                        for (int i = 0; i < listProperties.size(); i++) {
                            FyyConfigEntryDetailsVo o = listProperties.get(i);
                            lineStr.add(FyyVelocityUtils.convert(o.getMark(), FyyInitEnv.SettingInformation.context));
                            lineStr.add(StrUtil.blankToDefault(o.getBeforesuff(), "") + o.getKey() + o.getM() + o.getValue());
                        }
                    } else if (StrUtil.isNotBlank(mainConfig.getContext())) {
                        String name_en = FyyVelocityUtils.convert(mainConfig.getConfigName(), FyyInitEnv.SettingInformation.context);
                        String context = FyyVelocityUtils.convert(mainConfig.getContext(), FyyInitEnv.SettingInformation.context);
                        String file = FyyInitEnv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + name_en;
                        if (!FileUtil.exist(file)) {
                            FileUtil.writeUtf8String(context, file);
                        }
                    }
                }
                {//写入文件
                    String file = FyyInitEnv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + FyyInitEnv.SystemInformation.SYSTEM_EN_NAME + "-" + envName + ".ini";
                    if (!FileUtil.exist(file)) {
                        FileUtil.writeUtf8Lines(lineStr, file);
                    }
                }
            }
        }

        if (!FyyConfigProjectUtils.installed()) {//数据库未初始化的情况
            FyyConfigProjectUtils.writeReadMes();
            FyyConfigProjectUtils.install();
        }

    }


}
