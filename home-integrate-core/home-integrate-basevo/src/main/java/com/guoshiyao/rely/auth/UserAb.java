/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.auth;

import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/4/2
 * @readme 基础用户对象
 */
public interface UserAb {


    public String getToken();

    public String getUserId();

    public String getUserName();

    public Map<String, String> getUserSetting();

}
