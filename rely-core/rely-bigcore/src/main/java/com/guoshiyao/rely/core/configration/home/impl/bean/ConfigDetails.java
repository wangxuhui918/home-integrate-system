package com.guoshiyao.rely.core.configration.home.impl.bean;

import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.utils.velocity.VelocityUtils;
import org.apache.poi.ss.formula.functions.T;

public enum ConfigDetails {

    APOLLO_BOOTSTRAP_ENABLED(ConfigMain.APOLLO, "apollo.bootstrap.enabled", "=", "false", ConfigInitValue.suffp + " 开关", ""),//
    APOLLO_BOOTSTRAP_EAGERLOAD_ENABLED(ConfigMain.APOLLO, "apollo.bootstrap.eagerload.enabled", "=", "false", ConfigInitValue.suffp + " 开关", ""),//
    HOME_APOLLO_APP_ID(ConfigMain.APOLLO, "app.id", "=", "${idKey}", ConfigInitValue.suffp + " 阿波罗APPID", ""),//
    HOME_APOLLO_APOLLO_META(ConfigMain.APOLLO, "apollo.meta", "=", "", ConfigInitValue.suffp + " 阿波罗配置地址", ""),//
    //    HOME_APOLLO_APOLLO_ENV(ConfigMain.APOLLO, "apollo.apollo.env", "=", "${env}", ConfigInitValue.suffp + " dev,uat,pro 可以通过系统env传入,注释后自动通过传入变量决定,这个建议不传,系统自动识别", ""),//
    //
    //
    //
    DUBBO_ENABLED(ConfigMain.APOLLO, "dubbo.enabled", "=", "false", ConfigInitValue.suffp + " 开关", ""),//
    HOME_DUBBO_REFERENCE_PACKAGE(ConfigMain.DUBBO, "dubbo.scan.basePackages", "=", "${projectPackage}.dubboservice", ConfigInitValue.suffp + " dubbo默认扫描包:${projectPackage}.dubboservice", ""),//
    HOME_DUBBO_REFERENCE_TIMEOUT(ConfigMain.DUBBO, "dubbo.reference.timeout", "=", "120000", ConfigInitValue.suffp + " dubbo连接超时时间", ""),//
    HOME_DUBBO_REFERENCE_URL(ConfigMain.DUBBO, "dubbo.registry.address", "=", "zookeeper://zookeeper.com:2181", ConfigInitValue.suffp + " dubbo注册中心地址 N/A", ""),//
    HOME_DUBBO_REFERENCE_AGREEMENT(ConfigMain.DUBBO, "dubbo.protocol.name", "=", "dubbo", ConfigInitValue.suffp + " dubbo协议", ""),//
    HOME_DUBBO_REFERENCE_HOST(ConfigMain.DUBBO, "dubbo.reference.host", "=", "127.0.0.1", ConfigInitValue.suffp + " dubbo本地地址:127.0.0.1", ""),//
    HOME_DUBBO_REFERENCE_PORT(ConfigMain.DUBBO, "dubbo.protocol.port", "=", "20880", ConfigInitValue.suffp + " dubbo本地暴露端口", ""),//
    HOME_DUBBO_PROTOCOLCONFIG_THREADS(ConfigMain.DUBBO, "dubbo.protocolconfig.threads", "=", "300", ConfigInitValue.suffp + " dubbo线程数", ""),//
    HOME_DUBBO_PROTOCOLCONFIG_THREADPOOL(ConfigMain.DUBBO, "dubbo.protocolconfig.threadpool", "=", "fixed", ConfigInitValue.suffp + " #cached limited fixed all", ""),//
    //
    //
    //
    DB_ENABLE(ConfigMain.DB, "db.enable", "=", "false", ConfigInitValue.suffp + " 开关", ""),//
    HOME_DB_URL(ConfigMain.DB, "db.url", "=", "jdbc:mysql://localhost:3306/home?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8", ConfigInitValue.suffp + " 数据库连接地址", ""),//
    HOME_DB_USERNAME(ConfigMain.DB, "db.username", "=", "", ConfigInitValue.suffp + " 用户名", ""),//
    HOME_DB_PASSWORD(ConfigMain.DB, "db.password", "=", "", ConfigInitValue.suffp + " 密码", ""),//
    HOME_DB_DRIVERCLASSNAME(ConfigMain.DB, "db.driverclassname", "=", "com.mysql.cj.jdbc.Driver", ConfigInitValue.suffp + " DriverClass", ""),//
    HOME_DB_VALIDATIONQUERY(ConfigMain.DB, "db.validationquery", "=", "select 'x'", ConfigInitValue.suffp + " 数据库校验", ""),//
    HOME_DB_MAXACTIVE(ConfigMain.DB, "db.maxactive", "=", "30", "", ""),//
    HOME_DB_INITIALSIZE(ConfigMain.DB, "db.initialsize", "=", "1", "", ""),//
    HOME_DB_MAXWAIT(ConfigMain.DB, "db.maxwait", "=", "60000", "", ""),//
    HOME_DB_MINIDLE(ConfigMain.DB, "db.minidle", "=", "3", "", ""),//
    HOME_DB_TIMEBETWEENEVICTIONRUNSMILLIS(ConfigMain.DB, "db.timebetweenevictionrunsmillis", "=", "60000", "", ""),//
    HOME_DB_MINEVICTABLEIDLETIMEMILLIS(ConfigMain.DB, "db.minevictableidletimemillis", "=", "300000", "", ""),//
    HOME_DB_TESTWHILEIDLE(ConfigMain.DB, "db.testwhileidle", "=", "true", "", ""),//
    HOME_DB_TESTONBORROW(ConfigMain.DB, "db.testonborrow", "=", "false", "", ""),//
    HOME_DB_TESTONRETURN(ConfigMain.DB, "db.testonreturn", "=", "false", "", ""),//
    HOME_DB_POOLPREPAREDSTATEMENTS(ConfigMain.DB, "db.poolpreparedstatements", "=", "true", "", ""),//
    HOME_DB_REMOVEABANDONED(ConfigMain.DB, "db.removeabandoned", "=", "true", "", ""),//
    HOME_DB_REMOVEABANDONEDTIMEOUT(ConfigMain.DB, "db.removeabandonedtimeout", "=", "60", "", ""),//
    HOME_DB_NUMTESTSPEREVICTIONRUN(ConfigMain.DB, "db.numtestsperevictionrun", "=", "60", "", ""),//
    HOME_DB_MAXOPENPREPAREDSTATEMENTS(ConfigMain.DB, "db.maxopenpreparedstatements", "=", "20", "", ""),//
    HOME_DB_FILTERS(ConfigMain.DB, "db.filters", "=", "stat,wall", "", ""),//
    //
    //
    //
    FLYWAYDB_ENABLE(ConfigMain.FLYWAY, "flywaydb.enable", "=", "false", ConfigInitValue.suffp + " 开关", ""),//
    HOME_FLYWAYDB_URL(ConfigMain.FLYWAY, "flywaydb.url", "=", "", ConfigInitValue.suffp + " flyway jdbc连接地址,如果为空取数据库连接地址", ""),//
    HOME_FLYWAYDB_USERNAME(ConfigMain.FLYWAY, "flywaydb.username", "=", "", ConfigInitValue.suffp + " flyway 如果为空,取数据库用户名", ""),//
    HOME_FLYWAYDB_PASSWORD(ConfigMain.FLYWAY, "flywaydb.password", "=", "", ConfigInitValue.suffp + " flyway 如果为空,去数据库密码", ""),//
    HOME_FLYWAYDB_TABLE(ConfigMain.FLYWAY, "flywaydb.table", "=", "flyway_schema_history", ConfigInitValue.suffp + " flyway 版本管理表", ""),//
    //
    //
    //
    LOG_ENABLE(ConfigMain.LOGBACK, "log.enable", "=", "true", ConfigInitValue.suffp + " 开关", ""),//
    HOME_LOG_APPID(ConfigMain.LOGBACK, "log.appid", "=", "${idKey}", ConfigInitValue.suffp + " 默认为idkey debug", ""),//
    HOME_LOG_ROOTLEVEL(ConfigMain.LOGBACK, "log.rootlevel", "=", "info", ConfigInitValue.suffp + " 根日志级别 debug info  warn  error trance 默认debug# 从高到地低 OFF 、 FATAL 、 ERROR 、 WARN 、 INFO 、 DEBUG 、 TRACE 、 ALL -->", ""),//
    HOME_LOG_SYSLEVEL(ConfigMain.LOGBACK, "log.syslevel", "=", "info", ConfigInitValue.suffp + " 系统日志级别 默认debug", ""),//
    HOME_LOG_BUSINLEVEL(ConfigMain.LOGBACK, "log.businlevel", "=", "info", ConfigInitValue.suffp + " 业务日志级别 默认debug", ""),//
    HOME_LOG_BUSINPACKAGE(ConfigMain.LOGBACK, "log.businpackage", "=", "${projectPackage}", ConfigInitValue.suffp + " 业务日志包路径,空的话默认为系统扫描到的包路径", ""),//
    HOME_LOG_DIR(ConfigMain.LOGBACK, "log.dir", "=", "target/log/${idKey}", ConfigInitValue.suffp + " 日志存储位置", ""),//
    //
    //
    //
    MYBATIS_ENABLE(ConfigMain.MYBATIS, "mybatis.enable", "=", "false", ConfigInitValue.suffp + " 开关", ""),//
    HOME_MYBATIS_BASEPACKAGE(ConfigMain.MYBATIS, "mybatis.basepackage", "=", "${projectPackage}.mapper", ConfigInitValue.suffp + " 扫描mapper地址,默认为启动类下的 mapper文件夹", ""),//
    HOME_MYBATIS_TYPEALIASESPACKAGE(ConfigMain.MYBATIS, "mybatis.typealiasespackage", "=", "${projectPackage}.mapper", ConfigInitValue.suffp + " 扫描mapper地址,默认为启动类下的 mapper文件夹", ""),//
    HOME_MYBATIS_MAPPERLOCATIONS(ConfigMain.MYBATIS, "mybatis.mapperlocations", "=", "classpath:**/mapper/**.xml", ConfigInitValue.suffp + " 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹", ""),//
    //
    //
    //
    MYBATISPLUS_ENABLE(ConfigMain.MYBATIS_PLUS, "mybatisplus.enable", "=", "false", ConfigInitValue.suffp + " 开关", ""),//
    HOME_MYBATISPLUS_BASEPACKAGE(ConfigMain.MYBATIS_PLUS, "mybatisplus.basepackage", "=", "${projectPackage}.mapper", ConfigInitValue.suffp + " 扫描mapper地址,默认为启动类下的 mapper文件夹", ""),//
    HOME_MYBATISPLUS_TYPEALIASESPACKAGE(ConfigMain.MYBATIS_PLUS, "mybatisplus.typealiasespackage", "=", "${projectPackage}.mapper", ConfigInitValue.suffp + " 扫描mapper地址,默认为启动类下的 mapper文件夹", ""),//
    HOME_MYBATISPLUS_MAPPERLOCATIONS(ConfigMain.MYBATIS_PLUS, "mybatisplus.mapperlocations", "=", "classpath:**/mapper/**.xml", ConfigInitValue.suffp + " 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹", ""),//
    //
    //
    //
    MINIO_ENABLE(ConfigMain.MINIO, "minio.enable", "=", "false", ConfigInitValue.suffp + " 开关", ""),//
    HOME_MINIO_ENDPOINT(ConfigMain.MINIO, "minio.endpoint", "=", "", ConfigInitValue.suffp + " api 请求地址 必填:http://localhost:9000", ""),//
    HOME_MINIO_ACCESSKEY(ConfigMain.MINIO, "minio.accesskey", "=", "", ConfigInitValue.suffp + " api 请求用户名:SDS*DSJDlerw9(were)", ""),//
    HOME_MINIO_SECRETKEY(ConfigMain.MINIO, "minio.secretKey", "=", "", ConfigInitValue.suffp + " api 请求密钥:SDS*DSJDlerdsfsdf", ""),//
    HOME_MINIO_NAMESPACE_RE(ConfigMain.MINIO, "minio.namespace_re", "=", "${idKey}", ConfigInitValue.suffp + " 桶 命名空间,必填", ""),//
    HOME_MINIO_DEOM(ConfigMain.MINIO, "minio.deom", "=", "demop", ConfigInitValue.suffp + " 测试增加数据", ""),//
    //
    //
    //

