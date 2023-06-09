# 📚简介

(帆有云SpringBoot轻量级业务框架,简称:帆有云框架)home-integrate-system(以下简称系统)
，是一个轻量级,完全开源的Springboot框架，系统通过以类spi的方式封装常用工具插件（阿波罗，数据库，dubbo，flyway，git，mybatis，tk-mybatis，redis，雪花算法，swagger-ui，hutool等）
，降低了系统集成难度。

👉 实现自动配置（例如自动扫描Controller注解，无相关注解/Swagger配置则不加载Swagger）。

# 📘安装

1. 前提为先对 ***home-integrate-system*** 进行打包安装(mvn install)

## Maven仓库坐标

```xml

<parent>
    <groupId>cn.bigcore</groupId>
    <version>11.0.1.103-RELEASE</version>
    <artifactId>micro-dependencies</artifactId>
</parent>

<dependencies>
<dependency>
    <groupId>cn.bigcore</groupId>
    <artifactId>micro-pack</artifactId>
    <version>11.0.1.103-RELEASE</version>
</dependency>
</dependencies>

```

# 使用

1. 启动类加上注解 **Starter**,如下

```java
package cn.vesan.code;

import annotaion.cn.bigcore.micro.Starter;
import cn.bigcore.micro.message.i18n.I18n;
import org.springframework.boot.SpringApplication;

@Starter(idkey = "vesan-lowformat", i18n = I18n.CN)
public class LowFormatApplication {
    public static void main(String[] args) {
        SpringApplication.run(LowFormatApplication.class, args);
    }
}

```

2. Starter 属性说明(以1中类为例)

|属性| 说明     | 默认值                   | 必填 |
|----|--------|-----------------------|----|
|idkey| 项目唯一ID | 必填项                   | ✅  |
|i18n| 区域编码   | I18n.CN               | ❎ |
|runEnv| 当前运行环境 | ...                   | ❎  |
|configEnv| 配置默认环境 | dev,uat,pro           | ❎  |
|exclude| 不加载的类  | ...                   | ❎  |
|scanBasePackages| 扫描包    | cn.vesan.code(启动类所在包) | ❎  |

# 📝部分功能的使用

## Controller(请求体,消息码,入参,出参)

```java

@RuleAnnotationApi
@Controller
@Api(tags = "(CodeFile)控制层")
public class CodeFileController {

    @RequestMapping(value = "/codeFile/add", method = {RequestMethod.POST})
    @ApiOperation(value = "添加")
    @ResponseBody
    @PostMapping
    public Object add(@RequestBody InputParamReLow<CodeFileVo> input) {
        return this.codeFileService.insert(input.getInputData());
    }

    @RequestMapping(value = "/codeFile/add", method = {RequestMethod.POST})
    @ApiOperation(value = "添加")
    @ResponseBody
    @PostMapping
    public void add(@RequestBody InputParamReLow<CodeFileVo> input) {
        this.codeFileService.insert(input.getInputData());
    }

    @RequestMapping(value = "/codeFile/add", method = {RequestMethod.POST})
    @ApiOperation(value = "添加")
    @ResponseBody
    @PostMapping
    public OutputParamAb add(@RequestBody InputParamReLow<CodeFileVo> input) {
        this.codeFileService.insert(input.getInputData());
    }
    1.反参要求为OutputParamAb子类,
    当反参非 OutputParamAb
    会自动创建一个新的OutputParamAb类,并将结果加入OutputParamAb.data中
    2.入参要求InputParamReLow<T>,可通过input.getInputData()获取入参实体类,并提供一系列方法
    3.自动捕获Service中的业务异常并通过OutputParamAb加工返回给前端
    4.反参目前实现了OutputParamRe
    5.
    Conttroller需要实现加入 @RuleAnnotationApi 注解
}
```

## Service(业务异常处理)

```java
        if(1!=0){
        throw new ExceptionError("关联关系未填写");//返回业务异常
        throw new ExceptionRe("A02");//message-*.xml文件中的消息码code
        throw new ExceptionAb();//自定义实现ExceptionAb类
        }

```

#配置说明

如果需要启用相关功能,打开对应配置+写入相关实现类(如果有,比如dubboService业务类,Controller实现类)

