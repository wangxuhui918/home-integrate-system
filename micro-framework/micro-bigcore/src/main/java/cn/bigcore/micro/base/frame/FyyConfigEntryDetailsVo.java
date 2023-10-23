/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.base.frame;


import lombok.Data;

import java.io.Serializable;

/**
 * 配置条目详情对象
 */
@Data
public class FyyConfigEntryDetailsVo implements Serializable {

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

    public FyyConfigEntryDetailsVo() {
    }

    public FyyConfigEntryDetailsVo(String key, String beforesuff, String m, String value, String mark, String name_en) {
        this.key = key;
        this.beforesuff = beforesuff;
        this.m = m;
        this.value = value;
        this.mark = mark;
        this.name_en = name_en;
    }
}

