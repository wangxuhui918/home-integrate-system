/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶
 *
 */

package com.guoshiyao.rely.coreconf.vo;


import java.io.Serializable;


public class ModelConfigInfoVo implements Serializable {


    private Integer sort_id;


    private String name_en;


    private String name_cn;
    private String file_path;


    private String model_context;

    private String only_local;
    private String use_uk;
    private String need_format_zone;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort_id() {
        return sort_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public void setSort_id(Integer sort_id) {
        this.sort_id = sort_id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_cn() {
        return name_cn;
    }

    public void setName_cn(String name_cn) {
        this.name_cn = name_cn;
    }

    public String getModel_context() {
        return model_context;
    }

    public void setModel_context(String model_context) {
        this.model_context = model_context;
    }

    public String getOnly_local() {
        return only_local;
    }

    public void setOnly_local(String only_local) {
        this.only_local = only_local;
    }

    public String getUse_uk() {
        return use_uk;
    }

    public void setUse_uk(String use_uk) {
        this.use_uk = use_uk;
    }

    public String getNeed_format_zone() {
        return need_format_zone;
    }

    public void setNeed_format_zone(String need_format_zone) {
        this.need_format_zone = need_format_zone;
    }
}

