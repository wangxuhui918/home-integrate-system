/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.home.impl.enumtype;

import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.home.ICoreConf;
import com.guoshiyao.rely.core.configration.home.bean.FileStructureVo;
import com.guoshiyao.rely.core.configration.home.bean.ConfigMainVo;
import com.guoshiyao.rely.core.configration.home.bean.ConfigDetailsVo;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.filestructure.FileStructure;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.plugin.ConfigPluginDetails;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties.ConfigDetails;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties.ConfigMain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
@RuleInjection
public class CoreBuiltInImpl implements ICoreConf {


    @Override
    public List<FileStructureVo> getFileStructures() {//获取文件创建结构
        List<FileStructureVo> returnlist = new ArrayList<>();
        for (FileStructure o : FileStructure.values()) {
            FileStructureVo info = new FileStructureVo();
            info.setPath(o.getPath());
            info.setResourceType(o.getType());
            info.setContext(o.getContext());
            returnlist.add(info);
        }
        return returnlist;
    }


    @Override
    public List<String> getPlugins(String simpleName) {//获取功能插件列表
        List<String> returnlist = new ArrayList<>();
        for (int i = 0; i < ConfigPluginDetails.values().length; i++) {
            ConfigPluginDetails info = ConfigPluginDetails.values()[i];
            if (info.isPower() && info.getType().getCode().equals(simpleName)) {
                returnlist.add(info.getPath());
            }
        }
        return returnlist;
    }

    @Override
    public List<ConfigMainVo> getPropertiesMain() {//获取主配置文件
        List<ConfigMainVo> properties = new ArrayList<>();
        for (int i = 0; i < ConfigMain.values().length; i++) {
            ConfigMainVo m1 = new ConfigMainVo();
            m1.setConfigFileName(ConfigMain.values()[i].getConfigFileName());
            m1.setContext(ConfigMain.values()[i].getContext());
            m1.setOnly_local(ConfigMain.values()[i].isOnly_local());
            m1.setUse_uk(ConfigMain.values()[i].isUse_uk());
            m1.setNeed_format_zone(ConfigMain.values()[i].isNeed_format_zone());
            properties.add(m1);
        }
        return properties;
    }

    @Override
    public List<ConfigDetailsVo> getPropertiesDetails(String configFileName) {//获取配置明细
        List<ConfigDetailsVo> properties = new ArrayList<>();
        ConfigMain configmain = ConfigMain.getByFileName(configFileName);
        for (int j = 0; j < ConfigDetails.values().length && configmain != null; j++) {
            if (ConfigDetails.values()[j].getCodeType().getConfigFileName().equals(configmain.getConfigFileName())) {
                ConfigDetailsVo m2 = new ConfigDetailsVo();
                m2.setBeforesuff(ConfigDetails.values()[j].getSuff());
                m2.setId(ConfigDetails.values()[j].getSort_id());
                m2.setKey(ConfigDetails.values()[j].getKey());
                m2.setM(ConfigDetails.values()[j].getM());
                m2.setValue(ConfigDetails.values()[j].getValue());
                m2.setMark(ConfigDetails.values()[j].getMark());
                m2.setName_en(ConfigDetails.values()[j].getCodeType().getConfigFileName());
                properties.add(m2);
            }
        }
        return properties;
    }


}
