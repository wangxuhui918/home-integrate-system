

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.exception.impl;

import cn.bigcore.micro.exception.FyyCodeInterface;
import cn.bigcore.micro.base.message.FyyMessageType;
import cn.hutool.json.JSONUtil;

/**
 * 消息码处理的基础实现类
 *
 * @author 汪旭辉
 * @date 2022年1月21日
 * @readme
 */

public class FyyCodeImpl implements FyyCodeInterface {
    private String code;
    private String i18n;
    private FyyMessageType type;
    private String text;
    private String className;

    public FyyCodeImpl() {
        super();
    }


    /**
     * 重写 toString 为 jsonstr
     */
    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getI18n() {
        return i18n;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    @Override
    public FyyMessageType getType() {
        return type;
    }

    public void setType(FyyMessageType type) {
        this.type = type;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
