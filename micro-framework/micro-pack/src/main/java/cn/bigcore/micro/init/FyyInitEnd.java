

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.init;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.FyyProperties;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.bigcore.micro.snowflake.utils.FyyIdUtils;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import cn.bigcore.micro.base.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.log.utils.FyyLoggerUtil;
import org.springframework.boot.CommandLineRunner;

public class FyyInitEnd implements CommandLineRunner {

    @Override
    public void run(String... args) {
        FyyLogBaseUtils.info("开始翻转日志管理器!!");
        FyyLogBaseUtils.custLogger = new FyyLoggerUtil();
        FyyLogBaseUtils.info("开始处理雪花算法集群ID!!");
        FyyIdUtils.workerId = FyyInitEnv.distributedKey;


        FyyLogBaseUtils.info("用户目录 : {} [key]:[{}] ", FyyInitEnv.userHomeDir, "FyyInitEnv..userHomeDir");
        FyyLogBaseUtils.info("运行用户名 : {} [key]:[{}] ", FyyInitEnv.systemUserName, "FyyInitEnv..systemUserName");
        FyyLogBaseUtils.info("核心包 : {} [key]:[{}] ", FyyProperties.setting.get("fyy.system.core.package"), "FyyInitEnv..corePacket");
        FyyLogBaseUtils.info("项目源码目录 : {} [key]:[{}] ", FyyInitEnv.projectresourcepath, "FyyInitEnv..projectresourcepath");
        FyyLogBaseUtils.info("项目资源目录 : {} [key]:[{}] ", FyyInitEnv.projectcodesourcepath, "FyyInitEnv..projectcodesourcepath");
        FyyLogBaseUtils.info("系统托管类 : {} [key]:[{}] ", FyyInitEnv.iocclasses.size() + "", "FyyInitEnv..iocclasses");
        FyyLogBaseUtils.info("项目参数 : {} [key]:[{}] ", FyyInitEnv.setting.size() + "", "FyyInitEnv..setting");
        FyyLogBaseUtils.info("默认模板引擎参数 : {} [key]:[{}] ", FyyInitEnv.context + "", "FyyInitEnv..context");
        FyyLogBaseUtils.info("当前主机Mac地址 : {} [key]:[{}] ", FyyInitEnv.macSet.size() + "", "FyyInitEnv..macSet");
        FyyLogBaseUtils.info("项目ID : {} [key]:[{}] ", FyyProperties.setting.get("fyy.project.core.default_key"), "FyyInitEnv..idKey");
        FyyLogBaseUtils.info("项目分布式ID : {} [key]:[{}] ", FyyInitEnv.distributedKey + "", "FyyInitEnv..distributedKey");
        FyyLogBaseUtils.info("项目语言 : {} [key]:[{}] ", FyyProperties.setting.getStr("fyy.project.core.i18n"), "FyyInitEnv..i18n");
        FyyLogBaseUtils.info("当前运行环境 : {} [key]:[{}] ", FyyInitEnv.runEnv, "FyyInitEnv..runEnv");
        FyyLogBaseUtils.info("支持运行环境 : {} [key]:[{}] ", JSONUtil.toJsonStr(FyyInitEnv.configEnv), "FyyInitEnv..runEnv");
        FyyLogBaseUtils.info("当前主启动类 : {} [key]:[{}] ", FyyInitEnv.mainClass, "FyyInitEnv..mainClass");
        FyyLogBaseUtils.info("当前主Mac地址 : {} [key]:[{}] ", FyyInitEnv.mainMac, "FyyInitEnv..mainMac");
        FyyLogBaseUtils.info("jar包所在目录 : {} [key]:[{}] ", FyyInitEnv.jarpath, "FyyInitEnv..jarpath");
        FyyLogBaseUtils.info("项目包路径 : {} [key]:[{}] ", FyyInitEnv.projectPackage, "FyyInitEnv..projectPackage");
        FyyLogBaseUtils.info("运行状态(true[开发模式],false[运行时模式]) : {} [key]:[{}] ", FyyInitEnv.isClassModel + "", "FyyInitEnv..isClassModel");
        FyyLogBaseUtils.info("默认数据库资源池 : {} [key]:[{}] ", FyyInitEnv.dataSource == null ? "" : FyyInitEnv.dataSource.toString(), "FyyInitEnv..dataSource");
        FyyLogBaseUtils.info("项目工作目录 : {} [key]:[{}] ", FyyInitEnv.workHomeDir, "FyyInitEnv..workHomeDir");
        FyyLogBaseUtils.info("PC标记 : {} [key]:[{}] ", JSONUtil.toJsonStr(FyyInitEnv.daemonRoot), "FyyInitEnv.SettingInformation.daemonRoot");
        FyyLogBaseUtils.info("是否自动更新 : {} [key]:[{}] ", FyyInitEnv.autoUpdate + "", "FyyInitEnv..autoUpdate");
        FyyLogBaseUtils.info("默认redis连接池 : {} [key]:[{}] ", FyyInitEnv.redisds == null ? "" : FyyInitEnv.redisds.toString(), "FyyInitEnv..redisds");
        FyyLogBaseUtils.info("默认jdbc_jar包路径 : {} [key]:[{}] ", FyyInitEnv.main_jdbc_jar_fullpath, "FyyInitEnv..main_jdbc_jar_fullpath");
        FyyLogBaseUtils.info("swagger 地址 : {} ", "http://127.0.0.1:" + FyyInitEnv.setting.get(FyyConfigEntryDetailsValues.SERVER_PORT.getKey()) + "/swagger-ui/index.html");


        System.out.println(ResourceUtil.readUtf8Str("ban.txt"));
    }
}

