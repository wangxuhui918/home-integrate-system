

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.outgoing;

import cn.bigcore.micro.exception.code.FyyCodeInterface;
import cn.bigcore.micro.exception.code.FyyCodeUtils;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.bigcore.micro.exception.FyyExceptionMessageAbstract;

/**
 * 系统默认构造器
 *
 * @author 汪旭辉
 * @date 2021年9月28日
 * @readme
 */
public class FyyOutRe implements FyyMessageDataOutInterface<JSON, JSON> {

    @Override
    public FyyOutputParamAbstract<FyyCodeInterface, JSON> go(FyyInputParamInterface<JSON> inputparamer, FyyCodeInterface code,
                                                             cn.hutool.json.JSON data, Exception exception) {
        if (exception != null) {
            if (exception instanceof FyyExceptionMessageAbstract && StrUtil.isNotBlank(((FyyExceptionMessageAbstract) exception).getClassName())) {//自定义异常,有异常码
                if (code == null) {
                    code = ((FyyExceptionMessageAbstract) exception).getCode();//获取 exception 中的msg code 信息,转为对象
                } //抛出的异常无异常码
            } else {//其他异常类型
                exception.printStackTrace();
            }
        }
        FyyOutputParamAbstract<FyyCodeInterface, JSON> returninfo = new FyyOutputParamAbstract<FyyCodeInterface, JSON>() {
        };
        if (code == null) {
            String except_mess_txt = "未知异常!";
            if (exception != null) {
                except_mess_txt = ExceptionUtil.getRootCauseMessage(exception);
            }
            code = FyyCodeUtils.getError(except_mess_txt);
        } else {
            returninfo.setI18n(code.getI18n());
        }
        returninfo.setData(data);
        returninfo.setCodeBody(code);
        return returninfo;
    }


}
