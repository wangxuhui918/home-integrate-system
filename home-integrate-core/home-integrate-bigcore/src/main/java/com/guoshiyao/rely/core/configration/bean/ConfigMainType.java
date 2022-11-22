package com.guoshiyao.rely.core.configration.bean;

public enum ConfigMainType {


    apollo("apollo.ini", "apollo", 1, "", false, false, false, "1000001"),//                                     "),//
    dubbo("dubbo.ini", "dubbo", 2, "", false, false, false, "1000004"),//                                     "),//
    db("db.ini", "db", 3, "", false, false, false, "1000003"),//                                     "),//
    flyway("flyway.ini", "flyway", 4, "", false, false, false, "1000006"),//                                     "),//
    gitlab("gitlab.ini", "gitlab", 5, "", false, false, false, "1000007"),//                                     "),//
    logback("logback.ini", "logback", 6, "", false, false, false, "1000008"),//                                     "),//
    mybatis("mybatis.ini", "mybatis", 7, "", false, false, false, "1000011"),//                                     "),//
    minio("minio.ini", "minio", 8, "", false, false, false, "1000016"),//                                     "),//
    redis("redis.ini", "redis", 9, "", false, false, false, "1000012"),//                                     "),//
    swagger("swagger.ini", "swagger", 10, "", false, false, false, "1000013"),//                                     "),//
    systemconfig("systemconfig.ini", "systemconfig", 11, "", false, false, false, "1000014"),//                                     "),//
    systemextend("systemextend.ini", "systemextend", 12, "", false, false, false, "1000015"),//                                     "),//
    message("message-${i18n}.xml", "message", 13, ConfigInitValue.message_model, false, false, true, "1000009"),//                                     "),//


    ;

    private String code;
    private String name_en;
    private Integer sort_id;
    private String model_context;
    private boolean only_local;
    private boolean use_uk;
    private boolean need_format_zone;
    private String old_code;

    ConfigMainType(String code, String name_en, Integer sort_id, String model_context, boolean only_local, boolean use_uk, boolean need_format_zone, String old_code) {
        this.code = code;
        this.name_en = name_en;
        this.sort_id = sort_id;
        this.model_context = model_context;
        this.only_local = only_local;
        this.use_uk = use_uk;
        this.need_format_zone = need_format_zone;
        this.old_code = old_code;
    }

    public String getCode() {
        return code;
    }

    public String getOld_code() {
        return old_code;
    }

    public void setOld_code(String old_code) {
        this.old_code = old_code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public Integer getSort_id() {
        return sort_id;
    }

    public void setSort_id(Integer sort_id) {
        this.sort_id = sort_id;
    }

    public String getModel_context() {
        return model_context;
    }

    public void setModel_context(String model_context) {
        this.model_context = model_context;
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
