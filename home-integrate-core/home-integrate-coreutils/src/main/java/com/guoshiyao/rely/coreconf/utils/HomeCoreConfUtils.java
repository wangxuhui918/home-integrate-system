/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.coreconf.utils;

import cn.hutool.core.util.ClassUtil;
import com.guoshiyao.rely.cla.ClassForHomeUtils;
import com.guoshiyao.rely.coreannotation.AnnotationTools;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.ab.HomeCoreConfAb;
import com.guoshiyao.rely.coreconf.vo.EnvVo;
import com.guoshiyao.rely.coreconf.vo.ModelConfigInfoVo;
import com.guoshiyao.rely.coreconf.vo.ModelConfigPropertiesVo;

import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 汪旭辉
 * @date 2022/5/26
 * @readme
 */
public class HomeCoreConfUtils {
    private static HomeCoreConfAb lastHomeCoreConfAb;
    private static List<HomeCoreConfAb> homecoreconfabplugins = AnnotationTools.getRuleOn(RuleAnnotation.class, HomeCoreConfAb.class, Line.corePacket);

    static {
        try {
            if (homecoreconfabplugins.size() > 0) {//最大执行器
                homecoreconfabplugins = ClassForHomeUtils.sortForPackageAndName(homecoreconfabplugins);//按照类名以及所在包名进行 double 排序 xxx.xx,例如(12.32)
                lastHomeCoreConfAb = homecoreconfabplugins.get(homecoreconfabplugins.size() - 1);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static List<EnvVo> getEnvs() {
        return lastHomeCoreConfAb.getEnvs();
    }

    public static List<Map<String, Object>> getTableData(String tableName) {
        return lastHomeCoreConfAb.getTableData(tableName);
    }


    public static <T> List<T> sortByDbOrRuleApi(Class<T> a) {
        String tableName = "spi_" + a.getSimpleName().toLowerCase();
        HashMap<String, Integer> sort = lastHomeCoreConfAb.getOpenSpi(tableName);
        List<Class> intersectionSet = new ArrayList<>();
        List<T> listarra = new ArrayList<>();
        if (sort.size() > 0) {
            for (String o : sort.keySet()) {
                intersectionSet.add(ClassUtil.loadClass(o));
            }
            for (Class<?> class1 : intersectionSet) {
                try {
                    T sd = (T) ClassUtil.loadClass(class1.getName(), true).newInstance();
                    listarra.add(sd);
                } catch (Exception e) {
                    LoggerBaseAb.warn("{}转换失败!", class1.getName());
                }
            }
            listarra = listarra.stream().sorted(Comparator.comparingInt(od -> sort.containsKey(od.getClass().getName()) ? sort.get(od.getClass().getName()) : 0))
                    .collect(Collectors.toList());
        }
        return listarra;
    }

    public static HashMap<ModelConfigInfoVo, List<ModelConfigPropertiesVo>> getModelConf() {
        return lastHomeCoreConfAb.getModelConf();
    }
}
