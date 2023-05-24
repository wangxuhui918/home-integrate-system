
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.core;

import java.net.URI;
import java.util.List;

/**
 * 资源查找器资源管理接口
 */
public interface IResource {


    abstract List<URI> find(String patternPath);

    abstract List<String> findClassesPath(String patternPath);
}
