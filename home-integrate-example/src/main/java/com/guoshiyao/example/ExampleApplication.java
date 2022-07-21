/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.example;

import com.guoshiyao.rely.annotaion.Starter;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.log.utils.LoggerUtil;
import org.springframework.boot.SpringApplication;

import java.util.logging.Level;

@Starter(idkey = "home-example-ewell")
public class ExampleApplication {
    public static void main(String[] args) {
        System.setProperty("loglevel", Level.FINER.getName());
        SpringApplication.run(ExampleApplication.class, args);
        LoggerUtil.info(ExampleApplication.class, "[]{}{}{}{}{}", 1, 2, 3, 4, 5);
        LoggerUtil.info(ExampleApplication.class, "[]{}{}{}{}{}", 1, 2, 3, 4, 5);
        LoggerUtil.info(ExampleApplication.class, "[]{}{}{}{}{}", 1, 2, 3, 4, 5);
        LoggerUtil.info(ExampleApplication.class, "[]{}{}{}{}{}", 1, 2, 3, 4, 5);
        LoggerUtil.info(ExampleApplication.class, "[]{}{}{}{}{}", 1, 2, 3, 4, 5);
        LoggerUtil.info(ExampleApplication.class, "[]{}{}{}{}{}", 1, 2, 3, 4, 5);
        LoggerUtil.info(ExampleApplication.class, "[]{}{}{}{}{}", 1, 2, 3, 4, 5);
    }
}
