

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.mybatis.starter.mybatis.helper;

import com.github.pagehelper.dialect.AbstractRowBoundsDialect;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.session.RowBounds;

/**
 * oracle 基于 RowBounds 的分页
 *
 * @author liuzh
 */
public class HomeOracleRowBoundsDialect extends AbstractRowBoundsDialect {

    @Override
    public String getPageSql(String sql, RowBounds rowBounds, CacheKey pageKey) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
        sqlBuilder.append(sql);
        sqlBuilder.append(" OFFSET ");
        sqlBuilder.append(rowBounds.getOffset());
        sqlBuilder.append(" ROWS ");
        pageKey.update(rowBounds.getOffset());
        sqlBuilder.append(" FETCH NEXT ");
        sqlBuilder.append(rowBounds.getLimit());
        sqlBuilder.append(" ROWS ONLY");
        pageKey.update(rowBounds.getLimit());

        return sqlBuilder.toString();
    }

}
