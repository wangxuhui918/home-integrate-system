
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.utils.resource;

import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.plugin.exception.re.ex.ExceptionError;

import java.net.URI;
import java.util.List;

public class ResourceFindUtils {

    public static List<String> findClassesPath(String patternPath) {
        if (BaseEv.SettingInformation.resourcetool == null) {
            throw new ExceptionError(ResourceFindUtils.class.getName() + "缺失核心实现类!");
        }
        return BaseEv.SettingInformation.resourcetool.findClassesPath(patternPath);
    }

    public static List<URI> findUri(String patternPath) {
        if (BaseEv.SettingInformation.resourcetool == null) {
            throw new ExceptionError(ResourceFindUtils.class.getName() + "缺失核心实现类!");
        }
        return BaseEv.SettingInformation.resourcetool.find(patternPath);
    }
}
