

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AnnotationTools {

    public static int getRuleClassForClass(Class classe, String packet) {
        Set<Class<?>> classes = ClassUtil.scanPackageBySuper(packet, classe);
        return classes.size();
    }

    public static int getRuleClassForAnno(Class annotaione, String packet) {
        return ClassUtil.scanPackageByAnnotation(packet, annotaione).size();
    }


    public static <A extends Annotation, T> List<T> getRuleOn(Class<A> annotaione, Class<T> classe, String packet) {
        return getRule(annotaione, classe, packet, null);
    }


    public static <A extends Annotation, T> List<T> getRule(Class<A> annotaione, Class<T> classe, String packet,
                                                            String key) {
        List<T> listarra = new ArrayList<>();
        Set<Class<?>> annotaiones = ClassUtil.scanPackageByAnnotation(packet, annotaione);
        Set<Class<?>> classes = ClassUtil.scanPackageBySuper(packet, classe);
        Collection<Class<?>> intersectionSet = CollUtil.intersection(annotaiones, classes);
        for (Class<?> class1 : intersectionSet) {
            try {
                T sd = (T) ClassUtil.loadClass(class1.getName(), false).newInstance();
                if (StrUtil.isBlank(key)) {
                    listarra.add(sd);
                }
            } catch (Exception e) {
                e.printStackTrace();
                ILoggerBaseUtils.warn(class1 + "转换失败,服务停止!!!");
            }
        }
        return listarra;
    }
}
