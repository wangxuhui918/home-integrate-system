

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶
 *
 */


package com.guoshiyao.rely.data;

import java.util.HashMap;

/**
 * 默认支持如下字段不填
 *
 * @author 郭诗瑶
 * @date 2019年2月26日
 * @readme TODO
 */
public class DataType {
    public final static String datatype1 = "mysql";
    public final static String datatype2 = "oracle";
    public final static String datatype3 = "sqlserver";
    public final static String datatype4 = "oracle12c";
    private static final HashMap<String, String> DATASOURCECLASS = new HashMap<>();
    private static final HashMap<String, String> VALIDATIONQUERY = new HashMap<>();

    static {
        DATASOURCECLASS.put(datatype1, "com.mysql.jdbc.Driver");
        DATASOURCECLASS.put(datatype2, "oracle.jdbc.driver.OracleDriver");
        DATASOURCECLASS.put(datatype3, "com.microsoft.jdbc.sqlserver.SQLServerDriver");
        DATASOURCECLASS.put(datatype4, "oracle.jdbc.driver.OracleDriver");

        VALIDATIONQUERY.put(datatype1, "select 'x'");
        VALIDATIONQUERY.put(datatype2, "select 'x' from dual");
        VALIDATIONQUERY.put(datatype3, "select 1");
        VALIDATIONQUERY.put(datatype4, "select 'x' from dual");

    }

    public static String getValiQuery(String name) {
        name = name.toLowerCase();
        if (name.contains(datatype1)) {
//            ThisSystemEnv.env.getDatasourceconf().getValidationQuery().setValue(VALIDATIONQUERY.get(datatype1));
            return VALIDATIONQUERY.get(datatype1);
        }
        if (name.contains(datatype2)) {
//            ThisSystemEnv.env.getDatasourceconf().getValidationQuery().setValue(VALIDATIONQUERY.get(datatype2));
            return VALIDATIONQUERY.get(datatype2);
        }
        if (name.contains(datatype3)) {
//            ThisSystemEnv.env.getDatasourceconf().getValidationQuery().setValue(VALIDATIONQUERY.get(datatype3));
            return VALIDATIONQUERY.get(datatype3);
        }
        if (name.contains(datatype4)) {
//            ThisSystemEnv.env.getDatasourceconf().getValidationQuery().setValue(VALIDATIONQUERY.get(datatype4));
            return VALIDATIONQUERY.get(datatype4);
        }
        return null;
    }

    public static String getClassName(String name) {
        name = name.toLowerCase();
        if (name.contains(datatype1)) {
//            ThisSystemEnv.env.getDatasourceconf().getName().setValue(DATASOURCECLASS.get(datatype1));
            return DATASOURCECLASS.get(datatype1);
        }
        if (name.contains(datatype2)) {
//            ThisSystemEnv.env.getDatasourceconf().getName().setValue(DATASOURCECLASS.get(datatype2));
            return DATASOURCECLASS.get(datatype2);
        }
        if (name.contains(datatype3)) {
//            ThisSystemEnv.env.getDatasourceconf().getName().setValue(DATASOURCECLASS.get(datatype3));
            return DATASOURCECLASS.get(datatype3);
        }
        if (name.contains(datatype4)) {
//            ThisSystemEnv.env.getDatasourceconf().getName().setValue(DATASOURCECLASS.get(datatype4));
            return DATASOURCECLASS.get(datatype4);
        }
        return null;
    }
}
