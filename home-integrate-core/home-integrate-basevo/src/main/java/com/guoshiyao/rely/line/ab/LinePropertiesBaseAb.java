

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.line.ab;

/**
 * 定义基础value类型<F>
 *
 * @author 汪旭辉
 * @date 2021年12月8日
 * @readme
 * @param <F>
 */
public abstract class LinePropertiesBaseAb<F> {
    private F value;

    public LinePropertiesBaseAb(F value) {
        super();
        this.value = value;
    }

    public LinePropertiesBaseAb() {
        super();
    }

    public F getValue() {
        return value;
    }

}
