

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.map;

import cn.hutool.core.map.MapUtil;

import java.util.HashMap;

@Deprecated
public class HashMapUtils {

    public static void removeKey(HashMap<String, String> hardProperties, HashMap<String, String> params) {
        try {
            String[] keys = new String[hardProperties.keySet().size()];
            hardProperties.keySet().toArray(keys);
            MapUtil.removeAny(params, keys);
        } catch (Exception e) {
        }
    }
}
