/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 18671020380@163.com
 *
 */

package com.guoshiyao.rely.exception.code.re.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum DefaultCode {
    SUCCESS("true", "111111", "成功"),//
    ERROR("false", "000000", "失败"), //
    WARN("warn", "222222", "警告"),//
    UNKNOWN("no", "999999", "未知"),//

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

    DefaultCode(String code, String name, String ment) {
        this.code = code;
        this.name = name;
        this.ment = ment;
    }

    public static DefaultCode getByteCode(String code) {
        for (DefaultCode value : DefaultCode.values()) {
            if (StrUtil.equals(value.getCode(), code)) {
                return value;
            }
        }
        return UNKNOWN;
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
