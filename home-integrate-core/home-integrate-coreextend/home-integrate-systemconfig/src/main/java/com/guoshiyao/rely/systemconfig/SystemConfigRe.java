

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.systemconfig;

import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.sys.SystemConfigAb;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 读取系统配置文件
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
@RuleAnnotation
public class SystemConfigRe implements SystemConfigAb {

    @Override
    public void before() {

    }

    @Override
    public void after() {
    }

    @Override
    public String getName() {
        return "SYSTEMCONFIG";
    }

    @Override
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
        HashMap<String, PropertiesMap<String, LinePropertiesAb>> map = new HashMap<>();
        HashMap<String, String> params2 = ProjectCoreConfUtils.getEnvPropertiesByCode("1000014");
        map.put(getName(),
                LinePropertiesAb.convertLineProperties(params2));
        return map;
    }

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        return new LinkedHashMap<>();
    }


}
