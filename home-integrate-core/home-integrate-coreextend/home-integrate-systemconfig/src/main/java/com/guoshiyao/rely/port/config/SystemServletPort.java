

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.port.config;

import cn.hutool.core.net.NetUtil;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author 汪旭辉
 * @date 2019年4月12日
 * @readme TODO
 */
public class SystemServletPort {

    @Bean
    @ConditionalOnMissingBean(WebServerFactoryCustomizer.class)
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> initPort() {
        WebServerFactoryCustomizer<ConfigurableWebServerFactory> portBean = new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                Long port = Line.properties.get("system.servlet.port").getLong();
                if (port == null) {
                    port = 8080L;
                }
                boolean isuse = NetUtil.isUsableLocalPort(port.intValue());
                if (!isuse) {
                    throw new ExceptionError("端口{}被占用, 系统退出", port.toString());
                } else {
                    factory.setPort(port.intValue());
                    LoggerBaseAb.info("已设置端口" + port);
                }
            }
        };
        return portBean;
    }
}