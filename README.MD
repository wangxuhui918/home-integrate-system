# 📚简介
home-integrate-system(以下简称系统)，是一个轻量级的Springboot框架，系统通过以类spi的方式封装常用工具插件（阿波罗，数据库，dubbo，flyway，git，mybatis，tk-mybatis，redis，雪花算法，swagger-ui，hutool等）
，降低了系统集成难度。

👉 实现自动配置（例如自动扫描Controller注解，无相关注解/Swagger配置则不加载Swagger）。


# 📘使用

通过mvn 进行安装

```

mvn install ../home-integrate-system

```

项目引入

```xml
        <!-- 单jar包方式引入-->
        <dependency>
            <groupId>com.guoshiyao.home</groupId>
            <version>9.0.1-SNAPSHOT</version>
            <artifactId>home-integrate-systemall</artifactId>
        </dependency>
```
```xml
        <!-- pom关联方式引入-->
        <dependency>
            <groupId>com.guoshiyao.home</groupId>
            <version>9.0.1-SNAPSHOT</version>
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


# 🧬贡献代码的步骤

1. 在Gitee或者Github上fork项目到自己的repo
2. 把fork过去的项目也就是你的项目clone到你的本地
3. 修改代码（记得一定要修改v*-dev分支）
4. commit后push到自己的库（v*-dev分支）
5. 登录Gitee或Github在你首页可以看到一个 pull request 按钮，点击它，填写一些说明信息，然后提交即可。
6. 等待维护者合并