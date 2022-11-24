/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.utils;

import cn.hutool.core.util.ClassUtil;
import com.guoshiyao.rely.core.configration.home.ICoreConf;
import com.guoshiyao.rely.core.configration.home.bean.FileStructureVo;
import com.guoshiyao.rely.core.configration.home.bean.ConfigMainVo;
import com.guoshiyao.rely.core.configration.home.bean.ConfigDetailsVo;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.CoreBuiltInImpl;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 汪旭辉
 * @date 2022/5/26
 * @readme
 */
public class CoreConfUtils {

    private static ICoreConf lastHomeCoreConfAb = new CoreBuiltInImpl();//按需调整

    public static <T> List<T> getPlugins(Class<T> a) {
        List<String> plugins = lastHomeCoreConfAb.getPlugins(a.getSimpleName());
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
        return lastHomeCoreConfAb.getFileStructures();
    }

    public static List<ConfigMainVo> getPropertiesMain() {//获取配置明细
        return lastHomeCoreConfAb.getPropertiesMain();
    }

    public static List<ConfigDetailsVo> getPropertiesDetails(String configFileName) {//获取配置明细
        return lastHomeCoreConfAb.getPropertiesDetails(configFileName);
    }
}
