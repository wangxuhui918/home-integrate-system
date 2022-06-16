

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.environment;


import com.guoshiyao.rely.line.Line;

public enum ENV {
    LOCAL("LOCAL", 20001), DEV("DEV", 30001), UAT("UAT", 40001), PRO("PRO", 50001);

    private String name;
    private Integer value;

    ENV(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static boolean having(String name) {
        for (ENV envf : ENV.values()) {
            if (envf.getName().equalsIgnoreCase(name)) {//不区分大小写
                return true;
            }
        }
        return false;
    }

    public static ENV getEnv(String name) {
        for (ENV envf : ENV.values()) {
            if (envf.getName().equalsIgnoreCase(name)) {
                return envf;
            }
        }
        return null;
    }


    public static ENV getEnv(Integer value) {
        for (ENV envf : ENV.values()) {
            if (envf.getValue() - value == 0) {
                return envf;
            }
        }
        return null;
    }

    public String getLocalName() {
        if (this.getValue() == ENV.LOCAL.getValue()) {
            return Line.UK;
        }
        return name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }


}
