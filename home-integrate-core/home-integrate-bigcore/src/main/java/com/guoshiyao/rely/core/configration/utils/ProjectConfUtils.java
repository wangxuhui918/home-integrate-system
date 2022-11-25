/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.utils;

import com.guoshiyao.rely.BaseEv;

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


}
