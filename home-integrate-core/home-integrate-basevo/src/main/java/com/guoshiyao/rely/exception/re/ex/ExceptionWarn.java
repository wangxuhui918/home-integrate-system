

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.exception.re.ex;

import com.guoshiyao.rely.exception.ExceptionAb;
import com.guoshiyao.rely.exception.code.CodeAb;
import com.guoshiyao.rely.exception.code.re.CodeAbE;

/**
 * 文本异常抛出
 *
 * @author 汪旭辉
 * @date 2022年1月21日
 * @readme
 */
public class ExceptionWarn extends ExceptionAb {
    private static final long serialVersionUID = 42L;

    /**
     * @param text   错误文本?你好!
     * @param format 格式化参数: 张三
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme mark 入参为[?你好!] [张三] 格式化结果为 [张三你好!]
     */
    public ExceptionWarn(String text, String... format) {
        super(CodeAbE.getWarn(text, format));
    }

    public ExceptionWarn(CodeAb msgInfo) {
        super(msgInfo);
    }
}
