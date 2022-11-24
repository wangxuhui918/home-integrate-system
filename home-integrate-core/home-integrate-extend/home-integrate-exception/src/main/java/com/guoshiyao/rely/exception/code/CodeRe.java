

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
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.plugin.exception.code.bean.MessageCodeVo;
import com.guoshiyao.rely.plugin.exception.code.bean.MessageType;
import com.guoshiyao.rely.plugin.exception.code.impl.CodeImpl;
import com.guoshiyao.rely.plugin.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.plugin.i18n.I18n;
import com.guoshiyao.rely.plugin.thread.ThreadReUtils;
import com.guoshiyao.rely.plugin.thread.bean.KeyBase;

/**
 * 自定义消息码
 */
public class CodeRe extends CodeImpl {
    private String code;
    private String i18n;
    private MessageType type;
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
        if (BaseEv.ProjectInformation.OPEN_THREAD_I18N && (i18n == null || StrUtil.isBlank(i18n.getI18nCode()))) {//获取线程变量
            try {
                i18nCode = ThreadReUtils.getStrParamByPath(KeyBase.I18N.getName());
            } catch (Exception e) {
            }
        }
        if (StrUtil.isBlank(i18nCode)) {
            i18nCode = BaseEv.SettingInformation.i18n;
        }
        MessageCodeVo messageCodeVo = null;
        try {
            messageCodeVo = BaseEv.SettingInformation.messages.get(i18nCode).get(code);
        } catch (Exception e) {
        }
        if (messageCodeVo == null) {
            throw new ExceptionError("找不到该消息码!");
        }
        this.code = code;
        this.i18n = i18nCode;
        this.type = messageCodeVo.getStateType();
        this.className = this.getClass().getName();
        this.text = StrUtil.format(messageCodeVo.getContext(), messages);
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
    public MessageType getType() {
        return this.type;
    }

    @Override
    public void setType(MessageType type) {
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