    REDIS_ENABLE(ConfigMain.REDIS, "redis.enable", "=", "false", ConfigInitValue.suffp + " 开关", ""),//
    HOME_REDIS_HOST(ConfigMain.REDIS, "redis.host", "=", "", ConfigInitValue.suffp + " IP地址:192.168.3.251 使用方法:BaseEv.SettingInformation.redisds", ""),//
    HOME_REDIS_PORT(ConfigMain.REDIS, "redis.port", "=", "", ConfigInitValue.suffp + " 端口号:6379", ""),//
    HOME_REDIS_PASSWORD(ConfigMain.REDIS, "redis.password", "=", "", ConfigInitValue.suffp + " 密码:123456", ""),//
    HOME_REDIS_MASTERNAME(ConfigMain.REDIS, "redis.masterName", "=", "", ConfigInitValue.suffp + " 主干:mymaster", ""),//
    HOME_REDIS_BLOCKWHENEXHAUSTED(ConfigMain.REDIS, "redis.BlockWhenExhausted", "=", "true", ConfigInitValue.suffp + " 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true", ""),//
    HOME_REDIS_EVICTIONPOLICYCLASSNAME(ConfigMain.REDIS, "redis.evictionPolicyClassName", "=", "org.apache.commons.pool2.impl.DefaultEvictionPolicy", ConfigInitValue.suffp + " 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)", ""),//
    HOME_REDIS_JMXENABLED(ConfigMain.REDIS, "redis.jmxEnabled", "=", "true", ConfigInitValue.suffp + " 是否启用pool的jmx管理功能, 默认true", ""),//
    HOME_REDIS_LIFO(ConfigMain.REDIS, "redis.lifo", "=", "true", ConfigInitValue.suffp + " 是否启用后进先出, 默认true", ""),//
    HOME_REDIS_MAXIDLE(ConfigMain.REDIS, "redis.maxIdle", "=", "8", ConfigInitValue.suffp + " 最大空闲连接数, 默认8个", ""),//
    HOME_REDIS_MINIDLE(ConfigMain.REDIS, "redis.minIdle", "=", "1", ConfigInitValue.suffp + " 最小空闲连接数, 默认1", ""),//
    HOME_REDIS_MAXTOTAL(ConfigMain.REDIS, "redis.maxTotal", "=", "8", ConfigInitValue.suffp + " 最大连接数, 默认8个", ""),//
    HOME_REDIS_MAXWAITMILLIS(ConfigMain.REDIS, "redis.maxWaitMillis", "=", "-1", ConfigInitValue.suffp + " 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", ""),//
    HOME_REDIS_MINEVICTABLEIDLETIMEMILLIS(ConfigMain.REDIS, "redis.minEvictableIdleTimeMillis", "=", "1800000", ConfigInitValue.suffp + " 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)", ""),//
    HOME_REDIS_NUMTESTSPEREVICTIONRUN(ConfigMain.REDIS, "redis.numTestsPerEvictionRun", "=", "3", ConfigInitValue.suffp + " 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3", ""),//
    HOME_REDIS_SOFTMINEVICTABLEIDLETIMEMILLIS(ConfigMain.REDIS, "redis.SoftMinEvictableIdleTimeMillis", "=", "1800000", ConfigInitValue.suffp + " 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)", ""),//
    HOME_REDIS_TESTONBORROW(ConfigMain.REDIS, "redis.testOnBorrow", "=", "true", ConfigInitValue.suffp + " 在获取连接的时候检查有效性, 默认false", ""),//
    HOME_REDIS_TESTWHILEIDLE(ConfigMain.REDIS, "redis.testWhileIdle", "=", "true", ConfigInitValue.suffp + " 在空闲时检查有效性, 默认false", ""),//
    HOME_REDIS_TIMEBETWEENEVICTIONRUNSMILLIS(ConfigMain.REDIS, "redis.timeBetweenEvictionRunsMillis", "=", "-1", ConfigInitValue.suffp + " 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", ""),//

