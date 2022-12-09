/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config.baseenv;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.ManifestUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.SystemUtil;
import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.annotaion.Starter;
import cn.bigcore.micro.core.utils.data.JdbcFind;
import cn.bigcore.micro.plugin.exception.re.ex.ExceptionError;
import cn.bigcore.micro.plugin.log.ILoggerBaseUtils;

import java.io.File;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.regex.Pattern;


public class BaseEvBuiltIn implements BaseEv.SettingInformation.IBaseEv {
    @Override
    public void init() {
        ILoggerBaseUtils.debug("开始处理配置类[{}}", BaseEvBuiltIn.class.getName());

        String idkey = "", i18n = "", projectPackage = "", runEnv = "", uk_value = "",
                jarpath = "", mainClass = "", projectresourcepath = "", projectcodesourcepath = "",
                mainMac = "", uk = "", workHomeDir = "";
        String[] configEnv = null;
        Boolean isClassModel = null;
        Class mainClassC = null;
        boolean updateProperties = false;
        Set<String> macSet = new HashSet<>();

        {
            String startCommad = System.getProperty("sun.java.command");
            ILoggerBaseUtils.info("获取到启动命令值:[{}}", startCommad);
            try {
                String[] startCmdStr = StrUtil.splitToArray(startCommad, " ");
                if (startCmdStr != null && startCmdStr.length > 0) {
                    for (int i = 0; i < startCmdStr.length; i++) {
                        try {
                            Starter starter = AnnotationUtil.getAnnotation(ClassUtil.loadClass(startCmdStr[i], false), Starter.class);
                            if (StrUtil.isNotBlank(starter.idkey())) {
                                startCommad = startCmdStr[i];
                                break;
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }
                }
                if (StrUtil.isNotBlank(startCommad)) {
                    startCommad = StrUtil.subBefore(startCommad, " ", false);
                }
                if (URLUtil.isJarFileURL(new File(startCommad).toURI().toURL())) {//如果是运行时
                    ILoggerBaseUtils.info("[jar]运行模式");
                    jarpath = startCommad;
                    isClassModel = false;
                    java.util.jar.Manifest masin = ManifestUtil.getManifest(new JarFile(new File(startCommad)));
                    mainClassC = ClassUtil.loadClass(masin.getMainAttributes().getValue("Start-Class"), false);
                    mainClass = masin.getMainAttributes().getValue("Start-Class");
                } else if (ClassUtil.isNormalClass(ClassUtil.loadClass(startCommad, false))) {//如果是非运行时
                    ILoggerBaseUtils.info("[java]运行模式");
                    isClassModel = true;
                    mainClassC = ClassUtil.loadClass(startCommad, false);
                    mainClass = startCommad;
                } else {
                    throw new ExceptionError("环境变量[{}]值错误", "sun.java.command");
                }
            } catch (ExceptionError e1) {
                throw e1;
            } catch (Exception e) {
                throw new ExceptionError("未知异常:{}", e.getMessage());
            }
        }
        if (isClassModel) {
            String f1 = FileUtil.getAbsolutePath(ClassUtil.loadClass(BaseEvBuiltIn.class.getName()).getClassLoader().getResource("").getPath());
            for (int i = 0; ; i++) {
                String classes = FileUtil.getParent(f1, i);
                String target = FileUtil.getParent(f1, i + 1);
                String root = FileUtil.getParent(f1, i + 2);
                if (StrUtil.endWith(classes, "classes") && StrUtil.endWith(target, "target")) {
                    projectresourcepath = root + FileUtil.FILE_SEPARATOR + "src" + FileUtil.FILE_SEPARATOR + "main" + FileUtil.FILE_SEPARATOR + "resources" + FileUtil.FILE_SEPARATOR;
                    projectcodesourcepath = root + FileUtil.FILE_SEPARATOR + "src" + FileUtil.FILE_SEPARATOR + "main" + FileUtil.FILE_SEPARATOR + "java" + FileUtil.FILE_SEPARATOR;
                    break;
                }
                if (classes == null) {
                    throw new ExceptionError("获取根目录{}失败", f1);
                }
            }
        }

        {
            int s = 0;
            for (NetworkInterface networkinterface : NetUtil.getNetworkInterfaces()) {
                for (InterfaceAddress interfaceaddress : networkinterface.getInterfaceAddresses()) {
                    String temp = NetUtil.getMacAddress(interfaceaddress.getAddress());
                    if (StrUtil.isNotBlank(temp)) {
                        try {
                            ILoggerBaseUtils.debug("扫描到网卡[{}]:[{}}", ++s, temp.toUpperCase());
                            macSet.add(temp.toUpperCase());
                        } catch (Exception e) {
                        }
                    }
                }
            }
            mainMac = macSet.iterator().next();
            ILoggerBaseUtils.info("获取到随机主网卡:[{}}", mainMac);
        }
        {
            try {
                uk_value = FileUtil.readUtf8String(BaseEv.WorkDir.UK_FILE);
            } catch (Exception e) {
            }
            if (StrUtil.isBlank(uk_value) && isClassModel) {
                for (int i = 0; ; i++) {
                    ILoggerBaseUtils.warn("[{}]系统在本机首次运行,文件[{}]需要写入用户标识(例如:中文姓名,英文姓名[只能为中文,英文,数字]),请输入并点击Enter", BaseEv.SystemInformation.SYSTEM_EN_NAME, BaseEv.WorkDir.UK_FILE);
                    String uktag = Console.input();
                    String pattern = "^[\\u4e00-\\u9fa5,A-Za-z0-9]{0,}";
                    boolean isMatch = Pattern.matches(pattern, uktag);
                    if (isMatch) {
                        uk_value = uktag;
                        break;
                    }
                }
                FileUtil.writeUtf8String(uk_value, BaseEv.WorkDir.UK_FILE);
            } else if (StrUtil.isBlank(uk_value) && !isClassModel) {
                uk_value = mainMac + BaseEv.WorkDir.systemUserName;
                FileUtil.writeUtf8String(uk_value, BaseEv.WorkDir.UK_FILE);
            }
            uk = StrUtil.blankToDefault(uk_value, "local");//都获取不到使用local默认值
            ILoggerBaseUtils.info("获取到用户标识:[{}}", uk);
        }

        {
            {
                Starter starter = AnnotationUtil.getAnnotation(mainClassC, Starter.class);
                try {
                    i18n = starter.i18n().getI18nCode();
                } catch (Exception e) {
                }
                try {
                    configEnv = starter.configEnv();
                    if (configEnv == null || configEnv.length == 0) {
                        throw new ExceptionError("参数{}不能为空", "configEnv");
                    }
                } catch (Exception e) {
                }

                {
                    try {
                        runEnv = starter.runEnv();
                        if (StrUtil.isBlank(runEnv)) {//自动选择根据环境变量确定
                            List<String> lista = Arrays.asList(SystemUtil.get("env"), SystemUtil.get("ENV"), SystemUtil.get("Env"), SystemUtil.get("ENv"), SystemUtil.get("ENv"));
                            for (int i = 0; i < lista.size(); i++) {
                                String BaseEvBuiltInImplEnv = lista.get(i);
                                if (StrUtil.isNotBlank(BaseEvBuiltInImplEnv)) {
                                    runEnv = BaseEvBuiltInImplEnv;
                                    break;
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                    {
                        runEnv = StrUtil.blankToDefault(runEnv, "");
                        if (StrUtil.isBlank(runEnv) && !isClassModel) {
                            throw new ExceptionError("[jar]运行模式需指定一个环境变量:[-Denv={}]", JSONUtil.toJsonStr(configEnv));
                        }
                    }
                }

                try {
                    idkey = starter.idkey();
                } catch (Exception e) {
                }
                try {
                    projectPackage = starter.scanBasePackages()[0];
                } catch (Exception e) {
                }

                try {
                    updateProperties = starter.updateProperties();
                } catch (Exception e) {
                }
                if (StrUtil.isNotBlank(runEnv) && configEnv != null && configEnv.length > 0 && !ArrayUtil.contains(configEnv, runEnv)) {
                    throw new ExceptionError("参数{}[{}]需在参数{}[{}]范围内", "runEnv", runEnv, "configEnv", JSONUtil.toJsonStr(configEnv));
                }
            }
            {
                workHomeDir = SystemUtil.getUserInfo().getHomeDir() + FileUtil.FILE_SEPARATOR + BaseEv.SystemInformation.SYSTEM_EN_NAME + FileUtil.FILE_SEPARATOR + idkey + FileUtil.FILE_SEPARATOR;
            }
        }
        {
            //
            ILoggerBaseUtils.debug("配置类赋值开始[{}}", BaseEvBuiltIn.class.getName());
            BaseEv.SettingInformation.idKey = idkey;
            BaseEv.SettingInformation.i18n = i18n;
            BaseEv.WorkDir.projectPackage = StrUtil.isNotBlank(projectPackage) ? projectPackage : ClassUtil.getPackage(ClassUtil.loadClass(mainClass, false));
            BaseEv.WorkDir.workHomeDir = workHomeDir;
            BaseEv.SettingInformation.autoUpdate = updateProperties;
            BaseEv.SettingInformation.configEnv = configEnv;
            BaseEv.SettingInformation.runEnv = StrUtil.blankToDefault(runEnv, uk);
            BaseEv.WorkDir.jarpath = jarpath;
            BaseEv.SettingInformation.isClassModel = isClassModel;
            BaseEv.WorkDir.mainClassC = mainClassC;
            BaseEv.SettingInformation.mainClass = mainClass;
            BaseEv.WorkDir.projectresourcepath = projectresourcepath;
            BaseEv.WorkDir.projectcodesourcepath = projectcodesourcepath;
            BaseEv.SettingInformation.macSet.addAll(macSet);
            BaseEv.SettingInformation.mainMac = mainMac;
            BaseEv.SettingInformation.UK = uk;
            BaseEv.SettingInformation.driverClasses.addAll(JdbcFind.getProjectJdbc());
        }
        {
            ILoggerBaseUtils.debug("公共模型赋值开始[{}}", BaseEvBuiltIn.class.getName());
            BaseEv.SettingInformation.context.put("idKey", BaseEv.SettingInformation.idKey);
            BaseEv.SettingInformation.context.put("mainClass", BaseEv.SettingInformation.mainClass);
            BaseEv.SettingInformation.context.put("isdev", BaseEv.SettingInformation.isClassModel);
            BaseEv.SettingInformation.context.put("i18n", BaseEv.SettingInformation.i18n);
            BaseEv.SettingInformation.context.put("env", BaseEv.SettingInformation.runEnv);
            BaseEv.SettingInformation.context.put("projectPackage", BaseEv.WorkDir.projectPackage);
            BaseEv.SettingInformation.context.put("workHomeDir", BaseEv.WorkDir.workHomeDir);
        }

    }
}
