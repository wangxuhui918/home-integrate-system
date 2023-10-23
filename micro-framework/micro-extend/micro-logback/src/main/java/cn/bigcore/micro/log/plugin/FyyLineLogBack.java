

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.log.plugin;

import cn.bigcore.micro.line.FyyLineSystemInterface;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.annotation.type.FyyRuleInjection;
import cn.bigcore.micro.base.frame.impl.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.base.exception.type.FyyExceptionError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FyyRuleInjection
public class FyyLineLogBack implements FyyLineSystemInterface {

    @Override
    public void after() {
        if (FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.LOG_ENABLE.getKey())) {
            try {
                FyyLogBackConfigLoader.load("ching.xml");
            } catch (Exception e) {
                throw new FyyExceptionError("日志管理器{}加载失败!请检查文件是否符合规范!!", "ching.xml");
            }
        }
    }

    @Override
    public void before() {

    }

    @Override
    public List<Class> writeClasss() {
        return Arrays.asList(new Class[]{});
    }

    @Override
    public Map<String, String> getProperties() {
        return new HashMap<>();
    }

}
