package cn.bigcore.micro.core.configration.home.impl.bean;

public enum ConfigMain {


    APOLLO("apollo.ini", false, false, false, ""),//
    DUBBO("dubbo.ini", false, false, false, ""),//
    DB("db.ini", false, false, false, ""),//
    FLYWAY("flyway.ini", false, false, false, ""),//
    GITLAB("gitlab.ini", false, false, false, ""),//
    LOGBACK("logback.ini", false, false, false, ""),//
    MYBATIS("mybatis.ini", false, false, false, ""),//
    MYBATIS_PLUS("mybatisplus.ini", false, false, false, ""),//
    MINIO("minio.ini", false, false, false, ""),//
    REDIS("redis.ini", false, false, false, ""),//
    SWAGGER("swagger.ini", false, false, false, ""),//
    SYSTEMCONFIG("systemconfig.ini", false, false, false, ""),//
    SYSTEMEXTEND("systemextend.ini", false, false, false, ""),//
    MESSAGE("message-${i18n}.xml", false, false, true, ConfigInitValue.message_model),//


    ;

    private String configFileName;
    private String context;
    private boolean only_local;
    private boolean use_uk;
    private boolean need_format_zone;

    ConfigMain(String configFileName, boolean only_local, boolean use_uk, boolean need_format_zone, String context) {
        this.configFileName = configFileName;
        this.context = context;
        this.only_local = only_local;
        this.use_uk = use_uk;
        this.need_format_zone = need_format_zone;
    }

    public static ConfigMain getByFileName(String configFileName) {
        for (ConfigMain o : ConfigMain.values()) {
            if (o.getConfigFileName().equals(configFileName)) {
                return o;
            }
        }
        return null;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public boolean isOnly_local() {
        return only_local;
    }

    public void setOnly_local(boolean only_local) {
        this.only_local = only_local;
    }

    public boolean isUse_uk() {
        return use_uk;
    }

    public void setUse_uk(boolean use_uk) {
        this.use_uk = use_uk;
    }

    public boolean isNeed_format_zone() {
        return need_format_zone;
    }

    public void setNeed_format_zone(boolean need_format_zone) {
        this.need_format_zone = need_format_zone;
    }


}
