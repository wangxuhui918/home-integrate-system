/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 
 *
 */

package com.guoshiyao.rely.coreconf.vo;

/**
 * @author 郭诗瑶
 * @date 2022/5/25
 * @readme
 */
public class ReadMeVo {
    private Integer id;
    private String path;
    private String type;
    private String readme;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }
}
