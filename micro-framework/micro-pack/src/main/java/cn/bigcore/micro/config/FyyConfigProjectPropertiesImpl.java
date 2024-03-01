/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.base.daemon.FyyProjectConfigRoot;
import cn.bigcore.micro.base.exception.type.FyyExceptionError;
import cn.bigcore.micro.base.frame.FyyConfigEntryDetailsVo;
import cn.bigcore.micro.base.frame.FyyConfigEntryVo;
import cn.bigcore.micro.base.frame.FyyConfigFileStructureVo;
import cn.bigcore.micro.base.frame.impl.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.base.frame.impl.FyyConfigResourceType;
import cn.bigcore.micro.config.project.FyyConfigProjectInterface;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.bigcore.micro.utils.FyyConfigFrameUtils;
import cn.bigcore.micro.utils.FyyConfigProjectUtils;
import cn.bigcore.micro.utils.properties.FyyPropertiesUtils;
import cn.bigcore.micro.utils.resource.FyyResourceFindUtils;
import cn.bigcore.micro.utils.velocity.FyyVelocityUtils;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;

import java.net.URI;
import java.util.*;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
public class FyyConfigProjectPropertiesImpl implements FyyConfigProjectInterface {

    @Override
    public HashMap<String, String> reloadPropertiesValue() {
        HashMap<String, String> thisEnvPropertiesValue = new HashMap<>();
        Setting allEnvSetting = new Setting();
        try {
            List<URI> listUrl = FyyResourceFindUtils.findUri("application-*.properties");//Line.env.getName()
            for (int i = 0; i < listUrl.size(); i++) {
                String env = ReUtil.findAll("application-(.*?)\\.properties", listUrl.get(i).toString(), 1).get(0);
                FyyLogBaseUtils.debug("读取到[{}]配置文件", listUrl.get(i).toString());
                HashMap<String, String> properties = FyyPropertiesUtils.getProperties("application-" + env + ".properties");
                Setting o = new Setting();
                for (Object key : properties.keySet()) {
                    o.putByGroup(key.toString(), env, properties.get(key.toString()).toString());
                }
                allEnvSetting.addSetting(o);
            }

            if (StrUtil.isNotBlank(FyyInitEnv.ProjectInformation.runEnv)) {//塞入默认值
                List<String> envs = new ArrayList<>(Arrays.asList(FyyInitEnv.ProjecEnvInformation.configEnv));
                envs.add(FyyInitEnv.ProjectInformation.runEnv);
                for (String envName : envs) {
                    for (FyyConfigEntryDetailsValues o : FyyConfigEntryDetailsValues.values()) {
                        if (!allEnvSetting.containsKey(envName, o.getKey())) {
                            allEnvSetting.putByGroup(o.getKey(), envName, o.getValue());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        {
            thisEnvPropertiesValue.clear();
            FyyProjectConfigRoot projectConfigRoot = FyyInitEnv.ProjectInformation.daemonRoot.getProject_config().get(FyyInitEnv.ProjectInformation.idKey);
            if (projectConfigRoot != null && StrUtil.isNotBlank(projectConfigRoot.getPrject_application_properties_path())) {
                if (!FileUtil.exist(projectConfigRoot.getPrject_application_properties_path())) {
                    throw new FyyExceptionError("配置文件:{}不存在!", projectConfigRoot.getPrject_application_properties_path());
                }
                String path = projectConfigRoot.getPrject_application_properties_path();
                HashMap<String, String> properties = FyyPropertiesUtils.getProperties(path);
                if (properties == null || properties.size() == 0) {
                    throw new FyyExceptionError("读取{}文件中自定义配置路径失败,配置为空!", FyyInitEnv.FrameInformation.MAIN_CONFIG);
                }
                FyyLogBaseUtils.debug("加载主配置{},服务:{},配置{}!", FyyInitEnv.FrameInformation.MAIN_CONFIG, FyyInitEnv.ProjectInformation.idKey, path);
                thisEnvPropertiesValue.putAll(properties);
                {
                    Setting o = new Setting();
                    for (Object key : properties.keySet()) {
                        o.putByGroup(key.toString(), FyyInitEnv.ProjectInformation.runEnv, properties.get(key.toString()).toString());
                    }
                    allEnvSetting.clear(FyyInitEnv.ProjectInformation.runEnv);
                    allEnvSetting.addSetting(o);
                }
            } else {
                List<String> groups = allEnvSetting.getGroups();
                FyyLogBaseUtils.info("加载环境[{}]配置", FyyInitEnv.ProjectInformation.runEnv);
                for (int j = 0; j < groups.size(); j++) {
                    String s = groups.get(j);
                    if (s.equals(FyyInitEnv.ProjectInformation.runEnv)) {
                        thisEnvPropertiesValue.putAll(allEnvSetting.getMap(s));
                    }
                }
            }
        }
        return thisEnvPropertiesValue;
    }

    @Override
    public Map<String, String> getAllMessageXmlContexts() {
        Map<String, String> map = new HashMap<>();
        try {
            List<URI> listUri = FyyResourceFindUtils.findUri("message-*.xml");//Line.env.getName()
            for (int i = 0; i < listUri.size(); i++) {
                if (!listUri.get(i).toString().contains("-" + FyyInitEnv.ProjectInformation.i18n)) {
                    String name = StrUtil.subBetween(listUri.get(i).toString(), "message-", ".xml");
                    String context = FileUtil.readString(listUri.get(i).toURL(), CharsetUtil.CHARSET_UTF_8);
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
            return ResourceUtil.readUtf8Str(StrUtil.format("message-{}.xml", FyyInitEnv.ProjectInformation.i18n));
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
        String file = FyyInitEnv.WorkDir.PROJECT_RESOURCEPATH + FileUtil.FILE_SEPARATOR + "install" + FileUtil.FILE_SEPARATOR + "installed.txt";
        return FileUtil.exist(file);
    }

    @Override
    public boolean install() {
        String file = FyyInitEnv.WorkDir.PROJECT_RESOURCEPATH + FileUtil.FILE_SEPARATOR + "install" + FileUtil.FILE_SEPARATOR + "installed.txt";
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
                path = FyyInitEnv.WorkDir.PROJECT_CODESOURCEPATH + ClassUtil.getPackagePath(FyyInitEnv.WorkDir.mainClassC) + FileUtil.FILE_SEPARATOR + inf.getPath();
            } else if (inf.getResourceType().equals(FyyConfigResourceType.RESOURCE_TYPE)) {
                path = FyyInitEnv.WorkDir.PROJECT_RESOURCEPATH + FileUtil.FILE_SEPARATOR + inf.getPath();
            }
            if (!FileUtil.exist(path)) {
                FileUtil.writeUtf8String(context, path);
            }
        }
    }


    @Override
    public void writeProperties() {
        if (!FyyInitEnv.ProjecEnvInformation.isClassModel) {
            FyyLogBaseUtils.err("开发摸下才可进行初始化,系统已停止");
            System.exit(-1);
        }
        List<String> envs = new ArrayList<>(Arrays.asList(FyyInitEnv.ProjecEnvInformation.configEnv));
        envs.add(FyyInitEnv.ProjectInformation.runEnv);

        for (String envName : envs) {
            if (FyyInitEnv.ProjectInformation.autoUpdate || !FyyConfigProjectUtils.installed() || ((FyyInitEnv.ProjectInformation.daemonRoot.getDevelop_user_id()).equals(envName) && FyyInitEnv.ProjecEnvInformation.isClassModel)) {//数据库未初始化,手动更新配置
                List<FyyConfigEntryVo> listMainConfig = FyyConfigFrameUtils.getPropertiesMain();
                List<String> lineStr = new ArrayList<>();
                for (int k = 0; k < listMainConfig.size(); k++) {
                    FyyConfigEntryVo mainConfig = listMainConfig.get(k);
                    List<FyyConfigEntryDetailsVo> listProperties = FyyConfigFrameUtils.getPropertiesDetails(mainConfig.getConfigName());
                    if (listProperties != null && listProperties.size() > 0) {
                        for (int i = 0; i < listProperties.size(); i++) {
                            FyyConfigEntryDetailsVo o = listProperties.get(i);
                            lineStr.add(FyyVelocityUtils.convert(o.getMark(), FyyInitEnv.ProjectInformation.context));
                            lineStr.add(StrUtil.blankToDefault(o.getBeforesuff(), "") + o.getKey() + o.getM() + o.getValue());
                        }
                    } else if (StrUtil.isNotBlank(mainConfig.getContext())) {
                        String name_en = FyyVelocityUtils.convert(mainConfig.getConfigName(), FyyInitEnv.ProjectInformation.context);
                        String context = FyyVelocityUtils.convert(mainConfig.getContext(), FyyInitEnv.ProjectInformation.context);
                        String file = FyyInitEnv.WorkDir.PROJECT_RESOURCEPATH + FileUtil.FILE_SEPARATOR + name_en;
                        if (!FileUtil.exist(file)) {
                            FileUtil.writeUtf8String(context, file);
                        }
                    }
                }
                {//写入文件
                    String file = FyyInitEnv.WorkDir.PROJECT_RESOURCEPATH + FileUtil.FILE_SEPARATOR + "application-" + envName + ".properties";
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
