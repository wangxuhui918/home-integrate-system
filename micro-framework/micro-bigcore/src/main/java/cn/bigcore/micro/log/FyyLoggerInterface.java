

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
package cn.bigcore.micro.log;

public abstract class FyyLoggerInterface {

    public abstract void debug(String str);

    public abstract void debug(String str, Object obj);

    public abstract void debug(String str, Object... objs);

    public abstract void debug(Throwable e);

    public abstract void info(String str);

    public abstract void info(String str, Object obj);

    public abstract void info(String str, Object... objs);

    public abstract void error(String str);

    public abstract void error(String str, Object obj);

    public abstract void error(String str, Object... objs);

    public abstract void error(String str, Throwable e);

    public abstract void error(Throwable e);

    public abstract void warning(String str);

    public abstract void warning(String str, Object obj);

    public abstract void warning(String str, Object... objs);
}