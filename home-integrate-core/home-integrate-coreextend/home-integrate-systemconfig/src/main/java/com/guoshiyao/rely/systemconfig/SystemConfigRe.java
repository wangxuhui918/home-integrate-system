

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.systemconfig;

import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.sys.SystemConfigAb;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取系统配置文件
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
@RuleAnnotation
public class SystemConfigRe implements SystemConfigAb {

    @Override
    public void before() {

    }

    @Override
    public void after() {
    }

    @Override
    public String getName() {
        return "SYSTEMCONFIG";
    }

    @Override
    public Map<String, String> writeProperties() {
        return new HashMap<>();
    }

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        return new LinkedHashMap<>();
    }


}
