/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.plugin.auth.impl;

import cn.bigcore.micro.plugin.auth.IUser;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/4/2
 * @readme 基础用户对象实现
 */
public class UserImpl implements IUser, Serializable {


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
