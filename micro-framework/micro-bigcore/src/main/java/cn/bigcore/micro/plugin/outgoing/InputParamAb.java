

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.plugin.outgoing;

import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.plugin.auth.impl.UserImpl;
import cn.bigcore.micro.plugin.thread.bean.KeyBase;
import cn.hutool.json.JSONUtil;
import cn.bigcore.micro.plugin.thread.ThreadReUtils;

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

    private UserImpl userRe;

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


    public UserImpl getUserRe() {
        return userRe;
    }

    public void setUserRe(UserImpl userRe) {
        if (BaseEv.ProjectInformation.OPEN_THREAD_USER) {
            ThreadReUtils.putParam(KeyBase.USERRE.getKeyName(), JSONUtil.parseObj(userRe));
        }
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
        if (BaseEv.ProjectInformation.OPEN_THREAD_I18N) {
            ThreadReUtils.putParam(KeyBase.I18N.getKeyName(), i18n);
        }
        this.i18n = i18n;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

}
