

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.base.exception;

/**
 * 仅用于某些情况下用于判断是否为本系统抛出的空异常
 * 这是空一场处理类,表示某些特殊情况下抛出的异常
 */
public class FyyExceptionApiNull extends RuntimeException {
    public FyyExceptionApiNull() {

    }
}