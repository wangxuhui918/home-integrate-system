

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.hand;

import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.annotation.RuleController;
import com.guoshiyao.rely.outgoing.OutputParamRe;
import com.guoshiyao.rely.outgoing.utils.CodeUtils;
import com.guoshiyao.rely.plugin.exception.ExceptionAbs;
import com.guoshiyao.rely.plugin.exception.ExceptionApiNull;
import com.guoshiyao.rely.plugin.exception.code.impl.CodeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RuleController.class)
public class ExceptionHandV1 {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandV1.class);

    @ExceptionHandler(Exception.class)
    public OutputParamRe exception(Exception ex) {
        if (ex instanceof ExceptionApiNull) {//如果为ExceptionApiNull空,则直接返回空数据
            return CodeUtils.go(CodeImpl.getinfo());
        }
        if (ex instanceof ExceptionAbs && StrUtil.isNotBlank(((ExceptionAbs) ex).getClassName())) {//自定义异常,有异常码
            ex.printStackTrace();
            log.error("业务异常[" + ((ExceptionAbs) ex).getCode() + ":" + ex.getMessage() + "]" + ExceptionHandV1.getStackMsg(ex));
        }
        return CodeUtils.go(ex);
    }

    private static String getStackMsg(Exception e) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            if (i == stackArray.length - 1) {
                sb.append("\tat " + element.toString());
            } else {
                sb.append("\tat " + element.toString() + "\n");
            }
        }
        return sb.toString();
    }

}