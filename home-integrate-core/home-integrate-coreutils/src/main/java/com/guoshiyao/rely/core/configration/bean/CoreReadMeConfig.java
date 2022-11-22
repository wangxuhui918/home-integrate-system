package com.guoshiyao.rely.core.configration.bean;

public enum CoreReadMeConfig {

    文档创建1("1", 1, "api/domain/readme.txt", "1", "数据库对象"),//
    文档创建2("2", 2, "dubboservice/impl/readme.txt", "1", "dubbo实现类接口"),//
    文档创建3("3", 3, "dubboservice/readme.txt", "1", "dubbo暴露端口,一般该类暴露在单独的maven中"),//
    文档创建4("4", 4, "db/readme.txt", "2", "fly脚本配置,创建 db/migration/V1.0.0__baseline.sql文件"),//
    文档创建5("5", 5, "mapper/readme.txt", "1", "Java-Mapper文件"),//
    文档创建6("6", 6, "mapper/readme.txt", "2", "mapper.xml文件写在这里"),//
    文档创建7("7", 7, "api/vo/readme.txt", "1", "表单对象"),//
    文档创建8("8", 8, "api/bean/readme.txt", "1", "用户Bean对象"),//
    文档创建9("9", 9, "api/readme.txt", "1", "一般用来写controller"),//
    文档创建10("10", 10, "service/impl/readme.txt", "1", "业务实现类"),//
    文档创建11("11", 11, "service/readme.txt", "1", "业务接口类"),//
    文档创建12("12", 12, "readme.txt", "1", "基础包标记点"),//
    文档创建13("13", 13, "configuration/readme.txt", "1", "业务实现类"),//


    ;

    private String id;
    private Integer sort;
    private String path;
    private String type;
    private String readme;

    CoreReadMeConfig(String id, Integer sort, String path, String type, String readme) {
        this.id = id;
        this.sort = sort;
        this.path = path;
        this.type = type;
        this.readme = readme;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }
}
