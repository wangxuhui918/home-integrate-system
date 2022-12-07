

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.data.plugin;

import cn.hutool.setting.Setting;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.home.impl.bean.ConfigDetails;
import com.guoshiyao.rely.coreextension.run.IThirdConfig;
import com.guoshiyao.rely.data.DataSourcesConfig;

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
