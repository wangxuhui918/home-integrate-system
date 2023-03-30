/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.core.configration.home.bean;


import java.io.Serializable;

/**
 * properties文件描述类
 */
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


    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public boolean isOnly_local() {
        return only_local;
    }

    public void setOnly_local(boolean only_local) {
        this.only_local = only_local;
    }

    public boolean isUse_uk() {
        return use_uk;
    }

    public void setUse_uk(boolean use_uk) {
        this.use_uk = use_uk;
    }

    public boolean isNeed_format_zone() {
        return need_format_zone;
    }

    public void setNeed_format_zone(boolean need_format_zone) {
        this.need_format_zone = need_format_zone;
    }
}

