
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config.newplugin;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.annotation.type.FyyRuleInjection;
import cn.bigcore.micro.utils.FyyConfigProjectUtils;
import cn.bigcore.micro.line.FyyLineSystemInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/3/9
 * @readme
 */
@FyyRuleInjection
public class FyyLineCreateRunModel implements FyyLineSystemInterface {


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
        if (!FyyInitEnv.SettingInformation.isClassModel) {
            return FyyConfigProjectUtils.getThisEnvPropertiesValue();
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
