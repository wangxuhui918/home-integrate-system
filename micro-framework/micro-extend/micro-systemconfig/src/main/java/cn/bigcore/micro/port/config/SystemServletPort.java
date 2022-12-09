

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.port.config;

import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.core.configration.home.impl.bean.ConfigDetails;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

/**
 * @author 汪旭辉
 * @date 2019年4月12日
 * @readme TODO
 */
public class SystemServletPort {

//    @Bean
//    @ConditionalOnMissingBean(WebServerFactoryCustomizer.class)
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> initPort() {
//        WebServerFactoryCustomizer<ConfigurableWebServerFactory> portBean = new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                Long port = BaseEv.SettingInformation.setting.getLong(ConfigDetails.SYSTEM_SERVLET_PORT.getKey());
//                if (port == null) {
//                    port = 8080L;
//                }
//                boolean isuse = NetUtil.isUsableLocalPort(port.intValue());
//                if (!isuse) {
//                    throw new ExceptionError("端口{}被占用, 系统退出", port.toString());
//                } else {
//                    factory.setPort(port.intValue());
//                    ILoggerBaseUtils.debug("已设置端口" + port);
//                }
//            }
//        };
//        return portBean;
//    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        if (BaseEv.SettingInformation.setting.getBool(ConfigDetails.SERVLET_MULTIPART_ENABLE.getKey())) {
            factory.setLocation(BaseEv.SettingInformation.setting.get(ConfigDetails.SERVLET_MULTIPART_LOCATION.getKey()));
            factory.setMaxFileSize(DataSize.of(BaseEv.SettingInformation.setting.getInt(ConfigDetails.SERVLET_MULTIPART_MAX_FILE_SIZE.getKey()), DataUnit.MEGABYTES));
            factory.setMaxRequestSize(DataSize.of(BaseEv.SettingInformation.setting.getInt(ConfigDetails.SERVLET_MULTIPART_MAX_REQUEST_SIZE.getKey()), DataUnit.MEGABYTES));
            factory.setFileSizeThreshold(DataSize.of(BaseEv.SettingInformation.setting.getInt(ConfigDetails.SERVLET_MULTIPART_FILE_SIZE_THRESHOLD.getKey()), DataUnit.MEGABYTES));
        }
        return factory.createMultipartConfig();
    }
}