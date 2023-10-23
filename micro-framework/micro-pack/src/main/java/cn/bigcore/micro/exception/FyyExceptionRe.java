

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.exception;

import cn.bigcore.micro.base.exception.FyyExceptionMessageAbstract;

public class FyyExceptionRe extends FyyExceptionMessageAbstract {
    private static final long serialVersionUID = 42L;

    public FyyExceptionRe(FyyCodeRe msgInfo) {
        super(msgInfo);
    }

    public FyyExceptionRe(String code) {
        super(new FyyCodeRe(code));
    }
}
