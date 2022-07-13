

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.exception.code;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.exception.code.re.CodeAbE;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;

import java.util.List;

public class CodeRe extends CodeAbE {
    private String code;
    private String i18n;
    private String type;
    private String text;
    private String className;

    public CodeRe() {
    }

    public CodeRe(String code) {
        init(code, Line.i18n);
    }

    public CodeRe(String code, String i18n) {
        init(code, i18n, null);
    }

    public CodeRe(String code, String... messages) {
        init(code, Line.i18n, messages);
    }

    public CodeRe(String code, String i18n, String... messages) {
        init(code, i18n, messages);
    }

    private void init(String code, String i18n, String... messages) {
        List<String> messageList = null;
        try {
            messageList = Line.messages.get(i18n).get(code);
        } catch (Exception e) {
        }
        if (messageList == null || messageList.size() == 0) {
            throw new ExceptionError("找不到该消息码!");
        }
        this.code = code;
        this.i18n = i18n;
        this.type = messageList.get(1);
        this.className = this.getClass().getName();
        this.text = StrUtil.format(messageList.get(0), messages);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getI18n() {
        return this.i18n;
    }

    @Override
    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

}
