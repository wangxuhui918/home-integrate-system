package cn.bigcore.micro.config.config.bean;

/**
 * 配置文件类型
 */
public enum FyyConfigResourceType {

    SOURCE_TYPE("1", "代码文件"),//
    RESOURCE_TYPE("2", "资源文件"),//
    ;

    private String code;
    private String name;

    FyyConfigResourceType(String code, String readme) {
        this.code = code;
        this.name = readme;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
