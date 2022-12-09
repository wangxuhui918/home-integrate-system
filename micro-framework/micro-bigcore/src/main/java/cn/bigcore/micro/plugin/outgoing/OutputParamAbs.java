

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.plugin.outgoing;

import java.io.Serializable;

/**
 * 出参基础类
 *
 * @param <T>
 * @param <D>
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
public abstract class OutputParamAbs<T, D> implements Serializable {
    //国际化编码
    private String i18n;
    //返回消息码内容
    private T codeBody;
    //出参数据
    private D data;

    public String getI18n() {
        return i18n;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    public T getCodeBody() {
        return codeBody;
    }

    public void setCodeBody(T codeBody) {
        this.codeBody = codeBody;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

}
