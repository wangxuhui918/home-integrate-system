/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 
 *
 */

package com.guoshiyao.rely.auth;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 郭诗瑶
 * @date 2022/4/2
 * @readme 基础用户对象
 */
public class UserRe implements UserAb, Serializable {


    @Override
    public String getToken() {
        return null;
    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public Map<String, String> getUserSetting() {
        return null;
    }
}
