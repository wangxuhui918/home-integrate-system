/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.core.configration.home.bean;


import lombok.Data;

import java.io.Serializable;

/**
 * properties文件描述类
 */
@Data
public class ConfigMainVo implements Serializable {
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

