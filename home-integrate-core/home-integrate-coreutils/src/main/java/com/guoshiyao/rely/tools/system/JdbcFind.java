

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 
 *
 */

package com.guoshiyao.rely.tools.system;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.meta.JdbcType;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.log.base.LoggerBaseAb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * jdbc相关的一些支持属性
 *
 * @author 汪旭辉
 * @date 2021年12月27日
 * @readme
 */
public class JdbcFind {
    /**
     * 这里存放数据库jdbctype与java 对象属性之间的对照关系,目前仅支持如下类型
     */
    public static final HashMap<Integer, Class> JAVATYPE = new HashMap<>();

    static {
        JAVATYPE.put(JdbcType.INTEGER.typeCode, Integer.class);
        JAVATYPE.put(JdbcType.BIGINT.typeCode, Long.class);
        JAVATYPE.put(JdbcType.FLOAT.typeCode, BigDecimal.class);
        JAVATYPE.put(JdbcType.DOUBLE.typeCode, BigDecimal.class);
        JAVATYPE.put(JdbcType.NUMERIC.typeCode, BigDecimal.class);
        JAVATYPE.put(JdbcType.DECIMAL.typeCode, BigDecimal.class);
        JAVATYPE.put(JdbcType.CHAR.typeCode, String.class);
        JAVATYPE.put(JdbcType.VARCHAR.typeCode, String.class);
        JAVATYPE.put(JdbcType.LONGVARCHAR.typeCode, String.class);
        JAVATYPE.put(JdbcType.DATE.typeCode, Date.class);
        JAVATYPE.put(JdbcType.TIME.typeCode, Date.class);
        JAVATYPE.put(JdbcType.TIMESTAMP.typeCode, Date.class);
        JAVATYPE.put(JdbcType.NULL.typeCode, null);
        JAVATYPE.put(JdbcType.BLOB.typeCode, String.class);
        JAVATYPE.put(JdbcType.CLOB.typeCode, String.class);
        JAVATYPE.put(JdbcType.BOOLEAN.typeCode, Boolean.class);
        JAVATYPE.put(JdbcType.NVARCHAR.typeCode, String.class);
        JAVATYPE.put(JdbcType.NCHAR.typeCode, String.class);
        JAVATYPE.put(JdbcType.NCLOB.typeCode, String.class);
    }

    /**
     * 获取工程 jdbc 驱动列表
     *
     * @return
     */
    public static Set<Class<?>> getProjectJdbc() {
        String[] types = new String[]{"oracle", "mysql", "sqlserver", "h2", "sqllite"};
        LoggerBaseAb.info("寻找可用 JDBC 驱动...........");
        Set<Class<?>> classes = new HashSet<>();
        for (DataType datatype : DataType.values()) {
            Set<Class<?>> classes0 = ClassUtil.scanPackageBySuper(datatype.getPrefix(), java.sql.Driver.class);
            classes.addAll(classes0);
        }
        if (classes.size() > 0) {
            LoggerBaseAb.info("寻找到可用驱动,请确认是否匹配.........." + JSONUtil.toJsonStr(classes));
            LoggerBaseAb.info(JSONUtil.toJsonStr(classes));
        } else {
            LoggerBaseAb.warn("未查询到 JDBC 驱动包..........");
        }
        return classes;
    }

    /**
     * 根据数据库ur判断数据库的类型
     *
     * @param driverclass 数据库url
     * @return com.guoshiyao.rely.tools.system.DataType 系统目前支持的数据库枚举类型,如果查询不到则返回为空
     * @author 汪旭辉
     * @date 2021年12月27日
     * @readme
     */
    public static DataType getDataTypeForUrl(String driverclass) {
        for (DataType datatype : DataType.values()) {
            if (StrUtil.contains(driverclass, datatype.getPrefix())) {
                return datatype;
            }
        }
        return null;
    }

    /**
     * 根据jdbctype返回javatype相应的对照关系<br>
     * 如果返回为空,则表示当前对照关系在系统中不存在
     *
     * @param jdbctype
     * @return
     * @author 汪旭辉
     * @date 2021年12月27日
     * @readme
     */
    public static Class getRelationJavaType(JdbcType jdbctype) {
        if (JAVATYPE.containsKey(jdbctype.typeCode)) {
            return JAVATYPE.get(jdbctype.typeCode);
        }
        return null;
    }
}
