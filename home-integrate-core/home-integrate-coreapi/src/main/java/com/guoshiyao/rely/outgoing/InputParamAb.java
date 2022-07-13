

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */


package com.guoshiyao.rely.outgoing;

import com.guoshiyao.rely.auth.UserRe;

/**
 * 入参基类
 *
 * @param <D>
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
public abstract class InputParamAb<D> {
    //入参国际化编码
    private String i18n;
    //入参数据
    private D data;//inputparamab

    private UserRe userRe;

    @Deprecated
    private String pageSize;

    @Deprecated
    private String pageNum;

    /**
     * 重写该方法验证用户权限信息
     */
    public AuthReturnType checkAuth() {
 
        return AuthReturnType.AuthSuccess;
    }


    public UserRe getUserRe() {
        return userRe;
    }

    public void setUserRe(UserRe userRe) {
        this.userRe = userRe;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getI18n() {
        return i18n;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

}
