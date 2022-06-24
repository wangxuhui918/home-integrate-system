

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */


package com.guoshiyao.rely.line;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.setting.Setting;
import cn.hutool.system.SystemUtil;
import com.guoshiyao.rely.base.BaseEv;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotationApi;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.message.i18n.I18n;
import org.apache.velocity.VelocityContext;

import javax.sql.DataSource;
import java.io.File;
import java.lang.annotation.Annotation;
import java.util.*;

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
    public final static String userHomeDir = SystemUtil.getUserInfo().getHomeDir();
    /**
     * 获取当前用户名
     */
    public final static String systemUserName = SystemUtil.getUserInfo().getName().trim().replace("/", "");
    /**
     * 框架核心包
     */
    public final static String corePacket = StrUtil.sub(ClassUtil.getPackage(Line.class), 0,
            StrUtil.ordinalIndexOf(ClassUtil.getPackage(Line.class), ".", 2));
    /**
     * 工程资源目录
     */
    public static String projectresourcepath = "";
    /**
     * 工程源码目录
     */
    public static String projectcodesourcepath = "";
    /**
     * 用户唯一标志
     */
    public final static String UK_FILE = SystemUtil.getUserInfo().getHomeDir() + File.separator + BaseEv.UK_NAME;
    /**
     * IOC翻转执行类
     */
    public final static List<Class> iocclasses = new ArrayList<>();
    /**
     * 用户配置
     */
    public final static Setting setting = new Setting();
    /**
     * 消息码信息
     */
    public final static HashMap<String, HashMap<String, List<String>>> messages = new HashMap<>();
    /**
     * 系统模板引擎变量
     * VelocityContext context
     */
    public final static VelocityContext context = new VelocityContext();
    /**
     * 存放本机所有Mac地址
     */
    public final static Set<String> macSet = new HashSet<>();
    /**
     * 项目唯一ID
     */
    public static String idKey = BaseEv.INIT_KEY;
    /**
     *
     */
    public static Long distributedKey = 1L;
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
    public static String[] configEnv = new String[]{};
    /**
     * 启动类
     */
    public static String mainClass = null;
    /**
     * 存放本机主要 mac
     */
    public static String mainMac = "";
    /**
     * 启动类
     */
    public static Class mainClassC = null;
    /**
     * jarpath 当前jar包所在目录,仅限于isdev=false
     */
    public static String jarpath = "";
    /**
     * 默认扫描包
     */
    public static String projectPackage = BaseEv.CORE_PACKAGE;
    /**
     * 通过是否jar运行判断是否为开发模式
     */
    public static boolean isClassModel = false;
    /**
     * 预留一个系统数据源,通过其他创建进行赋值和创建
     */
    public static DataSource dataSource = null;
    /**
     * 获取当前用户目录
     */
    public static String workHomeDir = "";
    /**
     * 用户唯一标志
     */
    public static String UK = "";
    /**
     * 已经初始化过的 redisds 可以直接创建 jredis (.getJedis())
     */
    public static RedisDS redisds = null;

    public static String main_jdbc_jar_fullpath = "";

    public static boolean autoUpdate = false;//是否自动更新

    public interface InitLineEnvStaticAb {
        void init();
    }

    private static List<InitLineEnvStaticAb> projectCoreConfRes = getRule(RuleAnnotationApi.class, InitLineEnvStaticAb.class);

    static {
        if (projectCoreConfRes != null && projectCoreConfRes.size() > 0) {//简洁版
            for (int i = 0; i < projectCoreConfRes.size(); i++) {
                projectCoreConfRes.get(i).init();
            }
        } else {
            throw new ExceptionError("缺失Line初始化规则,请实现projectCoreConfRes...", "sun.java.command");
        }
    }


    public static <A extends Annotation, T> List<T> getRule(Class<A> annotaione, Class<T> classe) {
        List<T> listarra = new ArrayList<>();
        Set<Class<?>> annotaiones = ClassUtil.scanPackageByAnnotation(Line.corePacket, annotaione);
        Set<Class<?>> classes = ClassUtil.scanPackageBySuper(Line.corePacket, classe);
        Collection<Class<?>> intersectionSet = CollUtil.intersection(annotaiones, classes);
        for (Class<?> class1 : intersectionSet) {
            try {
                T sd = (T) ClassUtil.loadClass(class1.getName(), true).newInstance();
                listarra.add(sd);
            } catch (Exception e) {
            }
        }
        return listarra;
    }
}
