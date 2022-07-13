

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶
 *
 */

package com.guoshiyao.rely.distributed;

/**
 *
 * @author 郭诗瑶
 * @date 2021年12月6日
 * @readme
 */
public interface DistributedAb {
    /**
     * 获取分布式Key
     *
     * @author 郭诗瑶
     * @date 2021年12月6日
     * @readme
     * @return
     */
    Long getDistributedKey();

    /**
     * 获取分布式Code
     *
     * @author 郭诗瑶
     * @date 2021年12月6日
     * @readme
     * @return
     */
    String getDistributedCode();
}
