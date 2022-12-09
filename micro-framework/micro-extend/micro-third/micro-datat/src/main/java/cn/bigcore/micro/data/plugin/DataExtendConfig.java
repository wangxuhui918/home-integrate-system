

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.data.plugin;

import cn.bigcore.micro.data.DataSourcesConfig;
import cn.hutool.setting.Setting;
import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.core.configration.annotation.RuleInjection;
import cn.bigcore.micro.core.configration.home.impl.bean.ConfigDetails;
import cn.bigcore.micro.coreextension.run.IThirdConfig;

import java.util.*;

@RuleInjection
public class DataExtendConfig implements IThirdConfig {
    public final static String NAME = "DATA_DURID";

    @Override
    public List<Class> writeClasss() {
        if (BaseEv.SettingInformation.setting.getBool(ConfigDetails.DB_ENABLE.getKey())) {
            return Arrays.asList(new Class[]{DataSourcesConfig.class});
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


    }

}
