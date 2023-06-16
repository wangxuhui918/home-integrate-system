/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.auth;

/**
 * @author 汪旭辉
 * @date 2022/4/2
 * @readme 基础用户对象
 */
public interface FyyAuthInterface {


    public String getAuthCode();

    public String getAuthName();

    public String getAuthExtend();


}
