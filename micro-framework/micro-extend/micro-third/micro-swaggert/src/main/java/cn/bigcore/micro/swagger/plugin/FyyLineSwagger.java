

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.swagger.plugin;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.line.FyyLineThirdExtendInterface;
import cn.bigcore.micro.swagger.bean.FyyWebMvcConfigurationSupport;
import cn.bigcore.micro.swagger.bean.FyySwaggerConfigBean;
import cn.hutool.setting.Setting;
import cn.bigcore.micro.config.annotation.FyyRuleInjection;
import cn.bigcore.micro.hand.FyyControllerParamHandV1;
import cn.bigcore.micro.hand.FyyControllerExceptionHandV1;
import cn.bigcore.micro.hand.FyyControllerResponseHandV1;

import java.util.*;

@FyyRuleInjection
public class FyyLineSwagger implements FyyLineThirdExtendInterface {
    public final static String NAME = "SWAGGER";

    @Override
    public List<Class> writeClasss() {
        if (FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.SWAGGER_ENABLE.getKey())) {
//        if (AnnotationTools.getRuleClassForAnno(Controller.class, BaseEv.WorkDir.projectPackage) > 0) {
            LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
            return Arrays.asList(new Class[]{FyySwaggerConfigBean.class,
                    FyyWebMvcConfigurationSupport.class,
                    FyyControllerParamHandV1.class, FyyControllerExceptionHandV1.class, FyyControllerResponseHandV1.class});
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
