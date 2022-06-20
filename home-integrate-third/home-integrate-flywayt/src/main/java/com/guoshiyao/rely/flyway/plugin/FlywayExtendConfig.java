

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.flyway.plugin;

import cn.hutool.setting.Setting;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.flyway.bean.FlywayBean;
import com.guoshiyao.rely.line.Line;

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
    public List<Class> writeClasss() {
        try {
            ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
            Resource[] source = resourceLoader.getResources(filep);
            if (source.length == 0) {
                return new ArrayList<>();
            }
            if (!Line.setting.containsKey("home.flywaydb.url")) {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
        return Arrays.asList(new Class[]{FlywayBean.class});
    }

    @Override
    public Map<String, String> writeProperties() {
        return new HashMap<>();
    }

    @Override
    public void callSetting(Setting setting) {
        {
            String key = "home.flywaydb.url";
            if (!setting.containsKey(key)) {
                if (Line.setting.get("home.db.url") != null)
                    setting.put(key, (Line.setting.get("home.db.url")));
            }
        }
        {
            String key = "home.flywaydb.username";
            if (!setting.containsKey(key)) {
                if (Line.setting.get("home.db.username") != null)
                    setting.put(key, (Line.setting.get("home.db.username")));
            }
        }
        {
            String key = "home.flywaydb.password";
            if (!setting.containsKey(key)) {
                if (Line.setting.get("home.db.password") != null)
                    setting.put(key, (Line.setting.get("home.db.password")));
            }
        }
        {
            String key = "home.flywaydb.table";
            if (!setting.containsKey(key)) {
                if (Line.setting.get("home.db.table") != null)
                    setting.put(key, ("flyway_schema_history"));
            }
        }
    }

}
