

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.annotation.method;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/**
 * 权限码管理
 */
public @interface FyyRuleAuthMethod {

    String method_code() default "";

    String[] need_role() default {};

    boolean auth() default false;

}