package cn.bigcore.micro.config.config.impl.bean;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.utils.velocity.FyyVelocityUtils;
import org.apache.poi.ss.formula.functions.T;

/**
 * 配置条目明细值
 */
public enum FyyConfigEntryDetailsValues {

    APOLLO_BOOTSTRAP_ENABLED(FyyConfigEntryValues.APOLLO, "apollo.bootstrap.enabled", "=", "false", "# 开关", ""),//
    APOLLO_BOOTSTRAP_EAGERLOAD_ENABLED(FyyConfigEntryValues.APOLLO, "apollo.bootstrap.eagerload.enabled", "=", "false", "# 开关", ""),//
    HOME_APOLLO_APP_ID(FyyConfigEntryValues.APOLLO, "app.id", "=", "${idKey}", "# 阿波罗APPID", ""),//
    HOME_APOLLO_APOLLO_META(FyyConfigEntryValues.APOLLO, "apollo.meta", "=", "", "# 阿波罗配置地址", ""),//
    //    HOME_APOLLO_APOLLO_ENV(ConfigMain.APOLLO, "apollo.apollo.env", "=", "${env}", ConfigInitValue.suffp + " dev,uat,pro 可以通过系统env传入,注释后自动通过传入变量决定,这个建议不传,系统自动识别", ""),//
    //
    //
    //
    DUBBO_ENABLED(FyyConfigEntryValues.APOLLO, "dubbo.enabled", "=", "false", "# 开关", ""),//
    HOME_DUBBO_REFERENCE_PACKAGE(FyyConfigEntryValues.DUBBO, "dubbo.scan.basePackages", "=", "${projectPackage}.dubboservice", "# dubbo默认扫描包:${projectPackage}.dubboservice", ""),//
    HOME_DUBBO_REFERENCE_TIMEOUT(FyyConfigEntryValues.DUBBO, "dubbo.reference.timeout", "=", "120000", "# dubbo连接超时时间", ""),//
    HOME_DUBBO_REFERENCE_URL(FyyConfigEntryValues.DUBBO, "dubbo.registry.address", "=", "zookeeper://zookeeper.com:2181", "# dubbo注册中心地址 N/A", ""),//
    HOME_DUBBO_REFERENCE_AGREEMENT(FyyConfigEntryValues.DUBBO, "dubbo.protocol.name", "=", "dubbo", "# dubbo协议", ""),//
    HOME_DUBBO_REFERENCE_HOST(FyyConfigEntryValues.DUBBO, "dubbo.reference.host", "=", "127.0.0.1", "# dubbo本地地址:127.0.0.1", ""),//
    HOME_DUBBO_REFERENCE_PORT(FyyConfigEntryValues.DUBBO, "dubbo.protocol.port", "=", "20880", "# dubbo本地暴露端口", ""),//
    HOME_DUBBO_PROTOCOLCONFIG_THREADS(FyyConfigEntryValues.DUBBO, "dubbo.protocolconfig.threads", "=", "300", "# dubbo线程数", ""),//
    HOME_DUBBO_PROTOCOLCONFIG_THREADPOOL(FyyConfigEntryValues.DUBBO, "dubbo.protocolconfig.threadpool", "=", "fixed", "# #cached limited fixed all", ""),//
    //
    //
    //
    DB_ENABLE(FyyConfigEntryValues.DB, "db.enable", "=", "false", "# 开关", ""),//
    HOME_DB_URL(FyyConfigEntryValues.DB, "db.url", "=", "jdbc:mysql://localhost:3306/home?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8", "# 数据库连接地址", ""),//
    HOME_DB_USERNAME(FyyConfigEntryValues.DB, "db.username", "=", "", "# 用户名", ""),//
    HOME_DB_PASSWORD(FyyConfigEntryValues.DB, "db.password", "=", "", "# 密码", ""),//
    HOME_DB_DRIVERCLASSNAME(FyyConfigEntryValues.DB, "db.driverclassname", "=", "com.mysql.cj.jdbc.Driver", "# DriverClass", ""),//
    HOME_DB_VALIDATIONQUERY(FyyConfigEntryValues.DB, "db.validationquery", "=", "select 'x'", "# 数据库校验", ""),//
    HOME_DB_MAXACTIVE(FyyConfigEntryValues.DB, "db.maxactive", "=", "30", "", ""),//
    HOME_DB_INITIALSIZE(FyyConfigEntryValues.DB, "db.initialsize", "=", "1", "", ""),//
    HOME_DB_MAXWAIT(FyyConfigEntryValues.DB, "db.maxwait", "=", "60000", "", ""),//
    HOME_DB_MINIDLE(FyyConfigEntryValues.DB, "db.minidle", "=", "3", "", ""),//
    HOME_DB_TIMEBETWEENEVICTIONRUNSMILLIS(FyyConfigEntryValues.DB, "db.timebetweenevictionrunsmillis", "=", "60000", "", ""),//
    HOME_DB_MINEVICTABLEIDLETIMEMILLIS(FyyConfigEntryValues.DB, "db.minevictableidletimemillis", "=", "300000", "", ""),//
    HOME_DB_TESTWHILEIDLE(FyyConfigEntryValues.DB, "db.testwhileidle", "=", "true", "", ""),//
    HOME_DB_TESTONBORROW(FyyConfigEntryValues.DB, "db.testonborrow", "=", "false", "", ""),//
    HOME_DB_TESTONRETURN(FyyConfigEntryValues.DB, "db.testonreturn", "=", "false", "", ""),//
    HOME_DB_POOLPREPAREDSTATEMENTS(FyyConfigEntryValues.DB, "db.poolpreparedstatements", "=", "true", "", ""),//
    HOME_DB_REMOVEABANDONED(FyyConfigEntryValues.DB, "db.removeabandoned", "=", "true", "", ""),//
    HOME_DB_REMOVEABANDONEDTIMEOUT(FyyConfigEntryValues.DB, "db.removeabandonedtimeout", "=", "60", "", ""),//
    HOME_DB_NUMTESTSPEREVICTIONRUN(FyyConfigEntryValues.DB, "db.numtestsperevictionrun", "=", "60", "", ""),//
    HOME_DB_MAXOPENPREPAREDSTATEMENTS(FyyConfigEntryValues.DB, "db.maxopenpreparedstatements", "=", "20", "", ""),//
    HOME_DB_FILTERS(FyyConfigEntryValues.DB, "db.filters", "=", "stat,wall", "", ""),//
    //
    //
    //
    FLYWAYDB_ENABLE(FyyConfigEntryValues.FLYWAY, "flywaydb.enable", "=", "false", "# 开关", ""),//
    HOME_FLYWAYDB_URL(FyyConfigEntryValues.FLYWAY, "flywaydb.url", "=", "", "# flyway jdbc连接地址,如果为空取数据库连接地址", ""),//
    HOME_FLYWAYDB_USERNAME(FyyConfigEntryValues.FLYWAY, "flywaydb.username", "=", "", "# flyway 如果为空,取数据库用户名", ""),//
    HOME_FLYWAYDB_PASSWORD(FyyConfigEntryValues.FLYWAY, "flywaydb.password", "=", "", "# flyway 如果为空,去数据库密码", ""),//
    HOME_FLYWAYDB_TABLE(FyyConfigEntryValues.FLYWAY, "flywaydb.table", "=", "flyway_schema_history", "# flyway 版本管理表", ""),//
    //
    //
    //
    LOG_ENABLE(FyyConfigEntryValues.LOGBACK, "log.enable", "=", "true", "# 开关", ""),//
    HOME_LOG_APPID(FyyConfigEntryValues.LOGBACK, "log.appid", "=", "${idKey}", "# 默认为idkey debug", ""),//
    HOME_LOG_ROOTLEVEL(FyyConfigEntryValues.LOGBACK, "log.rootlevel", "=", "info", "# 根日志级别 debug info  warn  error trance 默认debug# 从高到地低 OFF 、 FATAL 、 ERROR 、 WARN 、 INFO 、 DEBUG 、 TRACE 、 ALL -->", ""),//
    HOME_LOG_SYSLEVEL(FyyConfigEntryValues.LOGBACK, "log.syslevel", "=", "info", "# 系统日志级别 默认debug", ""),//
    HOME_LOG_BUSINLEVEL(FyyConfigEntryValues.LOGBACK, "log.businlevel", "=", "info", "# 业务日志级别 默认debug", ""),//
    HOME_LOG_BUSINPACKAGE(FyyConfigEntryValues.LOGBACK, "log.businpackage", "=", "${projectPackage}", "# 业务日志包路径,空的话默认为系统扫描到的包路径", ""),//
    HOME_LOG_DIR(FyyConfigEntryValues.LOGBACK, "log.dir", "=", "target/log/${idKey}", "# 日志存储位置", ""),//
    //
    //
    //
    MYBATIS_ENABLE(FyyConfigEntryValues.MYBATIS, "mybatis.enable", "=", "false", "# 开关", ""),//
    HOME_MYBATIS_BASEPACKAGE(FyyConfigEntryValues.MYBATIS, "mybatis.basepackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", ""),//
    HOME_MYBATIS_TYPEALIASESPACKAGE(FyyConfigEntryValues.MYBATIS, "mybatis.typealiasespackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", ""),//
    HOME_MYBATIS_MAPPERLOCATIONS(FyyConfigEntryValues.MYBATIS, "mybatis.mapperlocations", "=", "classpath:**/mapper/**.xml", "# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹", ""),//
    //
    //
    //
    MYBATISPLUS_ENABLE(FyyConfigEntryValues.MYBATIS_PLUS, "mybatisplus.enable", "=", "false", "# 开关", ""),//
    HOME_MYBATISPLUS_BASEPACKAGE(FyyConfigEntryValues.MYBATIS_PLUS, "mybatisplus.basepackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", ""),//
    HOME_MYBATISPLUS_TYPEALIASESPACKAGE(FyyConfigEntryValues.MYBATIS_PLUS, "mybatisplus.typealiasespackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", ""),//
    HOME_MYBATISPLUS_MAPPERLOCATIONS(FyyConfigEntryValues.MYBATIS_PLUS, "mybatisplus.mapperlocations", "=", "classpath:**/mapper/**.xml", "# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹", ""),//
    //
    //
    //
    MINIO_ENABLE(FyyConfigEntryValues.MINIO, "minio.enable", "=", "false", "# 开关", ""),//
    HOME_MINIO_ENDPOINT(FyyConfigEntryValues.MINIO, "minio.endpoint", "=", "", "# api 请求地址 必填:http://localhost:9000", ""),//
    HOME_MINIO_ACCESSKEY(FyyConfigEntryValues.MINIO, "minio.accesskey", "=", "", "# api 请求用户名:SDS*DSJDlerw9(were)", ""),//
    HOME_MINIO_SECRETKEY(FyyConfigEntryValues.MINIO, "minio.secretKey", "=", "", "# api 请求密钥:SDS*DSJDlerdsfsdf", ""),//
    HOME_MINIO_NAMESPACE_RE(FyyConfigEntryValues.MINIO, "minio.namespace_re", "=", "${idKey}", "# 桶 命名空间,必填", ""),//
    HOME_MINIO_DEOM(FyyConfigEntryValues.MINIO, "minio.deom", "=", "demop", "# 测试增加数据", ""),//
    //
    //
    //

