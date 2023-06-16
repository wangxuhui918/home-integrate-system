

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.minio.plugin;


import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.line.FyyLineThirdExtendInterface;
import cn.hutool.setting.Setting;
import cn.bigcore.micro.config.annotation.FyyRuleInjection;
import cn.bigcore.micro.minio.utils.FyyLineMinIOGen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FyyRuleInjection
public class FyyLineMinIO implements FyyLineThirdExtendInterface {
    public final static String NAME = "MinIO管理器";

    @Override
    public List<Class> writeClasss() {
        //第0 个数据库
        if (FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.MINIO_ENABLE.getKey())) {
            FyyLineMinIOGen.intiConect();
        }
        return new ArrayList<>();
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
