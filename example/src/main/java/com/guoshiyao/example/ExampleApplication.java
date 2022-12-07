/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.example;

import com.guoshiyao.rely.annotaion.Starter;
import com.guoshiyao.rely.bean.Bean;
import com.guoshiyao.rely.core.configration.home.impl.bean.ConfigDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

@Starter(idkey = "home-example-ewell")
public class ExampleApplication {


    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
        Environment environment = Bean.getBean(Environment.class);
        System.out.println(environment.getProperty(ConfigDetails.APOLLO_BOOTSTRAP_ENABLED.getKey()).toString());
    }
}
