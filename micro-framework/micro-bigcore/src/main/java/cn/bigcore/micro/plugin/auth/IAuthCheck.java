/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.plugin.auth;

@FunctionalInterface
public interface IAuthCheck<U extends IUser, A extends IAuth> {
    /**
     * 校验用户是否有该方法的访问权限
     *
     * @param user
     * @param methodAuthAb
     * @return
     */
    public boolean check(U user, A[] methodAuthAb);
}
