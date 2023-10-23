/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.utils;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.base.frame.FyyConfigEntryDetailsVo;
import cn.bigcore.micro.base.frame.FyyConfigEntryVo;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.hutool.core.util.ClassUtil;
import cn.bigcore.micro.base.frame.FyyConfigFileStructureVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 汪旭辉
 * @date 2022/5/26
 * @readme 框架配置工具类
 */
public class FyyConfigFrameUtils {

    public static <T> List<T> getPlugins(Class<T> a) {
        List<String> plugins = FyyInitEnv.SettingInformation.homeConf.getPlugins(a);
        List<T> returnlist = new ArrayList<>();
        for (int i = 0; i < plugins.size(); i++) {
            Class class0 = ClassUtil.loadClass(plugins.get(i), false);
            try {
                T sd = (T) ClassUtil.loadClass(class0.getName(), false).newInstance();
                returnlist.add(sd);
            } catch (Exception e) {
                FyyLogBaseUtils.err("{}转换失败!", class0.getName());
            }
        }
        return returnlist;
    }

    public static List<FyyConfigFileStructureVo> getFileStructures() {
        return FyyInitEnv.SettingInformation.homeConf.getFileStructures();
    }

    public static List<FyyConfigEntryVo> getPropertiesMain() {//获取配置明细
        return FyyInitEnv.SettingInformation.homeConf.getPropertiesMain();
    }

    public static List<FyyConfigEntryDetailsVo> getPropertiesDetails(String configFileName) {//获取配置明细
        return FyyInitEnv.SettingInformation.homeConf.getPropertiesDetails(configFileName);
    }
}
