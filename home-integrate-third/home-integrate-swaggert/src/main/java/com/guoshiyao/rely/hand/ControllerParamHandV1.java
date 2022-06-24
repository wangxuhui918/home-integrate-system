

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.hand;

import cn.hutool.core.annotation.AnnotationUtil;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotationApi;
import com.guoshiyao.rely.exception.ExceptionApiNull;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ControllerParamHandV1 {


    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    private void annotationPointCut() {
    }


    @Around("annotationPointCut()")
    public Object annotationAround(ProceedingJoinPoint jp) throws Throwable {
        Object body = jp.proceed();
        boolean havingApiAnnataion = AnnotationUtil.hasAnnotation(jp.getTarget().getClass(), RuleAnnotationApi.class);
        if (havingApiAnnataion && body == null) {
            throw new ExceptionApiNull();//如果值为空的
        }
        return body;//返回值不为空以及非管理的放过,,,
    }

}
