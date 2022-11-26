

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.config.exception;

import com.guoshiyao.rely.plugin.exception.ExceptionAbs;

public class ExceptionRe extends ExceptionAbs {
    private static final long serialVersionUID = 42L;

    public ExceptionRe(CodeRe msgInfo) {
        super(msgInfo);
    }

    public ExceptionRe(String code) {
        super(new CodeRe(code));
    }
}
