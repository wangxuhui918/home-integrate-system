

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.mybatis.starter.mybatis.config;

import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.home.impl.bean.ConfigDetails;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;


public class MapperConfiguration {

    @Bean("mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_MYBATIS_BASEPACKAGE.getKey()));
        return mapperScannerConfigurer;
    }
}