/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.base.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 支付状态说明
 */
public enum FyyAuthReturnType {
    AuthSuccess("AUTH01", "鉴权通过,放行", "success"),//
    AuthNoAuth("AUTH02", "鉴权失败,拦截", "danger"),//
    LoginFailure("AUTH03", "用户失效或登陆失败", "danger"), //
    UnknownException("AUTH04", "未知鉴权异常", "danger"),//
    ;

    private String code;
    private String name;
    private String ment;


    FyyAuthReturnType(String code, String name, String ment) {
        this.code = code;
        this.name = name;
        this.ment = ment;

    }

    public static FyyAuthReturnType getByteCode(String code) {
        for (FyyAuthReturnType value : FyyAuthReturnType.values()) {
            if (StrUtil.equals(value.getCode(), code)) {
                return value;
            }
        }
        return UnknownException;
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

    public String getMent() {
        return ment;
    }

    public void setMent(String ment) {
        this.ment = ment;
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
