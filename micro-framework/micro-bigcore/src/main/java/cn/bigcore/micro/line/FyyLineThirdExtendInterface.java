

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.line;

import cn.hutool.setting.Setting;
import cn.bigcore.micro.line.base.FyyLineRuntimeModelInterface;

/**
 * 扩展插件管理器
 */
public interface FyyLineThirdExtendInterface extends FyyLineRuntimeModelInterface {

    void callSetting(Setting setting);

}
