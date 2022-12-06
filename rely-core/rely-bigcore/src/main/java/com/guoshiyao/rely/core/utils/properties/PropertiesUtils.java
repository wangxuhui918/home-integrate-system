/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.utils.properties;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;

import java.io.BufferedReader;
import java.io.Reader;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtils {

    public static HashMap<String, String> getProperties(String resourcePath) {
        HashMap<String, String> map = new HashMap<>();
        BufferedReader in = null;
        Properties p = new Properties();
        try {
            in = ResourceUtil.getUtf8Reader(resourcePath);
            p.load(in);
            Set<Entry<Object, Object>> entrySet = p.entrySet();
            for (Entry<Object, Object> entry : entrySet) {
                map.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            ILoggerBaseUtils.warn(resourcePath + " 配置读取失败.............");
        }
        return map;
    }


    public static HashMap<String, String> getProperties(URI resourcePath) {
        HashMap<String, String> map = new HashMap<>();
        Reader in = null;
        Properties p = new Properties();
        try {
            in = FileUtil.getUtf8Reader(resourcePath.getPath());
            p.load(in);
            Set<Entry<Object, Object>> entrySet = p.entrySet();
            for (Entry<Object, Object> entry : entrySet) {
                map.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            ILoggerBaseUtils.warn(resourcePath + " 配置读取失败.............");
        }
        return map;
    }

    /**
     * 该方法不建议使用
     * mainClass 参数已经无效,
     *
     * @param mainClass
     * @param resourcePath
     * @return
     */
    @Deprecated
    public static HashMap<String, String> getProperties(String mainClass, String resourcePath) {
        return getProperties(resourcePath);
    }

//    public static void main(String[] args) {
//        String line = "##sddfsff3=1222=343";
//        System.out.println(StrUtil.subAfter(StrUtil.subBefore(line, "=", false), "#", true));
//    }

    public static List<String> beyongProperties(String oldPropertiesPath, String newString) {
        HashMap<String, String> oldValues = getProperties(oldPropertiesPath);
        HashMap<String, String> oldValues1 = getProperties(oldPropertiesPath);
        String readString = ResourceUtil.readUtf8Str(oldPropertiesPath);

        List<String> newContexts = StrUtil.split(newString, "\n");

        int s = 0;
        for (int i = 0; i < newContexts.size(); i++) {
            String line = newContexts.get(i);
            if (StrUtil.isNotBlank(line) && StrUtil.indexOf(line, '=') > 0) {
                line = line.trim();
                String dep = line.startsWith("#") ? "#" : "";//是否注释
                String key = line.startsWith("#") ? StrUtil.subAfter(StrUtil.subBefore(line, "=", false), "#", true).trim() : StrUtil.subBefore(line, "=", false).trim();
                String d = "=";
                if (oldValues.containsKey(key)) {
                    newContexts.set(i, key + d + oldValues.get(key));
                    s++;
                    oldValues.remove(key);
                } else if (!oldValues.containsKey(key) && readString.contains(key + "=")) {
                    s++;
                }
            }
        }
        if (s == 0) {
            return null;
        }
        if (oldValues1 == null || oldValues1.size() == 0) {
            return newContexts;
        }
        if (oldValues.size() > 0) {
            for (String key : oldValues.keySet()) {
                newContexts.add(key + "=" + oldValues.get(key));
            }
        }
        return newContexts;
    }
}
