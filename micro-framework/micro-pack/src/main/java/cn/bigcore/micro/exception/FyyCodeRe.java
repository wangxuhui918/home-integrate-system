

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.exception;

import cn.bigcore.micro.base.message.FyyMessageCode;
import cn.bigcore.micro.base.message.FyyMessageType;
import cn.bigcore.micro.exception.impl.FyyCodeImpl;
import cn.bigcore.micro.i18n.FyyI18n;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.base.message.FyyMessageEnum;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.bigcore.micro.thread.FyyThreadReUtils;
import cn.bigcore.micro.base.thread.FyyKeyBase;

/**
 * 自定义消息码
 */
public class FyyCodeRe extends FyyCodeImpl {
    private String code;
    private String i18n;
    private FyyMessageType type;
    private String text;
    private String className;

    public FyyCodeRe() {
    }

    public FyyCodeRe(String code) {
        init(code, FyyI18n.NOT_FOUNT);
    }

    public FyyCodeRe(String code, FyyI18n i18n) {
        init(code, i18n, null);
    }

    public FyyCodeRe(String code, String... messages) {
        init(code, FyyI18n.NOT_FOUNT, messages);
    }

    public FyyCodeRe(String code, FyyI18n i18n, String... messages) {
        init(code, i18n, messages);
    }

    private void init(String code, FyyI18n i18n, String... messages) {
        String i18nCode = "";
        if (FyyInitEnv.ProjecEnvInformation.OPEN_THREAD_I18N && (i18n == null || StrUtil.isBlank(i18n.getI18nCode()))) {//获取线程变量
            try {
                i18nCode = FyyThreadReUtils.getStrParamByPath(FyyKeyBase.I18N.getKeyName());
            } catch (Exception e) {
                FyyLogBaseUtils.warn(FyyCodeRe.class, "线程上下文i18nCode获取失败!");
            }
        }
        if (StrUtil.isBlank(i18nCode)) {
            i18nCode = FyyInitEnv.ProjectInformation.i18n;
            FyyLogBaseUtils.warn(FyyCodeRe.class, "线程上下文i18nCode为空,采用默认值:", FyyInitEnv.ProjectInformation.i18n);
        }
        FyyMessageCode messageCodeVo = null;
        try {
            messageCodeVo = FyyInitEnv.ProjectInformation.messages.get(i18nCode).get(code);
        } catch (Exception e) {
        }
        if (messageCodeVo == null) {
            //            throw new ExceptionError("找不到该消息码!");
            messageCodeVo = FyyMessageEnum.NOT_FOUNT_CODE.getMessageCodeVo();
            this.code = messageCodeVo.getStateCode();
            this.i18n = i18nCode;
            this.type = messageCodeVo.getStateType();
            this.className = this.getClass().getName();
            this.text = StrUtil.format(messageCodeVo.getContext(), code);
            FyyLogBaseUtils.warn(FyyCodeRe.class, "在i18nCode[{}]中找不到消息码[{}],采用默认消息码[{}]", i18nCode, code, this.code);
        } else {
            this.code = code;
            this.i18n = i18nCode;
            this.type = messageCodeVo.getStateType();
            this.className = this.getClass().getName();
            this.text = StrUtil.format(messageCodeVo.getContext(), messages);
        }

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
    public FyyMessageType getType() {
        return this.type;
    }

    @Override
    public void setType(FyyMessageType type) {
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
