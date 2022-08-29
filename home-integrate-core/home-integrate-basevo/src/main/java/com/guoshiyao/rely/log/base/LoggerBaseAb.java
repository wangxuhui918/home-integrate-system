

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

/**
 *
 */
package com.guoshiyao.rely.log.base;

import cn.hutool.core.util.StrUtil;

import java.util.logging.Level;

/**
 * java 基础日志管理器
 */
public interface LoggerBaseAb {


    public static void debug(String str, Object... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.debug(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.finer(StrUtil.format(str, strs));
        }
    }

    public static void info(String str, Object... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.info(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.info(StrUtil.format(str, strs));
        }
    }

    public static void warn(String str, Object... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.warning(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.warning(StrUtil.format(str, strs));
        }
    }

    public static void err(String str, Object... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.error(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.severe(StrUtil.format(str, strs));
        }
    }


    public static void debug(Class classes, String str, String... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.debug(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.logp(Level.FINER, classes.getName(), "", StrUtil.format(str, strs));
        }
    }

    public static void info(Class classes, String str, String... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.info(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.logp(Level.INFO, classes.getName(), "", StrUtil.format(str, strs));
        }
    }

    public static void warn(Class classes, String str, String... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.warning(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.logp(Level.WARNING, classes.getName(), "", StrUtil.format(str, strs));
        }
    }

    public static void err(Class classes, String str, String... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.error(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.logp(Level.SEVERE, classes.getName(), "", StrUtil.format(str, strs));
        }
    }


}