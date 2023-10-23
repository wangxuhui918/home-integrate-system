/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config.project;

import cn.bigcore.micro.base.frame.FyyConfigEntryDetailsVo;
import cn.bigcore.micro.base.frame.FyyConfigEntryVo;

import java.util.List;

/**
 * 开放配置接口,主要用于参数写入到properties中
 */
public interface FyyConfigPropertiesInterface {

    FyyConfigEntryVo getFyyConfigEntry();

    List<FyyConfigEntryDetailsVo> getFyyConfigEntryDetails();

}
