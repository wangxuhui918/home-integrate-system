/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.line.FyyLineGroups;
import cn.bigcore.micro.config.annotation.FyyRuleInjection;
import cn.bigcore.micro.config.config.bean.FyyConfigEntryDetailsVo;
import cn.bigcore.micro.config.config.bean.FyyConfigEntryVo;
import cn.bigcore.micro.config.config.bean.FyyConfigFileStructureVo;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryValues;
import cn.bigcore.micro.line.base.FyyLineManagerInterface;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.bigcore.micro.utils.FyyAnnotationTools;
import cn.bigcore.micro.utils.FyyConfigFrameUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
@FyyRuleInjection
public class FyyConfigFrameImpl implements FyyConfigFrameInterface {


    @Override
    public List<FyyConfigFileStructureVo> getFileStructures() {//获取文件创建结构
        List<FyyConfigFileStructureVo> returnlist = new ArrayList<>();
        for (FyyConfigFileStructureEntrys o : FyyConfigFileStructureEntrys.values()) {
            FyyConfigFileStructureVo info = new FyyConfigFileStructureVo();
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
        for (int i = 0; i < FyyLineGroups.values().length; i++) {
            FyyLineGroups info = FyyLineGroups.values()[i];
            if (info.isPower() && info.getType().getCode().equals(simpleName)) {
                returnlist.add(info.getPath());
            }
        }
        return returnlist;
    }

    @Override
    public List<FyyConfigEntryVo> getPropertiesMain() {//获取主配置文件
        List<FyyConfigEntryVo> properties = new ArrayList<>();//加載內置插件属性信息
        {
            for (int i = 0; i < FyyConfigEntryValues.values().length; i++) {
                FyyConfigEntryVo m1 = new FyyConfigEntryVo();
                m1.setName(FyyConfigEntryValues.values()[i].name());
                m1.setConfigName(FyyConfigEntryValues.values()[i].getConfigName());
                m1.setContext(FyyConfigEntryValues.values()[i].getContext());
                properties.add(m1);
            }
        }
        {
            List<FyyConfigPropertiesInterface> plugins = FyyAnnotationTools.getRuleOn(FyyRuleInjection.class, FyyConfigPropertiesInterface.class, FyyInitEnv.PUBLIC_CORE_PACKAGE);//加載扩展插件属性信息
            plugins.addAll(FyyAnnotationTools.getRuleOn(FyyRuleInjection.class, FyyConfigPropertiesInterface.class, FyyInitEnv.WorkDir.projectPackage));//加載扩展插件属性信息
            for (int i = 0; i < plugins.size(); i++) {
                FyyConfigPropertiesInterface ob = plugins.get(i);
                FyyConfigEntryVo fyyConfigEntryVo = ob.getFyyConfigEntry();
                FyyConfigEntryVo m1 = new FyyConfigEntryVo();
                m1.setName(fyyConfigEntryVo.getName());
                m1.setConfigName(fyyConfigEntryVo.getConfigName());
                m1.setContext(fyyConfigEntryVo.getContext());
                properties.add(m1);
            }
        }
        return properties;
    }

    @Override
    public List<FyyConfigEntryDetailsVo> getPropertiesDetails(String configFileName) {//获取配置明细
        List<FyyConfigEntryDetailsVo> properties = new ArrayList<>();
        {//获取核心配置明细
            FyyConfigEntryValues configmain = FyyConfigEntryValues.getByFileName(configFileName);
            for (int j = 0; j < FyyConfigEntryDetailsValues.values().length && configmain != null; j++) {
                if (FyyConfigEntryDetailsValues.values()[j].getCodeType().getConfigName().equals(configmain.getConfigName())) {
                    FyyConfigEntryDetailsVo m2 = new FyyConfigEntryDetailsVo();
                    m2.setBeforesuff(FyyConfigEntryDetailsValues.values()[j].getSuff());
                    m2.setKey(FyyConfigEntryDetailsValues.values()[j].getKey());
                    m2.setM(FyyConfigEntryDetailsValues.values()[j].getM());
                    m2.setValue(FyyConfigEntryDetailsValues.values()[j].getValue());
                    m2.setMark(FyyConfigEntryDetailsValues.values()[j].getMark());
                    m2.setName_en(FyyConfigEntryDetailsValues.values()[j].getCodeType().getConfigName());
                    properties.add(m2);
                }
            }
        }
        {//获取擴展配置明细
            List<FyyConfigPropertiesInterface> plugins = FyyAnnotationTools.getRuleOn(FyyRuleInjection.class, FyyConfigPropertiesInterface.class, FyyInitEnv.PUBLIC_CORE_PACKAGE);//加載扩展插件属性信息
            plugins.addAll(FyyAnnotationTools.getRuleOn(FyyRuleInjection.class, FyyConfigPropertiesInterface.class, FyyInitEnv.WorkDir.projectPackage));//加載扩展插件属性信息
            for (int i = 0; i < plugins.size(); i++) {
                FyyConfigPropertiesInterface ob = plugins.get(i);
                FyyConfigEntryVo fyyConfigEntryVo = ob.getFyyConfigEntry();
                if (configFileName.equals(fyyConfigEntryVo.getConfigName())) {
                    List<FyyConfigEntryDetailsVo> fyyConfigEntryDetails = ob.getFyyConfigEntryDetails();
                    for (int j = 0; j < fyyConfigEntryDetails.size(); j++) {
                        FyyConfigEntryDetailsVo m2 = new FyyConfigEntryDetailsVo();
                        m2.setBeforesuff(fyyConfigEntryDetails.get(j).getBeforesuff());
                        m2.setKey(fyyConfigEntryDetails.get(j).getKey());
                        m2.setM(fyyConfigEntryDetails.get(j).getM());
                        m2.setValue(fyyConfigEntryDetails.get(j).getValue());
                        m2.setMark(fyyConfigEntryDetails.get(j).getMark());
                        m2.setName_en(fyyConfigEntryDetails.get(j).getName_en());
                        properties.add(m2);
                    }
                }
            }

        }
        return properties;
    }


}
