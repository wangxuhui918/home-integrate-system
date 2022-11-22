

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.log.bean;

import com.guoshiyao.rely.log.utils.LoggerUtil;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;
import org.springframework.boot.CommandLineRunner;

public class EndLogBean implements CommandLineRunner {

    @Override
    public void run(String... args) {
        ILoggerBaseUtils.info("开始翻转日志管理器!!");
        ILoggerBaseUtils.custLogger = new LoggerUtil();
        ILoggerBaseUtils.info("翻转日志管理器成功!!");
    }
}

