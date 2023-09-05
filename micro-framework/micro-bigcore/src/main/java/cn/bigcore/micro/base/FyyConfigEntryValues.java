package cn.bigcore.micro.base;

/**
 * 配置条目值
 */
public enum FyyConfigEntryValues {


    APOLLO("阿波罗"),//
    DUBBO("DUBBO"),//
    DB("数据库"),//
    FLYWAY("数据库增量"),//
    GITLAB("GIT代码库"),//
    LOGBACK("日志"),//
    MYBATIS("持久层"),//
    MYBATIS_PLUS("持久层工具"),//
    MINIO("文件桶"),//
    REDIS("内存库(redis)"),//
    SWAGGER("API调试"),//
    SYSTEMCONFIG("系统"),//
    SYSTEMEXTEND("系统扩展"),//
    MESSAGE("message-${i18n}.xml", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<message>\n" +
            "\t\t<member code=\"DEMO01\" conmtext=\"客户基础数据维护成功\" type=\"true\" />\n" +
            "\t\t<group name=\"${idKey}\">\n" +
            "\t\t\t\t<member code=\"01\" conmtext=\"获取数据成功\" type=\"true\" />\n" +
            "\t\t\t\t<member code=\"02\" conmtext=\"主键目前只能有一个!!\" type=\"false\" />\n" +
            "\t\t\t\t<member code=\"03\" conmtext=\"{}为不支持的数据库类型\" type=\"false\" />\n" +
            "\t\t</group>\n" +
            "</message>"),//



    ;


    private String mark;
    private String context;

    FyyConfigEntryValues(String mark) {
        this.mark = mark;
        this.context = null;
    }

    FyyConfigEntryValues(String mark, String context) {
        this.mark = mark;
        this.context = context;

    }

    public static FyyConfigEntryValues getByFileName(String configFileName) {
        for (FyyConfigEntryValues o : FyyConfigEntryValues.values()) {
            if (o.getMark().equals(configFileName)) {
                return o;
            }
        }
        return null;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

}
