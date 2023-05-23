/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.core.utils.properties;

import cn.bigcore.micro.plugin.log.ILoggerBaseUtils;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;

import java.io.BufferedReader;
import java.io.Reader;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * properties 文件读取工具
 */
public class PropertiesUtils {

    /**
     * 获取key-value集合
     *
     * @param resourcePath
     * @return
     */
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
            e.printStackTrace();
            ILoggerBaseUtils.warn(resourcePath + " 配置读取失败.............");
        }
        return map;
    }


    /**
     * 根据URI获取key-value集合
     *
     * @param resourcePath
     * @return
     */
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
            e.printStackTrace();
            ILoggerBaseUtils.warn(resourcePath + " 配置读取失败.............");
        }
        return map;
    }
 
}
