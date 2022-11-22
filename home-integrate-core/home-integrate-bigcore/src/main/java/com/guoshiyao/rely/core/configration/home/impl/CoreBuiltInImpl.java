/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.home.impl;

import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.bean.ConfigDetails;
import com.guoshiyao.rely.core.configration.bean.ConfigMainType;
import com.guoshiyao.rely.core.configration.bean.CoreReadMeConfig;
import com.guoshiyao.rely.core.configration.bean.CoreSpiConfig;
import com.guoshiyao.rely.core.configration.home.ICoreConf;
import com.guoshiyao.rely.core.configration.vo.ModelConfigInfoVo;
import com.guoshiyao.rely.core.configration.vo.ModelConfigPropertiesVo;
import com.guoshiyao.rely.core.configration.vo.ReadMeVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
@RuleInjection
public class CoreBuiltInImpl implements ICoreConf {


    @Override
    public List<ConfigMainType> getConfigMainType() {
        return Arrays.asList(ConfigMainType.values());
    }

    @Override
    public List<ReadMeVo> getReadMe() {
        List<ReadMeVo> re = new ArrayList<>();
        for (CoreReadMeConfig o : CoreReadMeConfig.values()) {
            ReadMeVo infp = new ReadMeVo();
            infp.setId(Integer.parseInt(o.getId()));
            infp.setPath(o.getPath());
            infp.setType(o.getType());
            infp.setReadme(o.getReadme());
            re.add(infp);
        }
        return re;
    }


    @Override
    public HashMap<String, Integer> getOpenSpi(String simpleName) {
        HashMap<String, Integer> sort = new HashMap<>();
        try {
            List<CoreSpiConfig> listResult = Arrays.asList(CoreSpiConfig.values()).stream().filter(info -> info.isPower() && info.getType().getCode().equals(simpleName)).collect(Collectors.toList());
            for (CoreSpiConfig o : listResult) {
                sort.put(o.getPath(), o.getSort());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sort;
    }

    @Override
    public HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> getModelConf() {
        HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> properties = new HashMap<>();
        for (int i = 0; i < ConfigMainType.values().length; i++) {
            ModelConfigInfoVo m1 = new ModelConfigInfoVo();
            m1.setCode(ConfigMainType.values()[i].getOld_code());
            m1.setFile_path("");
            m1.setSort_id(ConfigMainType.values()[i].getSort_id());
            m1.setName_en(ConfigMainType.values()[i].getCode());
            m1.setName_cn("");
            m1.setModel_context(ConfigMainType.values()[i].getModel_context());
            m1.setOnly_local(ConfigMainType.values()[i].isOnly_local() ? "1" : "0");
            m1.setUse_uk(ConfigMainType.values()[i].isUse_uk() ? "1" : "0");
            m1.setNeed_format_zone(ConfigMainType.values()[i].isNeed_format_zone() ? "1" : "0");
            properties.put(m1, new ArrayList<>());
            for (int j = 0; j < ConfigDetails.values().length; j++) {
                if (ConfigDetails.values()[j].getCodeType().getOld_code().equals(m1.getCode())) {
                    ModelConfigPropertiesVo m2 = new ModelConfigPropertiesVo();
                    m2.setBeforesuff(ConfigDetails.values()[j].getSuff());
                    m2.setId(ConfigDetails.values()[j].getSort_id());
                    m2.setKey(ConfigDetails.values()[j].getKey());
                    m2.setM(ConfigDetails.values()[j].getM());
                    m2.setValue(ConfigDetails.values()[j].getValue());
                    m2.setMark(ConfigDetails.values()[j].getMark());
                    m2.setName_en(ConfigDetails.values()[j].getCodeType().getCode());
                    properties.get(m1).add(m2);
                }
            }
        }
        return properties;
    }


}
