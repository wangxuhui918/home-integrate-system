

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.log.plugin;

import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.sys.SystemConfigAb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RuleAnnotation
public class LogExtendConfigRe implements SystemConfigAb {

    @Override
    public void after() {
        LoggerBaseAb.info("开始翻转日志管理器!!");
        try {
            LogBackConfigLoader.load("ching.xml");
            LoggerBaseAb.info("翻转日志管理器成功!!");
        } catch (Exception e) {
            throw new ExceptionError("日志管理器{}加载失败!请检查文件是否符合规范!!", "ching.xml");
        }
    }

    @Override
    public void before() {

    }

    @Override
    public List<Class> writeClasss() {
        return new ArrayList<>();
    }

    @Override
    public Map<String, String> writeProperties() {
        {
            String key = "home.log.appid";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, Line.idKey);
            }
        }
        {
            String key = "home.log.rootlevel";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, "info");
            }
        }
        {
            String key = "home.log.businlevel";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, "info");
            }
        }
        {
            String key = "home.log.businpackage";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, Line.projectPackage);
            }
        }
        {
            String key = "home.log.dir";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, "target/log/" + Line.idKey);
            }
        }
        return new HashMap<>();
    }

}
