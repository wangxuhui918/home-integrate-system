

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 
 *
 */

package com.guoshiyao.rely;

//@Aspect
//@Component
public class TimeAspect {
    
//    @Around("  execution(* http.honge.api.auth.*.*(..))")
//    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
//        Object[] args = pjp.getArgs();
//        boolean validate = false;
//        boolean havingtoken = false;
//        for (Object arg : args) {
//            if (arg instanceof InputParamHongE) {
//                InputParamHongE k = (InputParamHongE) arg;
//                try {
//                    byte[] key = "honge".getBytes();
//                    validate = JWT.of(k.getHeToken()).setKey(key).validate(3600 * 24 * 7);
//                    havingtoken = StrUtil.isNotBlank(k.getHeToken());
//                    k.setUuid(JWT.of(k.getHeToken()).setKey(key).getHeader("userid").toString());
//                } catch (Exception e) {
//                }
//                break;
//            }
//        }
//        if (!validate) {
//            if (!havingtoken) {
//                throw new ExceptionError("用户未登录!!");
//            } else {
//                throw new ExceptionError("用户登陆信息失效!");
//            }
//        }
//        return pjp.proceed();
//    }
}