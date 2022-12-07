

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.log.plugin;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import cn.hutool.core.io.resource.ResourceUtil;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author 汪旭辉
 * @date 2021年12月6日
 * @readme
 */
public class LogBackConfigLoader {
    /**
     * 加载自定义日志配置
     *
     * @param externalConfigFileLocation
     * @throws IOException
     * @throws JoranException
     * @author 汪旭辉
     * @date 2021年12月6日
     * @readme
     */
    public static void load(String externalConfigFileLocation) throws IOException, JoranException {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        configurator.doConfigure(ResourceUtil.getStream(externalConfigFileLocation));
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
    }
}