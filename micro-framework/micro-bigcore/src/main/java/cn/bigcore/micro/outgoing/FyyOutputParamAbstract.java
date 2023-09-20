

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.outgoing;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.thread.FyyThreadReUtils;
import cn.bigcore.micro.thread.bean.FyyKeyBase;
import cn.bigcore.micro.utils.FyyConfigFrameUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 出参基础类
 *
 * @param <T>
 * @param <D>
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
public abstract class FyyOutputParamAbstract<T, D> implements Serializable {
    //国际化编码
    private String i18n;
    //返回消息码内容
    private T codeBody;
    //出参数据
    private D data;
    //每页记录数
    private Integer pageSize;
    //当前页数
    private Integer pageNum;
    //分页总数
    private Long total;

    public Integer getPageSize() {
        if (FyyInitEnv.ProjectInformation.OPEN_THREAD_USER) {
            return Integer.parseInt(FyyThreadReUtils.getStrParamByPath(FyyKeyBase.PAGE_SIZE.getKeyName()));
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        if (FyyInitEnv.ProjectInformation.OPEN_THREAD_USER) {
            return Integer.parseInt(FyyThreadReUtils.getStrParamByPath(FyyKeyBase.PAGE_NUM.getKeyName()));
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotal() {
        if (FyyInitEnv.ProjectInformation.OPEN_THREAD_USER) {
            try {
                List<FyyPageTotalInterface> plugins = FyyConfigFrameUtils.getPlugins(FyyPageTotalInterface.class);
                for (int i = 0; i < plugins.size(); i++) {
                    long s = plugins.get(i).getTotal(getPageNum(), getPageSize());
                    plugins.get(i).remove();
                    return s;
                }
            } catch (Exception e) {
                return 0L;
            } finally {
            }
        }
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

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
