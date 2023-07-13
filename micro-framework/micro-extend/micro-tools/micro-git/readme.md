# 说明

## 引入坐标

```xml

 <dependency>
    <groupId>cn.bigcore</groupId>
    <artifactId>micro-git</artifactId>
    <version>11.0.9-SNAPSHOT</version>
 </dependency>

```
     
     
     
## 需要引入hutools

```xml

<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.8.9</version>
</dependency>

```


## 使用

调用如下静态方法:

cn.bigcore.micro.gittools.GitOprationUtils.operation

String , String , String , OperationMethod[] , String , String , String , String , String , String , Set<String> , Set<String> , String 

1. projectWorkDir:主工作目录
2. code : gitlab编码,仅用于(增加,删除保护分支操作)
3. enName: 项目名
4. operationMethod: 需要调用的操作
5. sourcebranch: 操作源分支
6. targetbranch: 操作目标分支
7. codeUrl: githttpurl地址
8. username: 用户名
9. password: 密码
10. token: 仅用于(增加,删除保护分支操作)
11. lockBranch: 保护分支列表,仅用于(增加,删除保护分支操作)
12. allBranch: 所有分支列表
13. gitlabUrl: gitlab地址,仅用于(增加,删除保护分支操作)
