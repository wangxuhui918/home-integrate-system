package com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties;

public enum ConfigDetails {


    CONFIG_DETAILS_1(ConfigMain.REDIS, 1, "home.redis.host", "=", "192.168.3.251", "# 使用方法为 com.guoshiyao.rely.line.Line.redisds.getjedis()# 地址，默认localhost", "#"),//
    CONFIG_DETAILS_2(ConfigMain.REDIS, 2, "home.redis.port", "=", "6379", "# 端口号", "#"),//
    CONFIG_DETAILS_3(ConfigMain.REDIS, 3, "home.redis.password", "=", "123456", "# 密码", "#"),//
    CONFIG_DETAILS_4(ConfigMain.REDIS, 4, "home.redis.masterName", "=", "mymaster", "# masterName", "#"),//
    CONFIG_DETAILS_5(ConfigMain.REDIS, 5, "home.redis.BlockWhenExhausted", "=", "true", "# 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true", "#"),//
    CONFIG_DETAILS_6(ConfigMain.REDIS, 6, "home.redis.evictionPolicyClassName", "=", "org.apache.commons.pool2.impl.DefaultEvictionPolicy", "# 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)", "#"),//
    CONFIG_DETAILS_7(ConfigMain.REDIS, 7, "home.redis.jmxEnabled", "=", "true", "# 是否启用pool的jmx管理功能, 默认true", "#"),//
    CONFIG_DETAILS_8(ConfigMain.REDIS, 8, "home.redis.lifo", "=", "true", "# 是否启用后进先出, 默认true", "#"),//
    CONFIG_DETAILS_9(ConfigMain.REDIS, 9, "home.redis.maxIdle", "=", "8", "# 最大空闲连接数, 默认8个", "#"),//
    CONFIG_DETAILS_10(ConfigMain.REDIS, 10, "home.redis.minIdle", "=", "1", "# 最小空闲连接数, 默认0", "#"),//
    CONFIG_DETAILS_11(ConfigMain.REDIS, 11, "home.redis.maxTotal", "=", "8", "# 最大连接数, 默认8个", "#"),//
    CONFIG_DETAILS_12(ConfigMain.REDIS, 12, "home.redis.maxWaitMillis", "=", "-1", "# 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", "#"),//
    CONFIG_DETAILS_13(ConfigMain.REDIS, 13, "home.redis.minEvictableIdleTimeMillis", "=", "1800000", "# 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)", "#"),//
    CONFIG_DETAILS_14(ConfigMain.REDIS, 14, "home.redis.numTestsPerEvictionRun", "=", "3", "# 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3", "#"),//
    CONFIG_DETAILS_15(ConfigMain.REDIS, 15, "home.redis.SoftMinEvictableIdleTimeMillis", "=", "1800000", "# 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)", "#"),//
    CONFIG_DETAILS_16(ConfigMain.REDIS, 16, "home.redis.testOnBorrow", "=", "true", "# 在获取连接的时候检查有效性, 默认false", "#"),//
    CONFIG_DETAILS_17(ConfigMain.REDIS, 17, "home.redis.testWhileIdle", "=", "true", "# 在空闲时检查有效性, 默认false", "#"),//
    CONFIG_DETAILS_18(ConfigMain.REDIS, 18, "home.redis.timeBetweenEvictionRunsMillis", "=", "-1", "# 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", "#"),//
    CONFIG_DETAILS_19(ConfigMain.LOGBACK, 19, "home.log.appid", "=", "${idKey}", "# 默认为idkey debug", ""),//
    CONFIG_DETAILS_20(ConfigMain.LOGBACK, 20, "home.log.rootlevel", "=", "info", "# 根日志级别 debug info  warn  error trance 默认debug# 从高到地低 OFF 、 FATAL 、 ERROR 、 WARN 、 INFO 、 DEBUG 、 TRACE 、 ALL -->", ""),//
    CONFIG_DETAILS_21(ConfigMain.LOGBACK, 21, "home.log.syslevel", "=", "info", "# 系统日志级别 默认debug", ""),//
    CONFIG_DETAILS_22(ConfigMain.LOGBACK, 22, "home.log.businlevel", "=", "info", "# 业务日志级别 默认debug", ""),//
    CONFIG_DETAILS_23(ConfigMain.LOGBACK, 23, "home.log.businpackage", "=", "${projectPackage}", "# 业务日志包路径,空的话默认为系统扫描到的包路径", ""),//
    CONFIG_DETAILS_24(ConfigMain.LOGBACK, 24, "home.log.dir", "=", "target/log/${idKey}", "# 日志存储位置", ""),//
    CONFIG_DETAILS_25(ConfigMain.MYBATIS, 25, "home.mybatis.basepackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    CONFIG_DETAILS_26(ConfigMain.MYBATIS, 26, "home.mybatis.typealiasespackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    CONFIG_DETAILS_27(ConfigMain.MYBATIS, 27, "home.mybatis.mapperlocations", "=", "classpath:**/mapper/**.xml", "# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹", "#"),//
    CONFIG_DETAILS_28(ConfigMain.DUBBO, 28, "home.dubbo.reference.package", "=", "${projectPackage}.dubboservice", "# dubbo默认扫描包:${projectPackage}.dubboservice", "#"),//
    CONFIG_DETAILS_29(ConfigMain.DUBBO, 29, "home.dubbo.reference.timeout", "=", "120000", "# dubbo连接超时时间", "#"),//
    CONFIG_DETAILS_30(ConfigMain.DUBBO, 30, "home.dubbo.reference.url", "=", "zookeeper://zookeeper.com:2181", "# dubbo注册中心地址", "#"),//
    CONFIG_DETAILS_31(ConfigMain.DUBBO, 31, "home.dubbo.reference.agreement", "=", "dubbo", "# dubbo协议", "#"),//
    CONFIG_DETAILS_32(ConfigMain.DUBBO, 32, "home.dubbo.reference.host", "=", "127.0.0.1", "# dubbo本地地址", "#"),//
    CONFIG_DETAILS_33(ConfigMain.DUBBO, 33, "home.dubbo.reference.port", "=", "20880", "# dubbo本地暴露端口", "#"),//
    CONFIG_DETAILS_34(ConfigMain.DUBBO, 34, "home.dubbo.protocolconfig.threads", "=", "300", "# dubbo 线程数", "#"),//
    CONFIG_DETAILS_35(ConfigMain.DUBBO, 35, "home.dubbo.protocolconfig.threadpool", "=", "fixed", "# #cached limited fixed all", "#"),//
    CONFIG_DETAILS_39(ConfigMain.MINIO, 39, "home.minio.endpoint", "=", "http://localhost:9000", "# api 请求地址 必填", "#"),//
    CONFIG_DETAILS_40(ConfigMain.MINIO, 40, "home.minio.accesskey", "=", "SDS*DSJDlerw9(were)", "# api 请求用户名", "#"),//
    CONFIG_DETAILS_41(ConfigMain.MINIO, 41, "home.minio.secretKey", "=", "SDS*DSJDlerdsfsdf", "# api 请求密钥", "#"),//
    CONFIG_DETAILS_42(ConfigMain.MINIO, 42, "home.minio.namespace_re", "=", "${idKey}", "# 桶 命名空间,必填", "#"),//
    CONFIG_DETAILS_43(ConfigMain.MINIO, 43, "home.minio.deom", "=", "demop", "# 测试增加数据", "#"),//
    CONFIG_DETAILS_44(ConfigMain.SWAGGER, 44, "home.swagger.basepackage", "=", "${projectPackage}.api", "# swagger 默认扫描 api路径为启动类所在目录下的 api目录", "#"),//
    CONFIG_DETAILS_45(ConfigMain.SWAGGER, 45, "home.swagger.name", "=", "SwaggeAPI", "# swagger命名,暂时命名为SwaggerAPI", "#"),//
    CONFIG_DETAILS_46(ConfigMain.DB, 46, "home.db.url", "=", "jdbc:mysql://localhost:3306/home?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8", "# 数据库连接地址", "#"),//
    CONFIG_DETAILS_47(ConfigMain.DB, 47, "home.db.username", "=", "root", "# 用户名", "#"),//
    CONFIG_DETAILS_48(ConfigMain.DB, 48, "home.db.password", "=", "password", "# 密码", "#"),//
    CONFIG_DETAILS_49(ConfigMain.DB, 49, "home.db.driverclassname", "=", "com.mysql.cj.jdbc.Driver", "# 驱动UrlClass", "#"),//
    CONFIG_DETAILS_50(ConfigMain.DB, 50, "home.db.driverclass.downloadurl", "=", "https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.49/mysql-connector-java-5.1.49.jar", "#驱动下载地址,使用 url 后可以不填写home.db.driverclassname,MAVEN POM 中无需加载驱动坐标,优先级低于home.db.driverclassname", "#"),//
    CONFIG_DETAILS_51(ConfigMain.DB, 51, "home.db.validationquery", "=", "select 'x'", "# 数据库校验sql", "#"),//
    CONFIG_DETAILS_52(ConfigMain.DB, 52, "home.db.maxactive", "=", "30", "", "#"),//
    CONFIG_DETAILS_53(ConfigMain.DB, 53, "home.db.initialsize", "=", "1", "", "#"),//
    CONFIG_DETAILS_54(ConfigMain.DB, 54, "home.db.maxwait", "=", "60000", "", "#"),//
    CONFIG_DETAILS_55(ConfigMain.DB, 55, "home.db.minidle", "=", "3", "", "#"),//
    CONFIG_DETAILS_56(ConfigMain.DB, 56, "home.db.timebetweenevictionrunsmillis", "=", "60000", "", "#"),//
    CONFIG_DETAILS_57(ConfigMain.DB, 57, "home.db.minevictableidletimemillis", "=", "300000", "", "#"),//
    CONFIG_DETAILS_58(ConfigMain.DB, 58, "home.db.testwhileidle", "=", "true", "", "#"),//
    CONFIG_DETAILS_59(ConfigMain.DB, 59, "home.db.testonborrow", "=", "false", "", "#"),//
    CONFIG_DETAILS_60(ConfigMain.DB, 60, "home.db.testonreturn", "=", "false", "", "#"),//
    CONFIG_DETAILS_61(ConfigMain.DB, 61, "home.db.poolpreparedstatements", "=", "true", "", "#"),//
    CONFIG_DETAILS_62(ConfigMain.DB, 62, "home.db.removeabandoned", "=", "true", "", "#"),//
    CONFIG_DETAILS_63(ConfigMain.DB, 63, "home.db.removeabandonedtimeout", "=", "60", "", "#"),//
    CONFIG_DETAILS_64(ConfigMain.DB, 64, "home.db.numtestsperevictionrun", "=", "60", "", "#"),//
    CONFIG_DETAILS_65(ConfigMain.DB, 65, "home.db.maxopenpreparedstatements", "=", "20", "", "#"),//
    CONFIG_DETAILS_66(ConfigMain.DB, 66, "home.db.filters", "=", "stat,wall", "", "#"),//
    CONFIG_DETAILS_67(ConfigMain.FLYWAY, 67, "home.flywaydb.url", "=", "", "# flyway jdbc连接地址,如果为空取数据库连接地址", "#"),//
    CONFIG_DETAILS_68(ConfigMain.FLYWAY, 68, "home.flywaydb.username", "=", "", "# flyway 如果为空,取数据库用户名", "#"),//
    CONFIG_DETAILS_69(ConfigMain.FLYWAY, 69, "home.flywaydb.password", "=", "", "# flyway 如果为空,去数据库密码", "#"),//
    CONFIG_DETAILS_70(ConfigMain.FLYWAY, 70, "home.flywaydb.table", "=", "flyway_schema_history", "# flyway 版本管理表", "#"),//
    CONFIG_DETAILS_71(ConfigMain.APOLLO, 71, "home.apollo.url", "=", "阿波罗管理地址,如果不填写则阿波罗不加载", "# 管理端", "#"),//
    CONFIG_DETAILS_72(ConfigMain.APOLLO, 72, "home.apollo.username", "=", "阿波罗管理用户名", "", "#"),//
    CONFIG_DETAILS_73(ConfigMain.APOLLO, 73, "home.apollo.password", "=", "阿波罗管理密码", "", "#"),//
    CONFIG_DETAILS_74(ConfigMain.APOLLO, 74, "home.apollo.app.id", "=", "${idKey}", "# 客户端", "#"),//
    CONFIG_DETAILS_75(ConfigMain.APOLLO, 75, "home.apollo.apollo.meta", "=", "http://106.54.227.205:8080", "# 地址后缀不允许以/结尾", "#"),//
    CONFIG_DETAILS_76(ConfigMain.APOLLO, 76, "home.apollo.apollo.env", "=", "${env}", "# dev,uat,pro 可以通过系统env传入,注释后自动通过传入变量决定,这个建议不传,系统自动识别", "#"),//
    CONFIG_DETAILS_77(ConfigMain.GITLAB, 77, "home.gitlab.gitdir", "=", "${workHomeDir}/git/download", "# git代码文件夹目录", "#"),//
    CONFIG_DETAILS_78(ConfigMain.GITLAB, 78, "home.gitlab.gitlaburl", "=", "http://192.168.150.61:2080/", "# gitlab的地址", "#"),//
    CONFIG_DETAILS_79(ConfigMain.GITLAB, 79, "home.gitlab.token", "=", "", "# gitlab管理token", "#"),//
    CONFIG_DETAILS_80(ConfigMain.GITLAB, 80, "home.gitlab.username", "=", "wangxuhui", "# gitlab用户名", "#"),//
    CONFIG_DETAILS_81(ConfigMain.GITLAB, 81, "home.gitlab.password", "=", "", "# gitlab密码", "#"),//
    CONFIG_DETAILS_82(ConfigMain.GITLAB, 82, "home.gitlab.branch", "=", "master", "# gitlab checout default branch", "#"),//
    CONFIG_DETAILS_83(ConfigMain.GITLAB, 83, "home.gitlab.log", "=", "${workHomeDir}/git/log/log.txt", "# gitlab log", "#"),//
    CONFIG_DETAILS_84(ConfigMain.SYSTEMCONFIG, 84, "system.servlet.port", "=", "8080", "# 系统容器rest端口号", ""),//
    CONFIG_DETAILS_85(ConfigMain.SYSTEMCONFIG, 85, "system.servlet.multipart.max-file-size", "=", "-1", "# 不限制文件大小", ""),//
    CONFIG_DETAILS_86(ConfigMain.SYSTEMCONFIG, 86, "system.servlet.multipart.max-request-size", "=", "-1", "# 不限制请求体大小", ""),//
    CONFIG_DETAILS_87(ConfigMain.SYSTEMCONFIG, 87, "system.servlet.multipart.location", "=", "${workHomeDir}/temp/001", "# 临时IO目录", "#"),//
    CONFIG_DETAILS_88(ConfigMain.SYSTEMCONFIG, 88, "system.servlet.multipart.file-size-threshold", "=", "1", "# 超过1Mb，就IO到临时目录", ""),//
    CONFIG_DETAILS_89(ConfigMain.SYSTEMCONFIG, 89, "system.inputparamab.class", "=", "com.guoshiyao.rely.outgoing.InputParamRe", "# 默认入参处理函数", "#"),//
    CONFIG_DETAILS_90(ConfigMain.SYSTEMEXTEND, 90, "home.extend.readme", "=", "示例值", "# 示例扩展属性", ""),//

    ;

    private ConfigMain codeType;
    private Integer sort_id;
    private String key;
    private String m;
    private String value;
    private String mark;
    private String suff;

    ConfigDetails(ConfigMain codeType, Integer sort_id, String key, String m, String value, String mark, String suff) {
        this.codeType = codeType;
        this.sort_id = sort_id;
        this.key = key;
        this.m = m;
        this.value = value;
        this.mark = mark;
        this.suff = suff;
    }

    public ConfigMain getCodeType() {
        return codeType;
    }

    public void setCodeType(ConfigMain codeType) {
        this.codeType = codeType;
    }

    public Integer getSort_id() {
        return sort_id;
    }

    public void setSort_id(Integer sort_id) {
        this.sort_id = sort_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSuff() {
        return suff;
    }

    public void setSuff(String suff) {
        this.suff = suff;
    }
}
