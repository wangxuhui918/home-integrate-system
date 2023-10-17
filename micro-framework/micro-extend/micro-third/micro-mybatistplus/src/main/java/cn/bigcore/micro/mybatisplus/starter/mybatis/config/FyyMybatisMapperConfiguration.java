

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatisplus.starter.mybatis.config;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.base.FyyConfigEntryDetailsValues;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;


public class FyyMybatisMapperConfiguration {
    @Bean("mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(FyyInitEnv.setting.get(FyyConfigEntryDetailsValues.HOME_MYBATISPLUS_BASEPACKAGE.getKey()));
        return mapperScannerConfigurer;
    }
}