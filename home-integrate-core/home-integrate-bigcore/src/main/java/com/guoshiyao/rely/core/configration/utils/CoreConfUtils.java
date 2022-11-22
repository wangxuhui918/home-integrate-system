/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.configration.utils;

import cn.hutool.core.util.ClassUtil;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.bean.ConfigMainType;
import com.guoshiyao.rely.core.configration.home.ICoreConf;
import com.guoshiyao.rely.core.configration.vo.ModelConfigInfoVo;
import com.guoshiyao.rely.core.configration.vo.ModelConfigPropertiesVo;
import com.guoshiyao.rely.core.configration.vo.ReadMeVo;
import com.guoshiyao.rely.core.utils.AnnotationTools;
import com.guoshiyao.rely.core.utils.ClassForPackageUtils;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 汪旭辉
 * @date 2022/5/26
 * @readme
 */
public class CoreConfUtils {
    private static ICoreConf lastHomeCoreConfAb;
    private static List<ICoreConf> homecoreconfabplugins = AnnotationTools.getRuleOn(RuleInjection.class, ICoreConf.class, BaseEv.SettingInformation.corePacket);

    static {
        try {
            if (homecoreconfabplugins.size() > 0) {//最大执行器
                homecoreconfabplugins = ClassForPackageUtils.sortForPackageAndName(homecoreconfabplugins);//按照类名以及所在包名进行 double 排序 xxx.xx,例如(12.32)
                lastHomeCoreConfAb = homecoreconfabplugins.get(homecoreconfabplugins.size() - 1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static <T> List<T> sortByDbOrRuleApi(Class<T> a) {
        HashMap<String, Integer> sort = lastHomeCoreConfAb.getOpenSpi(a.getSimpleName());
        List<Class> intersectionSet = new ArrayList<>();
        List<T> listarra = new ArrayList<>();
        if (sort.size() > 0) {
            for (String o : sort.keySet()) {
                intersectionSet.add(ClassUtil.loadClass(o, false));
            }
            for (Class<?> class1 : intersectionSet) {
                try {
                    T sd = (T) ClassUtil.loadClass(class1.getName(), false).newInstance();
                    listarra.add(sd);
                } catch (Exception e) {
                    ILoggerBaseUtils.warn("{}转换失败!", class1.getName());
                }
            }
            listarra = listarra.stream().sorted(Comparator.comparingInt(od -> sort.containsKey(od.getClass().getName()) ? sort.get(od.getClass().getName()) : 0))
                    .collect(Collectors.toList());
        }
        return listarra;
    }

    public static List<ConfigMainType> getConfigMainType() {
        return lastHomeCoreConfAb.getConfigMainType();
    }


    public static List<ReadMeVo> getReadMe() {
        return lastHomeCoreConfAb.getReadMe();
    }

    public static HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> getModelConf() {
        return lastHomeCoreConfAb.getModelConf();
    }
}
