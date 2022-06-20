

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.apollo.config;

import cn.hutool.setting.Setting;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.line.Line;

import com.guoshiyao.rely.third.ThirdExtendConfigAb;

import java.util.*;

@RuleAnnotation
public class ApolloExtendsConfigAe implements ThirdExtendConfigAb {


    @Override
    public void after() {
        if (Line.setting.containsKey("home.apollo.app.id") && Line.setting.containsKey("home.apollo.apollo.meta")) {
            Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
            Set<String> names = config.getPropertyNames();
            for (String key : names) {
                Line.setting.put(key, config.getProperty(key, ""));
            }
        }
    }

    @Override
    public void before() {
        System.setProperty("apollo.bootstrap.enabled", "false");
    }

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
        return map;
    }

    @Override
    public Map<String, String> writeProperties() {
        return new HashMap<>();
    }

    @Override
    public void callSetting(Setting setting) {
        {
            String key = "home.apollo.url";
            if (!setting.containsKey(key)) {
                setting.put(key, ("NA"));
            }
        }
        {
            String key = "home.apollo.username";
            if (!setting.containsKey(key)) {
                setting.put(key, ("apollo"));
            }
        }
        {
            String key = "home.apollo.password";
            if (!setting.containsKey(key)) {
                setting.put(key, ("apollo"));
            }
        }
        {
            String key = "home.apollo.app.id";
            String key1 = "app.id";
            if (!setting.containsKey(key)) {
                setting.put(key, (Line.idKey));
            } else {
                System.setProperty(key1, setting.get(key).toString());
            }
        }
        {
            String key = "home.apollo.apollo.meta";
            String key1 = "apollo.meta";
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            } else {
                System.setProperty(key1, setting.get(key).toString());
            }
        }
        {
            String key = "home.apollo.apollo.env";
            String key1 = "apollo.env";
            if (!setting.containsKey(key)) {
                setting.put(key, (Line.env.getName()));
            } else {
                System.setProperty(key1, setting.get(key).toString());
            }
        }

    }

}
