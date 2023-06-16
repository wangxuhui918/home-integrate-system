

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.data;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.exception.re.ex.FyyExceptionError;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FyyLineDataSourcesBean {

    @Bean("druidDataSource")
    //    @ConditionalOnMissingBean(DataSource.class) //取消这个注释 容器中如果没有这个类,那么自动配置这个类PropertiesValue.
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_URL.getKey()));
        druidDataSource.setUsername(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_USERNAME.getKey()));
        druidDataSource.setPassword(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_PASSWORD.getKey()));

        if (FyyInitEnv.SettingInformation.setting.containsKey(FyyConfigEntryDetailsValues.HOME_DB_DRIVERCLASSNAME.getKey())) {
            druidDataSource.setDriverClassName(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_DRIVERCLASSNAME.getKey()));
        } else if (StrUtil.isNotBlank(FyyInitEnv.WorkDir.main_jdbc_jar_fullpath)) {
            FyyLogBaseUtils.info("开始联机驱动!");
            try {
                URL url = new URL("file://" + FyyInitEnv.WorkDir.main_jdbc_jar_fullpath); // 这里需要重点看URLClassLoader用法，
                URLClassLoader loader = new URLClassLoader(new URL[]{url}); // URL跟我们日常见到的格式用法不太一样
                druidDataSource.setDriverClassLoader(loader);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new FyyExceptionError("联机驱动{}失败!", FyyInitEnv.WorkDir.main_jdbc_jar_fullpath);
            }
        }

        druidDataSource.setMaxActive(FyyInitEnv.SettingInformation.setting.getInt(FyyConfigEntryDetailsValues.HOME_DB_MAXACTIVE.getKey()));
        druidDataSource.setInitialSize(FyyInitEnv.SettingInformation.setting.getInt(FyyConfigEntryDetailsValues.HOME_DB_INITIALSIZE.getKey()));
        druidDataSource.setMaxWait(FyyInitEnv.SettingInformation.setting.getInt(FyyConfigEntryDetailsValues.HOME_DB_MAXWAIT.getKey()));
        druidDataSource.setMinIdle(FyyInitEnv.SettingInformation.setting.getInt(FyyConfigEntryDetailsValues.HOME_DB_MINIDLE.getKey()));
        druidDataSource.setTimeBetweenEvictionRunsMillis(FyyInitEnv.SettingInformation.setting.getInt(FyyConfigEntryDetailsValues.HOME_DB_TIMEBETWEENEVICTIONRUNSMILLIS.getKey()));
        druidDataSource
                .setMinEvictableIdleTimeMillis(FyyInitEnv.SettingInformation.setting.getInt(FyyConfigEntryDetailsValues.HOME_DB_MINEVICTABLEIDLETIMEMILLIS.getKey()));
        druidDataSource.setValidationQuery(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_VALIDATIONQUERY.getKey()));
        druidDataSource.setTestWhileIdle(FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.HOME_DB_TESTWHILEIDLE.getKey()));
        druidDataSource.setTestOnBorrow(FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.HOME_DB_TESTONBORROW.getKey()));
        druidDataSource.setTestOnReturn(FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.HOME_DB_TESTONRETURN.getKey()));
        druidDataSource.setPoolPreparedStatements(FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.HOME_DB_POOLPREPAREDSTATEMENTS.getKey()));
        druidDataSource.setRemoveAbandoned(FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.HOME_DB_REMOVEABANDONED.getKey()));
        druidDataSource.setRemoveAbandonedTimeout(FyyInitEnv.SettingInformation.setting.getInt(FyyConfigEntryDetailsValues.HOME_DB_REMOVEABANDONEDTIMEOUT.getKey()));
        druidDataSource.setNumTestsPerEvictionRun(FyyInitEnv.SettingInformation.setting.getInt(FyyConfigEntryDetailsValues.HOME_DB_NUMTESTSPEREVICTIONRUN.getKey()));
        druidDataSource
                .setMaxOpenPreparedStatements(FyyInitEnv.SettingInformation.setting.getInt(FyyConfigEntryDetailsValues.HOME_DB_MAXOPENPREPAREDSTATEMENTS.getKey()));
//		druidDataSource.allowMultiQueries
//		druidDataSource.setstatv
        System.setProperty("spring.datasource.druid.web-stat-filter.enabled", "true");
        System.setProperty("spring.datasource.druid.web-stat-filter.exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        FyyInitEnv.SettingInformation.dataSource = druidDataSource;
        try {
            druidDataSource.setFilters(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_FILTERS.getKey()));
        } catch (SQLException e) {
            throw new FyyExceptionError("设置数据库参数filters错误,请检查,系统退出!");

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
