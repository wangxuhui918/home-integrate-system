/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.auth;

@FunctionalInterface
public interface AuthCheckAb<U extends UserRe, A extends AuthAb> {
    /**
     * 校验用户是否有该方法的访问权限
     *
     * @param user
     * @param methodAuthAb
     * @return
     */
    public boolean check(U user, A[] methodAuthAb);
}
