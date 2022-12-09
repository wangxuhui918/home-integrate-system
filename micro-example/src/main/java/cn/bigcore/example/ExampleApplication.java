/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.example;

import cn.bigcore.micro.annotaion.Starter;
import cn.bigcore.micro.bean.Bean;
import cn.bigcore.micro.core.configration.home.impl.bean.ConfigDetails;
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
