

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package com.guoshiyao.rely.plugin.outgoing;

import com.guoshiyao.rely.plugin.exception.code.ICode;

/**
 * @param <T> 消息码实体类
 * @param <D> 出参数据
 * @param <I>
 * @author 汪旭辉
 * @date 2021年9月28日
 * @readme
 */
public interface IOutG<I, D> {

    /**
     * @param inputparamer 方法入参对象
     * @param code         消息码对象
     * @param data         返回数据
     * @param exception    异常提示
     * @return
     */
    OutputParamAbs<ICode, D> go(InputParamAb<I> inputparamer, ICode code, D data, Exception exception);

}
