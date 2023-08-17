# 1.📚简介

&emsp;&emsp;***home-integrate-system***(帆有云)是一个轻量级开源的Springboot框架,以插件方式提供功能扩展方便二次开发.统一微服务开发标准,方便管理,节约成本.

## 已经内置的插件
1. 阿波罗
2. 数据库
3. dubbo
4. flyway
5. git
6. mybatis
7. tk-mybatis
8. redis
9. 雪花算法
10. swagger-ui
11. hutool
12. 其他工具

# 2.📘安装

pom.xml文件引入parent和dependency节点,如下:

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>
    <groupId>cn.bigcore</groupId>
    <version>11.0.10-SNAPSHOT</version>
    <artifactId>micro-example</artifactId>
    <parent>
        <artifactId>micro-dependencies</artifactId>
        <groupId>cn.bigcore</groupId>
        <version>11.0.10-SNAPSHOT</version>
    </parent>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>cn.bigcore</groupId>
            <artifactId>micro-pack</artifactId>
            <version>11.0.10-SNAPSHOT</version>
        </dependency>
    </dependencies>

</project>

```

# 3.使用

启动类加上注解 **FyyStarter**,如下

```java

package cn.bigcore.example;


import cn.bigcore.micro.bean.FyyBean;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.starter.FyyStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

@FyyStarter(idkey = "home-example-ewell"})
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}

```


# 4.启动

直接运行启动类即可(初次使用会生成项目结构)


# 5.说明


## 5.1 启动类说明

### 5.1.1 使用说明

使用@FyyStarter注解类作为本框架的唯一入口,通过该注解类完成系统的启动,装配以及各项功能启动.如下:

```java

@FyyStarter(idkey = "home-example-ewell"})

```

### 5.1.2 其他属性:


|属性|说明|默认值|必填|
|----|----|----|----|
|idkey| 项目唯一ID(区分微服务,读取日志,获取apollo配置等) | 必填项                   | ✅  |
|i18n| 区域编码(消息码,国际化编码等) | I18n.CN               | ❎ |
|runEnv| 当前环境变量 | ...                   | ❎  |
|configEnv| 环境变量范围 | dev,uat,pro           | ❎  |
|exclude| 不加载的类 | ...                   | ❎  |
|scanBasePackages| 扫描包 | cn.vesan.code(启动类所在包) | ❎  |


## 5.2 fyy.json(用户唯一标识)

框架在用户根目录下生成一个 ***fyy.json*** 文件,文件内容如下

```aidl
project_config:项目详细配置
         project_config-key:项目名称
         project_config-project_name_en:项目名
         prject_application_properties_path:指定配置文件
develop_user_id:用户唯一标识        
```

```json

{
    "project_config":
    {
        "home-example-ewell1":
        {
            "project_name_en": "home-example-ewell1",
            "project_name_cn": "home-example-ewell1",
            "prject_application_properties_path": ""
        },
        "home-example-ewell":
        {
            "project_name_en": "home-example-ewell",
            "project_name_cn": "home-example-ewell",
            "prject_application_properties_path": ""
        }
    },
    "develop_user_id": "wangxuhui"
}

```


并在项目下会生成:***application-用户唯一标识.properties*** 配置文件


 ***注:用户唯一标识可以自定义,建议不要与其他同事重复***


## 5.3 配置文件

### 5.3.1 配置文件生成范围
框架按照 ***@FyyStarter.configEnv*** 属性(默认为:dev,uat,pro)生成对应的配置文件,对应生成如下配置文件

| configEnv | 对应配置文件                     |
|-----------|----------------------------|
| dev       | application-dev.properties |
| uat       | application-uat.properties |
| pro       | application-pro.properties |
| 用户唯一标识    | application-用户唯一标识.properties |

也可以重写***@FyyStarter.configEnv***属性,例如:

```java

@FyyStarter(idkey = "home-example-ewell", configEnv = {"kaifa", "ceshi"})
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}

