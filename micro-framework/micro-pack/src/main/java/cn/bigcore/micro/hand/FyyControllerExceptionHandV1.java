

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.hand;

import cn.bigcore.micro.annotation.FyyRuleController;
import cn.bigcore.micro.base.exception.FyyExceptionApiNull;
import cn.bigcore.micro.base.exception.FyyExceptionMessageAbstract;
import cn.bigcore.micro.exception.FyyExceptionUtils;
import cn.bigcore.micro.outgoing.impl.FyyOutputParamRe;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Configuration
@RestControllerAdvice(annotations = FyyRuleController.class)
public class FyyControllerExceptionHandV1 {
    private static final Logger log = LoggerFactory.getLogger(FyyControllerExceptionHandV1.class);

    @ExceptionHandler(Exception.class)
    public FyyOutputParamRe exception(Exception ex) {
        if (ex instanceof FyyExceptionApiNull) {//如果为ExceptionApiNull空,则直接返回空数据
            return cn.bigcore.micro.outgoing.utils.FyyCodeUtils.go(FyyExceptionUtils.getinfo());
        }
        if (ex instanceof FyyExceptionMessageAbstract && StrUtil.isNotBlank(((FyyExceptionMessageAbstract) ex).getClassName())) {//自定义异常,有异常码
            ex.printStackTrace();
            log.error("业务异常[" + ((FyyExceptionMessageAbstract) ex).getCode() + ":" + ex.getMessage() + "]" + FyyControllerExceptionHandV1.getStackMsg(ex));
        }
        return cn.bigcore.micro.outgoing.utils.FyyCodeUtils.go(ex);
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