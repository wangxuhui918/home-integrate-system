/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.auth.user;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/4/2
 * @readme 基础用户对象
 */
public interface FyyUserInterface extends Serializable {


    public String getToken();

    public String getUserId();

    public String getUserName();

    public Map<String, String> getUserSetting();

}
