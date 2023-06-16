/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatis.annotation.bean;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum FyyMybatisMethod {
    pageSelect("1", "分页查询方法", "danger"),//
    andSelect("2", "并查询方法", "warning"), //
    add("3", "添加", "success"),//
    deleteByPrimaryKey("4", "按主键删除 ", "danger"),//
    updateByPrimaryKeySelective("5", "按主键更新 ", "warning"),//
    queryByPrimaryKey("7", "按主键查询 ", "warning"),//
    nomethod("6", "未选择方法", "warning"),//
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

    FyyMybatisMethod(String code, String name, String ment) {
        this.code = code;
        this.name = name;
        this.ment = ment;
    }

    public static FyyMybatisMethod getByteCode(String code) {
        for (FyyMybatisMethod value : FyyMybatisMethod.values()) {
            if (StrUtil.equals(value.getCode(), code)) {
                return value;
            }
        }
        return nomethod;
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
