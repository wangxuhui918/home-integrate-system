

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.plugin.exception.code;

import com.guoshiyao.rely.plugin.exception.code.bean.MessageType;

/**
 * 消息码处理通用接口类
 *
 * @author 汪旭辉
 * @date 2022年1月21日
 * @readme
 */
public interface ICode {

    String getClassName();

    String getCode();

    String getI18n();

    MessageType getType();

    String getText();


}
