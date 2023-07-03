

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.init;

import cn.bigcore.micro.FyyInitEnv;
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
        FyyIdUtils.workerId = FyyInitEnv.SettingInformation.distributedKey;


        FyyLogBaseUtils.info("用户目录 : {} [key]:[{}] ", FyyInitEnv.WorkDir.userHomeDir, "FyyInitEnv..userHomeDir");
        FyyLogBaseUtils.info("运行用户名 : {} [key]:[{}] ", FyyInitEnv.WorkDir.systemUserName, "FyyInitEnv..systemUserName");
        FyyLogBaseUtils.info("核心包 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.corePacket, "FyyInitEnv..corePacket");
        FyyLogBaseUtils.info("项目源码目录 : {} [key]:[{}] ", FyyInitEnv.WorkDir.projectresourcepath, "FyyInitEnv..projectresourcepath");
        FyyLogBaseUtils.info("项目资源目录 : {} [key]:[{}] ", FyyInitEnv.WorkDir.projectcodesourcepath, "FyyInitEnv..projectcodesourcepath");
        FyyLogBaseUtils.info("系统托管类 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.iocclasses.size() + "", "FyyInitEnv..iocclasses");
        FyyLogBaseUtils.info("项目参数 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.setting.size() + "", "FyyInitEnv..setting");
        FyyLogBaseUtils.info("默认模板引擎参数 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.context + "", "FyyInitEnv..context");
        FyyLogBaseUtils.info("当前主机Mac地址 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.macSet.size() + "", "FyyInitEnv..macSet");
        FyyLogBaseUtils.info("项目ID : {} [key]:[{}] ", FyyInitEnv.SettingInformation.idKey, "FyyInitEnv..idKey");
        FyyLogBaseUtils.info("项目分布式ID : {} [key]:[{}] ", FyyInitEnv.SettingInformation.distributedKey + "", "FyyInitEnv..distributedKey");
        FyyLogBaseUtils.info("项目语言 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.i18n, "FyyInitEnv..i18n");
        FyyLogBaseUtils.info("当前运行环境 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.runEnv, "FyyInitEnv..runEnv");
        FyyLogBaseUtils.info("支持运行环境 : {} [key]:[{}] ", JSONUtil.toJsonStr(FyyInitEnv.SettingInformation.configEnv), "FyyInitEnv..runEnv");
        FyyLogBaseUtils.info("当前主启动类 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.mainClass, "FyyInitEnv..mainClass");
        FyyLogBaseUtils.info("当前主Mac地址 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.mainMac, "FyyInitEnv..mainMac");
        FyyLogBaseUtils.info("jar包所在目录 : {} [key]:[{}] ", FyyInitEnv.WorkDir.jarpath, "FyyInitEnv..jarpath");
        FyyLogBaseUtils.info("项目包路径 : {} [key]:[{}] ", FyyInitEnv.WorkDir.projectPackage, "FyyInitEnv..projectPackage");
        FyyLogBaseUtils.info("运行状态(true[开发模式],false[运行时模式]) : {} [key]:[{}] ", FyyInitEnv.SettingInformation.isClassModel + "", "FyyInitEnv..isClassModel");
        FyyLogBaseUtils.info("默认数据库资源池 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.dataSource == null ? "" : FyyInitEnv.SettingInformation.dataSource.toString(), "FyyInitEnv..dataSource");
        FyyLogBaseUtils.info("项目工作目录 : {} [key]:[{}] ", FyyInitEnv.WorkDir.workHomeDir, "FyyInitEnv..workHomeDir");
        FyyLogBaseUtils.info("PC标记 : {} [key]:[{}] ", JSONUtil.toJsonStr(FyyInitEnv.SettingInformation.daemonRoot), "FyyInitEnv.SettingInformation.daemonRoot");
        FyyLogBaseUtils.info("是否自动更新 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.autoUpdate + "", "FyyInitEnv..autoUpdate");
        FyyLogBaseUtils.info("默认redis连接池 : {} [key]:[{}] ", FyyInitEnv.SettingInformation.redisds == null ? "" : FyyInitEnv.SettingInformation.redisds.toString(), "FyyInitEnv..redisds");
        FyyLogBaseUtils.info("默认jdbc_jar包路径 : {} [key]:[{}] ", FyyInitEnv.WorkDir.main_jdbc_jar_fullpath, "FyyInitEnv..main_jdbc_jar_fullpath");
        FyyLogBaseUtils.info("swagger 地址 : {} ", "http://127.0.0.1:" + FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.SERVER_PORT.getKey()) + "/swagger-ui/index.html");


        System.out.println(ResourceUtil.readUtf8Str("ban.txt"));
    }
}

