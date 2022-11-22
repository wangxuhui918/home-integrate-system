/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.home;

import com.guoshiyao.rely.core.configration.bean.ConfigMainType;
import com.guoshiyao.rely.core.configration.vo.ModelConfigInfoVo;
import com.guoshiyao.rely.core.configration.vo.ModelConfigPropertiesVo;
import com.guoshiyao.rely.core.configration.vo.ReadMeVo;

import java.util.HashMap;
import java.util.List;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
public interface ICoreConf {

    HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> getModelConf();


    List<ConfigMainType> getConfigMainType();

    List<ReadMeVo> getReadMe();

    HashMap<String, Integer> getOpenSpi(String tableName);

}
