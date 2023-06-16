package cn.bigcore.micro.exception.code.bean;

/**
 * @author 汪旭辉
 * @date 2022/11/24
 * @readme
 */
public class FyyMessageType {
    /**
     * 字段说明可参照枚举类
     * cn.bigcore.micro.plugin.exception.code.bean.MessageType
     */
    private String typeCode;
    private String mark;
    private boolean booleanMark;
    private String booleanStrMark;
    private String typeStateCode;

    public FyyMessageType(String typeCode, String mark, boolean booleanMark, String booleanStrMark, String typeStateCode) {
        this.typeCode = typeCode;
        this.mark = mark;
        this.booleanMark = booleanMark;
        this.booleanStrMark = booleanStrMark;
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

    public String getTypeStateCode() {
        return typeStateCode;
    }

    public void setTypeStateCode(String typeStateCode) {
        this.typeStateCode = typeStateCode;
    }
}
