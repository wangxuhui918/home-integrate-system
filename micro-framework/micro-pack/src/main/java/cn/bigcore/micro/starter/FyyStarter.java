

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.starter;

import cn.bigcore.micro.starter.registrar.FyyRegistrarAfter;
import cn.bigcore.micro.starter.registrar.FyyRegistrarBefore;
import cn.bigcore.micro.i18n.FyyI18n;
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
@Import({FyyRegistrarBefore.class, FyyRegistrarAfter.class})
@ComponentScan(excludeFilters = {@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)})
@ServletComponentScan
@EnableTransactionManagement
//@EnableApolloConfig
public @interface FyyStarter {


    /**
     * 需要排除自动加载的插件
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月6日
     * @README
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
     * 当前所在环境,不指定则自动识别环境变量
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月1日
     * @readme
     */
    String runEnv() default "";

    /**
     * 内置环境变量
     */
    String[] configEnv() default {"dev", "uat", "pro"};

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
     * 可以在cn.bigcore.micro.message.i18n.I18n枚举类中选择
     *
     * @return
     * @author 汪旭辉
     * @date 2021年9月27日
     * @readme
     */
    FyyI18n i18n() default FyyI18n.CN;

    boolean updateProperties() default false;

}