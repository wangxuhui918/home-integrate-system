
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.line.propertiesmap;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.setting.Setting;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;

import java.util.HashMap;

public class PropertiesMap<K, V extends LinePropertiesAb> extends HashMap<K, V> {

    /**
     * @author 汪旭辉
     * @date 2021年12月28日
     * @readme mark
     */
    private static final long serialVersionUID = -8145729368391873282L;


    @Override
    public V get(Object key) {
        if (!super.containsKey(key)) {
            try {
                this.put((K) key, (V) new LinePropertiesAb(key.toString(), ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.get(key);
    }


}
