/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro;

import cn.bigcore.micro.base.daemon.FyyProjectDaemonRoot;
import cn.bigcore.micro.base.exception.type.FyyExceptionError;
import cn.bigcore.micro.base.message.FyyMessageCode;
import cn.bigcore.micro.config.frame.config.FyyConfigFrameInterface;
import cn.bigcore.micro.config.frame.resource.FyyReourceInterface;
import cn.bigcore.micro.config.project.FyyConfigProjectInterface;
import cn.bigcore.micro.i18n.FyyI18n;
import cn.bigcore.micro.outgoing.FyyMessageDataOutInterface;
import cn.bigcore.micro.utils.FyyConfigFrameUtils;
import cn.bigcore.micro.utils.FyyConfigProjectUtils;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.setting.Setting;
import cn.hutool.system.SystemUtil;
import org.apache.velocity.VelocityContext;

import javax.sql.DataSource;
import java.util.*;

public class FyyInitEnv {

    /**
     * 框架
     */
    public static class FrameInformation {
        //        public final static String PACKAGE = StrUtil.sub(ClassUtil.getPackage(FyyInitEnv.class), 0,
//                StrUtil.ordinalIndexOf(ClassUtil.getPackage(FyyInitEnv.class), ".", 2));//框架核心包
        //系统信息
        public final static String CHINA_NAME = "帆有云系统";//系统中文名
        public final static String ENGLISH_NAME = "fyy";//系统英文名
        public final static String MAIN_CONFIG = SystemUtil.getUserInfo().getHomeDir() + FileUtil.FILE_SEPARATOR + ENGLISH_NAME + ".json";//主配置
        public final static String INIT_ID_KEY = "fyy-demo-company";//系统默认KEY
        public final static String PACKAGE = ClassUtil.getPackage(FyyInitEnv.class);
        public final static String USER_HOME = SystemUtil.getUserInfo().getHomeDir();//获取当前用户名
        public final static String USER_NAME = SystemUtil.getUserInfo().getName().trim().replace("/", "").replace("\\", "");//各种工作目录
    }

    public static class ProjecEnvInformation {

        public final static List<Class> iocclasses = new ArrayList<>();//IOC翻转执行类
        public final static Set<String> macSet = new HashSet<>();//存放本机所有Mac地址
        //项目信息
        public static boolean OPEN_THREAD_I18N = true;
        public static boolean OPEN_THREAD_USER = true;
        public static boolean OPEN_THREAD_PAGE = true;
        public static String[] configEnv = new String[]{};//配置运行环境
        public static boolean isClassModel = false;//通过是否jar运行判断是否为开发模式
    }

    public static class ProjectInformation {
        public final static Setting setting = new Setting();// 用户配置
        public final static HashMap<String, HashMap<String, FyyMessageCode>> messages = new HashMap<>();//消息码信息
        public final static VelocityContext context = new VelocityContext();//系统模板引擎变量

        public static HashMap<String, Tree<Class>> methTree = new HashMap<>();
        public static String idKey = FrameInformation.INIT_ID_KEY;//项目唯一ID
        public static Long distributedKey = 1L;
        public static String i18n = FyyI18n.defaultI18n.getI18nCode();//项目国际化编码
        public static String runEnv = "";//当前运行环境
        public static String mainClass = null;//启动类
        public static String mainMac = "";//存放本机主要 mac
        public static DataSource dataSource = null;//预留一个系统数据源,通过其他创建进行赋值和创建
        public static FyyProjectDaemonRoot daemonRoot = new FyyProjectDaemonRoot();//用户主标志
        public static RedisDS redisds = null;//已经初始化过的 redisds 可以直接创建 jredis (.getJedis())
        public static boolean autoUpdate = false;//是否自动更新
        public static FyyInitEnvLoadInterface baseEv;//各种工作目录,class路径,jar路径
        public static FyyConfigFrameInterface homeConf;//按需调整
        public static FyyConfigProjectInterface projectConf;
        public static FyyReourceInterface resourcetool;
        public static FyyMessageDataOutInterface fyyMessageDataOutInterface;

        public static void init() {
            if (fyyMessageDataOutInterface == null) {
                throw new FyyExceptionError(FyyMessageDataOutInterface.class.getName() + "缺失核心实现类!");
            }
            if (resourcetool == null) {
                throw new FyyExceptionError(FyyReourceInterface.class.getName() + "缺失核心实现类!");
            }
            if (projectConf == null) {
                throw new FyyExceptionError(FyyConfigProjectUtils.class.getName() + "缺失核心实现类!");
            }
            if (homeConf == null) {
                throw new FyyExceptionError(FyyConfigFrameUtils.class.getName() + "缺失核心实现类!");
            }
            if (baseEv == null) {
                throw new FyyExceptionError(FyyInitEnv.class.getName() + ".baseEv 需要实现接口");
            }
            baseEv.load();
        }

        public interface FyyInitEnvLoadInterface {
            void load();
        }
    }

    public static class WorkDir {//各种工作目录,class路径,jar路径
        public static String projectresourcepath = "";//工程资源目录
        public static String projectcodesourcepath = "";//工程源码目录
        public static String jarpath = "";//jarpath 当前jar包所在目录,仅限于isdev=false
        public static String projectPackage = FrameInformation.PACKAGE;//默认扫描包
        public static String workHomeDir = "";//获取当前用户目录
        public static String main_jdbc_jar_fullpath = "";
        public static Class mainClassC = null;//启动类
    }


}

