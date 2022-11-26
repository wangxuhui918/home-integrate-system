/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.utils;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author 汪旭辉
 * @date 2022/4/11
 * @readme
 */
public class ClassForPackageUtils {
    /**
     * 按照类名以及包中的数字进行排序
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T> List<T> sortForPackageAndName(java.util.List<T> source) {
        return source = source.stream().sorted(Comparator.comparingDouble(od -> getNumeric(od))).collect(Collectors.toList());
    }

    /**
     * 过滤非数字
     *
     * @param str
     * @return
     */
    private static <T> Double getNumeric(T cla) {
        try {
            String tname = cla.getClass().getSimpleName();
            String pname = StrUtil.subAfter(cla.getClass().getPackage().getName(), ".", true);
            String str = pname + "." + tname;
            String regEx = "[^0-9.]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return new BigDecimal(m.replaceAll("").trim()).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1D;
    }

}
