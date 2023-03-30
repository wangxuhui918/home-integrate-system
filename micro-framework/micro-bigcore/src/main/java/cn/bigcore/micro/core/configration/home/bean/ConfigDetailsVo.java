/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.core.configration.home.bean;


import java.io.Serializable;

/**
 * properties文件描述类
 */
public class ConfigDetailsVo implements Serializable {

    /**
     * 参数ID
     */
    private Integer id;
    /**
     * 参数名
     */
    private String key;
    /**
     * 参数前缀
     */
    private String beforesuff;
    /**
     * 切割符号,比如=
     */
    private String m;
    /**
     * 参数值
     */
    private String value;
    /**
     * 参数备注
     */
    private String mark;
    /**
     * 当前参数文件名
     */
    private String name_en;

    public String getBeforesuff() {
        return beforesuff;
    }

    public void setBeforesuff(String beforesuff) {
        this.beforesuff = beforesuff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }


    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }
}

