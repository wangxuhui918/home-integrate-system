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
public class ConfigDetailsVo implements Serializable {

    /**
     * 参数ID
     */
    private Integer id;
    /**
     * 参数名
     */
    private String key;
    /**
     * 参数前缀
     */
    private String beforesuff;
    /**
     * 切割符号,比如=
     */
    private String m;
    /**
     * 参数值
     */
    private String value;
    /**
     * 参数备注
     */
    private String mark;
    /**
     * 当前参数文件名
     */
    private String name_en;
}

