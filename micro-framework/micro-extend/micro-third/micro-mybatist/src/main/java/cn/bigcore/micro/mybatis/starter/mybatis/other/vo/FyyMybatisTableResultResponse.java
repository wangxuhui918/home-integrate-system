

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatis.starter.mybatis.other.vo;

import java.io.Serializable;
import java.util.List;


public class FyyMybatisTableResultResponse<T> implements Serializable {
    int total;
    List<T> rows;

    public FyyMybatisTableResultResponse(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public FyyMybatisTableResultResponse() {
    }

    FyyMybatisTableResultResponse<T> total(int total) {
        this.total = total;
        return this;
    }

    FyyMybatisTableResultResponse<T> total(List<T> rows) {
        this.rows = rows;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
