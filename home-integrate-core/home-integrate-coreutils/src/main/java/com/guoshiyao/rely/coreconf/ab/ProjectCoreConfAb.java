/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 
 *
 */

package com.guoshiyao.rely.coreconf.ab;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 郭诗瑶
 * @date 2022/5/25
 * @readme
 */
public interface ProjectCoreConfAb {
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
