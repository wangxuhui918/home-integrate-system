package cn.bigcore.micro.exception.code.bean;

/**
 * @author 汪旭辉
 * @date 2022/11/24
 * @readme
 */
public class FyyMessageCode {
    private String stateCode;
    private FyyMessageType stateType;
    private String context;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public FyyMessageType getStateType() {
        return stateType;
    }

    public void setStateType(FyyMessageType stateType) {
        this.stateType = stateType;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
