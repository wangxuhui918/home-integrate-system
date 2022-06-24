# 📚简介
home-integrate-system(以下简称系统)，是一个轻量级,完全开源的Springboot框架，系统通过以类spi的方式封装常用工具插件（阿波罗，数据库，dubbo，flyway，git，mybatis，tk-mybatis，redis，雪花算法，swagger-ui，hutool等）
，降低了系统集成难度。

👉 实现自动配置（例如自动扫描Controller注解，无相关注解/Swagger配置则不加载Swagger）。


# 📘使用

通过mvn 进行安装

```shell

cd ../home-integrate-system

```
```shell

mvn install 
```

项目引入

```xml
        <!-- 单jar包方式引入,需要本地(home-integrate-system/home-integrate-package/home-integrate-packageextends/home-integrate-systemall) mvn install-->
        <dependency>
            <groupId>com.guoshiyao.rely</groupId>
             <version>9.0.5-RELEASE</version>
            <artifactId>home-integrate-systemall</artifactId>
        </dependency>
```
```xml
        <!-- pom关联方式引入,已经上传至maven中央库-->
        <dependency>
            <groupId>com.guoshiyao.rely</groupId>
            <version>9.0.5-RELEASE</version>
            <artifactId>home-integrate-systemallpom</artifactId>
        </dependency>
```

启用系统(创建Springboot启动类,参考如下方式加入注解)

```java
package cn.vesan.code;

import com.guoshiyao.rely.annotaion.Starter;
import com.guoshiyao.rely.message.i18n.I18n;
import org.springframework.boot.SpringApplication;

/**
 * @author 汪旭辉
 * @date 2022/3/10
 * @readme
 */
@Starter(idkey = "vesan-lowformat", i18n = I18n.CN)
public class LowFormatApplication {
    public static void main(String[] args) {
        SpringApplication.run(LowFormatApplication.class, args);
    }
}

```

注意:
首次启动会创建对应环境的配置文件(home-local.ini home-DEV.ini home-PRO.ini home-UAT.ini,以及消息码文件message-*.xml),
如果报错重新启动即可

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
    1. 反参要求为OutputParamAb子类,当反参非 OutputParamAb 会自动创建一个新的OutputParamAb类,并将结果加入OutputParamAb.data中
    2. 入参要求InputParamReLow<T>,可通过input.getInputData()获取入参实体类,并提供一系列方法
    3. 自动捕获Service中的业务异常并通过OutputParamAb加工返回给前端
    4. 反参目前实现了OutputParamRe
    5. Conttroller需要实现加入@RuleAnnotationApi 注解
}
```

## Service(业务异常处理)

```java
        if (1!=0) {
            throw new ExceptionError("关联关系未填写");//返回业务异常
            throw new ExceptionRe("A02");//message-*.xml文件中的消息码code
            throw new ExceptionAb();//自定义实现ExceptionAb类
        }
        
```

#配置说明

如果需要启用相关功能,打开对应配置+写入相关实现类(如果有,比如dubboService业务类,Controller实现类)

```ini


[1000001-DEV-apollo.ini]
# 管理端
#home.apollo.url=阿波罗管理地址,如果不填写则阿波罗不加载

#home.apollo.username=阿波罗管理用户名

#home.apollo.password=阿波罗管理密码
# 客户端
#home.apollo.app.id=minserver

#home.apollo.apollo.meta=http://106.54.227.205:8080
# dev,uat,pro 可以通过系统env传入,注释后自动通过传入变量决定,这个建议不传,系统自动识别
#home.apollo.apollo.env=LOCAL



[1000004-DEV-dubbo.ini]
# dubbo默认扫描包:com.guoshiyao.rely.dubboservice
#home.dubbo.reference.package=com.guoshiyao.rely.dubboservice
# dubbo连接超时时间
#home.dubbo.reference.timeout=120000
# dubbo注册中心地址
#home.dubbo.reference.url=zookeeper://zookeeper.com:2181
# dubbo协议
#home.dubbo.reference.agreement=dubbo
# dubbo本地地址
#home.dubbo.reference.host=127.0.0.1
# dubbo本地暴露端口
#home.dubbo.reference.port=20880
# dubbo 线程数
#home.dubbo.protocolconfig.threads=300
# #cached limited fixed all
#home.dubbo.protocolconfig.threadpool=fixed



[1000003-DEV-db.ini]
# 数据库连接地址
#home.db.url=jdbc:mysql://localhost:3306/home?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8
# 用户名
#home.db.username=root
# 密码
#home.db.password=password
# 驱动UrlClass
#home.db.driverclassname=com.mysql.cj.jdbc.Driver
#驱动下载地址,使用 url 后可以不填写home.db.driverclassname,MAVEN POM 中无需加载驱动坐标,优先级低于home.db.driverclassname
#home.db.driverclass.downloadurl=https://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.49/mysql-connector-java-5.1.49.jar
# 数据库校验sql
#home.db.validationquery=select 'x'

#home.db.maxactive=30

#home.db.initialsize=1

#home.db.maxwait=60000

#home.db.minidle=3

#home.db.timebetweenevictionrunsmillis=60000

#home.db.minevictableidletimemillis=300000

#home.db.testwhileidle=true

#home.db.testonborrow=false

#home.db.testonreturn=false

#home.db.poolpreparedstatements=true

#home.db.removeabandoned=true

#home.db.removeabandonedtimeout=60

#home.db.numtestsperevictionrun=60

#home.db.maxopenpreparedstatements=20