```
对应生成如下配置文件

| configEnv | 对应配置文件                     |
|-----------|----------------------------|
| kaifa       | application-kaifa.properties |
| ceshi       | application-ceshi.properties |
| 用户唯一标识    | application-用户唯一标识.properties |

### 5.3.1 配置文件读取优先级

@FyyStarter.runEnv>运行时环境变量'-Denv={}'>用户唯一标识,例如框架已有如下配置前提下:

| configEnv | 对应配置文件                     |
|-----------|----------------------------|
| dev       | application-dev.properties |
| uat       | application-uat.properties |
| pro       | application-pro.properties |
| 用户唯一标识    | application-用户唯一标识.properties |

读取的配置文件如下所示:

| @Starter.runEnv | -Denv={}    | 用户唯一标识   |结果| 说明                                  |
|-----------------|-------------|----------|-----------|-------------------------------------|
| dev             | uat         | zhangsan      |application-dev.properties| 只要runEnv有值直接读取该配置                   |
| -----------     | uat         | zhangsan      |application-uat.properties| 未指定runEnv的情况下会读取环境变量-Denv={}值       |
| -----------     | ----------- | zhangsan |application-zhangsan.properties| @Starter.runEnv,-Denv={} 均未指定的情况下读取 用户唯一标识|

***注:上述指定的变量必须在 配置文件生成范围 内,否则系统会提示错误***

## 5.4 消息码

框架启动后会在resources资源目录下生成message-{}.xml文件,标识符取决于@Starter.i18n属性,具体范围见:
***cn.bigcore.micro.plugin.i18n.FyyI18n***

## 5.5 主配置类

用以获取系统启动前后的各项参数和配置:

***cn.bigcore.micro.FyyInitEnv***


# 5.📝部分功能的使用



## Domain(数据库对象)

```java

package cn.bigcore.manager.api.domain;

import java.util.Date;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Table(name = "`dict_object_type`")
@ApiModel(value = "对象类型")
public class DictObjectTypeDomain implements Serializable {

    /**
     * 类型编码,例如1,2,3
     */
    @Id
    @Column(name = "code")
    @ApiModelProperty(value = "类型编码,例如1,2,3")
    private String code;


    /**
     * 类型名称,例如患者,医院,终端,等,该表数据增加来源代码,通过表控制权限
     */
    @Column(name = "name")
    @ApiModelProperty(value = "类型名称,例如患者,医院,终端,等,该表数据增加来源代码,通过表控制权限")
    private String name;

    /**
     * 创建人ID
     */
    @Column(name = "create_user_id")
    @ApiModelProperty(value = "创建人ID")
    private String createUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @Column(name = "mark")
    @ApiModelProperty(value = "备注")
    private String mark;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}

```

## Vo(页面表现层对象)

```java

package cn.bigcore.manager.api.vo;

import java.util.Date;

import java.lang.String;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 对象类型(`dict_object_type`)页面对象
 *
 * @author bigcore
 * @since 2023-07-14 11:16:46
 */
@ApiModel(value = "对象类型")
public class DictObjectTypeVo implements Serializable {

    /**
     * 类型编码,例如1,2,3
     */
    @ApiModelProperty(value = "类型编码,例如1,2,3", example = "类型编码,例如1,2,3")
    private String code;

    /**
     * 类型名称,例如患者,医院,终端,等,该表数据增加来源代码,通过表控制权限
     */
    @ApiModelProperty(value = "类型名称,例如患者,医院,终端,等,该表数据增加来源代码,通过表控制权限", example = "类型名称,例如患者,医院,终端,等,该表数据增加来源代码,通过表控制权限")
    private String name;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID", example = "创建人ID")
    private String createUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", example = "创建时间")
    private String createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", example = "更新时间")
    private String updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", example = "备注")
    private String mark;


    public java.lang.String getCode() {
        return code;
    }

    public void setCode(java.lang.String code) {
        this.code = code;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(java.lang.String createUserId) {
        this.createUserId = createUserId;
    }

    public java.lang.String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.lang.String createTime) {
        this.createTime = createTime;
    }

    public java.lang.String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.lang.String updateTime) {
        this.updateTime = updateTime;
    }

    public java.lang.String getMark() {
        return mark;
    }

    public void setMark(java.lang.String mark) {
        this.mark = mark;
    }

}



```

## 持久层接口

```java

package cn.bigcore.manager.mapper;

import java.util.Date;

import cn.bigcore.manager.api.domain.DictObjectTypeDomain;
import tk.mybatis.mapper.common.Mapper;

/**
 * 对象类型(`dict_object_type`)持久层接口
 *
 * @author bigcore
 * @since 2023-07-14 11:16:47
 */
public interface DictObjectTypeMapper extends Mapper<DictObjectTypeDomain> {


    //-----------------------以上为默认方法,新方法请写在下面------------------------//
}



