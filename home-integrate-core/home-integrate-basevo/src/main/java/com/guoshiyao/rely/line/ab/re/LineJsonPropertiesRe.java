

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.line.ab.re;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.line.ab.LinePropertiesBaseAb;

import java.util.List;

/**
 * 值转换为其他常见类型格式,可以扩展
 *
 * @author 汪旭辉
 * @date 2021年9月28日
 * @readme
 */
public class LineJsonPropertiesRe extends LinePropertiesBaseAb<JSON> {

    public LineJsonPropertiesRe() {
        super(JSONUtil.parse("{}"));
        // mark 自动生成的构造函数存根
    }

    public LineJsonPropertiesRe(JSON value) {
        super(value);
        // mark 自动生成的构造函数存根
    }

    public <T> List<T> toList(Class<T> clss) {
        List<T> ts = JSONUtil.toList(JSONUtil.parseArray(getValue()), clss);
        return ts;
    }

    public <T> T toBean(Class<T> clss) {
        T ts = JSONUtil.toBean(JSONUtil.toJsonStr(getValue()), clss);
        return ts;
    }

    public LinePropertiesAb toLine() {
        String key = "key";
        LinePropertiesAb pro = new LinePropertiesAb(key, getValue().toString());
        return pro;
    }

}
