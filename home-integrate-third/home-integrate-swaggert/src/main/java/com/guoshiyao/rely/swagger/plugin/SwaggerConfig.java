

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.swagger.plugin;

import com.guoshiyao.rely.coreannotation.AnnotationTools;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.hand.ControllerParamHandV1;
import com.guoshiyao.rely.hand.ExceptionHandV1;
import com.guoshiyao.rely.hand.ResponseHandV1;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.swagger.bean.RomensWebMvcConfigurationSupport;
import com.guoshiyao.rely.swagger.bean.SwaggerConfigRe;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RuleAnnotation
public class SwaggerConfig implements ThirdExtendConfigAb {
    public final static String NAME = "SWAGGER";

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        if (AnnotationTools.getRuleClassForAnno(Controller.class, Line.projectPackage) > 0) {
            LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
//            ControllerParamHand.class,
//            map.put(NAME, Arrays.asList(new Class[]{SwaggerConfigRe.class, RomensWebMvcConfigurationSupport.class,
//                    ControllerParamHand.class, ExceptionHand.class, ResponseHand.class}));
            map.put(NAME, Arrays.asList(new Class[]{SwaggerConfigRe.class, RomensWebMvcConfigurationSupport.class,
                    ControllerParamHandV1.class, ExceptionHandV1.class, ResponseHandV1.class}));
            return map;
        } else {
            return new LinkedHashMap<>();
        }

    }

    @Override
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
//        HashMap<String, String> params = ProjectCoreConfUtils.getEnvPropertiesByCode("1000013");
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
            String key = "home.swagger.basepackage";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key, Line.projectPackage + ".api"));
            }
        }
        {
            String key = "home.swagger.name";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key, "Swagger API"));
            }
        }
    }

}
