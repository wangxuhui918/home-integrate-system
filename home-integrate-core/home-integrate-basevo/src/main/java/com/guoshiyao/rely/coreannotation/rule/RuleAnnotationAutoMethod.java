

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.coreannotation.rule;

import com.guoshiyao.rely.coreannotation.base.Method;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RuleAnnotationAutoMethod {

    Class mapper() default HomeNull.class;

    Method method() default Method.nomethod;

}