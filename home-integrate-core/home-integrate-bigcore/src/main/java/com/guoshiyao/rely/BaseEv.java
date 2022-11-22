/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.setting.Setting;
import cn.hutool.system.SystemUtil;
import com.guoshiyao.rely.plugin.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.plugin.i18n.I18n;
import org.apache.velocity.VelocityContext;

import javax.sql.DataSource;
import java.lang.annotation.*;
import java.util.*;

public class BaseEv {


    public static class CompanyInformation {//公司信息
        public final static String COMPANY_LONG_NAME = "XXXX有限公司";//公司全称
        public final static String COMPANY_SHORT_NAME = "XXXX";//公司简称
        public final static String COMPANY_ADDRESS = "湖北省襄阳市高新区邓曼路";//公司详细地址
        public final static String COMPANY_PHONE = "0710-8888888";//公司电话
        public final static String COMPANY_CONTACT = "汪旭辉";//公司联系人
        public final static String COMPANY_EXTRANET_IP = "8.8.8.8";//公司外网地址
        public final static String COMPANY_HOME_URL = "www.guoshiyao.com";//公司主页地址
        public final static String COPYRIGHT_START_YEAR = "2021";//版权开始年
        public final static String COPYRIGHT_END_YEAR = DateUtil.format(new Date(), "yyyy");//版权结束年
        public final static String COPYRIGHT = "©";//版权结束年
    }

    public static class ManagerInformation {//管理员信息
        public final static String MANAGER_ADDRESS = "湖北省襄阳市高新区邓曼路";//公司详细地址
        public final static String MANAGER_PHONE = "0710-8888888";//公司电话
        public final static String MANAGER_CONTACT = "汪旭辉";//公司联系人
    }

    public static class AuthorInformation {//开发者信息
        public final static String AUTHOR_ADDRESS = "湖北省襄阳市高新区邓曼路";
        public final static String AUTHOR_PHONE = "0710-8888888";
        public final static String AUTHOR_CONTACT = "汪旭辉";
        public final static String[] AUTHOR_CONTACTS = new String[]{"汪旭辉", "郭诗瑶"};
    }

    public static class SystemInformation {//系统信息
        public static final String SYSTEM_CHINA_NAME = "帆有云系统";//系统中文名
        public static final String SYSTEM_EN_NAME = "home";//系统英文名
        public static final String SYSTEM_KEY = "model-project-company";//系统默认KEY
    }

    public static class ProjectInformation {//项目信息
        public static final boolean OPEN_THREAD_I18N = true;
        public static final boolean OPEN_THREAD_USER = true;

    }

    public static class WorkDir {//各种工作目录,class路径,jar路径
        public final static String userHomeDir = SystemUtil.getUserInfo().getHomeDir();//获取当前用户名
        public final static String systemUserName = SystemUtil.getUserInfo().getName().trim().replace("/", "").replace("\\", "");//各种工作目录
        public final static String UK_FILE = SystemUtil.getUserInfo().getHomeDir() + FileUtil.FILE_SEPARATOR + PUBLIC_UK_NAME;//用户唯一标志
        public static String projectresourcepath = "";//工程资源目录
        public static String projectcodesourcepath = "";//工程源码目录
        public static String jarpath = "";//jarpath 当前jar包所在目录,仅限于isdev=false
        public static String projectPackage = PUBLIC_CORE_PACKAGE;//默认扫描包
        public static String workHomeDir = "";//获取当前用户目录
        public static String main_jdbc_jar_fullpath = "";
        public static Class mainClassC = null;//启动类
    }

    public static class SettingInformation {//项目设置信息

        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        @Documented
        @Inherited
        public @interface RuleBaseEv {

        }

        public interface IBaseEv {
            void init();
        }

        public final static String corePacket = StrUtil.sub(ClassUtil.getPackage(BaseEv.class), 0,
                StrUtil.ordinalIndexOf(ClassUtil.getPackage(BaseEv.class), ".", 2));//框架核心包
        public static final HashMap<String, Tree<Class>> methTree = new HashMap<>();
        public final static List<Class> iocclasses = new ArrayList<>();//IOC翻转执行类
        public final static Setting setting = new Setting();// 用户配置
        public final static HashMap<String, HashMap<String, List<String>>> messages = new HashMap<>();//消息码信息
        public final static VelocityContext context = new VelocityContext();//系统模板引擎变量
        public final static Set<String> macSet = new HashSet<>();//存放本机所有Mac地址
        public static String idKey = SystemInformation.SYSTEM_KEY;//项目唯一ID
        public static Long distributedKey = 1L;
        public static String i18n = I18n.defaultI18n.getI18nCode();//项目国际化编码
        public static String runEnv = "";//当前运行环境
        public static String[] configEnv = new String[]{};//配置运行环境
        public static String mainClass = null;//启动类
        public static String mainMac = "";//存放本机主要 mac
        public static boolean isClassModel = false;//通过是否jar运行判断是否为开发模式
        public static DataSource dataSource = null;//预留一个系统数据源,通过其他创建进行赋值和创建
        public static String UK = "";//用户唯一标志
        public static RedisDS redisds = null;//已经初始化过的 redisds 可以直接创建 jredis (.getJedis())
        public static boolean autoUpdate = false;//是否自动更新
        public static List<IBaseEv> baseEvs = getRule(RuleBaseEv.class, IBaseEv.class);//各种工作目录,class路径,jar路径


        static {
            if (BaseEv.SettingInformation.baseEvs != null && BaseEv.SettingInformation.baseEvs.size() > 0) {//简洁版
                for (int i = 0; i < BaseEv.SettingInformation.baseEvs.size(); i++) {
                    BaseEv.SettingInformation.baseEvs.get(i).init();
                }
            } else {
                throw new ExceptionError("缺失Line初始化规则,请实现projectCoreConfRes...", "sun.java.command");
            }
        }

        public static <A extends Annotation, T> List<T> getRule(Class<A> annotaione, Class<T> classe) {
            List<T> listarra = new ArrayList<>();
            Set<Class<?>> annotaiones = ClassUtil.scanPackageByAnnotation(SettingInformation.corePacket, annotaione);
            Set<Class<?>> classes = ClassUtil.scanPackageBySuper(SettingInformation.corePacket, classe);
            Collection<Class<?>> intersectionSet = CollUtil.intersection(annotaiones, classes);
            for (Class<?> class1 : intersectionSet) {
                try {
                    T sd = (T) ClassUtil.loadClass(class1.getName(), false).newInstance();
                    listarra.add(sd);
                } catch (Exception e) {
                }
            }
            return listarra;
        }

    }


    public static final String PUBLIC_CORE_PACKAGE = ClassUtil.getPackage(BaseEv.class);
    public static final String PUBLIC_UK_NAME = SystemInformation.SYSTEM_EN_NAME.toUpperCase() + "_UK";


}
