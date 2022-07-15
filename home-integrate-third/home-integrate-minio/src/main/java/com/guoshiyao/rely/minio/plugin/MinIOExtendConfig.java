

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.minio.plugin;


import cn.hutool.setting.Setting;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.line.Line;

import com.guoshiyao.rely.minio.utils.MinIOGen;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;

import java.util.*;

@RuleAnnotation
public class MinIOExtendConfig implements ThirdExtendConfigAb {
    public final static String NAME = "MinIO管理器";

    @Override
    public List<Class> writeClasss() {
        //第0 个数据库
        if (Line.setting.containsKey("home.minio.endpoint") && Line.setting.containsKey("home.minio.namespace_re")) {
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
                setting.put(key, (Line.idKey));
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
