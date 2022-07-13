

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.mybatis.page;

import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.mybatis.page.inter.Data;
import com.guoshiyao.rely.mybatis.page.inter.Pager;
import org.apache.ibatis.session.RowBounds;

/**
 * Mybatis分页参数及查询结果封装. 注意所有序号从1开始
 *
 * @author ZhouChenglin
 * @email yczclcn@163.com
 * @url www.chenlintech.com
 * @date 2017年8月8日 上午11:41:07
 */
@lombok.Data
public class Page<T> {

    public T t;
    /**
     * 页编号 : 第几页
     */
    protected int pageNo = 1;
    /**
     * 页大小 : 每页的数量
     */
    protected int pageSize = 10;
    /**
     * 偏移量 : 第一条数据在表中的位置
     */
    protected int offset;
    /**
     * 限定数 : 每页的数量
     */
    protected int limit;
    /**
     * 不在限制类型
     */
    protected Object rows;
    /**
     * 总条数
     */
    protected int total;
    /**
     * 总页数
     */
    protected int totalPages;
    private RowBounds rowbounds;

    /**
     * @param pager
     * @param data
     * @author 汪旭辉
     * @date 2018年11月8日
     * @readme TODO
     */
    public Page(Pager pager, Data data) {
        try {
            if (pager != null) {
                this.pageNo = Integer.parseInt(pager.getPageNumber());
                this.pageSize = Integer.parseInt(pager.getPageSize());
            }
        } catch (Exception e) {
            LoggerBaseAb.warn("页面当前页/每页记录数传参转换异常,默认首页");
        }
        this.calcOffset();
        this.calcLimit();

        rowbounds = new RowBounds(this.getOffset(), this.getLimit());
        this.setTotal(data.selectCount());
        this.setRows(data.selectData(rowbounds));
    }

    /**
     * @param pager
     * @param data
     * @author 汪旭辉
     * @date 2018年11月8日
     * @readme TODO
     */
    public Page(String pageSize, String pageNumber, Data data) {
        try {
            if (StrUtil.isNotBlank(pageSize) && StrUtil.isNotBlank(pageNumber)) {
                this.pageNo = Integer.parseInt(pageNumber);
                this.pageSize = Integer.parseInt(pageSize);
            }
        } catch (Exception e) {
            LoggerBaseAb.warn("页面当前页/每页记录数传参转换异常,默认首页");
        }
        this.calcOffset();
        this.calcLimit();

        rowbounds = new RowBounds(this.getOffset(), this.getLimit());
        this.setTotal(data.selectCount());
        this.setRows(data.selectData(rowbounds));
    }

    public Page() {
        this.calcOffset();
        this.calcLimit();
    }

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.calcOffset();
        this.calcLimit();
        rowbounds = new RowBounds(this.getOffset(), this.getLimit());

    }

    /**
     * 计算偏移量
     */
    private void calcOffset() {
        this.offset = ((pageNo - 1) * pageSize);
    }

    /**
     * 计算限定数
     */
    private void calcLimit() {
        this.limit = pageSize;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public int getFirst() {
        return ((pageNo - 1) * pageSize) + 1;
    }

    /**
     * 设置总记录数.
     */
    public void setTotal(final int total) {
        this.total = total;
        this.totalPages = this.getTotalPages();
    }

    /**
     * 根据pageSize与total计算总页数, 默认值为-1.
     */
    public int getTotalPages() {
        if (total < 0) {
            return -1;
        }
        int pages = total / pageSize;
        return total % pageSize > 0 ? ++pages : pages;
    }

}