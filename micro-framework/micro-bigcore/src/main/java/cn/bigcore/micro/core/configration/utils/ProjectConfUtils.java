/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.core.configration.utils;

import cn.bigcore.micro.BaseEv;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
public class ProjectConfUtils {

    public static void writeProperties() {
        BaseEv.SettingInformation.projectConf.writeProperties();
    }


    public static void writeReadMes() {
        BaseEv.SettingInformation.projectConf.writeFileStructures();
    }


    public static boolean installed() {
        return BaseEv.SettingInformation.projectConf.installed();
    }

    public static void install() {
        BaseEv.SettingInformation.projectConf.install();
    }

    public static String getZoneMessageFileContext() {
        return BaseEv.SettingInformation.projectConf.getDefaultMessageXmlContexts();
    }

    public static Map<String, String> getAllMessageXmlContexts() {
        return BaseEv.SettingInformation.projectConf.getAllMessageXmlContexts();
    }

    public static Map<String, String> getThisEnvPropertiesValue() {
        return BaseEv.SettingInformation.projectConf.getThisEnvPropertiesValue();
    }

    public static HashMap<String, String> reloadPropertiesValue() {
        return BaseEv.SettingInformation.projectConf.reloadPropertiesValue();
    }

}