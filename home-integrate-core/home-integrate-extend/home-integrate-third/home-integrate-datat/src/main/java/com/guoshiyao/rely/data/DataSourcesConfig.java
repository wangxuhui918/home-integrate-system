

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.data;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties.ConfigDetails;
import com.guoshiyao.rely.plugin.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;
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
        druidDataSource.setUrl(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_DB_URL.getKey()));
        druidDataSource.setUsername(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_DB_USERNAME.getKey()));
        druidDataSource.setPassword(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_DB_PASSWORD.getKey()));

        if (BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_DRIVERCLASSNAME.getKey())) {
            druidDataSource.setDriverClassName(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_DB_DRIVERCLASSNAME.getKey()));
        } else if (StrUtil.isNotBlank(BaseEv.WorkDir.main_jdbc_jar_fullpath)) {
            ILoggerBaseUtils.info("开始联机驱动!");
            try {
                URL url = new URL("file://" + BaseEv.WorkDir.main_jdbc_jar_fullpath); // 这里需要重点看URLClassLoader用法，
                URLClassLoader loader = new URLClassLoader(new URL[]{url}); // URL跟我们日常见到的格式用法不太一样
                druidDataSource.setDriverClassLoader(loader);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ExceptionError("联机驱动{}失败!", BaseEv.WorkDir.main_jdbc_jar_fullpath);
            }
        }

        druidDataSource.setMaxActive(BaseEv.SettingInformation.setting.getInt(ConfigDetails.HOME_DB_MAXACTIVE.getKey()));
        druidDataSource.setInitialSize(BaseEv.SettingInformation.setting.getInt(ConfigDetails.HOME_DB_INITIALSIZE.getKey()));
        druidDataSource.setMaxWait(BaseEv.SettingInformation.setting.getInt(ConfigDetails.HOME_DB_MAXWAIT.getKey()));
        druidDataSource.setMinIdle(BaseEv.SettingInformation.setting.getInt(ConfigDetails.HOME_DB_MINIDLE.getKey()));
        druidDataSource.setTimeBetweenEvictionRunsMillis(BaseEv.SettingInformation.setting.getInt(ConfigDetails.HOME_DB_TIMEBETWEENEVICTIONRUNSMILLIS.getKey()));
        druidDataSource
                .setMinEvictableIdleTimeMillis(BaseEv.SettingInformation.setting.getInt(ConfigDetails.HOME_DB_MINEVICTABLEIDLETIMEMILLIS.getKey()));
        druidDataSource.setValidationQuery(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_DB_VALIDATIONQUERY.getKey()));
        druidDataSource.setTestWhileIdle(BaseEv.SettingInformation.setting.getBool(ConfigDetails.HOME_DB_TESTWHILEIDLE.getKey()));
        druidDataSource.setTestOnBorrow(BaseEv.SettingInformation.setting.getBool(ConfigDetails.HOME_DB_TESTONBORROW.getKey()));
        druidDataSource.setTestOnReturn(BaseEv.SettingInformation.setting.getBool(ConfigDetails.HOME_DB_TESTONRETURN.getKey()));
        druidDataSource.setPoolPreparedStatements(BaseEv.SettingInformation.setting.getBool(ConfigDetails.HOME_DB_POOLPREPAREDSTATEMENTS.getKey()));
        druidDataSource.setRemoveAbandoned(BaseEv.SettingInformation.setting.getBool(ConfigDetails.HOME_DB_REMOVEABANDONED.getKey()));
        druidDataSource.setRemoveAbandonedTimeout(BaseEv.SettingInformation.setting.getInt(ConfigDetails.HOME_DB_REMOVEABANDONEDTIMEOUT.getKey()));
        druidDataSource.setNumTestsPerEvictionRun(BaseEv.SettingInformation.setting.getInt(ConfigDetails.HOME_DB_NUMTESTSPEREVICTIONRUN.getKey()));
        druidDataSource
                .setMaxOpenPreparedStatements(BaseEv.SettingInformation.setting.getInt(ConfigDetails.HOME_DB_MAXOPENPREPAREDSTATEMENTS.getKey()));
//		druidDataSource.allowMultiQueries
//		druidDataSource.setstatv
        System.setProperty("spring.datasource.druid.web-stat-filter.enabled", "true");
        System.setProperty("spring.datasource.druid.web-stat-filter.exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        BaseEv.SettingInformation.dataSource = druidDataSource;
        try {
            druidDataSource.setFilters(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_DB_FILTERS.getKey()));
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
