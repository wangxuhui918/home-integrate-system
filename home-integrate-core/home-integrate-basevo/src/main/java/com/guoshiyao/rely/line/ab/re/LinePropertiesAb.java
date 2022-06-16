

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.line.ab.re;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.Setting;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 值转换为其他常见类型格式,可以扩展
 *
 * @author 汪旭辉
 * @date 2021年9月28日
 * @readme
 */
public class LinePropertiesAb extends Setting {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public LinePropertiesAb(String key, String value) {
        this.setKey(key);
        super.set(key, value);
    }

    public static PropertiesMap<String, LinePropertiesAb> convertLineProperties(Map<String, String> properties) {
        PropertiesMap<String, LinePropertiesAb> returnp = new PropertiesMap<>();
        for (String key : properties.keySet()) {
            returnp.put(key, new LinePropertiesAb(key, properties.get(key)));
        }
        return returnp;
    }

    @Deprecated
    public boolean isBlank() {
        return StrUtil.isBlank(super.getStr(key));
    }

    @Deprecated
    public Boolean getBoolean() {
        return super.getBool(key);
    }

    @Deprecated
    public String getString() {
        return super.getStr(key);
    }

    @Deprecated
    public String getValue() {
        return getString();
    }

    @Deprecated
    public Integer getInteger() {
        return super.getInt(key);
    }

    @Deprecated
    public Long getLong() {
        return super.getLong(key);
    }

    public Date getDate() {
        //支持常见格式
        return DateUtil.parse(getStr(key));
    }

    @Override
    public String toString() {
        //支持常见格式
        return getString();
    }

    public <T> List<T> getJsonStrToList(Class<T> clss) {
        String json = getString();
        List<T> ts = JSONUtil.toList(JSONUtil.parseArray(json), clss);
        return ts;
    }

    public JSON toJson() {
        //支持常见格式
        return JSONUtil.parse(getString());
    }
}
