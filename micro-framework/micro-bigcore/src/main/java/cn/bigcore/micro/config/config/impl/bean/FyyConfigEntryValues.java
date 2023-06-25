package cn.bigcore.micro.config.config.impl.bean;

/**
 * 配置条目值
 */
public enum FyyConfigEntryValues {


    APOLLO("apollo.ini", ""),//
    DUBBO("dubbo.ini", ""),//
    DB("db.ini", ""),//
    FLYWAY("flyway.ini", ""),//
    GITLAB("gitlab.ini", ""),//
    LOGBACK("logback.ini", ""),//
    MYBATIS("mybatis.ini", ""),//
    MYBATIS_PLUS("mybatisplus.ini", ""),//
    MINIO("minio.ini", ""),//
    REDIS("redis.ini", ""),//
    SWAGGER("swagger.ini", ""),//
    SYSTEMCONFIG("systemconfig.ini", ""),//
    SYSTEMEXTEND("systemextend.ini", ""),//
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


    private String configName;
    private String context;


    FyyConfigEntryValues(String configName, String context) {
        this.configName = configName;
        this.context = context;

    }

    public static FyyConfigEntryValues getByFileName(String configFileName) {
        for (FyyConfigEntryValues o : FyyConfigEntryValues.values()) {
            if (o.getConfigName().equals(configFileName)) {
                return o;
            }
        }
        return null;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

}
