

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.flyway.plugin;

import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.flyway.bean.FlywayBean;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.util.*;

@RuleAnnotation
public class FlywayExtendConfig implements ThirdExtendConfigAb {
    public final static String filep = "db" + File.separator + "migration" + File.separator + "**" + File.separator
            + "**.sql";

    @Override
    public void after() {
    }

    @Override
    public void before() {

    }

    @Override
    public String getName() {
        return "数据库版本管理器";
    }

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        try {
            ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
            Resource[] source = resourceLoader.getResources(filep);
            if (source.length == 0) {
                return new LinkedHashMap<>();
            }
            if (Line.properties.get("home.flywaydb.url").isBlank()) {
                return new LinkedHashMap<>();
            }
        } catch (Exception e) {
            return new LinkedHashMap<>();
        }
        LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
        map.put(getName(), Arrays.asList(new Class[]{FlywayBean.class}));
        return map;
    }

    @Override
    public Map<String, String> writeProperties() {
        return new HashMap<>();
    }

    @Override
    public void callProperties(PropertiesMap<String, LinePropertiesAb> properties) {
        {
            String key = "home.flywaydb.url";
            if (properties.get(key).isBlank()) {
                if (Line.properties.get("home.db.url") != null)
                    properties.put(key, new LinePropertiesAb(key, Line.properties.get("home.db.url").getString()));
            }
        }
        {
            String key = "home.flywaydb.username";
            if (properties.get(key).isBlank()) {
                if (Line.properties.get("home.db.username") != null)
                    properties.put(key, new LinePropertiesAb(key, Line.properties.get("home.db.username").getString()));
            }
        }
        {
            String key = "home.flywaydb.password";
            if (properties.get(key).isBlank()) {
                if (Line.properties.get("home.db.password") != null)
                    properties.put(key, new LinePropertiesAb(key, Line.properties.get("home.db.password").getString()));
            }
        }
        {
            String key = "home.flywaydb.table";
            if (properties.get(key).isBlank()) {
                if (Line.properties.get("home.db.table") != null)
                    properties.put(key, new LinePropertiesAb(key, "flyway_schema_history"));
            }
        }
    }

}
