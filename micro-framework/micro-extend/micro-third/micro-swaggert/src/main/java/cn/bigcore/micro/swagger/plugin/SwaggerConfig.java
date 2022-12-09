

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.swagger.plugin;

import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.core.configration.home.impl.bean.ConfigDetails;
import cn.bigcore.micro.coreextension.run.IThirdConfig;
import cn.bigcore.micro.swagger.bean.RomensWebMvcConfigurationSupport;
import cn.bigcore.micro.swagger.bean.SwaggerConfigRe;
import cn.hutool.setting.Setting;
import cn.bigcore.micro.core.configration.annotation.RuleInjection;
import cn.bigcore.micro.hand.ControllerParamHandV1;
import cn.bigcore.micro.hand.ExceptionHandV1;
import cn.bigcore.micro.hand.ResponseHandV1;

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
