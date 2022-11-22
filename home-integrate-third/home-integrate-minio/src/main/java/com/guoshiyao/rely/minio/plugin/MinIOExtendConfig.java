

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
import com.guoshiyao.rely.coreextension.IThirdExtendConfig;
import com.guoshiyao.rely.minio.utils.MinIOGen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RuleInjection
public class MinIOExtendConfig implements IThirdExtendConfig {
    public final static String NAME = "MinIO管理器";

    @Override
    public List<Class> writeClasss() {
        //第0 个数据库
        if (BaseEv.SettingInformation.setting.containsKey("home.minio.endpoint") && BaseEv.SettingInformation.setting.containsKey("home.minio.namespace_re")) {
            MinIOGen.intiConect();
        }
        return new ArrayList<>();
    }

    @Override
    public Map<String, String> writeProperties() {
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
            String key = "home.minio.namespace_re";
            if (!setting.containsKey(key)) {
                setting.put(key, (BaseEv.SettingInformation.idKey));
            }
        }
        {
            String key = "home.minio.endpoint";
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
        {
            String key = "home.minio.accesskey";
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
        {
            String key = "home.minio.secretKey";
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
    }

}
