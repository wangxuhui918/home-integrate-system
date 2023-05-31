

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.plugin.distributed;

/**
 * 分布式预留服务
 *
 * @author 汪旭辉
 * @date 2021年12月6日
 * @readme
 */
public interface IDistributed {
    /**
     * 获取分布式Key
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月6日
     * @readme
     */
    Long getDistributedKey();

    /**
     * 获取分布式Code
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月6日
     * @readme
     */
    String getDistributedCode();
}