    REDIS_ENABLE(FyyConfigEntryValues.REDIS, "redis.enable", "=", "false", "# 开关", ""),//
    HOME_REDIS_HOST(FyyConfigEntryValues.REDIS, "redis.host", "=", "", "# IP地址:192.168.3.251 使用方法:BaseEv.SettingInformation.redisds", ""),//
    HOME_REDIS_PORT(FyyConfigEntryValues.REDIS, "redis.port", "=", "", "# 端口号:6379", ""),//
    HOME_REDIS_PASSWORD(FyyConfigEntryValues.REDIS, "redis.password", "=", "", "# 密码:123456", ""),//
    HOME_REDIS_MASTERNAME(FyyConfigEntryValues.REDIS, "redis.masterName", "=", "", "# 主干:mymaster", ""),//
    HOME_REDIS_BLOCKWHENEXHAUSTED(FyyConfigEntryValues.REDIS, "redis.BlockWhenExhausted", "=", "true", "# 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true", ""),//
    HOME_REDIS_EVICTIONPOLICYCLASSNAME(FyyConfigEntryValues.REDIS, "redis.evictionPolicyClassName", "=", "org.apache.commons.pool2.impl.DefaultEvictionPolicy", "# 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)", ""),//
    HOME_REDIS_JMXENABLED(FyyConfigEntryValues.REDIS, "redis.jmxEnabled", "=", "true", "# 是否启用pool的jmx管理功能, 默认true", ""),//
    HOME_REDIS_LIFO(FyyConfigEntryValues.REDIS, "redis.lifo", "=", "true", "# 是否启用后进先出, 默认true", ""),//
    HOME_REDIS_MAXIDLE(FyyConfigEntryValues.REDIS, "redis.maxIdle", "=", "8", "# 最大空闲连接数, 默认8个", ""),//
    HOME_REDIS_MINIDLE(FyyConfigEntryValues.REDIS, "redis.minIdle", "=", "1", "# 最小空闲连接数, 默认1", ""),//
    HOME_REDIS_MAXTOTAL(FyyConfigEntryValues.REDIS, "redis.maxTotal", "=", "8", "# 最大连接数, 默认8个", ""),//
    HOME_REDIS_MAXWAITMILLIS(FyyConfigEntryValues.REDIS, "redis.maxWaitMillis", "=", "-1", "# 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", ""),//
    HOME_REDIS_MINEVICTABLEIDLETIMEMILLIS(FyyConfigEntryValues.REDIS, "redis.minEvictableIdleTimeMillis", "=", "1800000", "# 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)", ""),//
    HOME_REDIS_NUMTESTSPEREVICTIONRUN(FyyConfigEntryValues.REDIS, "redis.numTestsPerEvictionRun", "=", "3", "# 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3", ""),//
    HOME_REDIS_SOFTMINEVICTABLEIDLETIMEMILLIS(FyyConfigEntryValues.REDIS, "redis.SoftMinEvictableIdleTimeMillis", "=", "1800000", "# 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)", ""),//
    HOME_REDIS_TESTONBORROW(FyyConfigEntryValues.REDIS, "redis.testOnBorrow", "=", "true", "# 在获取连接的时候检查有效性, 默认false", ""),//
    HOME_REDIS_TESTWHILEIDLE(FyyConfigEntryValues.REDIS, "redis.testWhileIdle", "=", "true", "# 在空闲时检查有效性, 默认false", ""),//
    HOME_REDIS_TIMEBETWEENEVICTIONRUNSMILLIS(FyyConfigEntryValues.REDIS, "redis.timeBetweenEvictionRunsMillis", "=", "-1", "# 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", ""),//

