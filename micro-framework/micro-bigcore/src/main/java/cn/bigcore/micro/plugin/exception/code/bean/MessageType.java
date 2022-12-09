package cn.bigcore.micro.plugin.exception.code.bean;

public enum MessageType {

    SUCCESS("1", "成功的类型", true, "true", "111111"),//
    WARING("2", "警告的类型", true, "waring", "222222"),//
    ERR("3", "失败的类型", false, "false", "333333"),//
    NOT_FOUND_TYPE("404", "未知的错误类型", false, "NOT_FOUND_TYPE", "444444"),//
    NOT_FOUNT_CODE("5", "找不到消息码:{}!", false, "NOT_FOUNT_CODE", "555555"),//

    ;

    private String typeCode;//类型代码
    private String mark;//说明
    private boolean booleanMark;//布尔型提示
    private String booleanStrMark;//布尔型字符串提示
    private String typeStateCode;//布尔型状态码提示

    MessageType(String typeCode, String mark, boolean booleanMark, String booleanStrMark, String typeStateCode) {
        this.typeCode = typeCode;
        this.mark = mark;
        this.booleanMark = booleanMark;
        this.booleanStrMark = booleanStrMark;
        this.typeStateCode = typeStateCode;
    }

    public static MessageType getByBooleanStrMark(String booleanStrMark) {
        for (MessageType o : MessageType.values()) {
            if (o.getBooleanStrMark().toLowerCase().equals(booleanStrMark.toLowerCase())) {
                return o;
            }
        }
        return NOT_FOUND_TYPE;
    }


    public MessageTypeVo getMessageTypeVo() {
        return new MessageTypeVo(typeCode, mark, booleanMark, booleanStrMark, typeStateCode);
    }

    public MessageCodeVo getMessageCodeVo() {
        MessageCodeVo vo = new MessageCodeVo();
        vo.setContext(mark);
        vo.setStateCode(typeStateCode);
        vo.setStateType(new MessageTypeVo(typeCode, mark, booleanMark, booleanStrMark, typeStateCode));
        return vo;
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
