

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.minio.plugin;


import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.core.configration.home.impl.bean.ConfigDetails;
import cn.bigcore.micro.coreextension.run.IThirdConfig;
import cn.hutool.setting.Setting;
import cn.bigcore.micro.core.configration.annotation.RuleInjection;
import cn.bigcore.micro.minio.utils.MinIOGen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RuleInjection
public class MinIOExtendConfig implements IThirdConfig {
    public final static String NAME = "MinIO管理器";

    @Override
    public List<Class> writeClasss() {
        //第0 个数据库
        if (BaseEv.SettingInformation.setting.getBool(ConfigDetails.MINIO_ENABLE.getKey())) {
            MinIOGen.intiConect();
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
