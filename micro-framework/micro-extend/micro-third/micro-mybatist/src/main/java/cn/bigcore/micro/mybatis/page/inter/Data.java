

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatis.page.inter;

import org.apache.ibatis.session.RowBounds;
@Deprecated
public interface Data<T> {
    int selectCount();

    Object selectData(RowBounds rowBounds);
}