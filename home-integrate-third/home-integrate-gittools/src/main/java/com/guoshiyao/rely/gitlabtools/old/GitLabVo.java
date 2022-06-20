

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.gitlabtools.old;

public class GitLabVo {
    private String id;
    private String name;
    private String chinaName;
    private String url;

    public GitLabVo(String id, String name, String chinaName, String url) {
        this.id = id;
        this.name = name;
        this.chinaName = chinaName;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChinaName() {
        return chinaName;
    }

    public void setChinaName(String chinaName) {
        this.chinaName = chinaName;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
