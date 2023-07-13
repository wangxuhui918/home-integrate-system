

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.outgoing;

import cn.bigcore.micro.auth.FyyUserInterface;

/**
 * 入参基类
 *
 * @param <D>
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
public interface FyyInputParamInterface<D> {

    public String getI18n();

    public void setI18n(String i18n);

    public String getPageSize();

    public void setPageSize(String pageSize);

    public String getPageNum();

    public void setPageNum(String pageNum);

    public D getData();

    public void setData(D data);

    public FyyUserInterface getUserRe();

    public void setUserRe(FyyUserInterface userRe);

    public FyyAuthReturnType checkAuth();

}