#home.db.filters=stat,wall



[1000006-DEV-flyway.ini]
# flyway jdbc连接地址,如果为空取数据库连接地址
#home.flywaydb.url=
# flyway 如果为空,取数据库用户名
#home.flywaydb.username=
# flyway 如果为空,去数据库密码
#home.flywaydb.password=
# flyway 版本管理表
#home.flywaydb.table=flyway_schema_history



[1000007-DEV-gitlab.ini]
# git代码文件夹目录
#home.gitlab.gitdir=/Users/wangxuhui//home/minserver//git/download
# gitlab的地址
#home.gitlab.gitlaburl=http://192.168.150.61:2080/
# gitlab管理token
#home.gitlab.token=
# gitlab用户名
#home.gitlab.username=wangxuhui
# gitlab密码
#home.gitlab.password=
# gitlab checout default branch
#home.gitlab.branch=master
# gitlab log
#home.gitlab.log=/Users/wangxuhui//home/minserver//git/log/log.txt


[1000008-DEV-logback.ini]
# 默认为idkey debug
home.log.appid=minserver
# 根日志级别 debug info  warn  error trance 默认debug# 从高到地低 OFF 、 FATAL 、 ERROR 、 WARN 、 INFO 、 DEBUG 、 TRACE 、 ALL -->
home.log.rootlevel=debug
# 系统日志级别 默认debug
home.log.syslevel=debug
# 业务日志级别 默认debug
home.log.businlevel=debug
# 业务日志包路径,空的话默认为系统扫描到的包路径
home.log.businpackage=com.guoshiyao.rely
# 日志存储位置
#home.log.dir=target/log/minserver

[1000011-DEV-mybatis.ini]
# 扫描mapper地址,默认为启动类下的 mapper文件夹
#home.mybatis.basepackage=com.guoshiyao.rely.mapper
# 扫描mapper地址,默认为启动类下的 mapper文件夹
#home.mybatis.typealiasespackage=com.guoshiyao.rely.mapper
# 扫描mapper.xml,默认为资源文件夹下的 mapper文件夹
#home.mybatis.mapperlocations=classpath:**/mapper/**.xml


[1000016-DEV-minio.ini]
# api 请求地址 必填
#home.minio.endpoint=http://localhost:9000
# api 请求用户名
#home.minio.accesskey=SDS*DSJDlerw9(were)
# api 请求密钥
#home.minio.secretKey=SDS*DSJDlerdsfsdf
# 桶 命名空间,必填
#home.minio.namespace_re=minserver
# 测试增加数据
#home.minio.deom=demop

[1000012-DEV-redis.ini]
# 使用方法为 com.guoshiyao.rely.line.Line.redisds.getjedis()# 地址，默认localhost
#home.redis.host=192.168.3.251
# 端口号
#home.redis.port=6379
# 密码
#home.redis.password=123456
# masterName
#home.redis.masterName=mymaster
# 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
#home.redis.BlockWhenExhausted=true
# 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
#home.redis.evictionPolicyClassName=org.apache.commons.pool2.impl.DefaultEvictionPolicy
# 是否启用pool的jmx管理功能, 默认true
#home.redis.jmxEnabled=true
# 是否启用后进先出, 默认true
#home.redis.lifo=true
# 最大空闲连接数, 默认8个
#home.redis.maxIdle=8
# 最小空闲连接数, 默认0
#home.redis.minIdle=1
# 最大连接数, 默认8个
#home.redis.maxTotal=8
# 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1
#home.redis.maxWaitMillis=-1
# 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
#home.redis.minEvictableIdleTimeMillis=1800000
# 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
#home.redis.numTestsPerEvictionRun=3
# 对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
#home.redis.SoftMinEvictableIdleTimeMillis=1800000
# 在获取连接的时候检查有效性, 默认false
#home.redis.testOnBorrow=true
# 在空闲时检查有效性, 默认false
#home.redis.testWhileIdle=true
# 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1#经过框架测试当前值为 -1 时系统启动会报转换异常,建议注释或者值>-1
#home.redis.timeBetweenEvictionRunsMillis=-1



[1000013-DEV-swagger.ini]
# swagger 默认扫描 api路径为启动类所在目录下的 api目录
#home.swagger.basepackage=com.guoshiyao.rely.api
# swagger命名,暂时命名为SwaggerAPI
#home.swagger.name=SwaggeAPI


[1000014-DEV-systemconfig.ini]
# 系统容器rest端口号
system.servlet.port=8081


[1000015-DEV-systemextend.ini]
# 示例扩展属性
home.extend.readme=示例值


```


# 🧬贡献代码的步骤

1. 在Gitee或者Github上fork项目到自己的repo
2. 把fork过去的项目也就是你的项目clone到你的本地
3. 修改代码（记得一定要修改v*-dev分支）
4. commit后push到自己的库（v*-dev分支）
5. 登录Gitee或Github在你首页可以看到一个 pull request 按钮，点击它，填写一些说明信息，然后提交即可。
6. 等待维护者合并

# 近期计划

1. 调整环境变量依赖,初试环境变量配置依赖手动初始化配置,不再依赖系统

# 商务支持

1. 代码开源,技术无价,如需提供技术支持和讲解请通过如下方式:
2. QQ:185600894
3. email:185600894@qq.com
4. 代码使用了各种开源组件,软件作为支撑,如有侵权请联系
5. 请勿将软件应用于法律要求范围外的场景


# 捐赠计划

![img.png](img.png)
