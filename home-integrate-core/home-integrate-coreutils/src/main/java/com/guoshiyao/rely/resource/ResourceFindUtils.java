
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 
 *
 */

package com.guoshiyao.rely.resource;

import com.guoshiyao.rely.coreconf.utils.HomeCoreConfUtils;

import java.net.URI;
import java.util.List;

public class ResourceFindUtils {
    private static List<ResourceAb> creatconfigall = HomeCoreConfUtils.sortByDbOrRuleApi(ResourceAb.class);

    public static List<String> findClassesPath(String patternPath) {
        return creatconfigall.get(0).findClassesPath(patternPath);
    }

    public static List<URI> findUri(String patternPath) {
        return creatconfigall.get(0).find(patternPath);
    }
}
