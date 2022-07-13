

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.distributed;

/**
 *
 * @author 汪旭辉
 * @date 2021年12月6日
 * @readme
 */
public interface DistributedAb {
    /**
     * 获取分布式Key
     *
     * @author 汪旭辉
     * @date 2021年12月6日
     * @readme
     * @return
     */
    Long getDistributedKey();

    /**
     * 获取分布式Code
     *
     * @author 汪旭辉
     * @date 2021年12月6日
     * @readme
     * @return
     */
    String getDistributedCode();
}
