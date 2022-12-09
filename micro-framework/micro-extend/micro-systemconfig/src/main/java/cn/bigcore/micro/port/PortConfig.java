

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.port;

import cn.bigcore.micro.coreextension.run.ISystemConfig;
import cn.bigcore.micro.port.config.SystemServletPort;
import cn.bigcore.micro.core.configration.annotation.RuleInjection;

import java.util.*;

/**
 * 读取系统配置文件
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
@RuleInjection
public class PortConfig implements ISystemConfig {
    private static Object conf;

    @Override
    public void before() {
    }

    @Override
    public void after() {
    }


    @Override
    public Map<String, String> getProperties() {

//
//
//        {
//            String key = ConfigDetails.SYSTEM_INPUTPARAMAB_CLASS.getKey();
//            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
//                BaseEv.SettingInformation.setting.put(key, ConfigDetails.SYSTEM_INPUTPARAMAB_CLASS.getValue());
//            }
//        }
        return new HashMap<>();
    }

    @Override
    public List<Class> writeClasss() {
        LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
        return Arrays.asList(new Class[]{SystemServletPort.class});
    }

}
