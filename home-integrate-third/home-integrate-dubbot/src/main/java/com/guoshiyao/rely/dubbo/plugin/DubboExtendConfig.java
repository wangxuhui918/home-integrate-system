

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.dubbo.plugin;

import com.alibaba.dubbo.config.annotation.Service;
import com.guoshiyao.rely.coreannotation.AnnotationTools;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.dubbo.config.DubboBaseConfiguration;
import com.guoshiyao.rely.dubbo.config.DubboConsumerConfig;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
//         HashMap<String, String> params = ProjectCoreConfUtils.getEnvPropertiesByCode("1000004");
//          HashMap<String, PropertiesMap<String, LinePropertiesAb>> map = new HashMap<>();
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
            String key = "home.dubbo.reference.package";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,Line.projectPackage + ".dubboservice"));
            }
        }
        {
            String key = "home.dubbo.reference.timeout";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,"120000"));
            }
        }
        {
            String key = "home.dubbo.reference.url";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,"N/A"));
            }
        }
        {
            String key = "home.dubbo.reference.agreement";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,"dubbo"));
            }
        }
        {
            String key = "home.dubbo.reference.host";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,"127.0.0.1"));
            }
        }
        {
            String key = "home.dubbo.reference.port";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,"2181"));
            }
        }
        {
            String key = "home.dubbo.protocolconfig.threads";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,"300"));
            }
        }
        {
            String key = "home.dubbo.protocolconfig.threadpool";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,"fixed"));
            }
        }
    }

}
