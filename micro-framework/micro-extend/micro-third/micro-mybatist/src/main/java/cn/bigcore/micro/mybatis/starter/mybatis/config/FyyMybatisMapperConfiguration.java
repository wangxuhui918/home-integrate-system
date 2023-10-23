

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatis.starter.mybatis.config;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.base.frame.impl.FyyConfigEntryDetailsValues;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;


public class FyyMybatisMapperConfiguration {

    @Bean("mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_MYBATIS_BASEPACKAGE.getKey()));
        return mapperScannerConfigurer;
    }
}