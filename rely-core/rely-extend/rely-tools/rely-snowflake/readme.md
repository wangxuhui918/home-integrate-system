# 说明

自定义雪花算法ID

## 引入坐标
```xml

 <dependency>
    <groupId>com.guoshiyao.rely</groupId>
     <version>10.1.3-RELEASE</version>
    <artifactId>home-tools-snowflake</artifactId>
  </dependency>

```
     
     
     
## 需要引入hutools

```xml


```


## 使用

调用如下静态方法:

1. IdUtils.workerId 初始化机器ID,默认1
2. 调用 IdUtils 中的方法获取雪花算法ID