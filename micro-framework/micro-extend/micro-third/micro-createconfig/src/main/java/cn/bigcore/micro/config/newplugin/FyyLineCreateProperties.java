
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config.newplugin;

import cn.bigcore.micro.annotation.type.FyyRuleInjection;
import cn.bigcore.micro.utils.FyyConfigProjectUtils;
import cn.bigcore.micro.line.FyyLineCreateInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/3/9
 * @readme
 */
@FyyRuleInjection
public class FyyLineCreateProperties implements FyyLineCreateInterface {


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
        return FyyConfigProjectUtils.getThisEnvPropertiesValue();
    }

    @Override
    public void before() {
        FyyConfigProjectUtils.writeProperties();
    }

    @Override
    public void after() {

    }


}
