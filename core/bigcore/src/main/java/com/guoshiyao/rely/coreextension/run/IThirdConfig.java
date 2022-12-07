

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package com.guoshiyao.rely.coreextension.run;

import cn.hutool.setting.Setting;
import com.guoshiyao.rely.core.IRunModelConfig;

public interface IThirdConfig extends IRunModelConfig {

    void callSetting(Setting setting);

}
