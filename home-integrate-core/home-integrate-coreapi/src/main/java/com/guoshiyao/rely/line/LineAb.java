

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 
 *
 */


package com.guoshiyao.rely.line;

import com.guoshiyao.rely.coreab.ab.base.BaseConfigAb;

/**
 * 配置写入基类
 *
 * @param <T>
 * @author 郭诗瑶
 * @date 2021年9月27日
 * @readme
 */
public interface LineAb<T extends Line> extends BaseConfigAb {

    void before();

    void start();

    void after();

}
