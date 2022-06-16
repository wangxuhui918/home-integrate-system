

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
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


    static void debug(String str, String... strs) {
        LoggerBaseUtils.myLogger.log(Level.FINER, StrUtil.format(str, strs));
    }

    static void info(String str, String... strs) {
        LoggerBaseUtils.myLogger.info(StrUtil.format(str, strs));
    }


    static void warn(String str, String... strs) {
        LoggerBaseUtils.myLogger.warning(StrUtil.format(str, strs));
    }

    static void err(String str, String... strs) {
        LoggerBaseUtils.myLogger.log(Level.SEVERE, StrUtil.format(str, strs));
    }


}