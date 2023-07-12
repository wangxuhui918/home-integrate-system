/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config;

import cn.bigcore.micro.config.config.bean.FyyConfigEntryDetailsVo;
import cn.bigcore.micro.config.config.bean.FyyConfigEntryVo;
import cn.bigcore.micro.config.config.bean.FyyConfigFileStructureVo;

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
