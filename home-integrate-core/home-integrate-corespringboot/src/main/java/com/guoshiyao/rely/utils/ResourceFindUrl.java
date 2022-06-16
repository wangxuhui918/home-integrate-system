
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.utils;

import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.resource.ResourceAb;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RuleAnnotation
public class ResourceFindUrl implements ResourceAb {

    @Override
    public List<URL> find(String patternPath) {
        List<URL> listurl = new ArrayList<>();
        try {
            ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
            Resource[] source = resourceLoader.getResources(patternPath);
            for (Resource resource : source) {
                listurl.add(resource.getURL());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listurl;

    }
}
