

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */


package com.guoshiyao.rely.line;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.setting.Setting;
import cn.hutool.system.SystemUtil;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.log.base.LoggerBaseUtils;
import com.guoshiyao.rely.message.i18n.I18n;
import org.apache.velocity.VelocityContext;

import javax.sql.DataSource;
import java.io.File;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.logging.Level;

/**
 * 中央配置
 * 所有的静态配置均写在这里
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
public class Line {
    /**
     * 获取当前用户目录
     */
    public final static String userHomeDir = (SystemUtil.getUserInfo().getHomeDir());
    /**
     * 获取当前用户名
     */
    public final static String systemUserName = (SystemUtil.getUserInfo().getName().trim().replace("/", ""));
    /**
     * 框架核心包
     */
    public final static String corePacket = (StrUtil.sub(ClassUtil.getPackage(Line.class), 0,
            StrUtil.ordinalIndexOf(ClassUtil.getPackage(Line.class), ".", 2)));
    /**
     * 工程资源目录
     */
    public static String projectresourcepath = (StrUtil.subBefore(ClassUtil.loadClass(Line.class.getName()).getClassLoader().getResource("").getPath(), "target" + File.separator + "classes", true) + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator);
    /**
     * 工程源码目录
     */
    public static String projectcodesourcepath = (StrUtil.subBefore(ClassUtil.loadClass(Line.class.getName()).getClassLoader().getResource("").getPath(), "target" + File.separator + "classes", true) + File.separator + "src"
            + File.separator + "main" + File.separator + "java" + File.separator);
    /**
     * 用户唯一标志
     */
    public static String UK_FILE = (SystemUtil.getUserInfo().getHomeDir() + File.separator + "HOME_UK");


    /**
     * IOC翻转执行类
     */
    public final static List<Class> iocclasses = new ArrayList<>();
    /**
     * 建议使用com.guoshiyao.rely.line.Line.setting
     */
    @Deprecated
    public final static PropertiesMap<String, LinePropertiesAb> properties = (new PropertiesMap<>());
    public final static Setting setting = new Setting();
    /**
     * 消息码信息
     */
    public final static HashMap<String, HashMap<String, List<String>>> messages = (new HashMap<>());
    /**
     * 系统模板引擎变量
     * VelocityContext context
     */
    public final static VelocityContext context = (new VelocityContext());
    /**
     * 项目唯一ID
     */
    public static String idKey = "demo-demo-demo";
    /**
     *
     */
    public static Long distributedKey = (1L);
    /**
     * 项目国际化编码
     */
    public static String i18n = I18n.defaultI18n.getI18nCode();
    /**
     * 当前运行环境
     */
    public static String runEnv = "";
    /**
     * 配置运行环境
     */
    public static String[] configEnv = null;
    /**
     * 启动类
     */
    public static String mainClass = ("");
    /**
     * 存放本机所有Mac地址
     */
    public static Set<String> macSet = (new HashSet<>());
    /**
     * 存放本机主要 mac
     */
    public static String mainMac = ("");
    /**
     * 启动类
     */
    public static Class mainClassC = (null);
    /**
     * jarpath 当前jar包所在目录,仅限于isdev=false
     */
    public static String jarpath = ("");
    /**
     * 默认扫描包
     */
    public static String projectPackage = ("com.guoshiyao.rely");
    /**
     * 通过是否jar运行判断是否为开发模式
     */
    public static boolean isClassModel = (false);
    /**
     * 预留一个系统数据源,通过其他创建进行赋值和创建
     */
    public static DataSource dataSource = (null);
    /**
     * 获取当前用户目录
     */
    public static String workHomeDir = ("");
    /**
     * 用户唯一标志
     */
    public static String UK = ("");
    /**
     * 已经初始化过的 redisds 可以直接创建 jredis (.getJedis())
     */
    public static RedisDS redisds = (null);

    public static String main_jdbc_jar_fullpath = ("");

    public static boolean autoUpdate = (false);//是否自动更新

    public static Integer project_system_version;//项目版本号

    public static Integer home_system_version;//框架版本号

    private static int count = (0);


    public static void init(String mainClassx, String i18nx, String projectPackagex, String idkeyx, String runEnv, String[] configEnv, Level loglevelx, boolean updatePropertiesx) {
        if (count != 0) {
            throw new ExceptionError(Line.class.getName() + "重复初始化!");
        }
        Line.idKey = idkeyx;
        Line.mainClass = mainClassx;
        Line.i18n = i18nx;
        Line.mainClassC = ClassUtil.loadClass(mainClassx);
        Line.projectPackage = StrUtil.isNotBlank(projectPackagex) ? projectPackagex
                : ClassUtil.getPackage(ClassUtil.loadClass(Line.mainClass));
        Line.workHomeDir = SystemUtil.getUserInfo().getHomeDir() + File.separator + "home" + File.separator + Line.idKey
                + File.separator;
        Line.autoUpdate = updatePropertiesx;
        Line.configEnv = configEnv;
        LoggerBaseUtils.setLevel(loglevelx);//临时处理日志管理
        {
            String uk_value = "";
            try {
                uk_value = FileUtil.readUtf8String(Line.UK_FILE);
            } catch (Exception e) {
            }
            if (StrUtil.isBlank(uk_value)) {
                uk_value = Line.mainMac + Line.systemUserName;
                FileUtil.writeUtf8String(uk_value, Line.UK_FILE);
            }

            Line.UK = StrUtil.blankToDefault(uk_value, "local");//都获取不到使用local默认值
        }
        Line.runEnv = StrUtil.blankToDefault(runEnv, Line.UK);
        {
            if (ClassUtil.loadClass(Line.mainClass).getResource("").getPath().contains(".jar!")) {
                LoggerBaseAb.info("检测到当前为线上运行模式");
                Line.jarpath = StrUtil.subBefore(ClassUtil.loadClass(Line.mainClass).getResource("").getPath(), ".jar!",
                        true) + ".jar";
                Line.isClassModel = false;
            } else {
                LoggerBaseAb.info("检测到当前为开发者模式");
                Line.isClassModel = true;
            }
        }
        {
            //获取本机所有的MAC地址,转为XX:XX:XX:XX:XX:XX大写的形式
            Set<String> netMacAddress = new HashSet<>();
            for (NetworkInterface networkinterface : NetUtil.getNetworkInterfaces()) {
                for (InterfaceAddress interfaceaddress : networkinterface.getInterfaceAddresses()) {
                    String temp = NetUtil.getMacAddress(interfaceaddress.getAddress());
                    if (StrUtil.isNotBlank(temp)) {
                        try {
                            //StrUtil.replace(temp, "-", ":")
                            netMacAddress.add(temp.toUpperCase());
                        } catch (Exception e) {
                        }
                    }
                }
            }
            Line.macSet = netMacAddress;
            Line.mainMac = netMacAddress.iterator().next();
        }
        {
            Integer version = 0;
            try {
                ResourceUtil.getResource("apollo.properties");
                version = 1;
            } catch (Exception e) {
            }
            try {
                String file = Line.projectresourcepath + File.separator + "versionlog" + File.separator + "v2-init";
                if (FileUtil.exist(file)) {
                    version = 2;
                }
            } catch (Exception e) {
            }

        }
        {
            Line.context.put("idKey", Line.idKey);
            Line.context.put("mainClass", Line.mainClass);
            Line.context.put("isdev", Line.isClassModel);
            Line.context.put("i18n", Line.i18n);
            Line.context.put("env", Line.runEnv);
            Line.context.put("projectPackage", Line.projectPackage);
            Line.context.put("workHomeDir", Line.workHomeDir);
        }
    }


}
