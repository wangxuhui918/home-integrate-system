

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatisplus.starter.mybatis.config;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.base.frame.impl.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.base.exception.type.FyyExceptionError;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 声明扫描第三方包
 *
 * @author 汪旭辉
 * @date 2018年7月10日
 * @readme TODO
 */
public class FyyMybatisPlusAutoConfiguration {

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("druidDataSource") DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MYBATISPLUS_TYPEALIASESPACKAGE.getKey()));
        // 分页插件
        PageInterceptor pageHelper = new PageInterceptor();
        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Interceptor[] plugins = new Interceptor[]{pageHelper};
        bean.setPlugins(plugins);
        try {
            Resource[] s = resolver.getResources(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MYBATISPLUS_MAPPERLOCATIONS.getKey()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new FyyExceptionError(
                    "请在资源目录下创建文件夹并创建Mapper.xml文件:" + FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MYBATISPLUS_MAPPERLOCATIONS.getKey()));
        }
        try {
            bean.setMapperLocations(
                    resolver.getResources(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MYBATISPLUS_MAPPERLOCATIONS.getKey())));
            bean.setConfigLocation(resolver.getResource("classpath:mybatis.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FyyExceptionError("处理MapperLocations失败!");
        }
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("druidDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