    //
    //
    //
    SWAGGER_ENABLE(FyyConfigEntryValues.SWAGGER, "swagger.enable", "=", "true", "# 开关", ""),//
    HOME_SWAGGER_BASEPACKAGE(FyyConfigEntryValues.SWAGGER, "swagger.basepackage", "=", "${projectPackage}.api", "# swagger 默认扫描 api路径为启动类所在目录下的 api目录", ""),//
    HOME_SWAGGER_NAME(FyyConfigEntryValues.SWAGGER, "swagger.name", "=", "SwaggeAPI", "# swagger命名,暂时命名为SwaggerAPI", ""),//
    //
    //
    //
    SERVER_PORT(FyyConfigEntryValues.SYSTEMCONFIG, "server.port", "=", "8080", "# 系统容器rest端口号", ""),//
    SERVLET_MULTIPART_ENABLE(FyyConfigEntryValues.SYSTEMCONFIG, "spring.servlet.multipart.enable", "=", "true", "# 设置上传文件大小", ""),//
    SERVLET_MULTIPART_MAX_FILE_SIZE(FyyConfigEntryValues.SYSTEMCONFIG, "spring.servlet.multipart.max-file-size", "=", "-1", "# 不限制文件大小", ""),//
    SERVLET_MULTIPART_MAX_REQUEST_SIZE(FyyConfigEntryValues.SYSTEMCONFIG, "spring.servlet.multipart.max-request-size", "=", "-1", "# 不限制请求体大小", ""),//
    SERVLET_MULTIPART_LOCATION(FyyConfigEntryValues.SYSTEMCONFIG, "spring.servlet.multipart.location", "=", "${workHomeDir}/temp/001", "# 临时IO目录", "#"),//
    SERVLET_MULTIPART_FILE_SIZE_THRESHOLD(FyyConfigEntryValues.SYSTEMCONFIG, "spring.servlet.multipart.file-size-threshold", "=", "1", "# 超过1Mb，就IO到临时目录", ""),//
    //
    //
    //
    SYSTEM_INPUTPARAMAB_CLASS(FyyConfigEntryValues.SYSTEMCONFIG, "system.inputparamab.class", "=", "cn.bigcore.micro.outgoing.FyyInputParamRe", "# 默认入参处理函数", "#"),//
    //
    //
    //
    HOME_EXTEND_README(FyyConfigEntryValues.SYSTEMEXTEND, "extend.readme", "=", "示例值", "# 示例扩展属性", ""),//


    ;

    private FyyConfigEntryValues codeType;
    private String key;
    private String m;
    private String value;
    private String mark;
    private String suff;

    FyyConfigEntryDetailsValues(FyyConfigEntryValues codeType, String key, String m, String value, String mark, String suff) {
        this.codeType = codeType;
        this.key = key;
        this.m = m;
        this.value = value;
        this.mark = mark;
        this.suff = suff;
    }


    public T getSeetingValue(Class<T> classs) {
        return (T) FyyInitEnv.SettingInformation.setting.getObj(this.getKey());
    }

    public FyyConfigEntryValues getCodeType() {
        return codeType;
    }

    public void setCodeType(FyyConfigEntryValues codeType) {
        this.codeType = codeType;
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
        return FyyVelocityUtils.convert(value, FyyInitEnv.SettingInformation.context);
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
