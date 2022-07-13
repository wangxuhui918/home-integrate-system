

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶
 *
 */

package com.guoshiyao.rely.outgoing;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.exception.ExceptionAb;
import com.guoshiyao.rely.exception.code.CodeAb;
import com.guoshiyao.rely.exception.code.re.CodeAbE;

/**
 * 系统默认构造器
 *
 * @author 郭诗瑶
 * @date 2021年9月28日
 * @readme
 */
public class OutRe implements OutGAb<cn.hutool.json.JSON, cn.hutool.json.JSON> {

    @Override
    public OutputParamAb<CodeAb, cn.hutool.json.JSON> go(InputParamAb<cn.hutool.json.JSON> inputparamer, CodeAb code,
                                                         cn.hutool.json.JSON data, Exception exception) {
        if (exception != null) {
            if (exception instanceof ExceptionAb && StrUtil.isNotBlank(((ExceptionAb) exception).getClassName())) {//自定义异常,有异常码
                if (code == null) {
                    code = ((ExceptionAb) exception).getCode();//获取 exception 中的msg code 信息,转为对象
                } //抛出的异常无异常码
            } else {//其他异常类型
                exception.printStackTrace();
            }
        }
        OutputParamAb<CodeAb, cn.hutool.json.JSON> returninfo = new OutputParamAb<CodeAb, cn.hutool.json.JSON>() {
        };
        if (code == null) {
            String except_mess_txt = "未知异常!";
            if (exception != null) {
                except_mess_txt = ExceptionUtil.getRootCauseMessage(exception);
            }
            code = CodeAbE.getError(except_mess_txt);
        } else {
            returninfo.setI18n(code.getI18n());
        }
        returninfo.setData(data);
        returninfo.setCodeBody(code);
        return returninfo;
    }


}
