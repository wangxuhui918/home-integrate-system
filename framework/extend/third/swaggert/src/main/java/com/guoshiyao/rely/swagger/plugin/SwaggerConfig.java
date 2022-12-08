

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.swagger.plugin;

import cn.hutool.setting.Setting;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.home.impl.bean.ConfigDetails;
import com.guoshiyao.rely.coreextension.run.IThirdConfig;
import com.guoshiyao.rely.hand.ControllerParamHandV1;
import com.guoshiyao.rely.hand.ExceptionHandV1;
import com.guoshiyao.rely.hand.ResponseHandV1;
import com.guoshiyao.rely.swagger.bean.RomensWebMvcConfigurationSupport;
import com.guoshiyao.rely.swagger.bean.SwaggerConfigRe;

import java.util.*;

@RuleInjection
public class SwaggerConfig implements IThirdConfig {
    public final static String NAME = "SWAGGER";

    @Override
    public List<Class> writeClasss() {
        if (BaseEv.SettingInformation.setting.getBool(ConfigDetails.SWAGGER_ENABLE.getKey())) {
//        if (AnnotationTools.getRuleClassForAnno(Controller.class, BaseEv.WorkDir.projectPackage) > 0) {
            LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
            return Arrays.asList(new Class[]{SwaggerConfigRe.class,
                    RomensWebMvcConfigurationSupport.class,
                    ControllerParamHandV1.class, ExceptionHandV1.class, ResponseHandV1.class});
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public Map<String, String> getProperties() {
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
//        {
//            String key = ConfigDetails.HOME_SWAGGER_BASEPACKAGE.getKey();
//            if (!setting.containsKey(key)) {
//                setting.put(key, ConfigDetails.HOME_SWAGGER_BASEPACKAGE.getValue());
//            }
//        }

    }

}
