

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.log.utils;

import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.log.LoggerAb;
import org.slf4j.LoggerFactory;

/**
 * 日志
 */
public class LoggerUtil implements LoggerAb {

    private final Class<?> clazz;

    public LoggerUtil(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void warning(String str) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).warn(str);
        saveLog();
    }

    @Override
    public void warning(String str, Object obj) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).warn(str, obj);
        saveLog();
    }

    @Override
    public void warning(String str, Object[] objs) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).warn(str, objs);
        saveLog();
    }

    public LoggerUtil() {
        Class<?> clazz = null;
        try {
            String className = Thread.currentThread().getStackTrace()[2].getClassName();
            clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.clazz = clazz;
    }

    /**
     * 保存日志
     */
    private static void saveLog() {
        // LogBean lb = new LogBean(projectName, type, msg);
        // try {
        // RmiProxy.getLogService().add(lb);
        // } catch (Exception e) {
        // LoggerFactory.getLogger(LoggerUtil.class).error("项目名["+projectName+"]日志写入异常",
        // e.getMessage());
        // }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void info(Class<?> clazz, String str) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).info(str);
        saveLog();
    }

    public static void info(Class<?> clazz, String str, Object obj) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).info(str, obj);
        saveLog();
    }

    public static void info(Class<?> clazz, String str, Object... objs) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).info(str, objs);
        saveLog();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void warn(Class<?> clazz, String str) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).warn(str);
        saveLog();
    }

    public static void warn(Class<?> clazz, String str, Object obj) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).warn(str, obj);
        saveLog();
    }

    public static void warn(Class<?> clazz, String str, Object... objs) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).warn(str, objs);
        saveLog();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void trace(Class<?> clazz, String str) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).trace(str);
        saveLog();
    }

    public static void trace(Class<?> clazz, String str, Object obj) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).trace(str, obj);
        saveLog();
    }

    public static void trace(Class<?> clazz, String str, Object... objs) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).trace(str, objs);
        saveLog();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void error(Class<?> clazz, String str) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).error(str);
        saveLog();
    }

    public static void error(Class<?> clazz, String str, Object obj) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).error(str, obj);
        saveLog();
    }

    public static void error(Class<?> clazz, String str, Object[] objs) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).error(str, objs);
        saveLog();
    }

    public static void error(Class<?> clazz, String str, Throwable e) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).error(str, e);
        saveLog();
    }

    public static void error(Class<?> clazz, Throwable e) {
        error(clazz, "", e);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void debug(Class<?> clazz, String str) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).debug(str);
        saveLog();
    }

    public static void debug(Class<?> clazz, String str, Object obj) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).debug(str, obj);
        saveLog();
    }

    public static void debug(Class<?> clazz, String str, Object... objs) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).debug(str, objs);
        saveLog();
    }

    public static void debug(Class<?> clazz, Throwable e) {
        LoggerFactory.getLogger(clazz).debug("", e);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void debug(String str) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).debug(str);
        saveLog();
    }

    @Override
    public void debug(String str, Object obj) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).debug(str, obj);
        saveLog();
    }

    @Override
    public void debug(String str, Object[] objs) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).debug(str, objs);
        saveLog();
    }

    @Override
    public void debug(Throwable e) {
        LoggerFactory.getLogger(clazz).debug("", e);
    }

    @Override
    public void info(String str) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).info(str);
        saveLog();
    }

    @Override
    public void info(String str, Object obj) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).info(str, obj);
        saveLog();
    }

    @Override
    public void info(String str, Object[] objs) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).info(str, objs);
        saveLog();
    }

    @Override
    public void error(String str) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).error(str);
        saveLog();
    }

    @Override
    public void error(String str, Object obj) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).error(str, obj);
        saveLog();
    }

    @Override
    public void error(String str, Object[] objs) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).error(str, objs);
        saveLog();
    }

    @Override
    public void error(String str, Throwable e) {
        str = StrUtil.blankToDefault(str, "");
        LoggerFactory.getLogger(clazz).error(str, e);
        saveLog();
    }

    @Override
    public void error(Throwable e) {
        error(clazz, "", e);
    }


}
