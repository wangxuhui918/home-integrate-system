

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.outgoing;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.FyyProperties;
import cn.bigcore.micro.auth.FyyUserInterface;
import cn.bigcore.micro.thread.FyyThreadReUtils;
import cn.bigcore.micro.thread.bean.FyyKeyBase;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Method;

/**
 * 入参基类
 *
 * @param <D>
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
public class FyyInputParamParent<D extends JSONObject> implements FyyInputParamInterface<D> {
    //入参国际化编码
    private String i18n;

    private String pageSize;

    private String pageNum;

    //入参数据
    @ApiModelProperty(hidden = true)
    private D data;//用于存放入参的json对象

    @ApiModelProperty(hidden = true)
    private FyyUserInterface userRe;//用于存放内部使用的user对象

    /**
     * 重写该方法验证用户权限信息
     */
    @ApiModelProperty(hidden = true)
    public FyyAuthReturnType checkAuth(Method method) {
        return FyyAuthReturnType.AuthSuccess;
    }

    @ApiModelProperty(hidden = true)
    public FyyUserInterface getUserRe() {
        return userRe;
    }

    @ApiModelProperty(hidden = true)
    public void setUserRe(FyyUserInterface userRe) {
        if (FyyProperties.setting.getBool("fyy.project.core.open_thread_user")) {
            FyyThreadReUtils.putParam(FyyKeyBase.USERRE.getKeyName(), JSONUtil.parseObj(userRe));
        }
        this.userRe = userRe;
    }

    @ApiModelProperty(hidden = true)
    public void setI18n(String i18n) {
        if (FyyProperties.setting.getBool("fyy.project.core.open_thread_i18n")) {
            FyyThreadReUtils.putParam(FyyKeyBase.I18N.getKeyName(), i18n);
        }
        this.i18n = i18n;
    }

    @ApiModelProperty(hidden = true)
    public D getData() {
        return data;
    }

    @ApiModelProperty(hidden = true)
    public void setData(D data) {
        this.data = data;
    }

    @Override
    public String getI18n() {
        return i18n;
    }

    @Override
    public String getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(String pageSize) {
        try {
            Integer.parseInt(pageSize);
            this.pageSize = pageSize;
        } catch (Exception e) {
            this.pageSize = "0";
        }
        //
        if (FyyProperties.setting.getBool("fyy.project.core.open_thread_page")) {
            FyyThreadReUtils.putParam(FyyKeyBase.PAGE_SIZE.getKeyName(), this.pageSize);
        }
    }

    @Override
    public String getPageNum() {
        return pageNum;
    }

    @Override
    public void setPageNum(String pageNum) {
        try {
            Integer.parseInt(pageNum);
            this.pageNum = pageNum;
        } catch (Exception e) {
            this.pageNum = "0";
        }
        //
        if (FyyProperties.setting.getBool("fyy.project.core.open_thread_page")) {
            FyyThreadReUtils.putParam(FyyKeyBase.PAGE_NUM.getKeyName(), this.pageNum);
        }
    }

}
