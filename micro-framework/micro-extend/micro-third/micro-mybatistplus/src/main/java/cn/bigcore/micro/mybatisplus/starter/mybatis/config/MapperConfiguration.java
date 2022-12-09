

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatisplus.starter.mybatis.config;

import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.core.configration.home.impl.bean.ConfigDetails;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;


public class MapperConfiguration {
    @Bean("mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_MYBATISPLUS_BASEPACKAGE.getKey()));
        return mapperScannerConfigurer;
    }
}