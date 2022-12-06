

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
import com.guoshiyao.rely.core.configration.home.impl.bean.ConfigDetails;
import com.guoshiyao.rely.coreextension.run.IThirdConfig;

import java.util.*;

@RuleInjection
public class ApolloExtendsConfig implements IThirdConfig {


    @Override
    public void after() {
        if (BaseEv.SettingInformation.setting.getBool(ConfigDetails.APOLLO_BOOTSTRAP_ENABLED.getKey())) {
            Config config = ConfigService.getAppConfig();
            Set<String> names = config.getPropertyNames();
            for (String key : names) {
                BaseEv.SettingInformation.setting.put(key, config.getProperty(key, ""));
            }
        } else {
//            BaseEv.SettingInformation.setting.putByGroup(ConfigDetails.APOLLO_BOOTSTRAP_ENABLED.getKey(), BaseEv.SettingInformation.runEnv, "false");
//            System.setProperty(ConfigDetails.APOLLO_BOOTSTRAP_ENABLED.getKey(), "false");
//            System.setProperty(ConfigDetails.APOLLO_BOOTSTRAP_EAGERLOAD_ENABLED.getKey(), "false");
        }
    }

    @Override
    public void before() {

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

    }

}
