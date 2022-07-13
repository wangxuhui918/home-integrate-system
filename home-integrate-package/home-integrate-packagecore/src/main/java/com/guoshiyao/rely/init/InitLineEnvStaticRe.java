/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 
 *
 */

package com.guoshiyao.rely.init;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.ManifestUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.SystemUtil;
import com.guoshiyao.rely.annotaion.Starter;
import com.guoshiyao.rely.base.BaseEv;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotationApi;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.log.base.LoggerBaseUtils;

import java.io.File;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;
import java.util.logging.Level;

@RuleAnnotationApi
public class InitLineEnvStaticRe implements Line.InitLineEnvStaticAb {
    @Override
    public void init() {
        String idkey = "", i18n = "", projectPackage = "";
        Level loglevel = Level.FINER;//默认日志级别
        String runEnv = "";
        boolean updateProperties = false;
        String[] configEnv = null;
        {
            String getJavaCommand = System.getProperty("sun.java.command");
            try {
                if (URLUtil.isJarFileURL(new File(getJavaCommand).toURI().toURL())) {//如果是运行时
                    LoggerBaseAb.info("检测到当前为线上运行模式");
                    Line.jarpath = getJavaCommand;
                    Line.isClassModel = false;
                    java.util.jar.Manifest masin = ManifestUtil.getManifest(new JarFile(new File(getJavaCommand)));
                    Line.mainClassC = ClassUtil.loadClass(masin.getMainAttributes().getValue("Start-Class"), false);
                    Line.mainClass = masin.getMainAttributes().getValue("Start-Class");
                } else if (ClassUtil.isNormalClass(ClassUtil.loadClass(getJavaCommand,false))) {//如果是非运行时
                    LoggerBaseAb.info("检测到当前为开发者模式");
                    Line.isClassModel = true;
                    Line.mainClassC = ClassUtil.loadClass(getJavaCommand, false);
                    Line.mainClass = getJavaCommand;
                } else {
                    throw new ExceptionError("系统关键环境变量[{}]丢失,无法启动...", "sun.java.command");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExceptionError("系统关键环境变量[{}]丢失,无法启动...", "sun.java.command");
            }
        }
        if (Line.isClassModel) {
            Line.projectresourcepath = StrUtil.subBefore(ClassUtil.loadClass(Line.class.getName()).getClassLoader().getResource("").getPath(), "target" + File.separator + "classes", true) + File.separator + "src"
                    + File.separator + "main" + File.separator + "resources" + File.separator;
            Line.projectcodesourcepath = StrUtil.subBefore(ClassUtil.loadClass(Line.class.getName()).getClassLoader().getResource("").getPath(), "target" + File.separator + "classes", true) + File.separator + "src"
                    + File.separator + "main" + File.separator + "java" + File.separator;
        }

        {
            for (NetworkInterface networkinterface : NetUtil.getNetworkInterfaces()) {
                for (InterfaceAddress interfaceaddress : networkinterface.getInterfaceAddresses()) {
                    String temp = NetUtil.getMacAddress(interfaceaddress.getAddress());
                    if (StrUtil.isNotBlank(temp)) {
                        try {
                            Line.macSet.add(temp.toUpperCase());
                        } catch (Exception e) {
                        }
                    }
                }
            }
            Line.mainMac = Line.macSet.iterator().next();
        }
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

        {
            {
                Starter starter = AnnotationUtil.getAnnotation(Line.mainClassC, Starter.class);
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

                try {
                    runEnv = starter.runEnv();
                    if (StrUtil.isBlank(runEnv)) {//自动选择根据环境变量确定
                        List<String> lista = Arrays.asList(SystemUtil.get("env"), SystemUtil.get("ENV"), SystemUtil.get("Env"), SystemUtil.get("ENv"), SystemUtil.get("ENv"));
                        for (int i = 0; i < lista.size(); i++) {
                            String lineEnv = lista.get(i);
                            if (StrUtil.isNotBlank(lineEnv)) {
                                runEnv = lineEnv;
                                break;
                            }
                        }
                    }
                    runEnv = StrUtil.blankToDefault(runEnv, "");
                    if (StrUtil.isBlank(runEnv) && !Line.isClassModel) {
                        throw new ExceptionError("运行时模式下需指定环境变量 -Denv= {} ", JSONUtil.toJsonStr(configEnv));
                    }
                } catch (Exception e) {
                }
                try {
                    if (StrUtil.isNotBlank(SystemUtil.get("loglevel"))) {//如果日志界别不为空则直接查找,根据名字和值自动查找
                        loglevel = Level.parse(SystemUtil.get("loglevel"));
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
                if (StrUtil.isNotBlank(runEnv) && configEnv != null && configEnv.length > 0 && !ArrayUtil.contains(configEnv, runEnv)) {
                    throw new ExceptionError("参数{}[{}]需在参数{}[{}]范围内", "runEnv", runEnv, "configEnv", JSONUtil.toJsonStr(configEnv));
                }
            }
        }
        {
            Line.idKey = idkey;
            Line.i18n = i18n;
            Line.projectPackage = StrUtil.isNotBlank(projectPackage) ? projectPackage
                    : ClassUtil.getPackage(ClassUtil.loadClass(Line.mainClass,false));
            Line.workHomeDir = SystemUtil.getUserInfo().getHomeDir() + File.separator + BaseEv.HOME_TAG + File.separator + Line.idKey
                    + File.separator;
            Line.autoUpdate = updateProperties;
            Line.configEnv = configEnv;
            LoggerBaseUtils.setLevel(loglevel);//临时处理日志管理
            Line.runEnv = StrUtil.blankToDefault(runEnv, Line.UK);
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
