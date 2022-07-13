

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.outgoing.utils;

import com.guoshiyao.rely.exception.code.CodeAb;
import com.guoshiyao.rely.outgoing.OutRe;
import com.guoshiyao.rely.outgoing.OutputParamAb;
import com.guoshiyao.rely.outgoing.OutputParamRe;

public class CodeUtils {

    public static OutputParamRe go(CodeAb code) {
        OutRe out = new OutRe();
        OutputParamAb<CodeAb, cn.hutool.json.JSON> result = out.go(null, code, null, null);
        OutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    public static OutputParamRe go(CodeAb code, cn.hutool.json.JSON data) {
        OutRe out = new OutRe();
        OutputParamAb<CodeAb, cn.hutool.json.JSON> result = out.go(null, code, data, null);
        OutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    public static OutputParamRe go(Exception exception, cn.hutool.json.JSON data) {
        OutRe out = new OutRe();
        OutputParamAb<CodeAb, cn.hutool.json.JSON> result = out.go(null, null, data, exception);
        OutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    public static OutputParamRe go(Exception exception) {
        OutRe out = new OutRe();
        OutputParamAb<CodeAb, cn.hutool.json.JSON> result = out.go(null, null, null, exception);
        OutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    /**
     * 原始出参类转换为自定义出参类
     *
     * @author 汪旭辉
     * @date 2021年9月28日
     * @readme
     * @param result
     * @return
     */
    private static OutputParamRe extractedConvertToRe(OutputParamAb<CodeAb, cn.hutool.json.JSON> result) {
        OutputParamRe re = new OutputParamRe();
        re.setCodeBody(result.getCodeBody());
        re.setData(result.getData());
        re.setI18n(result.getI18n());
        return re;
    }
}
