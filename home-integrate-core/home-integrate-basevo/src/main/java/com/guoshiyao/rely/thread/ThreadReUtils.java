

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
 * @date 2022/01/08
 * @auther 汪旭辉
 * @readme 这里建议压入一下线程信息, 比如用户, 国际化编码
 */
public class ThreadReUtils {
    private static final ThreadLocal<JSONObject> allThreadLocal = new ThreadLocal<JSONObject>() {
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
}
