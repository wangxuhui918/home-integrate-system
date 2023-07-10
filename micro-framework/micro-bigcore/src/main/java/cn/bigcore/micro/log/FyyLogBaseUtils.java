

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

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.log.base.FyyLogFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * java 基础日志管理器
 */
public class FyyLogBaseUtils {
    public final static Logger jdkLogger = Logger.getLogger(FyyLogBaseUtils.class.getName());
    public final static ConsoleHandler myConsoleHandler = new ConsoleHandler();
    public static FyyLoggerInterface custLogger = null;
    private static FileHandler filehandler = null;

    static {
        {//jdklogger
            Level loglevel = Level.FINER;//默认日志级别
            try {
                if (StrUtil.isNotBlank(SystemUtil.get("loglevel"))) {//如果日志界别不为空则直接查找,根据名字和值自动查找
                    loglevel = Level.parse(SystemUtil.get("loglevel"));
                    FyyLogBaseUtils.info("读取日志变量-Dloglevel={}", loglevel);
                } else {
                    FyyLogBaseUtils.info("默认日志变量-Dloglevel={}", loglevel.getName());
                }
            } catch (Exception e) {
                FyyLogBaseUtils.info("默认日志变量-Dloglevel={}", loglevel.getName());
            }
            try {
                FileUtil.mkdir("target");//创建目录
            } catch (Exception e) {
            }
            String syslogpath = "target/" + FyyInitEnv.SystemInformation.SYSTEM_EN_NAME + ".%u.sys.log";//log/
//            ${syslog_dir}/${hostname}.%d.sys.log %h/java%u.log  SSLog%u.sys.log
            try {
                if (StrUtil.isNotBlank(SystemUtil.get("syslogpath"))) {//如果日志界别不为空则直接查找,根据名字和值自动查找
                    syslogpath = SystemUtil.get("syslogpath") + "/" + FyyInitEnv.SystemInformation.SYSTEM_EN_NAME + ".%u.sys.log";
                    FyyLogBaseUtils.info("读取日志变量-Dsyslogpath={}", syslogpath);
                } else {
                    FyyLogBaseUtils.info("默认日志变量-Dsyslogpath={}", syslogpath);
                }
            } catch (Exception e) {
                FyyLogBaseUtils.info("默认日志变量-Dsyslogpath={}", syslogpath);
            }
            {
                try {
                    filehandler = new FileHandler(syslogpath, 1024000, 16);
                    filehandler.setLevel(Level.ALL);
                    filehandler.setFormatter(new FyyLogFormatter());
                    myConsoleHandler.setLevel(loglevel);
                    myConsoleHandler.setFormatter(new FyyLogFormatter());
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

    public static void main(String[] args) {
        System.out.println(123);
    }

    public static void debug(String str, Object... strs) {
        if (custLogger != null) {
            custLogger.debug(str, strs);
        } else {
            jdkLogger.finer(StrUtil.format(str, strs));
        }
    }

    public static void info(String str, Object... strs) {
        if (custLogger != null) {
            custLogger.info(str, strs);
        } else {
            jdkLogger.info(StrUtil.format(str, strs));
        }
    }

    public static void warn(String str, Object... strs) {
        if (custLogger != null) {
            custLogger.warning(str, strs);
        } else {
            jdkLogger.warning(StrUtil.format(str, strs));
        }
    }

    public static void err(String str, Object... strs) {
        if (custLogger != null) {
            custLogger.error(str, strs);
        } else {
            jdkLogger.severe(StrUtil.format(str, strs));
        }
    }


    public static void debug(Class classes, String str, String... strs) {
        if (custLogger != null) {
            custLogger.debug(str, strs);
        } else {
            jdkLogger.logp(Level.FINER, classes.getName(), "", StrUtil.format(str, strs));
        }
    }


    public static void warn(Class classes, String str, String... strs) {
        if (custLogger != null) {
            custLogger.warning(str, strs);
        } else {
            jdkLogger.logp(Level.WARNING, classes.getName(), "", StrUtil.format(str, strs));
        }
    }

    public static void info(Class classes, String str, String... strs) {
        if (custLogger != null) {
            custLogger.info(str, strs);
        } else {
            jdkLogger.logp(Level.INFO, classes.getName(), "", StrUtil.format(str, strs));
        }
    }

    public static void err(Class classes, String str, String... strs) {
        if (custLogger != null) {
            custLogger.error(str, strs);
        } else {
            jdkLogger.logp(Level.SEVERE, classes.getName(), "", StrUtil.format(str, strs));
        }
    }


}