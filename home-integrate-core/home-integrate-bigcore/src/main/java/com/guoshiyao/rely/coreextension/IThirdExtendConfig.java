

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package com.guoshiyao.rely.coreextension;

import cn.hutool.setting.Setting;
import com.guoshiyao.rely.core.RunModelConfigRe;

public interface IThirdExtendConfig extends RunModelConfigRe {
    /**
     * 配置循环配置器,尚未结局多重循环问题,目前插件并不多,循环次数为 N*N
     *
     * @param setting
     * @author 汪旭辉
     * @date 2021年12月1日
     * @readme
     */
    void callSetting(Setting setting);
}
