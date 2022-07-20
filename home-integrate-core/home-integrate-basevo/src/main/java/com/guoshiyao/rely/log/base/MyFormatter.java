/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */
package com.guoshiyao.rely.log.base;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.base.BaseEv;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MyFormatter extends Formatter {


    @Override
    public String format(LogRecord arg0) {
//2022-07-20 16:18:42,831 INFO  [home-example-ewell] [main] [o.s.c.annotation.AutoProxyRegistrar:83]
        StringBuilder builder = new StringBuilder();
        builder.append(DateUtil.format(new DateTime(), "yyyy-MM-dd HH:mm:ss.SSS"));
        builder.append(" ");
        builder.append(arg0.getLevel()).append(" ");
        builder.append(StrUtil.format("[{}]  ", BaseEv.HOME_NAME));
        builder.append(StrUtil.format("[{}] ", arg0.getSourceMethodName()));
        builder.append(StrUtil.format("[{}] ", arg0.getSourceClassName()));
        builder.append(arg0.getMessage());
        builder.append("\r\n");
        return builder.toString();
    }

    @Override
    public String getHead(Handler h) {
        return "";
    }

    @Override

    public String getTail(Handler h) {
        return "";
    }

}
