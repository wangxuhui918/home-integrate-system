

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.core.configration.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RuleInjection {
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