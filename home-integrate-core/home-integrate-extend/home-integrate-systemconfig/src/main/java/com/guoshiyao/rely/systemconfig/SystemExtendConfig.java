

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.systemconfig;

import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.bean.Bean;
import com.guoshiyao.rely.coreextension.ISystemConfig;

import java.util.*;

/**
 * 读取系统配置文件
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
@RuleInjection
public class SystemExtendConfig implements ISystemConfig {

    @Override
    public void before() {

    }

    @Override
    public void after() {
    }


    @Override
    public Map<String, String> writeProperties() {
        return new HashMap<>();
    }

    @Override
    public List<Class> writeClasss() {
        LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
        return Arrays
                .asList(new Class[]{StartPingService.class, Bean.class});
    }

}
