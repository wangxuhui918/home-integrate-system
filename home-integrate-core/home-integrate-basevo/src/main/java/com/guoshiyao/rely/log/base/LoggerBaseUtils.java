/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.log.base;

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
    protected final static Logger myLogger = Logger.getLogger(LoggerBaseAb.class.getName());
    protected final static ConsoleHandler myConsoleHandler = new ConsoleHandler();
    public static LoggerAb loggerAb = null;

    public static final void setLevel(Level level) {
        myLogger.setLevel(level);
    }


    static {
        myConsoleHandler.setLevel(Level.FINER);
        myLogger.addHandler(myConsoleHandler);
        myLogger.setUseParentHandlers(false);
    }
}
