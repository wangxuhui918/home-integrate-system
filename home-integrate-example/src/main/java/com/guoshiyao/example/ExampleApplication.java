/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.example;

import com.guoshiyao.rely.annotaion.Starter;
import com.guoshiyao.rely.core.configration.project.impl.ProjectBuiltInImpl;
import org.springframework.boot.SpringApplication;

import java.util.logging.Level;

@Starter(idkey = "home-example-ewell")
public class ExampleApplication {
    public static void main(String[] args) {
//        ProjectBuiltInImpl
        System.setProperty("loglevel", Level.FINER.getName());
        SpringApplication.run(ExampleApplication.class, args);
    }
}
