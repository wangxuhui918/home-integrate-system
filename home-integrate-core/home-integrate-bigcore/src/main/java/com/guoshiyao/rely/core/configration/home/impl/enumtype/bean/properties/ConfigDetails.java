package com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties;

import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.utils.velocity.VelocityUtils;
import org.apache.poi.ss.formula.functions.T;

public enum ConfigDetails {


    HOME_REDIS_HOST(ConfigMain.REDIS, "home.redis.host", "=", "", "# IP地址:192.168.3.251 使用方法:BaseEv.SettingInformation.redisds", "#"),//
    HOME_REDIS_PORT(ConfigMain.REDIS, "home.redis.port", "=", "", "# 端口号:6379", "#"),//
    HOME_REDIS_PASSWORD(ConfigMain.REDIS, "home.redis.password", "=", "", "# 密码:123456", "#"),//
    HOME_REDIS_MASTERNAME(ConfigMain.REDIS, "home.redis.masterName", "=", "", "# 主干:mymaster", "#"),//
    HOME_REDIS_BLOCKWHENEXHAUSTED(ConfigMain.REDIS, "home.redis.BlockWhenExhausted", "=", "true", "# 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true", "#"),//
    HOME_REDIS_EVICTIONPOLICYCLASSNAME(ConfigMain.REDIS, "home.redis.evictionPolicyClassName", "=", "org.apache.commons.pool2.impl.DefaultEvictionPolicy", "# 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)", "#"),//
    HOME_REDIS_JMXENABLED(ConfigMain.REDIS, "home.redis.jmxEnabled", "=", "true", "# 是否启用pool的jmx管理功能, 默认true", "#"),//
    HOME_REDIS_LIFO(ConfigMain.REDIS, "home.redis.lifo", "=", "true", "# 是否启用后进先出, 默认true", "#"),//
    HOME_REDIS_MAXIDLE(ConfigMain.REDIS, "home.redis.maxIdle", "=", "8", "# 最大空闲连接数, 默认8个", "#"),//
    HOME_REDIS_MINIDLE(ConfigMain.REDIS, "home.redis.minIdle", "=", "1", "# 最小空闲连接数, 默认1", "#"),//
    HOME_REDIS_MAXTOTAL(ConfigMain.REDIS, "home.redis.maxTotal", "=", "8", "# 最大连接数, 默认8个", "#"),//
    HOME_REDIS_MAXWAITMILLIS(ConfigMain.REDIS, "home.redis.maxWaitMillis", "=", "-1", "# 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", "#"),//
    HOME_REDIS_MINEVICTABLEIDLETIMEMILLIS(ConfigMain.REDIS, "home.redis.minEvictableIdleTimeMillis", "=", "1800000", "# 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)", "#"),//
    HOME_REDIS_NUMTESTSPEREVICTIONRUN(ConfigMain.REDIS, "home.redis.numTestsPerEvictionRun", "=", "3", "# 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3", "#"),//
    HOME_REDIS_SOFTMINEVICTABLEIDLETIMEMILLIS(ConfigMain.REDIS, "home.redis.SoftMinEvictableIdleTimeMillis", "=", "1800000", "# 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)", "#"),//
    HOME_REDIS_TESTONBORROW(ConfigMain.REDIS, "home.redis.testOnBorrow", "=", "true", "# 在获取连接的时候检查有效性, 默认false", "#"),//
    HOME_REDIS_TESTWHILEIDLE(ConfigMain.REDIS, "home.redis.testWhileIdle", "=", "true", "# 在空闲时检查有效性, 默认false", "#"),//
    HOME_REDIS_TIMEBETWEENEVICTIONRUNSMILLIS(ConfigMain.REDIS, "home.redis.timeBetweenEvictionRunsMillis", "=", "-1", "# 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1", "#"),//
    //
    //
    //
    HOME_LOG_APPID(ConfigMain.LOGBACK, "home.log.appid", "=", "${idKey}", "# 默认为idkey debug", ""),//
    HOME_LOG_ROOTLEVEL(ConfigMain.LOGBACK, "home.log.rootlevel", "=", "info", "# 根日志级别 debug info  warn  error trance 默认debug# 从高到地低 OFF 、 FATAL 、 ERROR 、 WARN 、 INFO 、 DEBUG 、 TRACE 、 ALL -->", ""),//
    HOME_LOG_SYSLEVEL(ConfigMain.LOGBACK, "home.log.syslevel", "=", "info", "# 系统日志级别 默认debug", ""),//
    HOME_LOG_BUSINLEVEL(ConfigMain.LOGBACK, "home.log.businlevel", "=", "info", "# 业务日志级别 默认debug", ""),//
    HOME_LOG_BUSINPACKAGE(ConfigMain.LOGBACK, "home.log.businpackage", "=", "${projectPackage}", "# 业务日志包路径,空的话默认为系统扫描到的包路径", ""),//
    HOME_LOG_DIR(ConfigMain.LOGBACK, "home.log.dir", "=", "target/log/${idKey}", "# 日志存储位置", ""),//
    //
    //
    //
    HOME_MYBATIS_BASEPACKAGE(ConfigMain.MYBATIS, "home.mybatis.basepackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    HOME_MYBATIS_TYPEALIASESPACKAGE(ConfigMain.MYBATIS, "home.mybatis.typealiasespackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    HOME_MYBATIS_MAPPERLOCATIONS(ConfigMain.MYBATIS, "home.mybatis.mapperlocations", "=", "classpath:**/mapper/**.xml", "# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹", "#"),//
    //
    //
    //
    HOME_MYBATISPLUS_BASEPACKAGE(ConfigMain.MYBATIS_PLUS, "home.mybatisplus.basepackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    HOME_MYBATISPLUS_TYPEALIASESPACKAGE(ConfigMain.MYBATIS_PLUS, "home.mybatisplus.typealiasespackage", "=", "${projectPackage}.mapper", "# 扫描mapper地址,默认为启动类下的 mapper文件夹", "#"),//
    HOME_MYBATISPLUS_MAPPERLOCATIONS(ConfigMain.MYBATIS_PLUS, "home.mybatisplus.mapperlocations", "=", "classpath:**/mapper/**.xml", "# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹", "#"),//
    //
    //
    //
    HOME_DUBBO_REFERENCE_PACKAGE(ConfigMain.DUBBO, "home.dubbo.reference.package", "=", "${projectPackage}.dubboservice", "# dubbo默认扫描包:${projectPackage}.dubboservice", "#"),//
    HOME_DUBBO_REFERENCE_TIMEOUT(ConfigMain.DUBBO, "home.dubbo.reference.timeout", "=", "120000", "# dubbo连接超时时间", "#"),//
    HOME_DUBBO_REFERENCE_URL(ConfigMain.DUBBO, "home.dubbo.reference.url", "=", "N/A", "# dubbo注册中心地址:zookeeper://zookeeper.com:2181", "#"),//
    HOME_DUBBO_REFERENCE_AGREEMENT(ConfigMain.DUBBO, "home.dubbo.reference.agreement", "=", "dubbo", "# dubbo协议", "#"),//
    HOME_DUBBO_REFERENCE_HOST(ConfigMain.DUBBO, "home.dubbo.reference.host", "=", "", "# dubbo本地地址:127.0.0.1", "#"),//
    HOME_DUBBO_REFERENCE_PORT(ConfigMain.DUBBO, "home.dubbo.reference.port", "=", "", "# dubbo本地暴露端口:20880", "#"),//
    HOME_DUBBO_PROTOCOLCONFIG_THREADS(ConfigMain.DUBBO, "home.dubbo.protocolconfig.threads", "=", "300", "# dubbo 线程数", "#"),//
    HOME_DUBBO_PROTOCOLCONFIG_THREADPOOL(ConfigMain.DUBBO, "home.dubbo.protocolconfig.threadpool", "=", "fixed", "# #cached limited fixed all", "#"),//
    //
    //
    //
    HOME_MINIO_ENDPOINT(ConfigMain.MINIO, "home.minio.endpoint", "=", "", "# api 请求地址 必填:http://localhost:9000", "#"),//
    HOME_MINIO_ACCESSKEY(ConfigMain.MINIO, "home.minio.accesskey", "=", "", "# api 请求用户名:SDS*DSJDlerw9(were)", "#"),//
    HOME_MINIO_SECRETKEY(ConfigMain.MINIO, "home.minio.secretKey", "=", "", "# api 请求密钥:SDS*DSJDlerdsfsdf", "#"),//
    HOME_MINIO_NAMESPACE_RE(ConfigMain.MINIO, "home.minio.namespace_re", "=", "${idKey}", "# 桶 命名空间,必填", "#"),//
    HOME_MINIO_DEOM(ConfigMain.MINIO, "home.minio.deom", "=", "demop", "# 测试增加数据", "#"),//
    //
    //
    //
    HOME_SWAGGER_BASEPACKAGE(ConfigMain.SWAGGER, "home.swagger.basepackage", "=", "${projectPackage}.api", "# swagger 默认扫描 api路径为启动类所在目录下的 api目录", "#"),//
    HOME_SWAGGER_NAME(ConfigMain.SWAGGER, "home.swagger.name", "=", "SwaggeAPI", "# swagger命名,暂时命名为SwaggerAPI", "#"),//
    //
    //
    //
    HOME_DB_URL(ConfigMain.DB, "home.db.url", "=", "", "# 数据库连接地址:jdbc:mysql://localhost:3306/home?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8", "#"),//
    HOME_DB_USERNAME(ConfigMain.DB, "home.db.username", "=", "", "# 用户名:root", "#"),//
    HOME_DB_PASSWORD(ConfigMain.DB, "home.db.password", "=", "", "# 密码:password", "#"),//
    HOME_DB_DRIVERCLASSNAME(ConfigMain.DB, "home.db.driverclassname", "=", "", "# 驱动UrlClass;com.mysql.cj.jdbc.Driver", "#"),//
    HOME_DB_DRIVERCLASS_DOWNLOADURL(ConfigMain.DB, "home.db.driverclass.downloadurl", "=", "", "#驱动下载地址,使用 url 后可以不填写home.db.driverclassname,MAVEN POM 中无需加载驱动坐标,优先级低于home.db.driverclassname :https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.49/mysql-connector-java-5.1.49.jar", "#"),//
    HOME_DB_VALIDATIONQUERY(ConfigMain.DB, "home.db.validationquery", "=", "", "# 数据库校验sql:select 'x'", "#"),//
    HOME_DB_MAXACTIVE(ConfigMain.DB, "home.db.maxactive", "=", "30", "", "#"),//
    HOME_DB_INITIALSIZE(ConfigMain.DB, "home.db.initialsize", "=", "1", "", "#"),//
    HOME_DB_MAXWAIT(ConfigMain.DB, "home.db.maxwait", "=", "60000", "", "#"),//
    HOME_DB_MINIDLE(ConfigMain.DB, "home.db.minidle", "=", "3", "", "#"),//
    HOME_DB_TIMEBETWEENEVICTIONRUNSMILLIS(ConfigMain.DB, "home.db.timebetweenevictionrunsmillis", "=", "60000", "", "#"),//
    HOME_DB_MINEVICTABLEIDLETIMEMILLIS(ConfigMain.DB, "home.db.minevictableidletimemillis", "=", "300000", "", "#"),//
    HOME_DB_TESTWHILEIDLE(ConfigMain.DB, "home.db.testwhileidle", "=", "true", "", "#"),//
    HOME_DB_TESTONBORROW(ConfigMain.DB, "home.db.testonborrow", "=", "false", "", "#"),//
    HOME_DB_TESTONRETURN(ConfigMain.DB, "home.db.testonreturn", "=", "false", "", "#"),//
    HOME_DB_POOLPREPAREDSTATEMENTS(ConfigMain.DB, "home.db.poolpreparedstatements", "=", "true", "", "#"),//
    HOME_DB_REMOVEABANDONED(ConfigMain.DB, "home.db.removeabandoned", "=", "true", "", "#"),//
    HOME_DB_REMOVEABANDONEDTIMEOUT(ConfigMain.DB, "home.db.removeabandonedtimeout", "=", "60", "", "#"),//
    HOME_DB_NUMTESTSPEREVICTIONRUN(ConfigMain.DB, "home.db.numtestsperevictionrun", "=", "60", "", "#"),//
    HOME_DB_MAXOPENPREPAREDSTATEMENTS(ConfigMain.DB, "home.db.maxopenpreparedstatements", "=", "20", "", "#"),//
    HOME_DB_FILTERS(ConfigMain.DB, "home.db.filters", "=", "stat,wall", "", "#"),//
    //
    //
    //
    HOME_FLYWAYDB_URL(ConfigMain.FLYWAY, "home.flywaydb.url", "=", "", "# flyway jdbc连接地址,如果为空取数据库连接地址", "#"),//
    HOME_FLYWAYDB_USERNAME(ConfigMain.FLYWAY, "home.flywaydb.username", "=", "", "# flyway 如果为空,取数据库用户名", "#"),//
    HOME_FLYWAYDB_PASSWORD(ConfigMain.FLYWAY, "home.flywaydb.password", "=", "", "# flyway 如果为空,去数据库密码", "#"),//
    HOME_FLYWAYDB_TABLE(ConfigMain.FLYWAY, "home.flywaydb.table", "=", "flyway_schema_history", "# flyway 版本管理表", "#"),//
    //
    //
    //
    HOME_APOLLO_URL(ConfigMain.APOLLO, "home.apollo.url", "=", "", "# 管理端阿波罗管理地址,如果不填写则阿波罗不加载", "#"),//
    HOME_APOLLO_USERNAME(ConfigMain.APOLLO, "home.apollo.username", "=", "", "# 阿波罗管理用户名 apollo", "#"),//
    HOME_APOLLO_PASSWORD(ConfigMain.APOLLO, "home.apollo.password", "=", "", "# 阿波罗管理密码 apollo", "#"),//
    HOME_APOLLO_APP_ID(ConfigMain.APOLLO, "home.apollo.app.id", "=", "${idKey}", "# 客户端", "#"),//
    HOME_APOLLO_APOLLO_META(ConfigMain.APOLLO, "home.apollo.apollo.meta", "=", "", "# 地址后缀不允许以/结尾:http://106.54.227.205:8080", "#"),//
    HOME_APOLLO_APOLLO_ENV(ConfigMain.APOLLO, "home.apollo.apollo.env", "=", "${env}", "# dev,uat,pro 可以通过系统env传入,注释后自动通过传入变量决定,这个建议不传,系统自动识别", "#"),//
    //
    //
    //
    HOME_GITLAB_GITDIR(ConfigMain.GITLAB, "home.gitlab.gitdir", "=", "${workHomeDir}/git/download", "# git代码文件夹目录", "#"),//
    HOME_GITLAB_GITLABURL(ConfigMain.GITLAB, "home.gitlab.gitlaburl", "=", "", "# gitlab的地址:http://192.168.150.61:2080/", "#"),//
    HOME_GITLAB_TOKEN(ConfigMain.GITLAB, "home.gitlab.token", "=", "", "# gitlab管理token:sfdkfhiwernsdf", "#"),//
    HOME_GITLAB_USERNAME(ConfigMain.GITLAB, "home.gitlab.username", "=", "", "# gitlab用户名:wangxuhui", "#"),//
    HOME_GITLAB_PASSWORD(ConfigMain.GITLAB, "home.gitlab.password", "=", "", "# gitlab密码:ewellllllll", "#"),//
    HOME_GITLAB_BRANCH(ConfigMain.GITLAB, "home.gitlab.branch", "=", "master", "# gitlab checout default branch", "#"),//
    HOME_GITLAB_LOG(ConfigMain.GITLAB, "home.gitlab.log", "=", "${workHomeDir}/git/log/log.txt", "# gitlab log", "#"),//
    //
    //
    //
    SYSTEM_SERVLET_PORT(ConfigMain.SYSTEMCONFIG, "system.servlet.port", "=", "8080", "# 系统容器rest端口号", ""),//
    SYSTEM_SERVLET_MULTIPART_MAX_FILE_SIZE(ConfigMain.SYSTEMCONFIG, "system.servlet.multipart.max-file-size", "=", "-1", "# 不限制文件大小", ""),//
    SYSTEM_SERVLET_MULTIPART_MAX_REQUEST_SIZE(ConfigMain.SYSTEMCONFIG, "system.servlet.multipart.max-request-size", "=", "-1", "# 不限制请求体大小", ""),//
    SYSTEM_SERVLET_MULTIPART_LOCATION(ConfigMain.SYSTEMCONFIG, "system.servlet.multipart.location", "=", "${workHomeDir}/temp/001", "# 临时IO目录", "#"),//
    SYSTEM_SERVLET_MULTIPART_FILE_SIZE_THRESHOLD(ConfigMain.SYSTEMCONFIG, "system.servlet.multipart.file-size-threshold", "=", "1", "# 超过1Mb，就IO到临时目录", ""),//
    SYSTEM_INPUTPARAMAB_CLASS(ConfigMain.SYSTEMCONFIG, "system.inputparamab.class", "=", "com.guoshiyao.rely.outgoing.InputParamRe", "# 默认入参处理函数", "#"),//
    //
    //
    //
    HOME_EXTEND_README(ConfigMain.SYSTEMEXTEND, "home.extend.readme", "=", "示例值", "# 示例扩展属性", ""),//


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
