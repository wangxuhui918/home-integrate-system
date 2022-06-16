
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.resource;

import com.guoshiyao.rely.coreconf.utils.HomeCoreConfUtils;

import java.net.URL;
import java.util.List;

public class ResourceFindUtils {
    private static List<ResourceAb> creatconfigall = HomeCoreConfUtils.sortByDbOrRuleApi(ResourceAb.class);

    public static List<URL> find(String patternPath) {
        return creatconfigall.get(0).find(patternPath);
    }
}
