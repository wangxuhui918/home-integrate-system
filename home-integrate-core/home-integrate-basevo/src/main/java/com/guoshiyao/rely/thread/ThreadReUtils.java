

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.thread;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @auther 汪旭辉
 * @readme 这里建议压入一下线程信息, 比如用户, 国际化编码,不建议较大数据放入
 */
public class ThreadReUtils {
    protected static final ThreadLocal<JSONObject> allThreadLocal = new ThreadLocal<JSONObject>() {
        @Override
        protected JSONObject initialValue() {
            return JSONUtil.createObj();
        }
    };

    public static JSONObject getObj() {
        return ThreadReUtils.allThreadLocal.get();
    }

    public static JSONObject setObj(JSONObject obj) {
        return ThreadReUtils.allThreadLocal.get();
    }

    public static void remove() {
        ThreadReUtils.allThreadLocal.remove();
    }

    /**
     * 建议集成当前类,并实现对应的<T> getV()方法,setV(T value)方法
     *
     * @param key   V
     * @param value T
     */
    public static void putParam(String key, Object value) {
        ThreadReUtils.allThreadLocal.get().append(key, value);
    }


    public static void putStrParam(String key, String value) {
        ThreadReUtils.allThreadLocal.get().append(key, value);
    }

    /**
     * 通用获取线程变量
     *
     * @param path
     * @param classes
     * @param <T>
     * @return
     */
    public static <T> T getParamByPath(String path, Class<T> classes) {
        return JSONUtil.toBean(ThreadReUtils.allThreadLocal.get().getJSONObject(path), classes);
    }

    public static String getStrParamByPath(String path) {
        return ThreadReUtils.allThreadLocal.get().get(path, String.class);
    }
}
