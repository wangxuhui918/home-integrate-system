

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.apollo.config;

import cn.hutool.setting.Setting;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties.ConfigDetails;
import com.guoshiyao.rely.coreextension.run.IThirdConfig;

import java.util.*;

@RuleInjection
public class ApolloExtendsConfig implements IThirdConfig {


    @Override
    public void after() {
        if (BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_APOLLO_APP_ID.getKey()) && BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_APOLLO_APOLLO_META.getKey())) {
            Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
            Set<String> names = config.getPropertyNames();
            for (String key : names) {
                BaseEv.SettingInformation.setting.put(key, config.getProperty(key, ""));
            }
        }
    }

    @Override
    public void before() {
        System.setProperty("apollo.bootstrap.enabled", "false");
    }

    @Override
    public List<Class> writeClasss() {
        return new ArrayList<>();
    }

    @Override
    public Map<String, String> getProperties() {
        return new HashMap<>();
    }

    @Override
    public void callSetting(Setting setting) {

//        {
//            String key = ConfigDetails.HOME_APOLLO_APP_ID.getKey();
//            String key1 = "app.id";
//            if (!setting.containsKey(key)) {
//                setting.put(key, (BaseEv.SettingInformation.idKey));
//            } else {
//                System.setProperty(key1, setting.get(key).toString());
//            }
//        }

//        {
//            String key = ConfigDetails.HOME_APOLLO_APOLLO_ENV.getKey();
//            String key1 = "apollo.env";
//            if (!setting.containsKey(key)) {
//                setting.put(key, (BaseEv.SettingInformation.runEnv));
//            } else {
//                System.setProperty(key1, setting.get(key).toString());
//            }
//        }

    }

}
