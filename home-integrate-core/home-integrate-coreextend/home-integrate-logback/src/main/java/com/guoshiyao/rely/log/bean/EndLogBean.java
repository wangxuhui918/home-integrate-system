

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.log.bean;

import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.log.base.LoggerBaseUtils;
import com.guoshiyao.rely.log.utils.LoggerUtil;
import org.springframework.boot.CommandLineRunner;

public class EndLogBean implements CommandLineRunner {

    @Override
    public void run(String... args) {
        LoggerBaseAb.info("开始翻转日志管理器!!");
        LoggerBaseUtils.custLogger = new LoggerUtil();
        LoggerBaseAb.info("翻转日志管理器成功!!");
    }
}

