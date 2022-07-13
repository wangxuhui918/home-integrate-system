

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.mybatis.page.inter;

import org.apache.ibatis.session.RowBounds;

public interface Data<T> {
    int selectCount();

    Object selectData(RowBounds rowBounds);
}