    //
    //
    //
    SWAGGER_ENABLE(ConfigMain.SWAGGER, "swagger.enable", "=", "true", ConfigInitValue.suffp + " 开关", ""),//
    HOME_SWAGGER_BASEPACKAGE(ConfigMain.SWAGGER, "swagger.basepackage", "=", "${projectPackage}.api", ConfigInitValue.suffp + " swagger 默认扫描 api路径为启动类所在目录下的 api目录", ""),//
    HOME_SWAGGER_NAME(ConfigMain.SWAGGER, "swagger.name", "=", "SwaggeAPI", ConfigInitValue.suffp + " swagger命名,暂时命名为SwaggerAPI", ""),//
    //
    //
    //
    SERVER_PORT(ConfigMain.SYSTEMCONFIG, "server.port", "=", "8080", ConfigInitValue.suffp + " 系统容器rest端口号", ""),//
    SERVLET_MULTIPART_ENABLE(ConfigMain.SYSTEMCONFIG, "spring.servlet.multipart.enable", "=", "true", ConfigInitValue.suffp + " 设置上传文件大小", ""),//
    SERVLET_MULTIPART_MAX_FILE_SIZE(ConfigMain.SYSTEMCONFIG, "spring.servlet.multipart.max-file-size", "=", "-1", ConfigInitValue.suffp + " 不限制文件大小", ""),//
    SERVLET_MULTIPART_MAX_REQUEST_SIZE(ConfigMain.SYSTEMCONFIG, "spring.servlet.multipart.max-request-size", "=", "-1", ConfigInitValue.suffp + " 不限制请求体大小", ""),//
    SERVLET_MULTIPART_LOCATION(ConfigMain.SYSTEMCONFIG, "spring.servlet.multipart.location", "=", "${workHomeDir}/temp/001", ConfigInitValue.suffp + " 临时IO目录", ConfigInitValue.suffp),//
    SERVLET_MULTIPART_FILE_SIZE_THRESHOLD(ConfigMain.SYSTEMCONFIG, "spring.servlet.multipart.file-size-threshold", "=", "1", ConfigInitValue.suffp + " 超过1Mb，就IO到临时目录", ""),//
    //
    //
    //
    SYSTEM_INPUTPARAMAB_CLASS(ConfigMain.SYSTEMCONFIG, "system.inputparamab.class", "=", "com.guoshiyao.rely.outgoing.InputParamRe", ConfigInitValue.suffp + " 默认入参处理函数", ConfigInitValue.suffp),//
    //
    //
    //
    HOME_EXTEND_README(ConfigMain.SYSTEMEXTEND, "extend.readme", "=", "示例值", ConfigInitValue.suffp + " 示例扩展属性", ""),//


    ;

    private ConfigMain codeType;
    private String key;
    private String m;
    private String value;
    private String mark;
    private String suff;

    ConfigDetails(ConfigMain codeType, String key, String m, String value, String mark, String suff) {
        this.codeType = codeType;
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