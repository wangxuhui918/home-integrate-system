

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

/**
 * java 基础日志管理器
 */
public interface LoggerBaseAb {


    static void debug(String str, Object... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.debug(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.finer(StrUtil.format(str, strs));
        }
    }

    static void info(String str, Object... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.info(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.info(StrUtil.format(str, strs));
        }
    }

    static void warn(String str, Object... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.warning(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.warning(StrUtil.format(str, strs));
        }
    }

    static void err(String str, Object... strs) {
        if (LoggerBaseUtils.custLogger != null) {
            LoggerBaseUtils.custLogger.error(str, strs);
        } else {
            LoggerBaseUtils.jdkLogger.severe(StrUtil.format(str, strs));
        }
    }

}