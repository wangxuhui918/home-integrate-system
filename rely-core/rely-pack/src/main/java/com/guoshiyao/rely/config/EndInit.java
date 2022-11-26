

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.config;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.home.impl.bean.ConfigDetails;
import com.guoshiyao.rely.log.utils.LoggerUtil;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;
import com.guoshiyao.rely.snowflake.utils.IdUtils;
import org.springframework.boot.CommandLineRunner;

public class EndInit implements CommandLineRunner {

    @Override
    public void run(String... args) {
        ILoggerBaseUtils.info("开始翻转日志管理器!!");
        ILoggerBaseUtils.custLogger = new LoggerUtil();
        ILoggerBaseUtils.info("开始处理雪花算法集群ID!!");
        IdUtils.workerId = BaseEv.SettingInformation.distributedKey;


        ILoggerBaseUtils.info("用户目录 : {} [key]:[{}] ", BaseEv.WorkDir.userHomeDir, "Line.userHomeDir");
        ILoggerBaseUtils.info("运行用户名 : {} [key]:[{}] ", BaseEv.WorkDir.systemUserName, "Line.systemUserName");
        ILoggerBaseUtils.info("核心包 : {} [key]:[{}] ", BaseEv.SettingInformation.corePacket, "Line.corePacket");
        ILoggerBaseUtils.info("项目源码目录 : {} [key]:[{}] ", BaseEv.WorkDir.projectresourcepath, "Line.projectresourcepath");
        ILoggerBaseUtils.info("项目资源目录 : {} [key]:[{}] ", BaseEv.WorkDir.projectcodesourcepath, "Line.projectcodesourcepath");
        ILoggerBaseUtils.info("系统托管类 : {} [key]:[{}] ", BaseEv.SettingInformation.iocclasses.size() + "", "Line.iocclasses");
        ILoggerBaseUtils.info("项目参数 : {} [key]:[{}] ", BaseEv.SettingInformation.setting.size() + "", "Line.setting");
        ILoggerBaseUtils.info("默认模板引擎参数 : {} [key]:[{}] ", BaseEv.SettingInformation.context + "", "Line.context");
        ILoggerBaseUtils.info("当前主机Mac地址 : {} [key]:[{}] ", BaseEv.SettingInformation.macSet.size() + "", "Line.macSet");
        ILoggerBaseUtils.info("项目ID : {} [key]:[{}] ", BaseEv.SettingInformation.idKey, "Line.idKey");
        ILoggerBaseUtils.info("项目分布式ID : {} [key]:[{}] ", BaseEv.SettingInformation.distributedKey + "", "Line.distributedKey");
        ILoggerBaseUtils.info("项目语言 : {} [key]:[{}] ", BaseEv.SettingInformation.i18n, "Line.i18n");
        ILoggerBaseUtils.info("当前运行环境 : {} [key]:[{}] ", BaseEv.SettingInformation.runEnv, "Line.runEnv");
        ILoggerBaseUtils.info("支持运行环境 : {} [key]:[{}] ", JSONUtil.toJsonStr(BaseEv.SettingInformation.configEnv), "Line.runEnv");
        ILoggerBaseUtils.info("当前主启动类 : {} [key]:[{}] ", BaseEv.SettingInformation.mainClass, "Line.mainClass");
        ILoggerBaseUtils.info("当前主Mac地址 : {} [key]:[{}] ", BaseEv.SettingInformation.mainMac, "Line.mainMac");
        ILoggerBaseUtils.info("jar包所在目录 : {} [key]:[{}] ", BaseEv.WorkDir.jarpath, "Line.jarpath");
        ILoggerBaseUtils.info("项目包路径 : {} [key]:[{}] ", BaseEv.WorkDir.projectPackage, "Line.projectPackage");
        ILoggerBaseUtils.info("运行状态(true[开发模式],false[运行时模式]) : {} [key]:[{}] ", BaseEv.SettingInformation.isClassModel + "", "Line.isClassModel");
        ILoggerBaseUtils.info("默认数据库资源池 : {} [key]:[{}] ", BaseEv.SettingInformation.dataSource == null ? "" : BaseEv.SettingInformation.dataSource.toString(), "Line.dataSource");
        ILoggerBaseUtils.info("项目工作目录 : {} [key]:[{}] ", BaseEv.WorkDir.workHomeDir, "Line.workHomeDir");
        ILoggerBaseUtils.info("PC标记 : {} [key]:[{}] ", BaseEv.SettingInformation.UK, "Line.UK");
        ILoggerBaseUtils.info("是否自动更新 : {} [key]:[{}] ", BaseEv.SettingInformation.autoUpdate + "", "Line.autoUpdate");
        ILoggerBaseUtils.info("默认redis连接池 : {} [key]:[{}] ", BaseEv.SettingInformation.redisds == null ? "" : BaseEv.SettingInformation.redisds.toString(), "Line.redisds");
        ILoggerBaseUtils.info("默认jdbc_jar包路径 : {} [key]:[{}] ", BaseEv.WorkDir.main_jdbc_jar_fullpath, "Line.main_jdbc_jar_fullpath");
        ILoggerBaseUtils.info("swagger 地址 : {} ", "http://127.0.0.1:" + BaseEv.SettingInformation.setting.get(ConfigDetails.SYSTEM_SERVLET_PORT.getKey()) + "/swagger-ui/index.html");


        System.out.println(ResourceUtil.readUtf8Str("ban.txt"));
    }
}

