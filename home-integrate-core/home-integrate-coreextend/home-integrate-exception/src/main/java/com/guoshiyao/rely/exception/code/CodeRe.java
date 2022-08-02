

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.exception.code;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.base.BaseEv;
import com.guoshiyao.rely.coreannotation.base.KeyBase;
import com.guoshiyao.rely.exception.code.re.CodeAbE;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.message.i18n.I18n;
import com.guoshiyao.rely.thread.ThreadReUtils;

import java.util.List;

/**
 * 自定义消息码
 */
public class CodeRe extends CodeAbE {
    private String code;
    private String i18n;
    private String type;
    private String text;
    private String className;

    public CodeRe() {
    }

    public CodeRe(String code) {
        init(code, null);
    }

    public CodeRe(String code, I18n i18n) {
        init(code, i18n, null);
    }

    public CodeRe(String code, String... messages) {
        init(code, null, messages);
    }

    public CodeRe(String code, I18n i18n, String... messages) {
        init(code, i18n, messages);
    }

    private void init(String code, I18n i18n, String... messages) {
        String i18nCode = "";
        if (BaseEv.I18N_THREAD_POWER && (i18n == null || StrUtil.isBlank(i18n.getI18nCode()))) {//获取线程变量
            try {
                i18nCode = ThreadReUtils.getStrParamByPath(KeyBase.I18N.getName());
            } catch (Exception e) {
            }
        }
        if (StrUtil.isBlank(i18nCode)) {
            i18nCode = Line.i18n;
        }
        List<String> messageList = null;
        try {
            messageList = Line.messages.get(i18nCode).get(code);
        } catch (Exception e) {
        }
        if (messageList == null || messageList.size() == 0) {
            throw new ExceptionError("找不到该消息码!");
        }
        this.code = code;
        this.i18n = i18nCode;
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
