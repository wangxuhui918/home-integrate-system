

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
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.utils.AnnotationTools;
import com.guoshiyao.rely.coreextension.IThirdExtendConfig;
import com.guoshiyao.rely.dubbo.config.DubboBaseConfiguration;
import com.guoshiyao.rely.dubbo.config.DubboConsumerConfig;

import java.util.*;

@RuleInjection
public class DubboExtendConfig implements IThirdExtendConfig {
    public final static String NAME = "DUBBO";

    @Override
    public List<Class> writeClasss() {
        if (AnnotationTools.getRuleClassForAnno(Service.class, BaseEv.WorkDir.projectPackage) > 0) {
            return Arrays.asList(new Class[]{DubboBaseConfiguration.class, DubboConsumerConfig.class});
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public Map<String, String> writeProperties() {
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
        {
            String key = "home.dubbo.reference.package";
            if (!setting.containsKey(key)) {
                setting.put(key, (BaseEv.WorkDir.projectPackage + ".dubboservice"));
            }
        }
        {
            String key = "home.dubbo.reference.timeout";
            if (!setting.containsKey(key)) {
                setting.put(key, ("120000"));
            }
        }
        {
            String key = "home.dubbo.reference.url";
            if (!setting.containsKey(key)) {
                setting.put(key, ("N/A"));
            }
        }
        {
            String key = "home.dubbo.reference.agreement";
            if (!setting.containsKey(key)) {
                setting.put(key, ("dubbo"));
            }
        }
        {
            String key = "home.dubbo.reference.host";
            if (!setting.containsKey(key)) {
                setting.put(key, ("SUCCESS"));
            }
        }
        {
            String key = "home.dubbo.reference.port";
            if (!setting.containsKey(key)) {
                setting.put(key, ("2181"));
            }
        }
        {
            String key = "home.dubbo.protocolconfig.threads";
            if (!setting.containsKey(key)) {
                setting.put(key, ("300"));
            }
        }
        {
            String key = "home.dubbo.protocolconfig.threadpool";
            if (!setting.containsKey(key)) {
                setting.put(key, ("fixed"));
            }
        }
    }

}
