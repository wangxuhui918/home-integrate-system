

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.dubbo.plugin;

import cn.hutool.setting.Setting;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.home.impl.bean.ConfigDetails;
import com.guoshiyao.rely.core.utils.AnnotationTools;
import com.guoshiyao.rely.coreextension.run.IThirdConfig;
import com.guoshiyao.rely.dubbo.config.DubboBaseConfiguration;
import com.guoshiyao.rely.dubbo.config.DubboConsumerConfig;

import java.util.*;

@RuleInjection
@EnableDubbo
public class DubboExtendConfig implements IThirdConfig {
    public final static String NAME = "DUBBO";

    @Override
    public List<Class> writeClasss() {
//        if (AnnotationTools.getRuleClassForAnno(Service.class, BaseEv.WorkDir.projectPackage) > 0) {
        if (BaseEv.SettingInformation.setting.getBool(ConfigDetails.DUBBO_ENABLED.getKey())) {
            return Arrays.asList(new Class[]{DubboBaseConfiguration.class, DubboConsumerConfig.class});
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
//            String key = ConfigDetails.HOME_DUBBO_PROTOCOLCONFIG_THREADPOOL.getKey();
//            if (!setting.containsKey(key)) {
//                setting.put(key, ConfigDetails.HOME_DUBBO_PROTOCOLCONFIG_THREADPOOL.getValue());
//            }
//        }
    }

}