```properties

# 开关
apollo.bootstrap.enabled=false
# 开关
apollo.bootstrap.eagerload.enabled=false
# 客户端
app.id=home-example-ewell
# 地址后缀不允许以/结尾:http://106.54.227.205:8080
apollo.meta=
# dev,uat,pro 可以通过系统env传入,注释后自动通过传入变量决定,这个建议不传,系统自动识别
apollo.apollo.env=dev
# 开关
dubbo.enabled=false
# dubbo默认扫描包:cn.bigcore.micro.example.dubboservice
dubbo.scan.basePackages=cn.bigcore.micro.example.dubboservice
# dubbo连接超时时间
dubbo.reference.timeout=120000
# dubbo注册中心地址:zookeeper://zookeeper.com:2181
dubbo.registry.address=N/A
# dubbo协议
dubbo.protocol.name=dubbo
# dubbo本地地址:127.0.0.1
dubbo.reference.host=
# dubbo本地暴露端口:20880
dubbo.protocol.port=
# dubbo 线程数
dubbo.protocolconfig.threads=300
# #cached limited fixed all
dubbo.protocolconfig.threadpool=fixed
# 开关
db.enable=false
# 数据库连接地址:jdbc:mysql://localhost:3306/home?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8
db.url=
# 用户名:root
db.username=
# 密码:password
db.password=
# 驱动UrlClass;com.mysql.cj.jdbc.Driver
db.driverclassname=
# 数据库校验sql:select 'x'
db.validationquery=

db.maxactive=30

db.initialsize=1

db.maxwait=60000

db.minidle=3

db.timebetweenevictionrunsmillis=60000

db.minevictableidletimemillis=300000

db.testwhileidle=true

db.testonborrow=false

db.testonreturn=false

db.poolpreparedstatements=true

db.removeabandoned=true

db.removeabandonedtimeout=60

db.numtestsperevictionrun=60

db.maxopenpreparedstatements=20

db.filters=stat,wall
# 开关
flywaydb.enable=false
# flyway jdbc连接地址,如果为空取数据库连接地址
flywaydb.url=
# flyway 如果为空,取数据库用户名
flywaydb.username=
# flyway 如果为空,去数据库密码
flywaydb.password=
# flyway 版本管理表
flywaydb.table=flyway_schema_history
# 开关
log.enable=true
# 默认为idkey debug
log.appid=home-example-ewell
# 根日志级别 debug info  warn  error trance 默认debug# 从高到地低 OFF 、 FATAL 、 ERROR 、 WARN 、 INFO 、 DEBUG 、 TRACE 、 ALL -->
log.rootlevel=info
# 系统日志级别 默认debug
log.syslevel=info
# 业务日志级别 默认debug
log.businlevel=info
# 业务日志包路径,空的话默认为系统扫描到的包路径
log.businpackage=cn.bigcore.micro.example
# 日志存储位置
log.dir=target/log/home-example-ewell
# 开关
mybatis.enable=false
# 扫描mapper地址,默认为启动类下的 mapper文件夹
mybatis.basepackage=cn.bigcore.micro.example.mapper
# 扫描mapper地址,默认为启动类下的 mapper文件夹
mybatis.typealiasespackage=cn.bigcore.micro.example.mapper
# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹
mybatis.mapperlocations=classpath:**/mapper/**.xml
# 开关
mybatisplus.enable=false
# 扫描mapper地址,默认为启动类下的 mapper文件夹
mybatisplus.basepackage=cn.bigcore.micro.example.mapper
# 扫描mapper地址,默认为启动类下的 mapper文件夹
mybatisplus.typealiasespackage=cn.bigcore.micro.example.mapper
# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹
mybatisplus.mapperlocations=classpath:**/mapper/**.xml
# 开关
minio.enable=false
# api 请求地址 必填:http://localhost:9000
minio.endpoint=
# api 请求用户名:SDS*DSJDlerw9(were)
minio.accesskey=
# api 请求密钥:SDS*DSJDlerdsfsdf
minio.secretKey=
# 桶 命名空间,必填
minio.namespace_re=home-example-ewell
# 测试增加数据
minio.deom=demop
# 开关
redis.enable=false
# IP地址:192.168.3.251 使用方法:BaseEv.SettingInformation.redisds
redis.host=
# 端口号:6379
redis.port=
# 密码:123456
redis.password=
# 主干:mymaster
redis.masterName=
# 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
redis.BlockWhenExhausted=true
# 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
redis.evictionPolicyClassName=org.apache.commons.pool2.impl.DefaultEvictionPolicy
# 是否启用pool的jmx管理功能, 默认true
redis.jmxEnabled=true
# 是否启用后进先出, 默认true
redis.lifo=true
# 最大空闲连接数, 默认8个
redis.maxIdle=8
# 最小空闲连接数, 默认1
redis.minIdle=1
# 最大连接数, 默认8个
redis.maxTotal=8
# 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1
redis.maxWaitMillis=-1
# 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
redis.minEvictableIdleTimeMillis=1800000
# 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
redis.numTestsPerEvictionRun=3
# 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
redis.SoftMinEvictableIdleTimeMillis=1800000
# 在获取连接的时候检查有效性, 默认false
redis.testOnBorrow=true
# 在空闲时检查有效性, 默认false
redis.testWhileIdle=true
# 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1
redis.timeBetweenEvictionRunsMillis=-1
# 开关
swagger.enable=true
# swagger 默认扫描 api路径为启动类所在目录下的 api目录
swagger.basepackage=cn.bigcore.micro.example.api
# swagger命名,暂时命名为SwaggerAPI
swagger.name=SwaggeAPI
# 系统容器rest端口号
system.servlet.port=8080
# 设置上传文件大小
system.servlet.multipart.enable=true
# 不限制文件大小
system.servlet.multipart.max-file-size=-1
# 不限制请求体大小
system.servlet.multipart.max-request-size=-1
# 临时IO目录
#system.servlet.multipart.location=C:\Users\wangxuhui\\home\home-example-ewell\/temp/001
# 超过1Mb，就IO到临时目录
system.servlet.multipart.file-size-threshold=1
# 默认入参处理函数
#system.inputparamab.class=outgoing.cn.bigcore.micro.InputParamRe
# 示例扩展属性
extend.readme=示例值



```

# 🧬贡献代码的步骤

1. 在Gitee或者Github上fork项目到自己的repo
2. 把fork过去的项目也就是你的项目clone到你的本地
3. 修改代码（记得一定要修改v*-dev分支）
4. commit后push到自己的库（v*-dev分支）
5. 登录Gitee或Github在你首页可以看到一个 pull request 按钮，点击它，填写一些说明信息，然后提交即可。
6. 等待维护者合并

# 近期计划

# 技术支持

1. 代码开源,技术无价,如需提供技术支持和讲解请通过如下方式:
2. QQ:2233856992
3. email:2233856992@qq.com
4. 代码使用了各种开源组件,软件作为支撑,如有侵权请联系
5. 请勿将软件应用于法律要求范围外的场景
