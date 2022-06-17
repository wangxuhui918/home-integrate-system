

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.dubbo.plugin;

import cn.hutool.setting.Setting;
import com.alibaba.dubbo.config.annotation.Service;
import com.guoshiyao.rely.coreannotation.AnnotationTools;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.dubbo.config.DubboBaseConfiguration;
import com.guoshiyao.rely.dubbo.config.DubboConsumerConfig;
import com.guoshiyao.rely.line.Line;

import com.guoshiyao.rely.third.ThirdExtendConfigAb;

import java.util.*;

@RuleAnnotation
public class DubboExtendConfig implements ThirdExtendConfigAb {
    public final static String NAME = "DUBBO";

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        if (AnnotationTools.getRuleClassForAnno(Service.class, Line.projectPackage) > 0) {
            LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
            map.put(NAME, Arrays.asList(new Class[]{DubboBaseConfiguration.class, DubboConsumerConfig.class}));
            return map;
        } else {
            return new LinkedHashMap<>();
        }

    }

    @Override
    public Map<String, String> writeProperties() {
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
    public void callProperties(Setting properties) {
        {
            String key = "home.dubbo.reference.package";
            if (!properties.containsKey(key)) {
                properties.put(key, (Line.projectPackage + ".dubboservice"));
            }
        }
        {
            String key = "home.dubbo.reference.timeout";
            if (!properties.containsKey(key)) {
                properties.put(key, ("120000"));
            }
        }
        {
            String key = "home.dubbo.reference.url";
            if (!properties.containsKey(key)) {
                properties.put(key, ("N/A"));
            }
        }
        {
            String key = "home.dubbo.reference.agreement";
            if (!properties.containsKey(key)) {
                properties.put(key, ("dubbo"));
            }
        }
        {
            String key = "home.dubbo.reference.host";
            if (!properties.containsKey(key)) {
                properties.put(key, ("127.0.0.1"));
            }
        }
        {
            String key = "home.dubbo.reference.port";
            if (!properties.containsKey(key)) {
                properties.put(key, ("2181"));
            }
        }
        {
            String key = "home.dubbo.protocolconfig.threads";
            if (!properties.containsKey(key)) {
                properties.put(key, ("300"));
            }
        }
        {
            String key = "home.dubbo.protocolconfig.threadpool";
            if (!properties.containsKey(key)) {
                properties.put(key, ("fixed"));
            }
        }
    }

}
