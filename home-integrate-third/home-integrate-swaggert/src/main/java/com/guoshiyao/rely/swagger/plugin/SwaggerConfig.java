

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.swagger.plugin;

import cn.hutool.setting.Setting;
import com.guoshiyao.rely.coreannotation.AnnotationTools;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.hand.ControllerParamHandV1;
import com.guoshiyao.rely.hand.ExceptionHandV1;
import com.guoshiyao.rely.hand.ResponseHandV1;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.swagger.bean.RomensWebMvcConfigurationSupport;
import com.guoshiyao.rely.swagger.bean.SwaggerConfigRe;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;
import org.springframework.stereotype.Controller;

import java.util.*;

@RuleAnnotation
public class SwaggerConfig implements ThirdExtendConfigAb {
    public final static String NAME = "SWAGGER";

    @Override
    public List<Class> writeClasss() {
        if (AnnotationTools.getRuleClassForAnno(Controller.class, Line.projectPackage) > 0) {
            LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
//            ControllerParamHand.class,
//            map.put(NAME, Arrays.asList(new Class[]{SwaggerConfigRe.class, RomensWebMvcConfigurationSupport.class,
//                    ControllerParamHand.class, ExceptionHand.class, ResponseHand.class}));
            return Arrays.asList(new Class[]{SwaggerConfigRe.class,
                    RomensWebMvcConfigurationSupport.class,
                    ControllerParamHandV1.class, ExceptionHandV1.class, ResponseHandV1.class});
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public Map<String, String> writeProperties() {
        return new HashMap<>();
    }

    @Override
    public void before() {

    }

    @Override
    public void after() {
    }

    @Override
    public void callSetting(Setting setting) {
        {
            String key = "home.swagger.basepackage";
            if (!setting.containsKey(key)) {
                setting.put(key, (Line.projectPackage + ".api"));
            }
        }
        {
            String key = "home.swagger.name";
            if (!setting.containsKey(key)) {
                setting.put(key, ("Swagger API"));
            }
        }
    }

}
