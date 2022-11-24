

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.minio.plugin;


import cn.hutool.setting.Setting;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties.ConfigDetails;
import com.guoshiyao.rely.coreextension.run.IThirdConfig;
import com.guoshiyao.rely.minio.utils.MinIOGen;

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
        if (BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_MINIO_ENDPOINT.getKey()) && BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_MINIO_NAMESPACE_RE.getKey())) {
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
        {
            String key = ConfigDetails.HOME_MINIO_NAMESPACE_RE.getKey();
            if (!setting.containsKey(key)) {
                setting.put(key, (BaseEv.SettingInformation.idKey));
            }
        }
        {
            String key = ConfigDetails.HOME_MINIO_ENDPOINT.getKey();
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
        {
            String key = ConfigDetails.HOME_MINIO_ACCESSKEY.getKey();
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
        {
            String key = ConfigDetails.HOME_MINIO_SECRETKEY.getKey();
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
    }

}
