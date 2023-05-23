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
     * 文件名
     */
    private String configFileName;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件内容
     */
    private String context;
    /**
     * 是否为本地文件
     */
    private boolean only_local;
    /**
     * 是否使用uk文件替换
     */
    private boolean use_uk;
    /**
     * 是否格式化时区
     */
    private boolean need_format_zone;


}