```

## 持久层逻辑(*.xml)

```xml

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.bigcore.manager.mapper.DictObjectTypeMapper">

    <resultMap type="cn.bigcore.manager.api.domain.DictObjectTypeDomain" id="DictObjectTypeMap">
        <id property="code" column="code" jdbcType="VARCHAR"/>
        <result property="name" column="name" jdbcType="VARCHAR"/>
        <result property="createUserId" column="create_user_id" jdbcType="VARCHAR"/>
        <result property="createTime" column="create_time" jdbcType="TIMESTAMP"/>
        <result property="updateTime" column="update_time" jdbcType="TIMESTAMP"/>
        <result property="mark" column="mark" jdbcType="VARCHAR"/>
    </resultMap>

    <!--以上为默认方法,新方法请写在下面-->


</mapper>



```

## 公共应用层(application)

```java

package cn.bigcore.manager.application;

import java.util.Date;

import cn.bigcore.manager.api.domain.DictObjectTypeDomain;
import cn.bigcore.manager.api.vo.DictObjectTypeVo;
import cn.bigcore.manager.mapper.DictObjectTypeMapper;

import java.util.List;

import cn.bigcore.micro.exception.re.ex.FyyExceptionError;
import cn.bigcore.micro.mybatis.starter.mybatis.other.base.FyyBaseBiz;
import cn.bigcore.micro.snowflake.utils.FyyIdUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

/**
 * 对象类型(`dict_object_type`)公共应用原子层
 *
 * @author bigcore
 * @since 2023-07-14 11:16:47
 */
@Component("dictObjectTypeApplication")
public class DictObjectTypeApplication extends FyyBaseBiz<DictObjectTypeMapper, DictObjectTypeDomain> {

    /**
     * 新增数据
     *
     * @param dictObjectType 实例对象
     * @return 实例对象
     */
    public int insert(DictObjectTypeVo dictObjectType) {
        String code = FyyIdUtils.genIdString();
        dictObjectType.setCode(code);
        DictObjectTypeDomain domain = new DictObjectTypeDomain();
        BeanUtil.copyProperties(dictObjectType, domain);
        return mapper.insert(domain);
    }

    /**
     * 通过主键删除数据
     *
     * @param 主键
     * @return 是否成功
     */
    public int deleteByKey(DictObjectTypeVo dictObjectType) {
        if (StrUtil.isBlank(dictObjectType.getCode())) {
            throw new FyyExceptionError("主键{}不能为空", "Code");
        }
        DictObjectTypeDomain domain = new DictObjectTypeDomain();
        BeanUtil.copyProperties(dictObjectType, domain);
        return mapper.deleteByPrimaryKey(domain);
    }

    /**
     * 修改数据
     *
     * @param dictObjectType 实例对象
     * @return 实例对象
     */
    public int updateByKey(DictObjectTypeVo dictObjectType) {
        if (StrUtil.isBlank(dictObjectType.getCode())) {
            throw new FyyExceptionError("主键{}不能为空", "Code");
        }
        DictObjectTypeDomain domain = new DictObjectTypeDomain();
        BeanUtil.copyProperties(dictObjectType, domain);
        return mapper.updateByPrimaryKeySelective(domain);
    }

    /**
     * 按照条件查询
     *
     * @param dictObjectType 筛选条件
     * @return 查询结果
     */
    public List<DictObjectTypeVo> select(DictObjectTypeVo dictObjectType) {
        DictObjectTypeDomain domain = new DictObjectTypeDomain();
        BeanUtil.copyProperties(dictObjectType, domain);
        List<DictObjectTypeDomain> listDomain = mapper.select(domain);
        if (listDomain == null || listDomain.size() == 0) {
            return null;
        }
        return BeanUtil.copyToList(listDomain, DictObjectTypeVo.class);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param 主键
     * @return 实例对象
     */
    public DictObjectTypeVo selectByKey(DictObjectTypeVo dictObjectType) {
        DictObjectTypeDomain domain = new DictObjectTypeDomain();
        if (StrUtil.isBlank(dictObjectType.getCode())) {
            throw new FyyExceptionError("主键{}不能为空", "Code");
        }
        domain.setCode(dictObjectType.getCode());
        domain = mapper.selectOne(domain);
        if (domain == null) {
            return null;
        }
        DictObjectTypeVo info = new DictObjectTypeVo();
        BeanUtil.copyProperties(domain, info);
        return info;
    }

    //-----------------------以上为默认方法,新方法请写在下面------------------------//

}


```

## 业务层接口

```java

package cn.bigcore.manager.service;

import java.util.Date;

import cn.bigcore.manager.api.vo.DictObjectTypeVo;

import java.util.List;

/**
 * 对象类型(`dict_object_type`)逻辑层接口
 *
 * @author bigcore
 * @since 2023-07-14 11:16:46
 */
public interface DictObjectTypeService {

