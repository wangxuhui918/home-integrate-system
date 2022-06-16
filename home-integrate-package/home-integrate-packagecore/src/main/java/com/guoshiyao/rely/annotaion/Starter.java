

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.annotaion;

import com.guoshiyao.rely.annotaion.registrar.AfterRegistrar;
import com.guoshiyao.rely.annotaion.registrar.BeforeRegistrar;
import com.guoshiyao.rely.environment.ENV;
import com.guoshiyao.rely.message.i18n.I18n;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@Import({BeforeRegistrar.class, AfterRegistrar.class})
@ComponentScan(excludeFilters = {@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)})
@ServletComponentScan
@EnableTransactionManagement
public @interface Starter {


    /**
     * 需要排除自动加载的插件
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月6日
     * @readme
     */
    @AliasFor(annotation = EnableAutoConfiguration.class)
    Class<?>[] exclude() default {MongoDataAutoConfiguration.class, MongoAutoConfiguration.class,
            DataSourceAutoConfiguration.class, FlywayAutoConfiguration.class};

    /**
     * 如果不传ServletComponentScan中默认扫描mainClass所在包
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月14日
     * @readme
     */
    @AliasFor(annotation = ServletComponentScan.class, attribute = "basePackages")
    String[] scanBasePackages() default {};


    /**
     * 当前所在环境
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月1日
     * @readme
     */
    ENV env() default ENV.LOCAL;

    /**
     * 项目唯一标志
     *
     * @return
     * @author 汪旭辉
     * @date 2021年9月27日
     * @readme
     */
    String idkey();

    /**
     * 国际话编码
     * 可以在com.guoshiyao.rely.message.i18n.I18n枚举类中选择
     *
     * @return
     * @author 汪旭辉
     * @date 2021年9月27日
     * @readme
     */
    I18n i18n() default I18n.CN;

    boolean updateProperties() default false;

    /**
     * 国际话编码
     * 可以在com.guoshiyao.rely.message.i18n.I18n枚举类中选择
     *
     * @return
     * @author 汪旭辉
     * @date 2021年9月27日
     * @readme
     */
    String mainClass() default "";
}