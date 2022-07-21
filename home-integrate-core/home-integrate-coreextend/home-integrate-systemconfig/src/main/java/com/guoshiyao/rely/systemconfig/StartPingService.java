

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.systemconfig;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import org.springframework.boot.CommandLineRunner;

//@Component
public class StartPingService implements CommandLineRunner {
    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) {
        LoggerBaseAb.info("用户目录 : {} [key]:[{}] ", Line.userHomeDir, "Line.userHomeDir");
        LoggerBaseAb.info("运行用户名 : {} [key]:[{}] ", Line.systemUserName, "Line.systemUserName");
        LoggerBaseAb.info("核心包 : {} [key]:[{}] ", Line.corePacket, "Line.corePacket");
        LoggerBaseAb.info("项目源码目录 : {} [key]:[{}] ", Line.projectresourcepath, "Line.projectresourcepath");
        LoggerBaseAb.info("项目资源目录 : {} [key]:[{}] ", Line.projectcodesourcepath, "Line.projectcodesourcepath");
        LoggerBaseAb.info("系统托管类 : {} [key]:[{}] ", Line.iocclasses.size() + "", "Line.iocclasses");
        LoggerBaseAb.info("项目参数 : {} [key]:[{}] ", Line.setting.size() + "", "Line.setting");
        LoggerBaseAb.info("默认模板引擎参数 : {} [key]:[{}] ", Line.context + "", "Line.context");
        LoggerBaseAb.info("当前主机Mac地址 : {} [key]:[{}] ", Line.macSet.size() + "", "Line.macSet");
        LoggerBaseAb.info("项目ID : {} [key]:[{}] ", Line.idKey, "Line.idKey");
        LoggerBaseAb.info("项目分布式ID : {} [key]:[{}] ", Line.distributedKey + "", "Line.distributedKey");
        LoggerBaseAb.info("项目语言 : {} [key]:[{}] ", Line.i18n, "Line.i18n");
        LoggerBaseAb.info("当前运行环境 : {} [key]:[{}] ", Line.runEnv, "Line.runEnv");
        LoggerBaseAb.info("支持运行环境 : {} [key]:[{}] ", JSONUtil.toJsonStr(Line.configEnv), "Line.runEnv");
        LoggerBaseAb.info("当前主启动类 : {} [key]:[{}] ", Line.mainClass, "Line.mainClass");
        LoggerBaseAb.info("当前主Mac地址 : {} [key]:[{}] ", Line.mainMac, "Line.mainMac");
//        LoggerBaseAb.info("当前主启动类 : {} [key]:[{}] ", Line.mainClassC, "Line.mainClassC");
        LoggerBaseAb.info("jar包所在目录 : {} [key]:[{}] ", Line.jarpath, "Line.jarpath");
        LoggerBaseAb.info("项目包路径 : {} [key]:[{}] ", Line.projectPackage, "Line.projectPackage");
        LoggerBaseAb.info("运行状态(true[开发模式],false[运行时模式]) : {} [key]:[{}] ", Line.isClassModel + "", "Line.isClassModel");
        LoggerBaseAb.info("默认数据库资源池 : {} [key]:[{}] ", Line.dataSource == null ? "" : Line.dataSource.toString(), "Line.dataSource");
        LoggerBaseAb.info("项目工作目录 : {} [key]:[{}] ", Line.workHomeDir, "Line.workHomeDir");
        LoggerBaseAb.info("PC标记 : {} [key]:[{}] ", Line.UK, "Line.UK");
        LoggerBaseAb.info("是否自动更新 : {} [key]:[{}] ", Line.autoUpdate + "", "Line.autoUpdate");
        LoggerBaseAb.info("默认redis连接池 : {} [key]:[{}] ", Line.redisds == null ? "" : Line.redisds.toString(), "Line.redisds");
        LoggerBaseAb.info("默认jdbc_jar包路径 : {} [key]:[{}] ", Line.main_jdbc_jar_fullpath, "Line.main_jdbc_jar_fullpath");
        LoggerBaseAb.info("swagger 地址 : {} ", "http://127.0.0.1:" + Line.setting.get("system.servlet.port") + "/swagger-ui/index.html");
        System.out.println(ResourceUtil.readUtf8Str("ban.txt"));
        LoggerBaseAb.info("启动完成[{}]", "SpingBoot");
    }
}

