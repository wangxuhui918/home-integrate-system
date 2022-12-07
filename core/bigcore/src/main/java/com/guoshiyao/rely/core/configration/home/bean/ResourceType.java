package com.guoshiyao.rely.core.configration.home.bean;

public enum ResourceType {

    SOURCE_TYPE("1", "源代码路径"),//
    RESOURCE_TYPE("2", "资源路径"),//


    //


    ;

    private String code;
    private String name;

    ResourceType(String code, String readme) {
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
