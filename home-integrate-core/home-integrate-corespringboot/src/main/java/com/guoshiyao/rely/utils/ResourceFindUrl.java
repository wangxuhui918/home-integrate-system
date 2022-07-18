
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.utils;

import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.resource.ResourceAb;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RuleAnnotation
public class ResourceFindUrl implements ResourceAb {
    @Override
    public List<String> findClassesPath(String patternPath) {
        List<String> listpath = new ArrayList<>();
        try {
            ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
            Resource[] source = resourceLoader.getResources("classpath*:" + patternPath);
            for (Resource resource : source) {
                String path = resource.getURL().toString();
                if (Line.isClassModel) {
                    path = StrUtil.subAfter(path, "classes" + BaseEv.FILE_SEPARATOR, true);
                } else {
                    path = StrUtil.subAfter(path, "classes!" + BaseEv.FILE_SEPARATOR, true);
                }
                listpath.add(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listpath;
    }


    @Override
    public List<URI> find(String patternPath) {
        List<URI> listurl = new ArrayList<>();
        try {
            ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
            Resource[] source = resourceLoader.getResources("classpath*:" + patternPath);
            for (Resource resource : source) {
                listurl.add(resource.getURI());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listurl;
    }

}
