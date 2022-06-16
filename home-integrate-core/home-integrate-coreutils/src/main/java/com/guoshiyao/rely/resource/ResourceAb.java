
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.resource;

import java.net.URL;
import java.util.List;

public interface ResourceAb {

    List<URL> find(String patternPath);
}
