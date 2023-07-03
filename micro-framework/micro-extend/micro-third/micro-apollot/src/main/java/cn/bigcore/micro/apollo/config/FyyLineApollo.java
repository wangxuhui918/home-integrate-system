

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.apollo.config;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.base.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.line.FyyLineThirdExtendInterface;
import cn.hutool.setting.Setting;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import cn.bigcore.micro.config.annotation.FyyRuleInjection;

import java.util.*;

@FyyRuleInjection
public class FyyLineApollo implements FyyLineThirdExtendInterface {


    @Override
    public void after() {
        if (FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.APOLLO_BOOTSTRAP_ENABLED.getKey())) {
            Config config = ConfigService.getAppConfig();
            Set<String> names = config.getPropertyNames();
            for (String key : names) {
                FyyInitEnv.SettingInformation.setting.put(key, config.getProperty(key, ""));
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
