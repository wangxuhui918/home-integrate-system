/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.core.configration.utils;

import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.core.configration.home.bean.ConfigDetailsVo;
import cn.bigcore.micro.core.configration.home.bean.ConfigMainVo;
import cn.bigcore.micro.plugin.log.ILoggerBaseUtils;
import cn.hutool.core.util.ClassUtil;
import cn.bigcore.micro.core.configration.home.bean.FileStructureVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 汪旭辉
 * @date 2022/5/26
 * @readme
 */
public class CoreConfUtils {

    public static <T> List<T> getPlugins(Class<T> a) {
        List<String> plugins = BaseEv.SettingInformation.homeConf.getPlugins(a.getSimpleName());
        List<T> returnlist = new ArrayList<>();
        for (int i = 0; i < plugins.size(); i++) {
            Class class0 = ClassUtil.loadClass(plugins.get(i), false);
            try {
                T sd = (T) ClassUtil.loadClass(class0.getName(), false).newInstance();
                returnlist.add(sd);
            } catch (Exception e) {
                ILoggerBaseUtils.err("{}转换失败!", class0.getName());
            }
        }
        return returnlist;
    }

    public static List<FileStructureVo> getFileStructures() {
        return BaseEv.SettingInformation.homeConf.getFileStructures();
    }

    public static List<ConfigMainVo> getPropertiesMain() {//获取配置明细
        return BaseEv.SettingInformation.homeConf.getPropertiesMain();
    }

    public static List<ConfigDetailsVo> getPropertiesDetails(String configFileName) {//获取配置明细
        return BaseEv.SettingInformation.homeConf.getPropertiesDetails(configFileName);
    }
}