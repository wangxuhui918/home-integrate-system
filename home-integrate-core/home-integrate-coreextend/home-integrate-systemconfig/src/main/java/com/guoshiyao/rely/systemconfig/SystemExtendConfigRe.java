

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.systemconfig;

import com.guoshiyao.rely.bean.Bean;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.sys.SystemConfigAb;

import java.util.Arrays;
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
public class SystemExtendConfigRe implements SystemConfigAb {

    @Override
    public void before() {

    }

    @Override
    public void after() {
    }

    @Override
    public String getName() {
        return "SYSTEMEXTENDCONFIG";
    }

    @Override
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
        return new HashMap<>();
    }

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
        map.put(getName(), Arrays
                .asList(new Class[]{StartPingService.class, Bean.class}));
        return map;
    }

}