    /**
     * 新增数据
     *
     * @param dictObjectType 实例对象
     * @return 实例对象
     */
    int insert(DictObjectTypeVo dictObjectType);

    /**
     * 通过主键删除数据
     *
     * @param 主键
     * @return 是否成功
     */
    int deleteByKey(DictObjectTypeVo dictObjectType);

    /**
     * 修改数据
     *
     * @param dictObjectType 实例对象
     * @return 实例对象
     */
    int updateByKey(DictObjectTypeVo dictObjectType);

    /**
     * 按照条件查询
     *
     * @param dictObjectType 筛选条件
     * @return 查询结果
     */
    List<DictObjectTypeVo> select(DictObjectTypeVo dictObjectType);

    /**
     * 通过ID查询单条数据
     *
     * @param 主键
     * @return 实例对象
     */
    DictObjectTypeVo selectByKey(DictObjectTypeVo dictObjectType);

    //-----------------------以上为默认方法,新方法请写在下面------------------------//

}


```


## 业务层逻辑

```java

package cn.bigcore.manager.service.impl;

import java.util.Date;

import cn.bigcore.manager.api.domain.DictObjectTypeDomain;
import cn.bigcore.manager.api.vo.DictObjectTypeVo;
import cn.bigcore.manager.mapper.DictObjectTypeMapper;
import cn.bigcore.manager.service.DictObjectTypeService;
import cn.bigcore.manager.application.DictObjectTypeApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.Resource;

/**
 * 对象类型(`dict_object_type`)逻辑层实现类
 *
 * @author bigcore
 * @since 2023-07-14 11:16:46
 */
@Service("dictObjectTypeService")
public class DictObjectTypeServiceImpl implements DictObjectTypeService {
    @Resource
    private DictObjectTypeApplication applicat;

    /**
     * 新增数据
     *
     * @param dictObjectType 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(DictObjectTypeVo dictObjectType) {
        return applicat.insert(dictObjectType);
    }

    /**
     * 通过主键删除数据
     *
     * @param 主键
     * @return 是否成功
     */
    @Override
    public int deleteByKey(DictObjectTypeVo dictObjectType) {
        return applicat.deleteByKey(dictObjectType);
    }

    /**
     * 修改数据
     *
     * @param dictObjectType 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByKey(DictObjectTypeVo dictObjectType) {
        return applicat.updateByKey(dictObjectType);
    }

    /**
     * 按照条件查询
     *
     * @param dictObjectType 筛选条件
     * @return 查询结果
     */
    @Override
    public List<DictObjectTypeVo> select(DictObjectTypeVo dictObjectType) {
        return applicat.select(dictObjectType);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param 主键
     * @return 实例对象
     */
    @Override
    public DictObjectTypeVo selectByKey(DictObjectTypeVo dictObjectType) {
        return applicat.selectByKey(dictObjectType);
    }

    //-----------------------以上为默认方法,新方法请写在下面------------------------//

}


```

## 控制层逻辑(Controller)


```java

package cn.bigcore.manager.api;

import java.util.Date;

import cn.bigcore.manager.api.vo.DictObjectTypeVo;
import cn.bigcore.manager.service.DictObjectTypeService;
import cn.bigcore.micro.annotation.FyyRuleController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import cn.bigcore.manager.configuration.ETLInputParamRe;

/**
 * 对象类型(`dict_object_type`)控制层
 *
 * @author bigcore
 * @since 2023-07-14 11:16:47
 */
@FyyRuleController
@Controller
@Api(tags = "对象类型(DictObjectType)控制层")
public class DictObjectTypeController {
    @Resource
    private DictObjectTypeService service;
    //-----------------------默认方法,默认关闭------------------------//

//    @ApiOperation(value = "新增对象类型(DictObjectType)", notes = " ")
//    @RequestMapping(value = "/dictObjectType/insert", method = {RequestMethod.POST})
//    @ResponseBody
//    public Object insert(@RequestBody ETLInputParamRe<DictObjectTypeVo> input) {
//        return service.insert(input.getInputData());
//    }

//    @ApiOperation(value = "删除对象类型(DictObjectType)", notes = " ")
//    @RequestMapping(value = "/dictObjectType/deleteByKey", method = {RequestMethod.POST})
//    @ResponseBody
//    public Object deleteByKey(@RequestBody ETLInputParamRe<DictObjectTypeVo> input) {
//        return service.deleteByKey(input.getInputData());
//    }

//    @ApiOperation(value = "修改对象类型(DictObjectType)", notes = " ")
//    @RequestMapping(value = "/dictObjectType/updateByKey", method = {RequestMethod.POST})
//    @ResponseBody
//    public Object updateByKey(@RequestBody ETLInputParamRe<DictObjectTypeVo> input) {
//        return service.updateByKey(input.getInputData());
//    }

//    @ApiOperation(value = "查询对象类型(DictObjectType)", notes = " ")
//    @RequestMapping(value = "/dictObjectType/select", method = {RequestMethod.POST})
//    @ResponseBody
//    public Object select(@RequestBody ETLInputParamRe<DictObjectTypeVo> input) {
//        return service.select(input.getInputData());
//    }

//    @ApiOperation(value = "按照主键查询对象类型(DictObjectType)", notes = " ")
//    @RequestMapping(value = "/dictObjectType/selectByKey", method = {RequestMethod.POST})
//    @ResponseBody
//    public Object selectByKey(@RequestBody ETLInputParamRe<DictObjectTypeVo> input) {
//        return service.selectByKey(input.getInputData());
//    }

