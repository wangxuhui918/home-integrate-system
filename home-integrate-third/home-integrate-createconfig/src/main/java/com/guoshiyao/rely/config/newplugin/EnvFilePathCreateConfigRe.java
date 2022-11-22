
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.config.newplugin;

import com.guoshiyao.rely.annotation.RuleInjection;
import com.guoshiyao.rely.core.utils.conf.ProjectConfUtils;
import com.guoshiyao.rely.coreextension.ICreateConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/3/9
 * @readme
 */
@RuleInjection
public class EnvFilePathCreateConfigRe implements ICreateConfig {


    @Override
    public List<Class> writeClasss() {
        return new ArrayList<>();
    }


    /**
     * 处理了当前环境所有值,其他插件可选加载
     *
     * @return
     */
    @Override
    public Map<String, String> writeProperties() {
        return ProjectConfUtils.getAllProperties();
    }

    @Override
    public void before() {
        ProjectConfUtils.writeModelConfig();
    }

    @Override
    public void after() {

    }


}
