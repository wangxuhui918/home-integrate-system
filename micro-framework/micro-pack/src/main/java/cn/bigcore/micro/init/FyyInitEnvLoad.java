/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.init;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.base.daemon.FyyProjectConfigRoot;
import cn.bigcore.micro.base.daemon.FyyProjectDaemonRoot;
import cn.bigcore.micro.starter.FyyStarter;
import cn.bigcore.micro.base.exception.type.FyyExceptionError;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.ManifestUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.SystemUtil;

import java.io.File;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.regex.Pattern;


public class FyyInitEnvLoad implements FyyInitEnv.ProjectInformation.FyyInitEnvLoadInterface {
    @Override
    public void load() {
        FyyLogBaseUtils.debug("开始处理配置类[{}}", FyyInitEnvLoad.class.getName());
//        uk_value = "",uk = "",
        String idkey, i18n, projectPackage, runEnv, jarpath, mainClass, projectresourcepath, projectcodesourcepath, mainMac;
        String workHomeDir;
        FyyProjectDaemonRoot daemonRoot;
        String[] configEnv;
        Boolean isClassModel;
        Class mainClassC;
        boolean updateProperties;
        Set<String> macSet;
        {
            mainClassC = null;
            mainClass = "";
            isClassModel = null;
            jarpath = "";
            String startCommad = System.getProperty("sun.java.command");
            FyyLogBaseUtils.info("获取到启动命令值:[{}}", startCommad);
            try {
                String[] startCmdStr = StrUtil.splitToArray(startCommad, " ");
                if (startCmdStr != null && startCmdStr.length > 0) {
                    for (int i = 0; i < startCmdStr.length; i++) {
                        try {
                            FyyStarter starter = AnnotationUtil.getAnnotation(ClassUtil.loadClass(startCmdStr[i], false), FyyStarter.class);
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
                    FyyLogBaseUtils.info("[jar]运行模式");
                    jarpath = startCommad;
                    isClassModel = false;
                    java.util.jar.Manifest masin = ManifestUtil.getManifest(new JarFile(new File(startCommad)));
                    mainClassC = ClassUtil.loadClass(masin.getMainAttributes().getValue("Start-Class"), false);
                    mainClass = masin.getMainAttributes().getValue("Start-Class");
                } else if (ClassUtil.isNormalClass(ClassUtil.loadClass(startCommad, false))) {//如果是非运行时
                    FyyLogBaseUtils.info("[java]运行模式");
                    isClassModel = true;
                    mainClassC = ClassUtil.loadClass(startCommad, false);
                    mainClass = startCommad;
                } else {
                    throw new FyyExceptionError("环境变量[{}]值错误", "sun.java.command");
                }
            } catch (FyyExceptionError e1) {
                throw e1;
            } catch (Exception e) {
                throw new FyyExceptionError("未知异常:{}", e.getMessage());
            }
        }

        {
            runEnv = "";
            idkey = "";
            i18n = "";
            configEnv = null;
            projectPackage = "";
            updateProperties = false;
            workHomeDir = "";
            {
                FyyStarter starter = AnnotationUtil.getAnnotation(mainClassC, FyyStarter.class);
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
                    runEnv = StrUtil.blankToDefault(runEnv, "");
                    if (StrUtil.isBlank(runEnv) && !isClassModel) {
                        throw new FyyExceptionError("[jar]运行模式需指定一个环境变量:[-Denv={}]", JSONUtil.toJsonStr(configEnv));
                    }
                } catch (Exception e) {
                }
                try {
                    i18n = starter.i18n().getI18nCode();
                } catch (Exception e) {
                }
                try {
                    configEnv = starter.configEnv();
                    if (configEnv == null || configEnv.length == 0) {
                        throw new FyyExceptionError("参数{}不能为空", "configEnv");
                    }
                } catch (Exception e) {
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
//                if (StrUtil.isNotBlank(runEnv) && configEnv != null && configEnv.length > 0 && !ArrayUtil.contains(configEnv, runEnv)) {
//                    throw new FyyExceptionError("参数{}[{}]需在参数{}[{}]范围内", "runEnv", runEnv, "configEnv", JSONUtil.toJsonStr(configEnv));
//                }
            }
            {
                workHomeDir = SystemUtil.getUserInfo().getHomeDir() + FileUtil.FILE_SEPARATOR + FyyInitEnv.FrameInformation.ENGLISH_NAME + FileUtil.FILE_SEPARATOR + idkey + FileUtil.FILE_SEPARATOR;
            }
        }


        {
            projectresourcepath = "";
            projectcodesourcepath = "";
            if (isClassModel) {

                String f1 = FileUtil.getAbsolutePath(ClassUtil.loadClass(FyyInitEnvLoad.class.getName()).getClassLoader().getResource("").getPath());
                for (int i = 0; ; i++) {
                    String classes = FileUtil.getParent(f1, i);
                    String target = FileUtil.getParent(f1, i + 1);
                    String root = FileUtil.getParent(f1, i + 2);
                    if (StrUtil.endWith(classes, "classes") && StrUtil.endWith(target, "target")) {
                        projectresourcepath = URLDecoder.decode(root) + FileUtil.FILE_SEPARATOR + "src" + FileUtil.FILE_SEPARATOR + "main" + FileUtil.FILE_SEPARATOR + "resources" + FileUtil.FILE_SEPARATOR;
                        projectcodesourcepath = URLDecoder.decode(root) + FileUtil.FILE_SEPARATOR + "src" + FileUtil.FILE_SEPARATOR + "main" + FileUtil.FILE_SEPARATOR + "java" + FileUtil.FILE_SEPARATOR;
                        break;
                    }
                    if (classes == null) {
                        throw new FyyExceptionError("获取根目录{}失败", f1);
                    }
                }
            }
        }

        {
            macSet = new HashSet<>();
            int s = 0;
            for (NetworkInterface networkinterface : NetUtil.getNetworkInterfaces()) {
                for (InterfaceAddress interfaceaddress : networkinterface.getInterfaceAddresses()) {
                    String temp = NetUtil.getMacAddress(interfaceaddress.getAddress());
                    if (StrUtil.isNotBlank(temp)) {
                        try {
                            FyyLogBaseUtils.debug("扫描到网卡[{}]:[{}}", ++s, temp.toUpperCase());
                            macSet.add(temp.toUpperCase());
                        } catch (Exception e) {
                        }
                    }
                }
            }
            mainMac = macSet.iterator().next();
            FyyLogBaseUtils.info("获取到随机主网卡:[{}}", mainMac);
        }
        {
            daemonRoot = null;
            try {
                String daemonJson = FileUtil.readUtf8String(FyyInitEnv.FrameInformation.MAIN_CONFIG);
                daemonRoot = JSONUtil.toBean(daemonJson, FyyProjectDaemonRoot.class);
            } catch (Exception e) {
            }
            if (daemonRoot == null || StrUtil.isBlank(daemonRoot.getDevelop_user_id())) {
                if (isClassModel) {
                    daemonRoot = new FyyProjectDaemonRoot();
                    while (true) {
                        FyyLogBaseUtils.warn("[{}]系统在本机首次运行,文件[{}]需要写入用户标识(例如:中文姓名,英文姓名[只能为中文,英文,数字]),请输入并点击Enter", FyyInitEnv.FrameInformation.ENGLISH_NAME, FyyInitEnv.FrameInformation.MAIN_CONFIG);
                        String develop_user_id = Console.input();
                        String pattern = "^[\\u4e00-\\u9fa5,A-Za-z0-9]{0,}";
                        boolean isMatch = Pattern.matches(pattern, develop_user_id);
                        if (isMatch) {
                            daemonRoot.setDevelop_user_id(develop_user_id);
                            break;
                        }
                    }
                    FileUtil.writeUtf8String(JSONUtil.formatJsonStr(JSONUtil.toJsonStr(daemonRoot)), FyyInitEnv.FrameInformation.MAIN_CONFIG);
                } else {
                    daemonRoot = new FyyProjectDaemonRoot();
                    daemonRoot.setDevelop_user_id(mainMac + FyyInitEnv.FrameInformation.USER_NAME);
                    FileUtil.writeUtf8String(JSONUtil.formatJsonStr(JSONUtil.toJsonStr(daemonRoot)), FyyInitEnv.FrameInformation.MAIN_CONFIG);
                }
            }
            if (!daemonRoot.getProject_config().containsKey(idkey)) {
                FyyProjectConfigRoot configRoot = new FyyProjectConfigRoot();
                configRoot.setProject_name_en(idkey);
                configRoot.setProject_name_cn(idkey);
                configRoot.setPrject_application_properties_path("");
                daemonRoot.getProject_config().put(idkey, configRoot);
                FileUtil.writeUtf8String(JSONUtil.formatJsonStr(JSONUtil.toJsonStr(daemonRoot)), FyyInitEnv.FrameInformation.MAIN_CONFIG);
            }
            FyyInitEnv.ProjectInformation.daemonRoot = daemonRoot;
            FyyLogBaseUtils.info("获取到用户标识:[{}}", JSONUtil.toJsonStr(daemonRoot));
        }


        {
            //
            FyyLogBaseUtils.debug("配置类赋值开始[{}}", FyyInitEnvLoad.class.getName());
            FyyInitEnv.ProjectInformation.idKey = idkey;
            FyyInitEnv.ProjectInformation.i18n = i18n;
            FyyInitEnv.WorkDir.PROJECT_PACKAGE = StrUtil.isNotBlank(projectPackage) ? projectPackage : ClassUtil.getPackage(ClassUtil.loadClass(mainClass, false));
            FyyInitEnv.WorkDir.WORK_HOME_DIR = workHomeDir;
            FyyInitEnv.ProjectInformation.autoUpdate = updateProperties;
            FyyInitEnv.ProjecEnvInformation.configEnv = configEnv;
            FyyInitEnv.ProjectInformation.runEnv = StrUtil.blankToDefault(runEnv, daemonRoot.getDevelop_user_id());
            FyyInitEnv.WorkDir.JAR_PATH = jarpath;
            FyyInitEnv.ProjecEnvInformation.isClassModel = isClassModel;
            FyyInitEnv.WorkDir.MAIN_CLASS_C = mainClassC;
            FyyInitEnv.ProjectInformation.mainClass = mainClass;
            FyyInitEnv.WorkDir.PROJECT_RESOURCEPATH = projectresourcepath;
            FyyInitEnv.WorkDir.PROJECT_CODESOURCEPATH = projectcodesourcepath;
            FyyInitEnv.ProjecEnvInformation.macSet.addAll(macSet);
            FyyInitEnv.ProjectInformation.mainMac = mainMac;
//            FyyInitEnv.SettingInformation.UK = uk;
        }
        {
            FyyLogBaseUtils.debug("公共模型赋值开始[{}}", FyyInitEnvLoad.class.getName());
            FyyInitEnv.ProjectInformation.context.put("idKey", FyyInitEnv.ProjectInformation.idKey);
            FyyInitEnv.ProjectInformation.context.put("mainClass", FyyInitEnv.ProjectInformation.mainClass);
            FyyInitEnv.ProjectInformation.context.put("isdev", FyyInitEnv.ProjecEnvInformation.isClassModel);
            FyyInitEnv.ProjectInformation.context.put("i18n", FyyInitEnv.ProjectInformation.i18n);
            FyyInitEnv.ProjectInformation.context.put("env", FyyInitEnv.ProjectInformation.runEnv);
            FyyInitEnv.ProjectInformation.context.put("projectPackage", FyyInitEnv.WorkDir.PROJECT_PACKAGE);
            FyyInitEnv.ProjectInformation.context.put("workHomeDir", FyyInitEnv.WorkDir.WORK_HOME_DIR);
        }

    }
}
