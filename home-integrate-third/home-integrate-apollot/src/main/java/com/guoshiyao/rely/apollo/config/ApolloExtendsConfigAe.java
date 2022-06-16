

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.apollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@RuleAnnotation
public class ApolloExtendsConfigAe implements ThirdExtendConfigAb {


    @Override
    public void after() {
        if (!Line.properties.get("home.apollo.app.id").isBlank() && !Line.properties.get("home.apollo.apollo.meta").isBlank()) {
            Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
            Set<String> names = config.getPropertyNames();
            for (String key : names) {
                Line.properties.put(key, new LinePropertiesAb(key, config.getProperty(key, "")));
            }
        }
    }

    @Override
    public void before() {
        System.setProperty("apollo.bootstrap.enabled", "false");
    }

    @Override
    public String getName() {
        return "阿波罗配置加载器";
    }

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
        return map;
    }

    @Override
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
//         HashMap<String, String> params = ProjectCoreConfUtils.getEnvPropertiesByCode("1000001");
//         HashMap<String, PropertiesMap<String, LinePropertiesAb>> map = new HashMap<>();
//        PropertiesMap<String, LinePropertiesAb> lineProperties = LinePropertiesAb.convertLineProperties(params);
//        map.put(getName(), lineProperties);
        return new HashMap<>();
    }

    @Override
    public void callProperties(PropertiesMap<String, LinePropertiesAb> properties) {
        {
            String key = "home.apollo.url";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key, "NA"));
            }
        }
        {
            String key = "home.apollo.username";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key, "apollo"));
            }
        }
        {
            String key = "home.apollo.password";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key, "apollo"));
            }
        }
        {
            String key = "home.apollo.app.id";
            String key1 = "app.id";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key, Line.idKey));
            } else {
                System.setProperty(key1, properties.get(key).toString());
            }
        }
        {
            String key = "home.apollo.apollo.meta";
            String key1 = "apollo.meta";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key, ""));
            } else {
                System.setProperty(key1, properties.get(key).toString());
            }
        }
        {
            String key = "home.apollo.apollo.env";
            String key1 = "apollo.env";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key, Line.env.getName()));
            } else {
                System.setProperty(key1, properties.get(key).toString());
            }
        }

    }

}
