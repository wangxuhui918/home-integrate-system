

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 
 *
 */

package com.guoshiyao.rely.port;

import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.port.config.SystemServletPort;
import com.guoshiyao.rely.sys.SystemConfigAb;

import java.io.File;
import java.util.*;

/**
 * 读取系统配置文件
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
@RuleAnnotation
public class PortConfigRe implements SystemConfigAb {
    private static Object conf;

    @Override
    public void before() {
    }

    @Override
    public void after() {
    }


    @Override
    public Map<String, String> writeProperties() {
        {
            String key = "system.servlet.port";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, 8080 + "");
            }
        }
        {
            String key = "system.servlet.multipart.location";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, (Line.workHomeDir + File.separator + "temp" + File.separator + "001"));
            }
        }
        {
            String key = "system.servlet.multipart.max-file-size";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, ("-1"));
            }
        }
        {
            String key = "system.servlet.multipart.max-request-size";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, ("-1"));
            }
        }
        {
            String key = "system.servlet.multipart.file-size-threshold";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, ("1"));
            }
        }
        {
            String key = "system.inputparamab.class";
            if (!Line.setting.containsKey(key)) {
                Line.setting.put(key, "com.guoshiyao.rely.outgoing.InputParamRe");
            }
        }
        return new HashMap<>();
    }

    @Override
    public List<Class> writeClasss() {
        LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
        return Arrays.asList(new Class[]{SystemServletPort.class});
    }

}
