

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.plugin.exception.re.ex;

import cn.bigcore.micro.plugin.exception.code.ICode;
import cn.bigcore.micro.plugin.exception.ExceptionAbs;
import cn.bigcore.micro.plugin.exception.code.impl.CodeImpl;

/**
 * 文本异常抛出
 *
 * @author 汪旭辉
 * @date 2022年1月21日
 * @readme
 */
public class ExceptionWarn extends ExceptionAbs {
    private static final long serialVersionUID = 42L;

    /**
     * @param text   错误文本?你好!
     * @param format 格式化参数: 张三
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme mark 入参为[?你好!] [张三] 格式化结果为 [张三你好!]
     */
    public ExceptionWarn(String text, String... format) {
        super(CodeImpl.getWarn(text, format));
    }

    public ExceptionWarn(ICode msgInfo) {
        super(msgInfo);
    }
}
