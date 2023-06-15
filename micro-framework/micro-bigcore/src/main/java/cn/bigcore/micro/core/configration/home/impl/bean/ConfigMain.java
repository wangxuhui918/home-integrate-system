package cn.bigcore.micro.core.configration.home.impl.bean;

public enum ConfigMain {


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
            "\n" +
            "\t<member code=\"DEMO01\" conmtext=\"客户基础数据维护成功\" type=\"true\" />\n" +
            "\n" +
            "\t<group name=\"${idKey}\">\n" +
            "\t\t<member code=\"01\" conmtext=\"获取数据成功\" type=\"true\" />\n" +
            "\t\t<member code=\"02\" conmtext=\"主键目前只能有一个!!\" type=\"false\" />\n" +
            "\t\t<member code=\"03\" conmtext=\"{}为不支持的数据库类型\" type=\"false\" />\n" +
            "\t</group>\n" +
            "\n" +
            "\n" +
            "</message>"),//


    ;


    private String configName;
    private String context;


    ConfigMain(String configName, String context) {
        this.configName = configName;
        this.context = context;

    }

    public static ConfigMain getByFileName(String configFileName) {
        for (ConfigMain o : ConfigMain.values()) {
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
