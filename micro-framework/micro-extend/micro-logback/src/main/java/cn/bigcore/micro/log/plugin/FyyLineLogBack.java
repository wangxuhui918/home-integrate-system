

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.log.plugin;

import cn.bigcore.micro.FyyProperties;
import cn.bigcore.micro.line.FyyLineSystemInterface;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.annotation.FyyRuleInjection;
import cn.bigcore.micro.base.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.exception.re.ex.FyyExceptionError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FyyRuleInjection
public class FyyLineLogBack implements FyyLineSystemInterface {

    @Override
    public void after() {
        if (FyyInitEnv.setting.getBool(FyyConfigEntryDetailsValues.LOG_ENABLE.getKey())) {
            try {
                FyyLogBackConfigLoader.load(FyyProperties.setting.getStr("fyy.extends.logbackfile"));
            } catch (Exception e) {
                throw new FyyExceptionError("日志管理器{}加载失败!请检查文件是否符合规范!!", FyyProperties.setting.getStr("fyy.extends.logbackfile"));
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
