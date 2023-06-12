

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.plugin.exception;

/**
 * 仅用于某些情况下用于判断是否为本系统抛出的空异常
 */
public class ExceptionApiNull extends RuntimeException {
    public ExceptionApiNull() {

    }
}