

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatis.annotation;

import cn.bigcore.micro.mybatis.annotation.bean.HomeNull;
import cn.bigcore.micro.mybatis.annotation.bean.Method;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RuleInjectionMehtod {

    Class mapper() default HomeNull.class;

    Method method() default Method.nomethod;

}