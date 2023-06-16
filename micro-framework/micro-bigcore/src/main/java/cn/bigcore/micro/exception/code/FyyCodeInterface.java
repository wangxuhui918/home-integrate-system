

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.exception.code;

import cn.bigcore.micro.exception.code.bean.FyyMessageType;

/**
 * 消息码处理通用接口类
 *
 * @author 汪旭辉
 * @date 2022年1月21日
 * @readme
 */
public interface FyyCodeInterface {

    String getClassName();

    String getCode();

    String getI18n();

    FyyMessageType getType();

    String getText();


}
