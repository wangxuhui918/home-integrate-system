

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.mybatis.starter.mybatis.other;

public interface FyyMybatisFilter {
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