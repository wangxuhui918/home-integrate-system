

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.systemconfig;

import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import org.springframework.boot.CommandLineRunner;

//@Component
public class StartPingService implements CommandLineRunner {
    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        java.net.URI uri = java.net.URI.create("http://127.0.0.1:" + Line.setting.get("system.servlet.port") + "/swagger-ui/index.html");
//        if (java.awt.Desktop.isDesktopSupported() && Line.isNotJar) {
//            try {
//                // 获取当前系统桌面扩展
//                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
//                // 判断系统桌面是否支持要执行的功能
//                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
//                    // 获取系统默认浏览器打开链接
//                    dp.browse(uri);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
        LoggerBaseAb.info("swagger 地址: " + "http://127.0.0.1:" + Line.setting.get("system.servlet.port") + "/swagger-ui/index.html");

    }
}