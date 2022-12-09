

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.systemconfig;

import cn.bigcore.micro.coreextension.run.ISystemConfig;
import cn.bigcore.micro.core.configration.annotation.RuleInjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取系统配置文件
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
@RuleInjection
public class SystemConfig implements ISystemConfig {

    @Override
    public void before() {

    }

    @Override
    public void after() {
    }

    @Override
    public Map<String, String> getProperties() {
        return new HashMap<>();
    }

    @Override
    public List<Class> writeClasss() {
        return new ArrayList<>();
    }


}