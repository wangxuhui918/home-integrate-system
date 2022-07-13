

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.coreannotation;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.log.base.LoggerBaseAb;

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

    /**
     * 根据注解,超类,默认有效标志获取有效的规则类
     *
     * @param <A>
     * @param <T>
     * @param annotaione
     * @param classe
     * @param packet
     * @return
     * @author 汪旭辉
     * @date 2021年12月6日
     * @readme
     */
    public static <A extends Annotation, T> List<T> getRuleOn(Class<A> annotaione, Class<T> classe, String packet) {
        return getRule(annotaione, classe, packet, null);
    }

    /**
     * 根据注解,超类,有效标志获取有效的规则类
     *
     * @param <A>
     * @param <T>
     * @param annotaione
     * @param classe
     * @param packet
     * @param key
     * @return
     * @author 汪旭辉
     * @date 2021年12月6日
     * @readme
     */
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
//                else {
//                    boolean value = (boolean) AnnotationUtil.getAnnotationValueMap(class1, annotaione).get(key);
//                    if (value) {
//                        listarra.add(sd);
//                    }
//                }
            } catch (Exception e) {
                e.printStackTrace();
                LoggerBaseAb.warn(class1 + "转换失败,服务停止!!!");
            }
        }
        return listarra;
    }
}
