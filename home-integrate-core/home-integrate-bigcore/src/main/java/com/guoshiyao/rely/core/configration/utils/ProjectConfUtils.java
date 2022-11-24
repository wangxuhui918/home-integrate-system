/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.utils;

import com.guoshiyao.rely.core.configration.project.IProjectConf;
import com.guoshiyao.rely.core.configration.project.impl.enumtype.ProjectBuiltInImpl;

import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
public class ProjectConfUtils {
    private static IProjectConf projectConf = new ProjectBuiltInImpl();

    public static void writeProperties() {
        projectConf.writeProperties();
    }


    public static void writeReadMes() {
        projectConf.writeFileStructures();
    }


    public static boolean installed() {
        return projectConf.installed();
    }

    public static void install() {
        projectConf.install();
    }

    public static String getZoneMessageFileContext() {
        return projectConf.getDefaultMessageXmlContexts();
    }

    public static Map<String, String> getAllMessageXmlContexts() {
        return projectConf.getAllMessageXmlContexts();
    }

    public static Map<String, String> getThisEnvPropertiesValue() {
        return projectConf.getThisEnvPropertiesValue();
    }


}
