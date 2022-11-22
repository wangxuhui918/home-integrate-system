/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.project;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
public interface IProjectConf {

    Map<String, String> getEnvPropertiesByCode(String code);

    Map<String, String> getotherMessageFileContext();

    String getZoneMessageFileContext();

    HashMap<String, String> getEnvAllProperties();

    void writeModelConfig();

    void writeReadMes();

    boolean getDbInit();

    boolean setDbInit();

    void copyQuasiproduction();
}
