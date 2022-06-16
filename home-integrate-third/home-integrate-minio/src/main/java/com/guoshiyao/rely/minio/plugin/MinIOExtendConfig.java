

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.minio.plugin;


import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.minio.utils.MinIOGen;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RuleAnnotation
public class MinIOExtendConfig implements ThirdExtendConfigAb {
    public final static String NAME = "MinIO管理器";

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        //第0 个数据库
        if (!Line.properties.get("home.minio.endpoint").isBlank() && !Line.properties.get("home.minio.namespace_re").isBlank()) {
            MinIOGen.intiConect();
            LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
            return map;
        } else {
            return new LinkedHashMap<>();

        }
    }

    @Override
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
//        HashMap<String, String> params = ProjectCoreConfUtils.getEnvPropertiesByCode("1000016");
//        HashMap<String, PropertiesMap<String, LinePropertiesAb>> map = new HashMap<>();
//        map.put(NAME, LinePropertiesAb.convertLineProperties(params));
        return new HashMap<>();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void before() {

    }

    @Override
    public void after() {
    }

    @Override
    public void callProperties(PropertiesMap<String, LinePropertiesAb> properties) {
        {
            String key = "home.minio.namespace_re";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,Line.idKey));
            }
        }
        {
            String key = "home.minio.endpoint";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,""));
            }
        }
        {
            String key = "home.minio.accesskey";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,""));
            }
        }
        {
            String key = "home.minio.secretKey";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,""));
            }
        }
    }

}
