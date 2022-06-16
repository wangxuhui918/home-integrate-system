

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.apollo.api;

import cn.hutool.core.lang.tree.Tree;

public interface TreeInterface {
    boolean setValue(String key, Tree<Object> tree);

    String[] setNameSpace();
}
