

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.outgoing;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import com.guoshiyao.rely.plugin.exception.ExceptionAbs;
import com.guoshiyao.rely.plugin.exception.code.ICode;
import com.guoshiyao.rely.plugin.exception.code.impl.CodeImpl;
import com.guoshiyao.rely.plugin.outgoing.IOutG;
import com.guoshiyao.rely.plugin.outgoing.InputParamAb;
import com.guoshiyao.rely.plugin.outgoing.OutputParamAbs;

/**
 * 系统默认构造器
 *
 * @author 汪旭辉
 * @date 2021年9月28日
 * @readme
 */
public class OutRe implements IOutG<JSON, JSON> {

    @Override
    public OutputParamAbs<ICode, JSON> go(InputParamAb<JSON> inputparamer, ICode code,
                                          cn.hutool.json.JSON data, Exception exception) {
        if (exception != null) {
            if (exception instanceof ExceptionAbs && StrUtil.isNotBlank(((ExceptionAbs) exception).getClassName())) {//自定义异常,有异常码
                if (code == null) {
                    code = ((ExceptionAbs) exception).getCode();//获取 exception 中的msg code 信息,转为对象
                } //抛出的异常无异常码
            } else {//其他异常类型
                exception.printStackTrace();
            }
        }
        OutputParamAbs<ICode, JSON> returninfo = new OutputParamAbs<ICode, JSON>() {
        };
        if (code == null) {
            String except_mess_txt = "未知异常!";
            if (exception != null) {
                except_mess_txt = ExceptionUtil.getRootCauseMessage(exception);
            }
            code = CodeImpl.getError(except_mess_txt);
        } else {
            returninfo.setI18n(code.getI18n());
        }
        returninfo.setData(data);
        returninfo.setCodeBody(code);
        return returninfo;
    }


}
