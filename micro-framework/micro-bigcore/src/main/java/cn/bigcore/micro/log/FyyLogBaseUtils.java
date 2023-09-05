

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

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.exception.re.ex.FyyExceptionError;
import cn.bigcore.micro.log.base.FyyLogFormatter;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.ManifestUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.system.SystemUtil;

import java.io.File;
import java.util.jar.JarFile;
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
                }
            } catch (Exception e) {
            }
            //这里由于先启动,所以无法调整日志所在目录,目前只能先放在用户目录下
            String syslogpath = SystemUtil.getUserInfo().getHomeDir() + FileUtil.FILE_SEPARATOR + FyyInitEnv.SystemInformation.SYSTEM_EN_NAME + FileUtil.FILE_SEPARATOR + FyyInitEnv.SystemInformation.SYSTEM_EN_NAME + ".%u.sys.log";
            try {
                FileUtil.mkParentDirs(syslogpath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (StrUtil.isNotBlank(SystemUtil.get("syslogpath"))) {//如果日志界别不为空则直接查找,根据名字和值自动查找
                    syslogpath = SystemUtil.get("syslogpath") + File.separator + FyyInitEnv.SystemInformation.SYSTEM_EN_NAME + ".%u.sys.log";
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