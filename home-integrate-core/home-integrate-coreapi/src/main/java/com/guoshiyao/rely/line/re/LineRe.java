

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */


package com.guoshiyao.rely.line.re;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.coreab.ClassModelConfigRe;
import com.guoshiyao.rely.coreab.RunModelConfigRe;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.HomeCoreConfUtils;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.line.LineAb;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.sys.SystemConfigAb;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 首选链式处理器
 *
 * @author 汪旭辉
 * @date 2021年11月30日
 * @readme
 */
@RuleAnnotation
public class LineRe implements LineAb<Line> {

    private static List<ClassModelConfigRe> classmodelconfigrerules = HomeCoreConfUtils.sortByDbOrRuleApi(ClassModelConfigRe.class);

    private static List<RunModelConfigRe> runmodelconfigrerules = HomeCoreConfUtils.sortByDbOrRuleApi(RunModelConfigRe.class);


    /**
     * 处理变量,处理系统运行前的一些配置准备
     */
    @Override
    public void before() {

        if (Line.isClassModel) {//仅在class模式下需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                ClassModelConfigRe info = classmodelconfigrerules.get(i);
                info.before();
            }
        }
        {//运行时模式需要处理的部分
            for (int i = 0; i < runmodelconfigrerules.size(); i++) {
                RunModelConfigRe info = runmodelconfigrerules.get(i);
                info.before();
            }
        }
    }

    /**
     * 加载配置
     */
    @Override
    public void start() {
        //读取配置参数
        {
            //读取系统参数
            for (int i = 0; i < runmodelconfigrerules.size(); i++) {
                RunModelConfigRe info = runmodelconfigrerules.get(i);
                if (info instanceof SystemConfigAb) {
                    Map<String, String> properties = info.writeProperties();
                    Line.setting.putAll(properties);
                }
            }
            //读取第三方参数
            for (int i = 0; i < runmodelconfigrerules.size(); i++) {
                RunModelConfigRe info = runmodelconfigrerules.get(i);
                if (info instanceof ThirdExtendConfigAb) {
                    Map<String, String> properties = info.writeProperties();
                    Line.setting.putAll(properties);
                }
            }
            if (Line.isClassModel) {//提前处理非Jar模式需要处理的东西
                for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                    ClassModelConfigRe info = classmodelconfigrerules.get(i);
                    Map<String, String> properties = info.writeProperties();
                    Line.setting.putAll(properties);
                }
            }
        }
        //额外参数处理器,比如配置参数变动值
        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            RunModelConfigRe info1 = runmodelconfigrerules.get(i);
            if (info1 instanceof ThirdExtendConfigAb) {
                for (RunModelConfigRe info : runmodelconfigrerules) {
                    if (info instanceof ThirdExtendConfigAb) {
                        ((ThirdExtendConfigAb) info).callSetting(Line.setting);
                    }
                }
            }
        }
        {
            {
                Map<String, String> notBlankMap = MapUtil.filter(new HashMap<>(Line.setting), t -> StrUtil.isNotBlank(t.getValue()));
                Line.setting.clear();
                Line.setting.putAll(notBlankMap);
            }
            {//兼容老数据
                Line.properties.putAll(LinePropertiesAb.convertLineProperties(Line.setting));
            }
            //写入系统变量
            for (String key : Line.setting.keySet()) {
                System.setProperty(key, Line.setting.get(key) == null ? null : Line.setting.getStr(key));
            }
        }
    }

    /**
     *
     */
    @Override
    public void after() {
        //IOC对象加载,按照顺序
        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            RunModelConfigRe info = runmodelconfigrerules.get(i);
            if (info instanceof SystemConfigAb) {
                LinkedHashMap<String, List<Class>> classes = info.writeClasss();
                Line.iocclasses.putAll(classes);
            }
        }
        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            RunModelConfigRe info = runmodelconfigrerules.get(i);
            if (info instanceof ThirdExtendConfigAb) {
                LinkedHashMap<String, List<Class>> classes = info.writeClasss();
                Line.iocclasses.putAll(classes);
            }
        }
        if (Line.isClassModel) {//提前处理非Jar模式需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                ClassModelConfigRe info = classmodelconfigrerules.get(i);
                LinkedHashMap<String, List<Class>> classes = info.writeClasss();
                if (classes != null) {
                    Line.iocclasses.putAll(classes);
                }
            }
        }

        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            RunModelConfigRe info = runmodelconfigrerules.get(i);
            info.after();
        }

        if (Line.isClassModel) {//提前处理非Jar模式需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                ClassModelConfigRe info = classmodelconfigrerules.get(i);
                info.after();
            }
        }
        {
            {
                Map<String, String> notBlankMap = MapUtil.filter(new HashMap<>(Line.setting), t -> StrUtil.isNotBlank(t.getValue()));
                Line.setting.clear();
                Line.setting.putAll(notBlankMap);
            }
            {//兼容老数据
                Line.properties.putAll(LinePropertiesAb.convertLineProperties(Line.setting));
            }
            //写入系统变量
            for (String key : Line.setting.keySet()) {//防止一些额外的变量未写入到系统参数中
                System.setProperty(key, Line.setting.get(key));
            }
        }
    }

}
