

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 
 *
 */

package com.guoshiyao.rely.exception.code;

/**
 * 消息码处理通用接口类
 *
 * @author 郭诗瑶
 * @date 2022年1月21日
 * @readme
 */
public interface CodeAb {

    String getClassName();

    String getCode();

    String getI18n();

    String getType();

    String getText();

}
