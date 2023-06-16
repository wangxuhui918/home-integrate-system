/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config.config.bean;


import lombok.Data;

import java.io.Serializable;

/**
 * 配置条目对象
 */
@Data
public class FyyConfigEntryVo implements Serializable {
    /**
     * 枚举名
     */
    private String name;
    /**
     * 配置名
     */
    private String configName;
    /**
     * 文件内容
     */
    private String context;

}

