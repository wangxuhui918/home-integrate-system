package com.guoshiyao.rely.plugin.exception.code.bean;

/**
 * @author 汪旭辉
 * @date 2022/11/24
 * @readme
 */
public class MessageCodeVo {
    private String stateCode;
    private MessageType stateType;
    private String context;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public MessageType getStateType() {
        return stateType;
    }

    public void setStateType(MessageType stateType) {
        this.stateType = stateType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
