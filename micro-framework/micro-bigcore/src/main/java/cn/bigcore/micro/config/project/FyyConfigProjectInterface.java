/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config.project;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme 项目配置接口
 */
public interface FyyConfigProjectInterface {


    HashMap<String, String> reloadPropertiesValue();

    Map<String, String> getAllMessageXmlContexts();

    String getDefaultMessageXmlContexts();

    HashMap<String, String> getThisEnvPropertiesValue();

    void writeProperties();

    void writeFileStructures();

    boolean installed();

    boolean install();

}
