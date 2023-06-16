/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.utils;

import cn.bigcore.micro.FyyInitEnv;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme  项目配置工具类
 */
public class FyyConfigProjectUtils {

    public static void writeProperties() {
        FyyInitEnv.SettingInformation.projectConf.writeProperties();
    }


    public static void writeReadMes() {
        FyyInitEnv.SettingInformation.projectConf.writeFileStructures();
    }


    public static boolean installed() {
        return FyyInitEnv.SettingInformation.projectConf.installed();
    }

    public static void install() {
        FyyInitEnv.SettingInformation.projectConf.install();
    }

    public static String getZoneMessageFileContext() {
        return FyyInitEnv.SettingInformation.projectConf.getDefaultMessageXmlContexts();
    }

    public static Map<String, String> getAllMessageXmlContexts() {
        return FyyInitEnv.SettingInformation.projectConf.getAllMessageXmlContexts();
    }

    public static Map<String, String> getThisEnvPropertiesValue() {
        return FyyInitEnv.SettingInformation.projectConf.getThisEnvPropertiesValue();
    }

    public static HashMap<String, String> reloadPropertiesValue() {
        return FyyInitEnv.SettingInformation.projectConf.reloadPropertiesValue();
    }

}
