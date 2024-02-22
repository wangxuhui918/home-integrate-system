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
        FyyInitEnv.ProjectInformation.projectConf.writeProperties();
    }


    public static void writeReadMes() {
        FyyInitEnv.ProjectInformation.projectConf.writeFileStructures();
    }


    public static boolean installed() {
        return FyyInitEnv.ProjectInformation.projectConf.installed();
    }

    public static void install() {
        FyyInitEnv.ProjectInformation.projectConf.install();
    }

    public static String getZoneMessageFileContext() {
        return FyyInitEnv.ProjectInformation.projectConf.getDefaultMessageXmlContexts();
    }

    public static Map<String, String> getAllMessageXmlContexts() {
        return FyyInitEnv.ProjectInformation.projectConf.getAllMessageXmlContexts();
    }

    public static Map<String, String> getThisEnvPropertiesValue() {
        return FyyInitEnv.ProjectInformation.projectConf.getThisEnvPropertiesValue();
    }

    public static HashMap<String, String> reloadPropertiesValue() {
        return FyyInitEnv.ProjectInformation.projectConf.reloadPropertiesValue();
    }

}
