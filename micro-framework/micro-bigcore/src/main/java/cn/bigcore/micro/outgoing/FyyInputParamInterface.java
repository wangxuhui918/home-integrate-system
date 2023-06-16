

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.outgoing;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.auth.FyyUserInterface;
import cn.bigcore.micro.thread.FyyThreadReUtils;
import cn.bigcore.micro.thread.bean.FyyKeyBase;
import cn.hutool.json.JSONUtil;

/**
 * 入参基类
 *
 * @param <D>
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
public abstract class FyyInputParamInterface<D> {
    //入参国际化编码
    private String i18n;
    //入参数据
    private D data;//inputparamab

    private FyyUserInterface userRe;

    @Deprecated
    private String pageSize;

    @Deprecated
    private String pageNum;

    /**
     * 重写该方法验证用户权限信息
     */
    public FyyAuthReturnType checkAuth() {
        return FyyAuthReturnType.AuthSuccess;
    }


    public FyyUserInterface getUserRe() {
        return userRe;
    }

    public void setUserRe(FyyUserInterface userRe) {
        if (FyyInitEnv.ProjectInformation.OPEN_THREAD_USER) {
            FyyThreadReUtils.putParam(FyyKeyBase.USERRE.getKeyName(), JSONUtil.parseObj(userRe));
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
        if (FyyInitEnv.ProjectInformation.OPEN_THREAD_I18N) {
            FyyThreadReUtils.putParam(FyyKeyBase.I18N.getKeyName(), i18n);
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
