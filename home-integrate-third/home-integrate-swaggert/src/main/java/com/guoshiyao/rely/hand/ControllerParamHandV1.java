

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.hand;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotationApi;
import com.guoshiyao.rely.exception.ExceptionApiNull;
import com.guoshiyao.rely.exception.code.re.CodeAbE;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.outgoing.AuthReturnType;
import com.guoshiyao.rely.outgoing.InputParamAb;
import com.guoshiyao.rely.outgoing.utils.CodeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(Integer.MAX_VALUE - 1)
public class ControllerParamHandV1 {


    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    private void annotationPointCut() {
    }


    @Around("annotationPointCut()")
    public Object annotationAround(ProceedingJoinPoint jp) throws Throwable {
        boolean havingApiAnnataion = AnnotationUtil.hasAnnotation(jp.getTarget().getClass(), RuleAnnotationApi.class);

        if (havingApiAnnataion) {
            Object[] args = jp.getArgs();
            boolean validate = false;
            InputParamAb k = null;
            for (Object arg : args) {
                if (arg instanceof InputParamAb) {
                    k = (InputParamAb) arg;
                    break;
                }
            }
            if (k == null) {//无InputParamAb拦截的情况
                try {
                    String className = Line.setting.get("system.inputparamab.class");
                    k = (InputParamAb) ClassUtil.loadClass(className, false).newInstance();
                } catch (Exception e) {
                }
            }
            if (k == null) {
                LoggerBaseAb.warn("参数{}未配置{}子类", "system.inputparamab.class", InputParamAb.class.getName());
            }
            if (k != null) {
                try {
                    AuthReturnType authReturnType = k.checkAuth();
                    if (authReturnType == AuthReturnType.AuthSuccess) {//鉴权通过
                    } else {//鉴权失败
                        return CodeUtils.go(CodeAbE.getBuiltinCode(CodeAbE.getError().getType(), Line.i18n, authReturnType.getCode(), authReturnType.getName()));
                    }
                } catch (Exception e) {
                    return CodeUtils.go(CodeAbE.getError("未知鉴权异常!"));
                }
            }
        }

        Object body = jp.proceed();
        if (havingApiAnnataion && body == null) {
            throw new ExceptionApiNull();//如果值为空的
        }
        return body;//返回值不为空以及非管理的放过,,,
    }


}
