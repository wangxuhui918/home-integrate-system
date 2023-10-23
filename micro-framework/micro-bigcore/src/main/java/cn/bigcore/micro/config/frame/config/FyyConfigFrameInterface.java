/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config.frame.config;

import cn.bigcore.micro.base.frame.FyyConfigEntryDetailsVo;
import cn.bigcore.micro.base.frame.FyyConfigEntryVo;
import cn.bigcore.micro.base.frame.FyyConfigFileStructureVo;

import java.util.List;

/**
 * 配置接口
 */
public interface FyyConfigFrameInterface {

    <T> List<String> getPlugins(Class<T> a);

    List<FyyConfigEntryVo> getPropertiesMain();

    List<FyyConfigEntryDetailsVo> getPropertiesDetails(String configFileName);

    List<FyyConfigFileStructureVo> getFileStructures();


}
