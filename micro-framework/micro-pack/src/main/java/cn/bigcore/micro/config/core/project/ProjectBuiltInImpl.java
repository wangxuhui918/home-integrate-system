/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config.core.project;

import cn.bigcore.micro.core.configration.home.bean.ConfigMainVo;
import cn.bigcore.micro.core.configration.home.bean.ResourceType;
import cn.bigcore.micro.core.configration.utils.ProjectConfUtils;
import cn.bigcore.micro.plugin.log.ILoggerBaseUtils;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.core.configration.home.bean.ConfigDetailsVo;
import cn.bigcore.micro.core.configration.home.bean.FileStructureVo;
import cn.bigcore.micro.core.configration.home.impl.bean.ConfigDetails;
import cn.bigcore.micro.core.configration.project.IProjectConf;
import cn.bigcore.micro.core.configration.utils.CoreConfUtils;
import cn.bigcore.micro.core.utils.resource.ResourceFindUtils;
import cn.bigcore.micro.core.utils.velocity.VelocityUtils;

import java.net.URI;
import java.util.*;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme ini文件格式方式, 已经在注册表中注销
 */
@Deprecated
public class ProjectBuiltInImpl implements IProjectConf {

    @Override
    public HashMap<String, String> reloadPropertiesValue() {
        HashMap<String, String> thisEnvPropertiesValue = new HashMap<>();
        Setting allEnvSetting = new Setting();
        try {
            List<URI> listUrl = ResourceFindUtils.findUri(BaseEv.SystemInformation.SYSTEM_EN_NAME + "-*.ini");//Line.env.getName()
            for (int i = 0; i < listUrl.size(); i++) {
                ILoggerBaseUtils.debug("读取到[{}]配置文件", listUrl.get(i).toString());
                Setting o = new Setting(listUrl.get(i).toURL(), CharsetUtil.CHARSET_UTF_8, true);
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
                allEnvSetting.addSetting(o);
            }

            if (StrUtil.isNotBlank(BaseEv.SettingInformation.runEnv)) {//塞入默认值
                List<String> envs = new ArrayList<>(Arrays.asList(BaseEv.SettingInformation.configEnv));
                envs.add(BaseEv.SettingInformation.runEnv);
                for (String envName : envs) {
                    for (ConfigDetails o : ConfigDetails.values()) {
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
                ILoggerBaseUtils.info("加载环境[{}]配置", BaseEv.SettingInformation.runEnv);
                for (int j = 0; j < groups.size(); j++) {
                    String s = groups.get(j);
                    if (s.contains("-" + BaseEv.SettingInformation.runEnv)) {
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
    public String getDefaultMessageXmlContexts() {
        try {
            return ResourceUtil.readUtf8Str(StrUtil.format("message-{}.xml", BaseEv.SettingInformation.i18n));
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
        String file = BaseEv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + "install" + FileUtil.FILE_SEPARATOR + "installed.txt";
        return FileUtil.exist(file);
    }

    @Override
    public boolean install() {
        String file = BaseEv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + "install" + FileUtil.FILE_SEPARATOR + "installed.txt";
        FileUtil.writeUtf8String("v1-" + DateUtil.now(), file);
        return true;
    }

    @Override
    public void writeFileStructures() {
        List<FileStructureVo> list = CoreConfUtils.getFileStructures();
        for (int i = 0; i < list.size(); i++) {
            FileStructureVo inf = list.get(i);
            String path = "";
            String context = inf.getContext();
            if (inf.getResourceType().equals(ResourceType.SOURCE_TYPE)) {
                path = BaseEv.WorkDir.projectcodesourcepath + ClassUtil.getPackagePath(BaseEv.WorkDir.mainClassC) + FileUtil.FILE_SEPARATOR + inf.getPath();
            } else if (inf.getResourceType().equals(ResourceType.RESOURCE_TYPE)) {
                path = BaseEv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + inf.getPath();
            }
            if (!FileUtil.exist(path)) {
                FileUtil.writeUtf8String(context, path);
            }
        }
    }


    @Override
    public void writeProperties() {
        if (!BaseEv.SettingInformation.isClassModel) {
            ILoggerBaseUtils.err("开发摸下才可进行初始化,系统已停止");
            System.exit(-1);
        }
        List<String> envs = new ArrayList<>(Arrays.asList(BaseEv.SettingInformation.configEnv));
        envs.add(BaseEv.SettingInformation.runEnv);

        for (String envName : envs) {
            if (BaseEv.SettingInformation.autoUpdate || !ProjectConfUtils.installed() || ((BaseEv.SettingInformation.UK).equals(envName) && BaseEv.SettingInformation.isClassModel)) {//数据库未初始化,手动更新配置
                List<ConfigMainVo> listMainConfig = CoreConfUtils.getPropertiesMain();
                List<String> lineStr = new ArrayList<>();
                for (int k = 0; k < listMainConfig.size(); k++) {
                    ConfigMainVo mainConfig = listMainConfig.get(k);
                    List<ConfigDetailsVo> listProperties = CoreConfUtils.getPropertiesDetails(mainConfig.getConfigName());
                    if (listProperties != null && listProperties.size() > 0) {
                        lineStr.add(StrUtil.format("[{}-{}]", mainConfig.getName(), envName));//格式为 code-env
                        for (int i = 0; i < listProperties.size(); i++) {
                            ConfigDetailsVo o = listProperties.get(i);
                            lineStr.add(VelocityUtils.convert(o.getMark(), BaseEv.SettingInformation.context));
                            lineStr.add(StrUtil.blankToDefault(o.getBeforesuff(), "") + o.getKey() + o.getM() + o.getValue());
                        }
                    } else if (StrUtil.isNotBlank(mainConfig.getContext())) {
                        String name_en = VelocityUtils.convert(mainConfig.getConfigName(), BaseEv.SettingInformation.context);
                        String context = VelocityUtils.convert(mainConfig.getContext(), BaseEv.SettingInformation.context);
                        String file = BaseEv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + name_en;
                        if (!FileUtil.exist(file)) {
                            FileUtil.writeUtf8String(context, file);
                        }
                    }
                }
                {//写入文件
                    String file = BaseEv.WorkDir.projectresourcepath + FileUtil.FILE_SEPARATOR + BaseEv.SystemInformation.SYSTEM_EN_NAME + "-" + envName + ".ini";
                    if (!FileUtil.exist(file)) {
                        FileUtil.writeUtf8Lines(lineStr, file);
                    }
                }
            }
        }

        if (!ProjectConfUtils.installed()) {//数据库未初始化的情况
            ProjectConfUtils.writeReadMes();
            ProjectConfUtils.install();
        }

    }


}