    //-----------------------以上为默认方法,新方法请写在下面------------------------//

}



```

## 控制层(Controller其他用法)

```java

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.example.api;

import cn.bigcore.example.api.vo.DemoVo;

import cn.bigcore.micro.annotation.FyyRuleController;
import cn.bigcore.micro.exception.FyyCodeRe;
import cn.bigcore.micro.exception.re.ex.FyyExceptionError;
import cn.bigcore.micro.outgoing.FyyInputParamRe;
import cn.bigcore.micro.outgoing.FyyOutputParamRe;
import cn.bigcore.micro.outgoing.utils.FyyCodeUtils;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@FyyRuleController
@Controller
public class RedirectController extends BaseController {

    @ApiOperation(value = "读取文件", notes = " ")
    @RequestMapping(value = "/example/readCodeFile", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String readCodeFile(String fileResourcePath) {
        return ResourceUtil.readUtf8Str(fileResourcePath);
    }


    @ApiOperation(value = "安装流程", notes = " ")
    @RequestMapping(value = "index", method = {RequestMethod.POST, RequestMethod.GET})
    public String mainindex() {
        return "example/index.html";
    }

    @ApiOperation(value = "安装流程", notes = " ")
    @RequestMapping(value = "/example/{index}", method = {RequestMethod.POST, RequestMethod.GET})
    public String index(@PathVariable String index) {
        return "example/" + index + ".html";
    }


    @ApiOperation(value = "样例", notes = " ")
    @RequestMapping(value = "/example/example", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object example(@RequestBody FyyInputParamRe<DemoVo> input) {
        return input.getInputData().getDemo_id();
    }


    @ApiOperation(value = "样例", notes = " ")
    @RequestMapping(value = "/example/output2", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public FyyOutputParamRe output2(@RequestBody FyyInputParamRe<DemoVo> input) {
        return FyyCodeUtils.go(new FyyCodeRe("A001", "你的"));
    }

    @ApiOperation(value = "样例", notes = " ")
    @RequestMapping(value = "/example/output3", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public void output3(@RequestBody FyyInputParamRe<DemoVo> input) {
        if (StrUtil.equals(input.getInputData().getName(), "阿莫西林")) {//正常的业务异常
            throw new FyyExceptionError("药品{},库存不足", "阿莫西林");
        } else {//继续处理流程
            //1. 进行库存扣减正常业务,此时会返回给前端内置true的消息体
            //2. 如果此时出现空指针等异常也会封装为false消息码体并返回
        }
    }
}

    1.反参要求为FyyOutputParamAb子类,
    当反参非 FyyOutputParamAb
    会自动创建一个新的FyyOutputParamAb类,并将结果加入FyyOutputParamAb.data中
    2.入参要求FyyInputParamReLow<T>,可通过input.getInputData()获取入参实体类,并提供一系列方法
    3.自动捕获Service中的业务异常并通过FyyOutputParamAb加工返回给前端
    4.反参目前实现了FyyOutputParamRe
    5.
    Conttroller需要实现加入 @FyyRuleController 注解
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
