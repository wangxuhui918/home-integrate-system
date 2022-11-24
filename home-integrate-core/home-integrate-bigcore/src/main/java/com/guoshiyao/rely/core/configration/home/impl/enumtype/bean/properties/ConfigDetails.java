package com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties;

import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.utils.velocity.VelocityUtils;
import org.apache.poi.ss.formula.functions.T;

public enum ConfigDetails {


    HOME_REDIS_HOST(ConfigMain.REDIS, 1, "home.redis.host", "=", "192.168.3.251", "# 使用方法为 com.guoshiyao.rely.line.Line.redisds.getjedis()# 地址，默认localhost", "#"),//
    HOME_REDIS_PORT(ConfigMain.REDIS, 2, "home.redis.port", "=", "6379", "# 端口号", "#"),//
    HOME_REDIS_PASSWORD(ConfigMain.REDIS, 3, "home.redis.password", "=", "123456", "# 密码", "#"),//
    HOME_REDIS_MASTERNAME(ConfigMain.REDIS, 4, "home.redis.masterName", "=", "mymaster", "# masterName", "#"),//
    HOME_REDIS_BLOCKWHENEXHAUSTED(ConfigMain.REDIS, 5, "home.redis.BlockWhenExhausted", "=", "true", "# 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true", "#"),//
    HOME_REDIS_EVICTIONPOLICYCLASSNAME(ConfigMain.REDIS, 6, "home.redis.evictionPolicyClassName", "=", "org.apache.commons.pool2.impl.DefaultEvictionPolicy", "# 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)", "#"),//
    HOME_REDIS_JMXENABLED(ConfigMain.REDIS, 7, "home.redis.jmxEnabled", "=", "true", "# 是否启用pool的jmx管理功能, 默认true", "#"),//
    HOME_REDIS_LIFO(ConfigMain.REDIS, 8, "home.redis.lifo", "=", "true", "# 是否启用后进先出, 默认true", "#"),//
    HOME_REDIS_MAXIDLE(ConfigMain.REDIS, 9, "home.redis.maxIdle", "=", "8", "# 最大空闲连接数, 默认8个", "#"),//
    HOME_REDIS_MINIDLE(ConfigMain.REDIS, 10, "home.redis.minIdle", "=", "1", "# 最小空闲连接数, 默认0", "#"),//
    HOME_REDIS_MAXTOTAL(ConfigMain.REDIS, 11, "home.redis.maxTotal", "=", "8", "# 最大连接数, 默认8个", "#"),//
    HOME_REDIS_MAXWAITMILLIS(ConfigMain.REDIS, 12, "home.redis.maxWaitMillis", "=", "-1", "# 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", "#"),//
    HOME_REDIS_MINEVICTABLEIDLETIMEMILLIS(ConfigMain.REDIS, 13, "home.redis.minEvictableIdleTimeMillis", "=", "1800000", "# 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)", "#"),//
    HOME_REDIS_NUMTESTSPEREVICTIONRUN(ConfigMain.REDIS, 14, "home.redis.numTestsPerEvictionRun", "=", "3", "# 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3", "#"),//
    HOME_REDIS_SOFTMINEVICTABLEIDLETIMEMILLIS(ConfigMain.REDIS, 15, "home.redis.SoftMinEvictableIdleTimeMillis", "=", "1800000", "# 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)", "#"),//
    HOME_REDIS_TESTONBORROW(ConfigMain.REDIS, 16, "home.redis.testOnBorrow", "=", "true", "# 在获取连接的时候检查有效性, 默认false", "#"),//
    HOME_REDIS_TESTWHILEIDLE(ConfigMain.REDIS, 17, "home.redis.testWhileIdle", "=", "true", "# 在空闲时检查有效性, 默认false", "#"),//
    HOME_REDIS_TIMEBETWEENEVICTIONRUNSMILLIS(ConfigMain.REDIS, 18, "home.redis.timeBetweenEvictionRunsMillis", "=", "-1", "# 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", "#"),//
    HOME_LOG_APPID(ConfigMain.LOGBACK, 19, "home.log.appid", "=", "${idKey}", "# 默认为idkey debug", ""),//
    HOME_LOG_ROOTLEVEL(ConfigMain.LOGBACK, 20, "home.log.rootlevel", "=", "info", "# 根日志级别 debug info  warn  error trance 默认debug# 从高到地低 OFF 、 FATAL 、 ERROR 、 WARN 、 INFO 、 DEBUG 、 TRACE 、 ALL -->", ""),//
    HOME_LOG_SYSLEVEL(ConfigMain.LOGBACK, 21, "home.log.syslevel", "=", "info", "# 系统日志级别 默认debug", ""),//
    HOME_LOG_BUSINLEVEL(ConfigMain.LOGBACK, 22, "home.log.businlevel", "=", "info", "# 业务日志级别 默认debug", ""),//
    HOME_LOG_BUSINPACKAGE(ConfigMain.LOGBACK, 23, "home.log.businpackage", "=", "${projectPackage}", "# 业务日志包路径,空的话默认为系统扫描到的包路径", ""),//
    HOME_LOG_DIR(ConfigMain.LOGBACK, 24, "home.log.dir", "=", "target/log/${idKey}", "# 日志存储位置", ""),//
    HOME_MYBATIS_BASEPACKAGE(ConfigMain.MYBATIS, 25, "home.mybatis.basepackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    HOME_MYBATIS_TYPEALIASESPACKAGE(ConfigMain.MYBATIS, 26, "home.mybatis.typealiasespackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    HOME_MYBATIS_MAPPERLOCATIONS(ConfigMain.MYBATIS, 27, "home.mybatis.mapperlocations", "=", "classpath:**/mapper/**.xml", "# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹", "#"),//
    HOME_MYBATISPLUS_BASEPACKAGE(ConfigMain.MYBATIS_PLUS, 25, "home.mybatisplus.basepackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    HOME_MYBATISPLUS_TYPEALIASESPACKAGE(ConfigMain.MYBATIS_PLUS, 26, "home.mybatisplus.typealiasespackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    HOME_MYBATISPLUS_MAPPERLOCATIONS(ConfigMain.MYBATIS_PLUS, 27, "home.mybatisplus.mapperlocations", "=", "classpath:**/mapper/**.xml", "# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹", "#"),//
    HOME_DUBBO_REFERENCE_PACKAGE(ConfigMain.DUBBO, 28, "home.dubbo.reference.package", "=", "${projectPackage}.dubboservice", "# dubbo默认扫描包:${projectPackage}.dubboservice", "#"),//
    HOME_DUBBO_REFERENCE_TIMEOUT(ConfigMain.DUBBO, 29, "home.dubbo.reference.timeout", "=", "120000", "# dubbo连接超时时间", "#"),//
    HOME_DUBBO_REFERENCE_URL(ConfigMain.DUBBO, 30, "home.dubbo.reference.url", "=", "N/A", "# dubbo注册中心地址:zookeeper://zookeeper.com:2181", "#"),//
    HOME_DUBBO_REFERENCE_AGREEMENT(ConfigMain.DUBBO, 31, "home.dubbo.reference.agreement", "=", "dubbo", "# dubbo协议", "#"),//
    HOME_DUBBO_REFERENCE_HOST(ConfigMain.DUBBO, 32, "home.dubbo.reference.host", "=", "", "# dubbo本地地址:127.0.0.1", "#"),//
    HOME_DUBBO_REFERENCE_PORT(ConfigMain.DUBBO, 33, "home.dubbo.reference.port", "=", "20880", "# dubbo本地暴露端口", "#"),//
    HOME_DUBBO_PROTOCOLCONFIG_THREADS(ConfigMain.DUBBO, 34, "home.dubbo.protocolconfig.threads", "=", "300", "# dubbo 线程数", "#"),//
    HOME_DUBBO_PROTOCOLCONFIG_THREADPOOL(ConfigMain.DUBBO, 35, "home.dubbo.protocolconfig.threadpool", "=", "fixed", "# #cached limited fixed all", "#"),//
    HOME_MINIO_ENDPOINT(ConfigMain.MINIO, 39, "home.minio.endpoint", "=", "", "# api 请求地址 必填:http://localhost:9000", "#"),//
    HOME_MINIO_ACCESSKEY(ConfigMain.MINIO, 40, "home.minio.accesskey", "=", "", "# api 请求用户名:SDS*DSJDlerw9(were)", "#"),//
    HOME_MINIO_SECRETKEY(ConfigMain.MINIO, 41, "home.minio.secretKey", "=", "", "# api 请求密钥:SDS*DSJDlerdsfsdf", "#"),//
    HOME_MINIO_NAMESPACE_RE(ConfigMain.MINIO, 42, "home.minio.namespace_re", "=", "${idKey}", "# 桶 命名空间,必填", "#"),//
    HOME_MINIO_DEOM(ConfigMain.MINIO, 43, "home.minio.deom", "=", "demop", "# 测试增加数据", "#"),//
    HOME_SWAGGER_BASEPACKAGE(ConfigMain.SWAGGER, 44, "home.swagger.basepackage", "=", "${projectPackage}.api", "# swagger 默认扫描 api路径为启动类所在目录下的 api目录", "#"),//
    HOME_SWAGGER_NAME(ConfigMain.SWAGGER, 45, "home.swagger.name", "=", "SwaggeAPI", "# swagger命名,暂时命名为SwaggerAPI", "#"),//
    HOME_DB_URL(ConfigMain.DB, 46, "home.db.url", "=", "", "# 数据库连接地址:jdbc:mysql://localhost:3306/home?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8", "#"),//
    HOME_DB_USERNAME(ConfigMain.DB, 47, "home.db.username", "=", "", "# 用户名:root", "#"),//
    HOME_DB_PASSWORD(ConfigMain.DB, 48, "home.db.password", "=", "", "# 密码:password", "#"),//
    HOME_DB_DRIVERCLASSNAME(ConfigMain.DB, 49, "home.db.driverclassname", "=", "", "# 驱动UrlClass;com.mysql.cj.jdbc.Driver", "#"),//
    HOME_DB_DRIVERCLASS_DOWNLOADURL(ConfigMain.DB, 50, "home.db.driverclass.downloadurl", "=", "", "#驱动下载地址,使用 url 后可以不填写home.db.driverclassname,MAVEN POM 中无需加载驱动坐标,优先级低于home.db.driverclassname :https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.49/mysql-connector-java-5.1.49.jar", "#"),//
    HOME_DB_VALIDATIONQUERY(ConfigMain.DB, 51, "home.db.validationquery", "=", "", "# 数据库校验sql:select 'x'", "#"),//
    HOME_DB_MAXACTIVE(ConfigMain.DB, 52, "home.db.maxactive", "=", "30", "", "#"),//
    HOME_DB_INITIALSIZE(ConfigMain.DB, 53, "home.db.initialsize", "=", "1", "", "#"),//
    HOME_DB_MAXWAIT(ConfigMain.DB, 54, "home.db.maxwait", "=", "60000", "", "#"),//
    HOME_DB_MINIDLE(ConfigMain.DB, 55, "home.db.minidle", "=", "3", "", "#"),//
    HOME_DB_TIMEBETWEENEVICTIONRUNSMILLIS(ConfigMain.DB, 56, "home.db.timebetweenevictionrunsmillis", "=", "60000", "", "#"),//
    HOME_DB_MINEVICTABLEIDLETIMEMILLIS(ConfigMain.DB, 57, "home.db.minevictableidletimemillis", "=", "300000", "", "#"),//
    HOME_DB_TESTWHILEIDLE(ConfigMain.DB, 58, "home.db.testwhileidle", "=", "true", "", "#"),//
    HOME_DB_TESTONBORROW(ConfigMain.DB, 59, "home.db.testonborrow", "=", "false", "", "#"),//
    HOME_DB_TESTONRETURN(ConfigMain.DB, 60, "home.db.testonreturn", "=", "false", "", "#"),//
    HOME_DB_POOLPREPAREDSTATEMENTS(ConfigMain.DB, 61, "home.db.poolpreparedstatements", "=", "true", "", "#"),//
    HOME_DB_REMOVEABANDONED(ConfigMain.DB, 62, "home.db.removeabandoned", "=", "true", "", "#"),//
    HOME_DB_REMOVEABANDONEDTIMEOUT(ConfigMain.DB, 63, "home.db.removeabandonedtimeout", "=", "60", "", "#"),//
    HOME_DB_NUMTESTSPEREVICTIONRUN(ConfigMain.DB, 64, "home.db.numtestsperevictionrun", "=", "60", "", "#"),//
    HOME_DB_MAXOPENPREPAREDSTATEMENTS(ConfigMain.DB, 65, "home.db.maxopenpreparedstatements", "=", "20", "", "#"),//
    HOME_DB_FILTERS(ConfigMain.DB, 66, "home.db.filters", "=", "stat,wall", "", "#"),//
    HOME_FLYWAYDB_URL(ConfigMain.FLYWAY, 67, "home.flywaydb.url", "=", "", "# flyway jdbc连接地址,如果为空取数据库连接地址", "#"),//
    HOME_FLYWAYDB_USERNAME(ConfigMain.FLYWAY, 68, "home.flywaydb.username", "=", "", "# flyway 如果为空,取数据库用户名", "#"),//
    HOME_FLYWAYDB_PASSWORD(ConfigMain.FLYWAY, 69, "home.flywaydb.password", "=", "", "# flyway 如果为空,去数据库密码", "#"),//
    HOME_FLYWAYDB_TABLE(ConfigMain.FLYWAY, 70, "home.flywaydb.table", "=", "flyway_schema_history", "# flyway 版本管理表", "#"),//
    HOME_APOLLO_URL(ConfigMain.APOLLO, 71, "home.apollo.url", "=", "", "# 管理端阿波罗管理地址,如果不填写则阿波罗不加载", "#"),//
    HOME_APOLLO_USERNAME(ConfigMain.APOLLO, 72, "home.apollo.username", "=", "", "# 阿波罗管理用户名 apollo", "#"),//
    HOME_APOLLO_PASSWORD(ConfigMain.APOLLO, 73, "home.apollo.password", "=", "", "# 阿波罗管理密码 apollo", "#"),//
    HOME_APOLLO_APP_ID(ConfigMain.APOLLO, 74, "home.apollo.app.id", "=", "${idKey}", "# 客户端", "#"),//
    HOME_APOLLO_APOLLO_META(ConfigMain.APOLLO, 75, "home.apollo.apollo.meta", "=", "", "# 地址后缀不允许以/结尾:http://106.54.227.205:8080", "#"),//
    HOME_APOLLO_APOLLO_ENV(ConfigMain.APOLLO, 76, "home.apollo.apollo.env", "=", "${env}", "# dev,uat,pro 可以通过系统env传入,注释后自动通过传入变量决定,这个建议不传,系统自动识别", "#"),//
    HOME_GITLAB_GITDIR(ConfigMain.GITLAB, 77, "home.gitlab.gitdir", "=", "${workHomeDir}/git/download", "# git代码文件夹目录", "#"),//
    HOME_GITLAB_GITLABURL(ConfigMain.GITLAB, 78, "home.gitlab.gitlaburl", "=", "http://192.168.150.61:2080/", "# gitlab的地址", "#"),//
    HOME_GITLAB_TOKEN(ConfigMain.GITLAB, 79, "home.gitlab.token", "=", "", "# gitlab管理token", "#"),//
    HOME_GITLAB_USERNAME(ConfigMain.GITLAB, 80, "home.gitlab.username", "=", "wangxuhui", "# gitlab用户名", "#"),//
    HOME_GITLAB_PASSWORD(ConfigMain.GITLAB, 81, "home.gitlab.password", "=", "", "# gitlab密码", "#"),//
    HOME_GITLAB_BRANCH(ConfigMain.GITLAB, 82, "home.gitlab.branch", "=", "master", "# gitlab checout default branch", "#"),//
    HOME_GITLAB_LOG(ConfigMain.GITLAB, 83, "home.gitlab.log", "=", "${workHomeDir}/git/log/log.txt", "# gitlab log", "#"),//
    SYSTEM_SERVLET_PORT(ConfigMain.SYSTEMCONFIG, 84, "system.servlet.port", "=", "8080", "# 系统容器rest端口号", ""),//
    SYSTEM_SERVLET_MULTIPART_MAX_FILE_SIZE(ConfigMain.SYSTEMCONFIG, 85, "system.servlet.multipart.max-file-size", "=", "-1", "# 不限制文件大小", ""),//
    SYSTEM_SERVLET_MULTIPART_MAX_REQUEST_SIZE(ConfigMain.SYSTEMCONFIG, 86, "system.servlet.multipart.max-request-size", "=", "-1", "# 不限制请求体大小", ""),//
    SYSTEM_SERVLET_MULTIPART_LOCATION(ConfigMain.SYSTEMCONFIG, 87, "system.servlet.multipart.location", "=", "${workHomeDir}/temp/001", "# 临时IO目录", "#"),//
    SYSTEM_SERVLET_MULTIPART_FILE_SIZE_THRESHOLD(ConfigMain.SYSTEMCONFIG, 88, "system.servlet.multipart.file-size-threshold", "=", "1", "# 超过1Mb，就IO到临时目录", ""),//
    SYSTEM_INPUTPARAMAB_CLASS(ConfigMain.SYSTEMCONFIG, 89, "system.inputparamab.class", "=", "com.guoshiyao.rely.outgoing.InputParamRe", "# 默认入参处理函数", "#"),//
    HOME_EXTEND_README(ConfigMain.SYSTEMEXTEND, 90, "home.extend.readme", "=", "示例值", "# 示例扩展属性", ""),//


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


    public T getSeetingValue(Class<T> classs) {
        return (T) BaseEv.SettingInformation.setting.getObj(this.getKey());
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
        return VelocityUtils.convert(value, BaseEv.SettingInformation.context);
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
