
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.config.newplugin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.createconfig.CreateConfigAb;
import com.guoshiyao.rely.line.Line;

import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.velocity.VelocityUtils;
import org.apache.commons.collections.keyvalue.DefaultKeyValue;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.*;

/**
 * @author 汪旭辉
 * @date 2022/3/9
 * @readme
 */
@RuleAnnotation
public class EnvFilePathCreateConfigRe implements CreateConfigAb {


    @Override
    public String getName() {
        return "通用配置写入器";
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        return null;
    }


    /**
     * 处理了当前环境所有值,其他插件可选加载
     *
     * @return
     */
    @Override
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
        Map<String, String> params = ProjectCoreConfUtils.getAllProperties();
        HashMap<String, PropertiesMap<String, LinePropertiesAb>> map = new HashMap<>();
        map.put("ALL", LinePropertiesAb.convertLineProperties(params));
        return map;
    }

    @Override
    public void before() {
        ProjectCoreConfUtils.writeModelConfig();
    }

    @Override
    public void after() {

    }


}
