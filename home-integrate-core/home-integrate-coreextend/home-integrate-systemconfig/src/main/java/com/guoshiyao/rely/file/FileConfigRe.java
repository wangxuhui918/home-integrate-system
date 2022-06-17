/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.file;

import com.guoshiyao.rely.line.Line;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

public class FileConfigRe {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(Line.properties.get("system.servlet.multipart.location").getValue());
        factory.setMaxFileSize(DataSize.of(Line.properties.get("system.servlet.multipart.max-file-size").getInteger(), DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(Line.properties.get("system.servlet.multipart.max-request-size").getInteger(), DataUnit.MEGABYTES));
        factory.setFileSizeThreshold(DataSize.of(Line.properties.get("system.servlet.multipart.file-size-threshold").getInteger(), DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

}

        
        
        
        
        
       
        
        