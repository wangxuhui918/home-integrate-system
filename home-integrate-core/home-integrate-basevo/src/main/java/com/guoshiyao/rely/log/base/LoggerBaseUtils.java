/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.log.base;

import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import com.guoshiyao.rely.base.BaseEv;
import com.guoshiyao.rely.log.LoggerAb;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 汪旭辉
 * @date 2022/4/14
 * @readme
 */
public final class LoggerBaseUtils {
    public final static Logger jdkLogger = Logger.getLogger(LoggerBaseAb.class.getName());
    public final static ConsoleHandler myConsoleHandler = new ConsoleHandler();
    private static FileHandler filehandler = null;

    public static LoggerAb custLogger = null;


    static {
        {//jdklogger
            Level loglevel = Level.FINER;//默认日志级别
            try {
                if (StrUtil.isNotBlank(SystemUtil.get("loglevel"))) {//如果日志界别不为空则直接查找,根据名字和值自动查找
                    loglevel = Level.parse(SystemUtil.get("loglevel"));
                    LoggerBaseAb.info("读取日志变量-Denv={}", loglevel);
                } else {
                    LoggerBaseAb.info("默认日志变量-Denv={}", loglevel.getName());
                }
            } catch (Exception e) {
                LoggerBaseAb.info("默认日志变量-Denv={}", loglevel.getName());
            }
            String syslogpath = "target/log/" + BaseEv.HOME_TAG + "SSLog%u.sys.log";
//            ${syslog_dir}/${hostname}.%d.sys.log
            try {
                if (StrUtil.isNotBlank(SystemUtil.get("syslogpath"))) {//如果日志界别不为空则直接查找,根据名字和值自动查找
                    syslogpath = SystemUtil.get("syslogpath");
                    LoggerBaseAb.info("读取日志变量-Dsyslogpath={}", syslogpath);
                } else {
                    LoggerBaseAb.info("默认日志变量-Dsyslogpath={}", syslogpath);
                }
            } catch (Exception e) {
                LoggerBaseAb.info("默认日志变量-Dsyslogpath={}", syslogpath);
            }

            {
                try {
                    filehandler = new FileHandler(syslogpath, 1024000, 1);
                    filehandler.setLevel(Level.ALL);
                    filehandler.setFormatter(new MyFormatter());

                    myConsoleHandler.setLevel(loglevel);
                    myConsoleHandler.setFormatter(new MyFormatter());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            jdkLogger.setLevel(Level.ALL);
            jdkLogger.setUseParentHandlers(false);
            jdkLogger.addHandler(myConsoleHandler);
            jdkLogger.addHandler(filehandler);
        }
    }
}
