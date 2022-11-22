package com.guoshiyao.rely.core.configration.bean;

public enum ConfigDetails {


    明细配置11(ConfigMainType.redis, 1, "home.redis.host", "=", "192.168.3.251", "# 使用方法为 com.guoshiyao.rely.line.Line.redisds.getjedis()# 地址，默认localhost", "#"),//
    明细配置12(ConfigMainType.redis, 2, "home.redis.port", "=", "6379", "# 端口号", "#"),//
    明细配置13(ConfigMainType.redis, 3, "home.redis.password", "=", "123456", "# 密码", "#"),//
    明细配置14(ConfigMainType.redis, 4, "home.redis.masterName", "=", "mymaster", "# masterName", "#"),//
    明细配置15(ConfigMainType.redis, 5, "home.redis.BlockWhenExhausted", "=", "true", "# 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true", "#"),//
    明细配置16(ConfigMainType.redis, 6, "home.redis.evictionPolicyClassName", "=", "org.apache.commons.pool2.impl.DefaultEvictionPolicy", "# 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)", "#"),//
    明细配置17(ConfigMainType.redis, 7, "home.redis.jmxEnabled", "=", "true", "# 是否启用pool的jmx管理功能, 默认true", "#"),//
    明细配置18(ConfigMainType.redis, 8, "home.redis.lifo", "=", "true", "# 是否启用后进先出, 默认true", "#"),//
    明细配置19(ConfigMainType.redis, 9, "home.redis.maxIdle", "=", "8", "# 最大空闲连接数, 默认8个", "#"),//
    明细配置110(ConfigMainType.redis, 10, "home.redis.minIdle", "=", "1", "# 最小空闲连接数, 默认0", "#"),//
    明细配置111(ConfigMainType.redis, 11, "home.redis.maxTotal", "=", "8", "# 最大连接数, 默认8个", "#"),//
    明细配置112(ConfigMainType.redis, 12, "home.redis.maxWaitMillis", "=", "-1", "# 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", "#"),//
    明细配置113(ConfigMainType.redis, 13, "home.redis.minEvictableIdleTimeMillis", "=", "1800000", "# 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)", "#"),//
    明细配置114(ConfigMainType.redis, 14, "home.redis.numTestsPerEvictionRun", "=", "3", "# 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3", "#"),//
    明细配置115(ConfigMainType.redis, 15, "home.redis.SoftMinEvictableIdleTimeMillis", "=", "1800000", "# 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)", "#"),//
    明细配置116(ConfigMainType.redis, 16, "home.redis.testOnBorrow", "=", "true", "# 在获取连接的时候检查有效性, 默认false", "#"),//
    明细配置117(ConfigMainType.redis, 17, "home.redis.testWhileIdle", "=", "true", "# 在空闲时检查有效性, 默认false", "#"),//
    明细配置118(ConfigMainType.redis, 18, "home.redis.timeBetweenEvictionRunsMillis", "=", "-1", "# 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", "#"),//
    明细配置119(ConfigMainType.logback, 19, "home.log.appid", "=", "${idKey}", "# 默认为idkey debug", ""),//
    明细配置120(ConfigMainType.logback, 20, "home.log.rootlevel", "=", "info", "# 根日志级别 debug info  warn  error trance 默认debug# 从高到地低 OFF 、 FATAL 、 ERROR 、 WARN 、 INFO 、 DEBUG 、 TRACE 、 ALL -->", ""),//
    明细配置121(ConfigMainType.logback, 21, "home.log.syslevel", "=", "info", "# 系统日志级别 默认debug", ""),//
    明细配置122(ConfigMainType.logback, 22, "home.log.businlevel", "=", "info", "# 业务日志级别 默认debug", ""),//
    明细配置123(ConfigMainType.logback, 23, "home.log.businpackage", "=", "${projectPackage}", "# 业务日志包路径,空的话默认为系统扫描到的包路径", ""),//
    明细配置124(ConfigMainType.logback, 24, "home.log.dir", "=", "target/log/${idKey}", "# 日志存储位置", ""),//
    明细配置125(ConfigMainType.mybatis, 25, "home.mybatis.basepackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    明细配置126(ConfigMainType.mybatis, 26, "home.mybatis.typealiasespackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    明细配置127(ConfigMainType.mybatis, 27, "home.mybatis.mapperlocations", "=", "classpath:**/mapper/**.xml", "# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹", "#"),//
    明细配置128(ConfigMainType.dubbo, 28, "home.dubbo.reference.package", "=", "${projectPackage}.dubboservice", "# dubbo默认扫描包:${projectPackage}.dubboservice", "#"),//
    明细配置129(ConfigMainType.dubbo, 29, "home.dubbo.reference.timeout", "=", "120000", "# dubbo连接超时时间", "#"),//
    明细配置130(ConfigMainType.dubbo, 30, "home.dubbo.reference.url", "=", "zookeeper://zookeeper.com:2181", "# dubbo注册中心地址", "#"),//
    明细配置131(ConfigMainType.dubbo, 31, "home.dubbo.reference.agreement", "=", "dubbo", "# dubbo协议", "#"),//
    明细配置132(ConfigMainType.dubbo, 32, "home.dubbo.reference.host", "=", "127.0.0.1", "# dubbo本地地址", "#"),//
    明细配置133(ConfigMainType.dubbo, 33, "home.dubbo.reference.port", "=", "20880", "# dubbo本地暴露端口", "#"),//
    明细配置134(ConfigMainType.dubbo, 34, "home.dubbo.protocolconfig.threads", "=", "300", "# dubbo 线程数", "#"),//
    明细配置135(ConfigMainType.dubbo, 35, "home.dubbo.protocolconfig.threadpool", "=", "fixed", "# #cached limited fixed all", "#"),//
    //    明细配置136(ConfigMainType.META - INF / app, 36, "app.id", "=", "${idKey}", "# 服务ID", "#"),//
//    明细配置137(ConfigMainType.META - INF / app, 37, "apollo.meta", "=", "http://106.54.227.205:8080", "# 阿波罗服务器地址", "#"),//
//    明细配置138(ConfigMainType.META - INF / app, 38, "apollo.env", "=", "${env}", "# dev,uat,pro 可以通过系统env传入,注释后自动通过传入变量决定,", "#"),//
    明细配置139(ConfigMainType.minio, 39, "home.minio.endpoint", "=", "http://localhost:9000", "# api 请求地址 必填", "#"),//
    明细配置140(ConfigMainType.minio, 40, "home.minio.accesskey", "=", "SDS*DSJDlerw9(were)", "# api 请求用户名", "#"),//
    明细配置141(ConfigMainType.minio, 41, "home.minio.secretKey", "=", "SDS*DSJDlerdsfsdf", "# api 请求密钥", "#"),//
    明细配置142(ConfigMainType.minio, 42, "home.minio.namespace_re", "=", "${idKey}", "# 桶 命名空间,必填", "#"),//
    明细配置143(ConfigMainType.minio, 43, "home.minio.deom", "=", "demop", "# 测试增加数据", "#"),//
    明细配置144(ConfigMainType.swagger, 44, "home.swagger.basepackage", "=", "${projectPackage}.api", "# swagger 默认扫描 api路径为启动类所在目录下的 api目录", "#"),//
    明细配置145(ConfigMainType.swagger, 45, "home.swagger.name", "=", "SwaggeAPI", "# swagger命名,暂时命名为SwaggerAPI", "#"),//
    明细配置146(ConfigMainType.db, 46, "home.db.url", "=", "jdbc:mysql://localhost:3306/home?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8", "# 数据库连接地址", "#"),//
    明细配置147(ConfigMainType.db, 47, "home.db.username", "=", "root", "# 用户名", "#"),//
    明细配置148(ConfigMainType.db, 48, "home.db.password", "=", "password", "# 密码", "#"),//
    明细配置149(ConfigMainType.db, 49, "home.db.driverclassname", "=", "com.mysql.cj.jdbc.Driver", "# 驱动UrlClass", "#"),//
    明细配置150(ConfigMainType.db, 50, "home.db.driverclass.downloadurl", "=", "https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.49/mysql-connector-java-5.1.49.jar", "#驱动下载地址,使用 url 后可以不填写home.db.driverclassname,MAVEN POM 中无需加载驱动坐标,优先级低于home.db.driverclassname", "#"),//
    明细配置151(ConfigMainType.db, 51, "home.db.validationquery", "=", "select 'x'", "# 数据库校验sql", "#"),//
    明细配置152(ConfigMainType.db, 52, "home.db.maxactive", "=", "30", "", "#"),//
    明细配置153(ConfigMainType.db, 53, "home.db.initialsize", "=", "1", "", "#"),//
    明细配置154(ConfigMainType.db, 54, "home.db.maxwait", "=", "60000", "", "#"),//
    明细配置155(ConfigMainType.db, 55, "home.db.minidle", "=", "3", "", "#"),//
    明细配置156(ConfigMainType.db, 56, "home.db.timebetweenevictionrunsmillis", "=", "60000", "", "#"),//
    明细配置157(ConfigMainType.db, 57, "home.db.minevictableidletimemillis", "=", "300000", "", "#"),//
    明细配置158(ConfigMainType.db, 58, "home.db.testwhileidle", "=", "true", "", "#"),//
    明细配置159(ConfigMainType.db, 59, "home.db.testonborrow", "=", "false", "", "#"),//
    明细配置160(ConfigMainType.db, 60, "home.db.testonreturn", "=", "false", "", "#"),//
    明细配置161(ConfigMainType.db, 61, "home.db.poolpreparedstatements", "=", "true", "", "#"),//
    明细配置162(ConfigMainType.db, 62, "home.db.removeabandoned", "=", "true", "", "#"),//
    明细配置163(ConfigMainType.db, 63, "home.db.removeabandonedtimeout", "=", "60", "", "#"),//
    明细配置164(ConfigMainType.db, 64, "home.db.numtestsperevictionrun", "=", "60", "", "#"),//
    明细配置165(ConfigMainType.db, 65, "home.db.maxopenpreparedstatements", "=", "20", "", "#"),//
    明细配置166(ConfigMainType.db, 66, "home.db.filters", "=", "stat,wall", "", "#"),//
    明细配置167(ConfigMainType.flyway, 67, "home.flywaydb.url", "=", "", "# flyway jdbc连接地址,如果为空取数据库连接地址", "#"),//
    明细配置168(ConfigMainType.flyway, 68, "home.flywaydb.username", "=", "", "# flyway 如果为空,取数据库用户名", "#"),//
    明细配置169(ConfigMainType.flyway, 69, "home.flywaydb.password", "=", "", "# flyway 如果为空,去数据库密码", "#"),//
    明细配置170(ConfigMainType.flyway, 70, "home.flywaydb.table", "=", "flyway_schema_history", "# flyway 版本管理表", "#"),//
    明细配置171(ConfigMainType.apollo, 71, "home.apollo.url", "=", "阿波罗管理地址,如果不填写则阿波罗不加载", "# 管理端", "#"),//
    明细配置172(ConfigMainType.apollo, 72, "home.apollo.username", "=", "阿波罗管理用户名", "", "#"),//
    明细配置173(ConfigMainType.apollo, 73, "home.apollo.password", "=", "阿波罗管理密码", "", "#"),//
    明细配置174(ConfigMainType.apollo, 74, "home.apollo.app.id", "=", "${idKey}", "# 客户端", "#"),//
    明细配置175(ConfigMainType.apollo, 75, "home.apollo.apollo.meta", "=", "http://106.54.227.205:8080", "# 地址后缀不允许以/结尾", "#"),//
    明细配置176(ConfigMainType.apollo, 76, "home.apollo.apollo.env", "=", "${env}", "# dev,uat,pro 可以通过系统env传入,注释后自动通过传入变量决定,这个建议不传,系统自动识别", "#"),//
    明细配置177(ConfigMainType.gitlab, 77, "home.gitlab.gitdir", "=", "${workHomeDir}/git/download", "# git代码文件夹目录", "#"),//
    明细配置178(ConfigMainType.gitlab, 78, "home.gitlab.gitlaburl", "=", "http://192.168.150.61:2080/", "# gitlab的地址", "#"),//
    明细配置179(ConfigMainType.gitlab, 79, "home.gitlab.token", "=", "", "# gitlab管理token", "#"),//
    明细配置180(ConfigMainType.gitlab, 80, "home.gitlab.username", "=", "wangxuhui", "# gitlab用户名", "#"),//
    明细配置181(ConfigMainType.gitlab, 81, "home.gitlab.password", "=", "", "# gitlab密码", "#"),//
    明细配置182(ConfigMainType.gitlab, 82, "home.gitlab.branch", "=", "master", "# gitlab checout default branch", "#"),//
    明细配置183(ConfigMainType.gitlab, 83, "home.gitlab.log", "=", "${workHomeDir}/git/log/log.txt", "# gitlab log", "#"),//
    明细配置184(ConfigMainType.systemconfig, 84, "system.servlet.port", "=", "8080", "# 系统容器rest端口号", ""),//
    明细配置185(ConfigMainType.systemconfig, 85, "system.servlet.multipart.max-file-size", "=", "-1", "# 不限制文件大小", ""),//
    明细配置186(ConfigMainType.systemconfig, 86, "system.servlet.multipart.max-request-size", "=", "-1", "# 不限制请求体大小", ""),//
    明细配置187(ConfigMainType.systemconfig, 87, "system.servlet.multipart.location", "=", "${workHomeDir}/temp/001", "# 临时IO目录", "#"),//
    明细配置188(ConfigMainType.systemconfig, 88, "system.servlet.multipart.file-size-threshold", "=", "1", "# 超过1Mb，就IO到临时目录", ""),//
    明细配置189(ConfigMainType.systemconfig, 89, "system.inputparamab.class", "=", "com.guoshiyao.rely.outgoing.InputParamRe", "# 默认入参处理函数", "#"),//
    明细配置190(ConfigMainType.systemextend, 90, "home.extend.readme", "=", "示例值", "# 示例扩展属性", ""),//

    ;

    private ConfigMainType codeType;
    private Integer sort_id;
    private String key;
    private String m;
    private String value;
    private String mark;
    private String suff;

    ConfigDetails(ConfigMainType codeType, Integer sort_id, String key, String m, String value, String mark, String suff) {
        this.codeType = codeType;
        this.sort_id = sort_id;
        this.key = key;
        this.m = m;
        this.value = value;
        this.mark = mark;
        this.suff = suff;
    }

    public ConfigMainType getCodeType() {
        return codeType;
    }

    public void setCodeType(ConfigMainType codeType) {
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
