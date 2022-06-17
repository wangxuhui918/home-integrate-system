

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */


package com.guoshiyao.rely.third;

import cn.hutool.setting.Setting;
import com.guoshiyao.rely.coreab.RunModelConfigRe;

public interface ThirdExtendConfigAb extends RunModelConfigRe {
    /**
     * 配置循环配置器,尚未结局多重循环问题,目前插件并不多,循环次数为 N*N
     *
     * @param properties
     * @author 汪旭辉
     * @date 2021年12月1日
     * @readme
     */
    void callProperties(Setting properties);
}
