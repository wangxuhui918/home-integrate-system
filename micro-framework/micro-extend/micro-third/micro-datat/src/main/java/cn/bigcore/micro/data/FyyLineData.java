

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.data;

import cn.hutool.setting.Setting;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.annotation.type.FyyRuleInjection;
import cn.bigcore.micro.base.frame.impl.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.line.FyyLineThirdExtendInterface;

import java.util.*;

@FyyRuleInjection
public class FyyLineData implements FyyLineThirdExtendInterface {
    public final static String NAME = "DATA_DURID";

    @Override
    public List<Class> writeClasss() {
        if (FyyInitEnv.ProjectInformation.setting.getBool(FyyConfigEntryDetailsValues.DB_ENABLE.getKey())) {
            return Arrays.asList(new Class[]{FyyLineDataSourcesBean.class});
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Map<String, String> getProperties() {
        return new HashMap<>();
    }

    @Override
    public void before() {

    }

    @Override
    public void after() {

    }

    @Override
    public void callSetting(Setting setting) {


    }

}
