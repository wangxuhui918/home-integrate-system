

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.mybatisplus.starter.mybatis.other;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @param <M>
 * @param <T>
 * @author 汪旭辉
 * @date 2018年7月6日
 * @readme TODO
 * @date 2018年11月14日, 删除所有多余方法
 */

public abstract class BaseBiz<M extends BaseMapper<T>, T> {
    @Autowired
    protected M mapper;

}
