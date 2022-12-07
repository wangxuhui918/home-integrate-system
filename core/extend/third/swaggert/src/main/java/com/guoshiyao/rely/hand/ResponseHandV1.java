

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.hand;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.annotation.RuleController;
import com.guoshiyao.rely.outgoing.utils.CodeUtils;
import com.guoshiyao.rely.plugin.exception.code.impl.CodeImpl;
import com.guoshiyao.rely.plugin.outgoing.OutputParamAbs;
import org.apache.commons.collections.keyvalue.DefaultKeyValue;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(annotations = RuleController.class)
public class ResponseHandV1 implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Class type = returnType.getMethod().getReturnType();

        if (body == null) {//如果值为空的
            return CodeUtils.go(CodeImpl.getinfo());
        } else if (body instanceof OutputParamAbs) {//如果返回值直接为理想类型的,直接返回
            return body;
        } else if (ClassUtil.isSimpleValueType(body.getClass())) {//如果为基础类型,返回拼写
            return CodeUtils.go(CodeImpl.getinfo(), JSONUtil.parse(new DefaultKeyValue(null, body)));
        } else if (body instanceof Object) {//如果为其他复杂对象,直接进行转换
            return CodeUtils.go(CodeImpl.getinfo(), JSONUtil.parse(body));
        }

        if (type.getName().equals("void")) {//如果返回值为 void  null 直接返回 true
            return CodeUtils.go(CodeImpl.getinfo());
        } else if (ClassUtil.isAssignable(type, OutputParamAbs.class)) {
            return body;
        } else if (ClassUtil.isSimpleValueType(type)) {//如果为基础类型,返回拼写
            return CodeUtils.go(CodeImpl.getinfo(), JSONUtil.parse(new DefaultKeyValue(null, body)));
        } else if (type.getName().equals(Object.class.getName())) {//如果为其他复杂对象,直接进行转换
            return CodeUtils.go(CodeImpl.getinfo(), JSONUtil.parse(body));
        }
        return body;//其他基础类型
    }

}