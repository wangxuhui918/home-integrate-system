package com.guoshiyao.rely.plugin.thread;


/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.plugin.thread.bean.KeyBase;

/**
 * @auther 汪旭辉
 * @readme 这里建议压入一下线程信息, 比如用户, 国际化编码,不建议较大数据放入
 */
public class ThreadReUtils {
    protected static final ThreadLocal<ThreadVar> allThreadLocal = new ThreadLocal<ThreadVar>() {
        @Override
        protected ThreadVar initialValue() {
            return new ThreadVar();
        }
    };

    public static ThreadVar getObj() {
        return allThreadLocal.get();
    }

    public static ThreadVar setObj(JSONObject obj) {
        return allThreadLocal.get();
    }

    public static void remove() {
        allThreadLocal.remove();
    }

    /**
     * 建议集成当前类,并实现对应的<T> getV()方法,setV(T value)方法
     *
     * @param key   V
     * @param value T
     */
    public static void putParam(String key, Object value) {
        allThreadLocal.get().append(key, value);
    }


    public static void putStrParam(String key, String value) {
        allThreadLocal.get().set(key, value);
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
        return JSONUtil.toBean(allThreadLocal.get().getJSONObject(path), classes);
    }

    public static String getStrParamByPath(String path) {
        return allThreadLocal.get().get(path, String.class);
    }
    //8//////

    /**
     * 获取I18n线程变量
     *
     * @return
     */
    public static String getI18n() {
        try {
            return allThreadLocal.get().get(KeyBase.I18N.getName(), String.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取用户线程变量
     *
     * @return
     */
    public static <T> T getUserRe(Class<T> user) {
        try {
            return JSONUtil.toBean(allThreadLocal.get().getJSONObject(KeyBase.USERRE.getName()), user);
        } catch (Exception e) {
            return null;
        }
    }

}
