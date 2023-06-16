

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatis.annotation;

import cn.bigcore.micro.mybatis.annotation.bean.FyyMybatisNull;
import cn.bigcore.micro.mybatis.annotation.bean.FyyMybatisMethod;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FyyMybatisRuleInjectionMehtod {

    Class mapper() default FyyMybatisNull.class;

    FyyMybatisMethod method() default FyyMybatisMethod.nomethod;

}