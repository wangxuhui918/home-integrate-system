

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.dubbo.plugin;

import cn.bigcore.micro.dubbo.config.DubboBaseConfiguration;
import cn.bigcore.micro.dubbo.config.DubboConsumerConfig;
import cn.hutool.setting.Setting;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.core.configration.annotation.RuleInjection;
import cn.bigcore.micro.core.configration.home.impl.bean.ConfigDetails;
import cn.bigcore.micro.coreextension.run.IThirdConfig;

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
