

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
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.annotation.RuleController;
import com.guoshiyao.rely.core.configration.home.impl.bean.ConfigDetails;
import com.guoshiyao.rely.outgoing.utils.CodeUtils;
import com.guoshiyao.rely.plugin.exception.ExceptionApiNull;
import com.guoshiyao.rely.plugin.exception.code.impl.CodeImpl;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;
import com.guoshiyao.rely.plugin.outgoing.AuthReturnType;
import com.guoshiyao.rely.plugin.outgoing.InputParamAb;
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
        boolean havingApiAnnataion = AnnotationUtil.hasAnnotation(jp.getTarget().getClass(), RuleController.class);

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
                    String className = BaseEv.SettingInformation.setting.get(ConfigDetails.SYSTEM_INPUTPARAMAB_CLASS.getKey());
                    k = (InputParamAb) ClassUtil.loadClass(className, false).newInstance();
                } catch (Exception e) {
                }
            }
            if (k == null) {
                ILoggerBaseUtils.warn("参数{}未配置{}子类", ConfigDetails.SYSTEM_INPUTPARAMAB_CLASS.getKey(), InputParamAb.class.getName());
            }
            if (k != null) {
                try {
                    AuthReturnType authReturnType = k.checkAuth();
                    if (authReturnType == AuthReturnType.AuthSuccess) {//鉴权通过
                    } else {//鉴权失败
                        return CodeUtils.go(CodeImpl.getBuiltinCode(CodeImpl.getError().getType(), BaseEv.SettingInformation.i18n, authReturnType.getCode(), authReturnType.getName()));
                    }
                } catch (Exception e) {
                    return CodeUtils.go(CodeImpl.getError("未知鉴权异常!"));
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
