

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.coreab.ab;

import com.guoshiyao.rely.coreab.ab.base.BaseConfigAb;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 核心通用配置读取器
 *
 * @author 汪旭辉
 * @date 2021年11月30日
 * @readme
 */
public interface BaseConfigRe extends BaseConfigAb {
    /**
     * 参数完全读取后,交付IOC前调用
     *
     * @author 汪旭辉
     * @date 2021年12月2日
     * @readme
     */
    void after();

    /**
     * 读取参数前调用
     *
     * @author 汪旭辉
     * @date 2021年12月2日
     * @readme
     */
    void before();

    /**
     * 插件名称
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月2日
     * @readme
     */
    String getName();

    /**
     * 需要交付Ioc的class类
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月2日
     * @readme
     */
    LinkedHashMap<String, List<Class>> writeClasss();

    /**
     * 写入Line.properties参数
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月2日
     * @readme
     */
    Map<String, String> writeProperties();

}
