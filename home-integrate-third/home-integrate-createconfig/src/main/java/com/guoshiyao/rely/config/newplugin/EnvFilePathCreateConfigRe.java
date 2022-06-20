
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.config.newplugin;

import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.createconfig.CreateConfigAb;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/3/9
 * @readme
 */
@RuleAnnotation
public class EnvFilePathCreateConfigRe implements CreateConfigAb {


    @Override
    public String getName() {
        return "通用配置写入器";
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        return null;
    }


    /**
     * 处理了当前环境所有值,其他插件可选加载
     *
     * @return
     */
    @Override
    public Map<String, String> writeProperties() {
        return ProjectCoreConfUtils.getAllProperties();
    }

    @Override
    public void before() {
        ProjectCoreConfUtils.writeModelConfig();
    }

    @Override
    public void after() {

    }


}
