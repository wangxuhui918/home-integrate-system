
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config.newplugin;

import cn.bigcore.micro.core.configration.annotation.RuleInjection;
import cn.bigcore.micro.core.configration.utils.ProjectConfUtils;
import cn.bigcore.micro.coreextension.classm.ICreateConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/3/9
 * @readme
 */
@RuleInjection
public class ClassCreateConfig implements ICreateConfig {


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
        return ProjectConfUtils.getThisEnvPropertiesValue();
    }

    @Override
    public void before() {
        ProjectConfUtils.writeProperties();
    }

    @Override
    public void after() {

    }


}
