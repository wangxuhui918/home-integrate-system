

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.base.exception.type;

import cn.bigcore.micro.base.exception.FyyExceptionMessageAbstract;
import cn.bigcore.micro.exception.FyyCodeInterface;
import cn.bigcore.micro.exception.FyyCodeUtils;

/**
 * 文本异常抛出
 *
 * @author 汪旭辉
 * @date 2022年1月21日
 * @readme
 */
public class FyyExceptionInfo extends FyyExceptionMessageAbstract {
    private static final long serialVersionUID = 42L;

    /**
     * @param text   错误文本?你好!
     * @param format 格式化参数: 张三
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme mark 入参为[?你好!] [张三] 格式化结果为 [张三你好!]
     */
    public FyyExceptionInfo(String text, String... format) {
        super(FyyCodeUtils.getinfo(text, format));
    }

    public FyyExceptionInfo(FyyCodeInterface msgInfo) {
        super(msgInfo);
    }
}
