/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.coreconf.re.homeR100;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.guoshiyao.rely.base.BaseEv;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.ab.HomeCoreConfAb;
import com.guoshiyao.rely.coreconf.vo.ModelConfigInfoVo;
import com.guoshiyao.rely.coreconf.vo.ModelConfigPropertiesVo;
import com.guoshiyao.rely.coreconf.vo.SpiVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
@RuleAnnotation
public class R001HomeExcel implements HomeCoreConfAb {

    private final static String homedbpath = BaseEv.HOME_TAG+"db.xls";

    private final static String homedbextendspath = BaseEv.HOME_TAG+"db-1.xls";

    public final static HashMap<String, List<Map<String, Object>>> allExcelModelValues = new HashMap<>();


    static {
        try {//优先读取homedbextendspath配置清单
            List<String> tableNames = ExcelUtil.getReader(ResourceUtil.getStream(homedbextendspath)).getSheetNames();
            for (int i = 0; i < tableNames.size(); i++) {
                List<Map<String, Object>> modelValues = ExcelUtil.getReader(ResourceUtil.getStream(homedbextendspath), tableNames.get(i)).readAll();
                allExcelModelValues.put(tableNames.get(i), modelValues);
            }
        } catch (Exception e) {//读取失败后读取内置配置清单
            List<String> tableNames = ExcelUtil.getReader(ResourceUtil.getStream(homedbpath)).getSheetNames();
            for (int i = 0; i < tableNames.size(); i++) {
                List<Map<String, Object>> modelValues = ExcelUtil.getReader(ResourceUtil.getStream(homedbpath), tableNames.get(i)).readAll();
                allExcelModelValues.put(tableNames.get(i), modelValues);
            }
        }

    }

    public List<Map<String, Object>> getTableData(String tableName) {
        return allExcelModelValues.get(tableName);
    }


    @Override
    public HashMap<String, Integer> getOpenSpi(String tableName) {
        List<SpiVo> listResult2 = new ArrayList<>();
        HashMap<String, Integer> sort = new HashMap<>();
        try {
            List<Map<String, Object>> listResult = allExcelModelValues.get(tableName);
            if (listResult.size() > 0) {
                listResult = listResult.stream().filter(info -> "1".equals(info.get("open") != null ? info.get("open").toString() : "0")).collect(Collectors.toList());
            }
            listResult2 = BeanUtil.copyToList(listResult, SpiVo.class);
            for (SpiVo o : listResult2) {
                sort.put(o.getB(), o.getA());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sort;
    }

    @Override
    public HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> getModelConf() {
        List<ModelConfigInfoVo> listResult1 = new ArrayList<>();
        List<ModelConfigPropertiesVo> listResult2 = new ArrayList<>();
        HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> properties = new HashMap<>();
        try {
            {
                List<Map<String, Object>> listResult = allExcelModelValues.get("model_config_info");
                listResult1 = BeanUtil.copyToList(listResult, ModelConfigInfoVo.class);
            }
            {
                List<Map<String, Object>> listResult = allExcelModelValues.get("model_config_properties");
                listResult2 = BeanUtil.copyToList(listResult, ModelConfigPropertiesVo.class);
            }
            for (ModelConfigInfoVo info1 : listResult1) {
                properties.put(info1, new ArrayList<>());
                for (int i = 0; i < listResult2.size(); i++) {
                    ModelConfigPropertiesVo info2 = listResult2.get(i);
                    if (info2.getName_en().equals(info1.getName_en())) {
                        properties.get(info1).add(info2);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return properties;
    }


}
