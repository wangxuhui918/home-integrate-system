

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.data;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataSourcesConfig {

    @Bean("druidDataSource")
    //    @ConditionalOnMissingBean(DataSource.class) //取消这个注释 容器中如果没有这个类,那么自动配置这个类PropertiesValue.
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(Line.properties.get("home.db.url").getString());
        druidDataSource.setUsername(Line.properties.get("home.db.username").getString());
        druidDataSource.setPassword(Line.properties.get("home.db.password").getString());

        if (!Line.properties.get("home.db.driverclassname").isBlank()) {
            druidDataSource.setDriverClassName(Line.properties.get("home.db.driverclassname").getString());
        } else if (StrUtil.isNotBlank(Line.main_jdbc_jar_fullpath)) {
            LoggerBaseAb.info("开始联机驱动!");
            try {
                URL url = new URL("file://" + Line.main_jdbc_jar_fullpath); // 这里需要重点看URLClassLoader用法，
                URLClassLoader loader = new URLClassLoader(new URL[]{url}); // URL跟我们日常见到的格式用法不太一样
                druidDataSource.setDriverClassLoader(loader);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ExceptionError("联机驱动{}失败!", Line.main_jdbc_jar_fullpath);
            }
        }

        druidDataSource.setMaxActive(Line.properties.get("home.db.maxactive").getInteger());
        druidDataSource.setInitialSize(Line.properties.get("home.db.initialsize").getInteger());
        druidDataSource.setMaxWait(Line.properties.get("home.db.maxwait").getInteger());
        druidDataSource.setMinIdle(Line.properties.get("home.db.minidle").getInteger());
        druidDataSource.setTimeBetweenEvictionRunsMillis(
                Line.properties.get("home.db.timebetweenevictionrunsmillis").getInteger());
        druidDataSource
                .setMinEvictableIdleTimeMillis(Line.properties.get("home.db.minevictableidletimemillis").getInteger());
        druidDataSource.setValidationQuery(Line.properties.get("home.db.validationquery").getString());
        druidDataSource.setTestWhileIdle(Line.properties.get("home.db.testwhileidle").getBoolean());
        druidDataSource.setTestOnBorrow(Line.properties.get("home.db.testonborrow").getBoolean());
        druidDataSource.setTestOnReturn(Line.properties.get("home.db.testonreturn").getBoolean());
        druidDataSource.setPoolPreparedStatements(Line.properties.get("home.db.poolpreparedstatements").getBoolean());
        druidDataSource.setRemoveAbandoned(Line.properties.get("home.db.removeabandoned").getBoolean());
        druidDataSource.setRemoveAbandonedTimeout(Line.properties.get("home.db.removeabandonedtimeout").getInteger());
        druidDataSource.setNumTestsPerEvictionRun(Line.properties.get("home.db.numtestsperevictionrun").getInteger());
        druidDataSource
                .setMaxOpenPreparedStatements(Line.properties.get("home.db.maxopenpreparedstatements").getInteger());
//		druidDataSource.allowMultiQueries
//		druidDataSource.setstatv
        System.setProperty("spring.datasource.druid.web-stat-filter.enabled", "true");
        System.setProperty("spring.datasource.druid.web-stat-filter.exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        com.guoshiyao.rely.line.Line.dataSource = druidDataSource;
        try {
            druidDataSource.setFilters(Line.properties.get("home.db.filters").getString());
        } catch (SQLException e) {
            throw new ExceptionError("设置数据库参数filters错误,请检查,系统退出!");

        }
        return druidDataSource;
    }

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        // 可配的属性都在 StatViewServlet 和其父类下
         servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> druidWebStatFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(
                new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

}
