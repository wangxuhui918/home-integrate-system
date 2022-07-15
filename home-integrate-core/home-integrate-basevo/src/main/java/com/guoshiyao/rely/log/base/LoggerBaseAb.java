

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



    static void debug(String str, Object... strs) {
        if (LoggerBaseUtils.loggerAb != null) {
            LoggerBaseUtils.loggerAb.debug(str, strs);
        } else {
            LoggerBaseUtils.myLogger.log(Level.FINER, StrUtil.format(str, strs));
        }
    }

    static void info(String str, Object... strs) {
        if (LoggerBaseUtils.loggerAb != null) {
            LoggerBaseUtils.loggerAb.info(str, strs);
        } else {
            LoggerBaseUtils.myLogger.info(StrUtil.format(str, strs));
        }
    }

    static void warn(String str, Object... strs) {
        if (LoggerBaseUtils.loggerAb != null) {
            LoggerBaseUtils.loggerAb.warning(str, strs);
        } else {
            LoggerBaseUtils.myLogger.warning(StrUtil.format(str, strs));
        }
    }

    static void err(String str, Object... strs) {
        if (LoggerBaseUtils.loggerAb != null) {
            LoggerBaseUtils.loggerAb.error(str, strs);
        } else {
            LoggerBaseUtils.myLogger.log(Level.SEVERE, StrUtil.format(str, strs));
        }
    }

}