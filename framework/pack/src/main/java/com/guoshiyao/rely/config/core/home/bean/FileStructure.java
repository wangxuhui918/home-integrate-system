package com.guoshiyao.rely.config.core.home.bean;

import com.guoshiyao.rely.core.configration.home.bean.ResourceType;

public enum FileStructure {

    FILE_STRUCTURE_1("api/domain/readme.txt", ResourceType.SOURCE_TYPE, "数据库对象"),//
    FILE_STRUCTURE_2("dubboservice/impl/readme.txt", ResourceType.SOURCE_TYPE, "dubbo实现类接口"),//
    FILE_STRUCTURE_3("dubboservice/readme.txt", ResourceType.SOURCE_TYPE, "dubbo暴露端口,一般该类暴露在单独的maven中"),//
    FILE_STRUCTURE_4("db/readme.txt", ResourceType.RESOURCE_TYPE, "fly脚本配置,创建 db/migration/V1.0.0__baseline.sql文件"),//
    FILE_STRUCTURE_5("mapper/readme.txt", ResourceType.SOURCE_TYPE, "Java-Mapper文件"),//
    FILE_STRUCTURE_6("mapper/readme.txt", ResourceType.RESOURCE_TYPE, "mapper.xml文件写在这里"),//
    FILE_STRUCTURE_7("api/vo/readme.txt", ResourceType.SOURCE_TYPE, "表单对象"),//
    FILE_STRUCTURE_8("api/bean/readme.txt", ResourceType.SOURCE_TYPE, "用户Bean对象"),//
    FILE_STRUCTURE_9("api/readme.txt", ResourceType.SOURCE_TYPE, "一般用来写controller"),//
    FILE_STRUCTURE_10("service/impl/readme.txt", ResourceType.SOURCE_TYPE, "业务实现类"),//
    FILE_STRUCTURE_11("service/readme.txt", ResourceType.SOURCE_TYPE, "业务接口类"),//
    FILE_STRUCTURE_12("readme.txt", ResourceType.SOURCE_TYPE, "基础包标记点"),//
    FILE_STRUCTURE_13("configuration/readme.txt", ResourceType.SOURCE_TYPE, "业务实现类"),//


    ;


    private String path;
    private ResourceType type;
    private String context;

    FileStructure(String path, ResourceType type, String context) {

        this.path = path;
        this.type = type;
        this.context = context;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
