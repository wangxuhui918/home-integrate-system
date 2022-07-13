/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.coreconf.ab;

import com.guoshiyao.rely.coreconf.vo.ModelConfigInfoVo;
import com.guoshiyao.rely.coreconf.vo.ModelConfigPropertiesVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
public interface HomeCoreConfAb {
    HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> getModelConf();

    List<Map<String, Object>> getTableData(String tableName);


    HashMap<String, Integer> getOpenSpi(String tableName);

}
