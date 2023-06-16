

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config;

import cn.bigcore.micro.line.FyyLineSystemInterface;
import cn.bigcore.micro.config.annotation.FyyRuleInjection;

import java.util.*;

/**
 * 读取系统配置文件
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
@FyyRuleInjection
public class FyyLineSystemConfigPort implements FyyLineSystemInterface {
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
        return Arrays.asList(new Class[]{FyyLineSystemServletPort.class});
    }

}
