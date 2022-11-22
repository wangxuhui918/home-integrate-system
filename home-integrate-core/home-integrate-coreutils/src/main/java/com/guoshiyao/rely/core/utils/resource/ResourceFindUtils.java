
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.utils.resource;

import com.guoshiyao.rely.core.IResource;
import com.guoshiyao.rely.core.utils.conf.CoreConfUtils;

import java.net.URI;
import java.util.List;

public class ResourceFindUtils {
    private static List<IResource> creatconfigall = CoreConfUtils.sortByDbOrRuleApi(IResource.class);

    public static List<String> findClassesPath(String patternPath) {
        return creatconfigall.get(0).findClassesPath(patternPath);
    }

    public static List<URI> findUri(String patternPath) {
        return creatconfigall.get(0).find(patternPath);
    }
}
