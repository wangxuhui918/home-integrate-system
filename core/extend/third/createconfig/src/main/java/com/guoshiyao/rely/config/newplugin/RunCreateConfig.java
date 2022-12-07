
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.config.newplugin;

import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.utils.ProjectConfUtils;
import com.guoshiyao.rely.coreextension.run.ISystemConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/3/9
 * @readme
 */
@RuleInjection
public class RunCreateConfig implements ISystemConfig {


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
    public Map<String, String> getProperties() {
        if (!BaseEv.SettingInformation.isClassModel) {
            return ProjectConfUtils.getThisEnvPropertiesValue();
        }
        return new HashMap<>();
    }

    @Override
    public void before() {

    }

    @Override
    public void after() {

    }


}
