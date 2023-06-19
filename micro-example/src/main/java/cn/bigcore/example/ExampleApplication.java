/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.example;


import cn.bigcore.micro.starter.FyyStarter;
import org.springframework.boot.SpringApplication;

@FyyStarter(idkey = "home-example-ewell1", configEnv = {"kaifa", "ceshi"})
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
