

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.apollo.api;

import cn.hutool.core.lang.tree.Tree;

public interface TreeInterface {
    boolean setValue(String key, Tree<Object> tree);

    String[] setNameSpace();
}
