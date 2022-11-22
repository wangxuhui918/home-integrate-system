/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.plugin.thread.bean;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 汪旭辉
 */
public enum KeyBase {
    I18N("1", "I18N", "danger"),//
    USERRE("2", "userRe", "danger"),//

    ;//

    private String code;
    private String name;
    private String ment;

    public String getMent() {
        return ment;
    }

    public void setMent(String ment) {
        this.ment = ment;
    }

    KeyBase(String code, String name, String ment) {
        this.code = code;
        this.name = name;
        this.ment = ment;
    }

    public static KeyBase getByteCode(String code) {
        for (KeyBase value : KeyBase.values()) {
            if (StrUtil.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }

    public static List getListDict() {
        List<Map<String, Object>> mlist = new ArrayList<>();
        for (Object value : values()) {
            Map<String, Object> all = BeanUtil.beanToMap(value);
            all.put("keyname", value.toString());
            mlist.add(all);
        }
        return mlist;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
