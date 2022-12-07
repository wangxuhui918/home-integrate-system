

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.plugin.exception.re.ex;

import com.guoshiyao.rely.plugin.exception.ExceptionAbs;
import com.guoshiyao.rely.plugin.exception.code.ICode;
import com.guoshiyao.rely.plugin.exception.code.impl.CodeImpl;

/**
 * 文本异常抛出
 *
 * @author 汪旭辉
 * @date 2022年1月21日
 * @readme
 */
public class ExceptionInfo extends ExceptionAbs {
    private static final long serialVersionUID = 42L;

    /**
     * @param text   错误文本?你好!
     * @param format 格式化参数: 张三
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme mark 入参为[?你好!] [张三] 格式化结果为 [张三你好!]
     */
    public ExceptionInfo(String text, String... format) {
        super(CodeImpl.getinfo(text, format));
    }

    public ExceptionInfo(ICode msgInfo) {
        super(msgInfo);
    }
}
