package com.guoshiyao.rely.plugin.exception.code.bean;

public enum MessageType {

    SUCCESS("1", "成功", true, "true", "111111"),//
    ERR("2", "失败", false, "false", "000000"),//
    WARING("3", "警告", true, "waring", "222222"),//
    NO("404", "找不到错误类型的警告", false, "404", "999999"),//

    ;

    private String typeCode;
    private String mark;
    private boolean booleanMark;
    private String booleanStrMark;
    private String typeStateCode;

    MessageType(String typeCode, String mark, boolean booleanMark, String booleanStrMark, String typeStateCode) {
        this.typeCode = typeCode;
        this.mark = mark;
        this.booleanMark = booleanMark;
        this.booleanStrMark = booleanStrMark;
        this.typeStateCode = typeStateCode;
    }

    public static MessageType getByBooleanStrMark(String booleanStrMark) {
        for (MessageType o : MessageType.values()) {
            if (o.getBooleanStrMark().equals(booleanStrMark)) {
                return o;
            }
        }
        return NO;
    }

    public String getTypeStateCode() {
        return typeStateCode;
    }

    public void setTypeStateCode(String typeStateCode) {
        this.typeStateCode = typeStateCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public boolean isBooleanMark() {
        return booleanMark;
    }

    public void setBooleanMark(boolean booleanMark) {
        this.booleanMark = booleanMark;
    }

    public String getBooleanStrMark() {
        return booleanStrMark;
    }

    public void setBooleanStrMark(String booleanStrMark) {
        this.booleanStrMark = booleanStrMark;
    }
}
