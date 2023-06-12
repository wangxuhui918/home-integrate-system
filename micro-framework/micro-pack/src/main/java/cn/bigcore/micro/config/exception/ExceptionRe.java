

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config.exception;

import cn.bigcore.micro.plugin.exception.ExceptionMessageAbstract;

public class ExceptionRe extends ExceptionMessageAbstract {
    private static final long serialVersionUID = 42L;

    public ExceptionRe(CodeRe msgInfo) {
        super(msgInfo);
    }

    public ExceptionRe(String code) {
        super(new CodeRe(code));
    }
}
