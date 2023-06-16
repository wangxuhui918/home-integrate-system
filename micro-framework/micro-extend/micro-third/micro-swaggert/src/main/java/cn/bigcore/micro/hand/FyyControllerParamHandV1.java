

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.hand;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.annotation.FyyRuleController;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.exception.FyyExceptionApiNull;
import cn.bigcore.micro.exception.code.FyyCodeUtils;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.bigcore.micro.outgoing.FyyAuthReturnType;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.bigcore.micro.outgoing.FyyInputParamInterface;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(Integer.MAX_VALUE - 1)
public class FyyControllerParamHandV1 {


    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    private void annotationPointCut() {
    }


    @Around("annotationPointCut()")
    public Object annotationAround(ProceedingJoinPoint jp) throws Throwable {
        boolean havingApiAnnataion = AnnotationUtil.hasAnnotation(jp.getTarget().getClass(), FyyRuleController.class);

        if (havingApiAnnataion) {
            Object[] args = jp.getArgs();
            boolean validate = false;
            FyyInputParamInterface k = null;
            for (Object arg : args) {
                if (arg instanceof FyyInputParamInterface) {
                    k = (FyyInputParamInterface) arg;
                    break;
                }
            }
            if (k == null) {//无InputParamAb拦截的情况
                try {
                    String className = FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.SYSTEM_INPUTPARAMAB_CLASS.getKey());
                    k = (FyyInputParamInterface) ClassUtil.loadClass(className, false).newInstance();
                } catch (Exception e) {
                }
            }
            if (k == null) {
                FyyLogBaseUtils.warn("参数{}未配置{}子类", FyyConfigEntryDetailsValues.SYSTEM_INPUTPARAMAB_CLASS.getKey(), FyyInputParamInterface.class.getName());
            }
            if (k != null) {
                try {
                    FyyAuthReturnType authReturnType = k.checkAuth();
                    if (authReturnType == FyyAuthReturnType.AuthSuccess) {//鉴权通过
                    } else {//鉴权失败
                        return cn.bigcore.micro.outgoing.utils.FyyCodeUtils.go(FyyCodeUtils.getBuiltinCode(FyyCodeUtils.getError().getType(), FyyInitEnv.SettingInformation.i18n, authReturnType.getCode(), authReturnType.getName()));
                    }
                } catch (Exception e) {
                    return cn.bigcore.micro.outgoing.utils.FyyCodeUtils.go(FyyCodeUtils.getError("未知鉴权异常!"));
                }
            }
        }

        Object body = jp.proceed();
        if (havingApiAnnataion && body == null) {
            throw new FyyExceptionApiNull();//如果值为空的
        }
        return body;//返回值不为空以及非管理的放过,,,
    }


}
