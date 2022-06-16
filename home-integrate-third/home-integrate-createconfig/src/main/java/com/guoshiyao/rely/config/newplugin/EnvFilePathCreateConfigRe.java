
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

    //通用 Class 变量类生成
    @Override
    public void after() {
        try {
            ClassUtil.loadClass(Line.projectPackage + ".HomeProperties").newInstance();
            String path = Line.projectcodesourcepath + StrUtil.replace(Line.projectPackage, ".", File.separator) + File.separator + "HomeProperties.java";
            String context = FileUtil.readUtf8String(path);
            for (String key : Line.properties.keySet()) {
                String k = StrUtil.replace(key, ".", "_");
                if (!StrUtil.containsAny(context, k, key)) {
                    createHomePropertiesModel();
                    break;
                }
            }
        } catch (Exception e) {//不存在则创建
            createHomePropertiesModel();
        }
    }

    private void createHomePropertiesModel() {
        VelocityContext context = new VelocityContext();
        context.put("projectPackage", Line.projectPackage);
        List<DefaultKeyValue> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (String key : Line.properties.keySet()) {
            String k = StrUtil.replace(key, ".", "_");
            k = StrUtil.toCamelCase(k);
            k = StrUtil.upperFirst(k);
            list.add(new DefaultKeyValue(k, Line.properties.get(key).getValue()));
            map.put(k, key);
        }
        context.put("list", list);
        context.put("map", map);
        String str = VelocityUtils.convert(ResourceUtil.readUtf8Str("configfiles" + File.separator + "homeproperties.vm"), context);
        FileUtil.writeUtf8String(str, Line.projectcodesourcepath + StrUtil.replace(Line.projectPackage, ".", File.separator) + File.separator + "HomeProperties.java");
    }


}
