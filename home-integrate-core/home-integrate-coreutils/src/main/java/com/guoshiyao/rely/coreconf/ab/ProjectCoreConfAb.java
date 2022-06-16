/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.coreconf.ab;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
public interface ProjectCoreConfAb {
    Map<String, String> getEnvPropertiesByCode(String code);

    //    List<URL> listUrl = ResourceFindUtils.find(StrUtil.format("home-*.ini"));//Line.env.getName()
    Map<String, String> getotherMessageFileContext();

    String getZoneMessageFileContext();

    HashMap<String, String> getEnvAllProperties();

    void writeModelConfig();

    void writeReadMes();

    boolean getDbInit();

    boolean setDbInit();

    void copyQuasiproduction();
}
