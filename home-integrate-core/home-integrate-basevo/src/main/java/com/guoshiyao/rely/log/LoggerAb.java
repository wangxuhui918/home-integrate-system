

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
package com.guoshiyao.rely.log;

import com.guoshiyao.rely.log.base.LoggerBaseAb;

public interface LoggerAb extends LoggerBaseAb {

    void debug(String str);

    void debug(String str, Object obj);

    void debug(String str, Object[] objs);

    void debug(Throwable e);

    void info(String str);

    void info(String str, Object obj);

    void info(String str, Object[] objs);

    void error(String str);

    void error(String str, Object obj);

    void error(String str, Object[] objs);

    void error(String str, Throwable e);

    void error(Throwable e);

    void warning(String str);

    void warning(String str, Object obj);

    void warning(String str, Object[] objs);
}