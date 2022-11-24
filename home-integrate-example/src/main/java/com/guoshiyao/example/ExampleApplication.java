/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.example;

import com.guoshiyao.rely.annotaion.Starter;
import org.springframework.boot.SpringApplication;

import java.util.logging.Level;

@Starter(idkey = "home-example-ewell")
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
