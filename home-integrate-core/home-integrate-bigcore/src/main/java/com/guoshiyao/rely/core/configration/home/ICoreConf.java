/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.home;

import com.guoshiyao.rely.core.configration.home.bean.FileStructureVo;
import com.guoshiyao.rely.core.configration.home.bean.ConfigMainVo;
import com.guoshiyao.rely.core.configration.home.bean.ConfigDetailsVo;

import java.util.List;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
public interface ICoreConf {

    List<ConfigMainVo> getPropertiesMain();

    List<ConfigDetailsVo> getPropertiesDetails(String configFileName);

    List<FileStructureVo> getFileStructures();

    List<String> getPlugins(String tableName);

}
