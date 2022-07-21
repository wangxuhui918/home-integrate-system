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
import com.guoshiyao.rely.log.LoggerAb;

import java.util.logging.ConsoleHandler;
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
            myConsoleHandler.setLevel(loglevel);
            myConsoleHandler.setFormatter(new MyFormatter());
            jdkLogger.setLevel(Level.ALL);
            jdkLogger.setUseParentHandlers(false);
            jdkLogger.addHandler(myConsoleHandler);
        }
    }
}
