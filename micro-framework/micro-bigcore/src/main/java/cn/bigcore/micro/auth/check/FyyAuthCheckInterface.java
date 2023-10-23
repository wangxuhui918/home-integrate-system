/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.auth.check;

import cn.bigcore.micro.auth.FyyAuthInterface;
import cn.bigcore.micro.auth.user.FyyUserInterface;

@FunctionalInterface
public interface FyyAuthCheckInterface<U extends FyyUserInterface, A extends FyyAuthInterface> {
    /**
     * 校验用户是否有该方法的访问权限
     *
     * @param user
     * @param methodAuthAb
     * @return
     */
    public boolean check(U user, A[] methodAuthAb);
}
