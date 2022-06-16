
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.tools.system;

/**
 * 框架支持的数据库集合
 *
 * @author 汪旭辉
 * @date 2021年12月27日
 * @readme
 */
public enum DataType {
    MYSQL("com.mysql", "mysql数据库"), ORACLE("oracle.jdbc", "oracle数据库"), MICROSOFT("com.microsoft", "sqlserver数据库"),
    DB2("om.ibm.db2", "db2数据库"), POSTGRESQL("org.postgresql", "postgresql数据库");

    /**
     * 数据库前缀识别部分
     */
    private final String prefix;
    /**
     * 数据库的中文名称,目前仅用作打印
     */
    private final String name;

    DataType(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }

}
