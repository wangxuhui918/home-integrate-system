/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro;

import cn.bigcore.micro.config.FyyReourceInterface;
import cn.bigcore.micro.config.FyyConfigProjectInterface;
import cn.bigcore.micro.daemon.FyyProjectDaemonRoot;
import cn.bigcore.micro.utils.FyyConfigProjectUtils;
import cn.bigcore.micro.exception.code.bean.FyyMessageCode;
import cn.bigcore.micro.i18n.FyyI18n;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.setting.Setting;
import cn.hutool.system.SystemUtil;
import cn.bigcore.micro.config.FyyConfigFrameInterface;
import cn.bigcore.micro.utils.FyyConfigFrameUtils;
import cn.bigcore.micro.exception.re.ex.FyyExceptionError;
import org.apache.velocity.VelocityContext;

import javax.sql.DataSource;
import java.util.*;

public class FyyInitEnv {


    public final static String userHomeDir = SystemUtil.getUserInfo().getHomeDir();//获取当前用户名
    public final static String systemUserName = SystemUtil.getUserInfo().getName().trim().replace("/", "").replace("\\", "");//各种工作目录
    public final static List<Class> iocclasses = new ArrayList<>();//IOC翻转执行类
    public final static Setting setting = new Setting();// 用户配置
    public final static HashMap<String, HashMap<String, FyyMessageCode>> messages = new HashMap<>();//消息码信息
    public final static VelocityContext context = new VelocityContext();//系统模板引擎变量
    public final static Set<String> macSet = new HashSet<>();//存放本机所有Mac地址
    public static String PUBLIC_CORE_PACKAGE = ClassUtil.getPackage(FyyInitEnv.class);
    public static String projectPackage = PUBLIC_CORE_PACKAGE;//默认扫描包
    //系统信息
    public final static String MAIN_CONFIG = SystemUtil.getUserInfo().getHomeDir() + FileUtil.FILE_SEPARATOR + FyyProperties.setting.getStr("fyy.project.core.usertag");//主配置

    //各种工作目录,class路径,jar路径
    public static String projectresourcepath = "";//工程资源目录
    public static String projectcodesourcepath = "";//工程源码目录
    public static String jarpath = "";//jarpath 当前jar包所在目录,仅限于isdev=false
    public static String workHomeDir = "";//获取当前用户目录
    public static String main_jdbc_jar_fullpath = "";
    public static Class mainClassC = null;//启动类
    //一些环境变量
    public static HashMap<String, Tree<Class>> methTree = new HashMap<>();
    public static Long distributedKey = 1L;
    public static String runEnv = "";//当前运行环境
    public static String[] configEnv = new String[]{};//配置运行环境
    public static String mainClass = null;//启动类
    public static String mainMac = "";//存放本机主要 mac
    public static boolean isClassModel = false;//通过是否jar运行判断是否为开发模式
    public static DataSource dataSource = null;//预留一个系统数据源,通过其他创建进行赋值和创建
    public static FyyProjectDaemonRoot daemonRoot = new FyyProjectDaemonRoot();//用户主标志
    public static RedisDS redisds = null;//已经初始化过的 redisds 可以直接创建 jredis (.getJedis())
    public static boolean autoUpdate = false;//是否自动更新
    public static FyyInitEnvLoadInterface baseEv;//各种工作目录,class路径,jar路径
    public static FyyConfigFrameInterface homeConf;//按需调整
    public static FyyConfigProjectInterface projectConf;
    public static FyyReourceInterface resourcetool;

    public static void init() {
        try {
            FyyInitEnv.resourcetool = (FyyReourceInterface) ClassUtil.loadClass(FyyProperties.setting.getStr("cn.bigcore.micro.config.FyyReourceInterface"), false).newInstance();
            FyyInitEnv.baseEv = (FyyInitEnv.FyyInitEnvLoadInterface) ClassUtil.loadClass(FyyProperties.setting.getStr("cn.bigcore.micro.FyyInitEnv.FyyInitEnvLoadInterface"), false).newInstance();
            FyyInitEnv.homeConf = (FyyConfigFrameInterface) ClassUtil.loadClass(FyyProperties.setting.getStr("cn.bigcore.micro.config.FyyConfigFrameInterface"), false).newInstance();
            FyyInitEnv.projectConf = (FyyConfigProjectInterface) ClassUtil.loadClass(FyyProperties.setting.getStr("cn.bigcore.micro.config.FyyConfigProjectInterface"), false).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FyyExceptionError("初始化注册器失败");
        }
        baseEv.load();
    }

    public interface FyyInitEnvLoadInterface {
        void load();
    }


}

