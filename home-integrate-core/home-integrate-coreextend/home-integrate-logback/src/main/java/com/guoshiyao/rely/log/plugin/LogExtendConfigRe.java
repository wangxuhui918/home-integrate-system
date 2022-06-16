

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.log.plugin;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.sys.SystemConfigAb;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RuleAnnotation
public class LogExtendConfigRe implements SystemConfigAb {


    public final static String logappid = "home.log.appid";
    public final static String loglevel = "home.log.syslevel";
    public final static String rootlevel = "home.log.rootlevel";
    public final static String businlevel = "home.log.businlevel";
    public final static String businpackage = "home.log.businpackage";
    public final static String logdir = "home.log.dir";

    @Override
    public void after() {
        LoggerBaseAb.info("开始翻转日志管理器!!");
        try {
            LogBackConfigLoader.load("ching.xml");
            LoggerBaseAb.info("翻转日志管理器成功!!");
        } catch (Exception e) {
            throw new ExceptionError("日志管理器{}加载失败!请检查文件是否符合规范!!", "ching.xml");
        }
    }

    @Override
    public void before() {

    }

    @Override
    public String getName() {
        return "日志管理器";
    }

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        return new LinkedHashMap<>();
    }

    @Override
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
//        String PROPERTIES = Line.envFileUtil.getThisEnvResourceConfigPath(1000008);
        HashMap<String, PropertiesMap<String, LinePropertiesAb>> map = new HashMap<>();
        HashMap<String, String> params1 = new LinkedHashMap<>();
        try {
//            HashMap<String, String> params2 = PropertiesUtils.getProperties(Line.mainClass, PROPERTIES);
            HashMap<String, String> params2 =  ProjectCoreConfUtils.getEnvPropertiesByCode("1000008");
            for (String key : params2.keySet()) {
                if (StrUtil.isNotBlank(params2.get(key))) {
                    params1.put(key, params2.get(key));
                }
            }
        } catch (Exception e) {
            LoggerBaseAb.warn("系统错误,采用默认");
            LoggerBaseAb.warn(JSONUtil.toJsonStr(params1));
        }
        map.put(getName(), LinePropertiesAb.convertLineProperties(params1));
        return map;
    }

}
