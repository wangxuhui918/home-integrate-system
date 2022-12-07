

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.plugin.distributed;

/**
 *
 * @author 汪旭辉
 * @date 2021年12月6日
 * @readme
 */
public interface IDistributed {
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
