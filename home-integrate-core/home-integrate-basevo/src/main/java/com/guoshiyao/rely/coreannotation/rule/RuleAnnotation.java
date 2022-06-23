

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.coreannotation.rule;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RuleAnnotation {
    /**
     * 加载开关 默认打开,关闭后
     *
     * @return
     * @author 汪旭辉
     * @date 2021年9月29日
     * @readme
     */
    boolean on() default true;
}