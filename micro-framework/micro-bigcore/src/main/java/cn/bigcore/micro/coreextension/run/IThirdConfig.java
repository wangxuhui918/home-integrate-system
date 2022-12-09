

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.coreextension.run;

import cn.hutool.setting.Setting;
import cn.bigcore.micro.core.IRunModelConfig;

public interface IThirdConfig extends IRunModelConfig {

    void callSetting(Setting setting);

}
