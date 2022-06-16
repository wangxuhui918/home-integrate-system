

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.exception;

import com.guoshiyao.rely.exception.code.CodeRe;

public class ExceptionRe extends ExceptionAb {
    private static final long serialVersionUID = 42L;

    public ExceptionRe(CodeRe msgInfo) {
        super(msgInfo);
    }

    public ExceptionRe(String code) {
        super(new CodeRe(code));
    }
}
