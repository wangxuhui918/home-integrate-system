/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.utils;

import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.core.configration.project.IProjectConf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
public class ProjectConfUtils {
    private static List<IProjectConf> projectCoreConfRes = CoreConfUtils.sortByDbOrRuleApi(IProjectConf.class);


    //------------------------------------projectcore操作
    public static void writeModelConfig() {
        for (int i = 0; i < projectCoreConfRes.size(); i++) {
            projectCoreConfRes.get(i).writeModelConfig();
        }
    }

    public static HashMap<String, String> getEnvPropertiesByCode(String code) {
        HashMap<String, String> all = new HashMap<>();
        for (int i = 0; i < projectCoreConfRes.size(); i++) {
            all.putAll(projectCoreConfRes.get(i).getEnvPropertiesByCode(code));
        }
        return all;
    }

    public static void writeReadMes() {
        for (int i = 0; i < projectCoreConfRes.size(); i++) {
            projectCoreConfRes.get(i).writeReadMes();
        }
    }

    public static void copyQuasiproduction() {
        for (int i = 0; i < projectCoreConfRes.size(); i++) {
            projectCoreConfRes.get(i).copyQuasiproduction();
        }
    }

    public static boolean getDbInit() {
        for (int i = 0; i < projectCoreConfRes.size(); i++) {
            if (projectCoreConfRes.get(i).getDbInit()) {
                return true;
            }
        }
        return false;
    }

    public static void setDbInit() {
        for (int i = 0; i < projectCoreConfRes.size(); i++) {
            projectCoreConfRes.get(i).setDbInit();
        }
    }

    public static String getZoneMessageFileContext() {
        for (int i = 0; i < projectCoreConfRes.size(); i++) {
            String c = new String();
            c = projectCoreConfRes.get(i).getZoneMessageFileContext();
            if (StrUtil.isNotBlank(c)) {
                return c;
            }
        }
        return "";
    }

    public static Map<String, String> getOtherMessageFileContext() {
        Map<String, String> mapmessage = new HashMap<>();
        for (int i = 0; i < projectCoreConfRes.size(); i++) {
            mapmessage.putAll(projectCoreConfRes.get(i).getotherMessageFileContext());
        }
        return mapmessage;
    }

    public static Map<String, String> getAllProperties() {
        Map<String, String> mapmessage = new HashMap<>();
        for (int i = 0; i < projectCoreConfRes.size(); i++) {
            mapmessage.putAll(projectCoreConfRes.get(i).getEnvAllProperties());
        }
        return mapmessage;
    }
    //------------------------------------public操作


}
