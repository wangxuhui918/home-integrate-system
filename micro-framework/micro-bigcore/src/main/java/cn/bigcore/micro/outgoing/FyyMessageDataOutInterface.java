

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.outgoing;

import cn.bigcore.micro.exception.FyyCodeInterface;

/**
 * @param <T> 消息码实体类
 * @param <D> 出参数据
 * @param <I>
 * @author 汪旭辉
 * @date 2021年9月28日
 * @readme
 */
public interface FyyMessageDataOutInterface<I, D> {

    /**
     * @param inputparamer 方法入参对象
     * @param code         消息码对象
     * @param data         返回数据
     * @param exception    异常提示
     * @return
     */
    FyyOutputParamAbstract<FyyCodeInterface, D> go(FyyInputParamInterface<I> inputparamer, FyyCodeInterface code, D data, Exception exception);

}
