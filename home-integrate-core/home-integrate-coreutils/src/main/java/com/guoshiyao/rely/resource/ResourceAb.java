
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.resource;

import java.net.URI;
import java.util.List;

public interface ResourceAb {


    abstract List<URI> find(String patternPath);

    abstract List<String> findClassesPath(String patternPath);
}
