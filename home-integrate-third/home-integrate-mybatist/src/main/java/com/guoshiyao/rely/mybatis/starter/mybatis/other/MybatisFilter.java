

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */


package com.guoshiyao.rely.mybatis.starter.mybatis.other;

public interface MybatisFilter {
    /**
     * 使用mybatis-plus插件集成.当前方法已经废除
     */
    @Deprecated
    String PlusFilter = "0";
    /**
     * 使用tk-mybatis集成
     */
    String TkFilter = "1";
}