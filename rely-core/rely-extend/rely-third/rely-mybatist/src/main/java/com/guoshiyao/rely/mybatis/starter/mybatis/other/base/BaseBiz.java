

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.mybatis.starter.mybatis.other.base;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

/**
 * @param <M>
 * @param <T>
 * @author 汪旭辉
 * @date 2018年7月6日
 * @readme TODO
 * @date 2018年11月14日, 删除所有多余方法
 */

public abstract class BaseBiz<M extends Mapper<T>, T> {
    @Autowired
    protected M mapper;

}
