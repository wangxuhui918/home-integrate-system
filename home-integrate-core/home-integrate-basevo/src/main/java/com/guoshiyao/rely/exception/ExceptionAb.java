

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶
 *
 */


package com.guoshiyao.rely.exception;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.exception.code.CodeAb;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;

/**
 * 框架项目基础异常类
 *
 * @author 郭诗瑶
 * @date 2021年9月26日
 * @readme
 */
public abstract class ExceptionAb extends RuntimeException {
    private final String msg;
    private final String className;

    /**
     * 构造器,传入消息码类,并重写对应的消息码toString类,获取具体的消息内容
     *
     * @param <T>
     * @param code
     * @author 郭诗瑶
     * @date 2021年9月27日
     * @readme mark
     */
    public ExceptionAb(CodeAb code) {
        super(code.toString());
        this.msg = code.toString();
        this.className = code.getClassName();
    }

    /**
     * 获取异常消息内容的方法
     *
     * @return
     * @author 郭诗瑶
     * @date 2021年9月27日
     * @readme
     */
    public String getClassName() {
        return this.className;
    }

    /**
     * 获取异常消息内容的方法
     *
     * @return
     * @author 郭诗瑶
     * @date 2021年9月27日
     * @readme
     */
    public String getMsg() {
        return this.msg;
    }

    public CodeAb getCode() {
        CodeAb code = null;
        try {
            code = (CodeAb) ClassUtil.loadClass((this).getClassName(),false).newInstance();
            BeanUtil.copyProperties(JSONUtil.parse(this.getMsg()), code);
        } catch (Exception e) {
            throw new ExceptionError(ExceptionAb.class.getName() + "对应子类的 Msg  转换为 " + CodeAb.class.getName() + "子类失败");
        }
        return code;
    }

}