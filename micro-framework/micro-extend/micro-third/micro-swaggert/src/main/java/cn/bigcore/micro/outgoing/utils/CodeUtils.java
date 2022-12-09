

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.outgoing.utils;

import cn.bigcore.micro.plugin.exception.code.ICode;
import cn.bigcore.micro.plugin.outgoing.OutputParamAbs;
import cn.hutool.json.JSON;
import cn.bigcore.micro.outgoing.OutRe;
import cn.bigcore.micro.outgoing.OutputParamRe;

public class CodeUtils {

    public static OutputParamRe go(ICode code) {
        OutRe out = new OutRe();
        OutputParamAbs<ICode, JSON> result = out.go(null, code, null, null);
        OutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    public static OutputParamRe go(ICode code, cn.hutool.json.JSON data) {
        OutRe out = new OutRe();
        OutputParamAbs<ICode, JSON> result = out.go(null, code, data, null);
        OutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    public static OutputParamRe go(Exception exception, cn.hutool.json.JSON data) {
        OutRe out = new OutRe();
        OutputParamAbs<ICode, JSON> result = out.go(null, null, data, exception);
        OutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    public static OutputParamRe go(Exception exception) {
        OutRe out = new OutRe();
        OutputParamAbs<ICode, JSON> result = out.go(null, null, null, exception);
        OutputParamRe re = extractedConvertToRe(result);
        return re;
    }

    /**
     * 原始出参类转换为自定义出参类
     *
     * @param result
     * @return
     * @author 汪旭辉
     * @date 2021年9月28日
     * @readme
     */
    private static OutputParamRe extractedConvertToRe(OutputParamAbs<ICode, JSON> result) {
        OutputParamRe re = new OutputParamRe();
        re.setCodeBody(result.getCodeBody());
        re.setData(result.getData());
        re.setI18n(result.getI18n());
        return re;
    }

}
