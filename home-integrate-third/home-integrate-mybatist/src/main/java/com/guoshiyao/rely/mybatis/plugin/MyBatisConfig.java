

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.mybatis.plugin;

import com.guoshiyao.rely.coreannotation.AnnotationTools;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.mybatis.starter.mybatis.config.MapperConfiguration;
import com.guoshiyao.rely.mybatis.starter.mybatis.config.TkMybatisAutoConfiguration;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;
import com.guoshiyao.rely.tools.system.JdbcFind;
import tk.mybatis.mapper.common.Mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RuleAnnotation

public class MyBatisConfig implements ThirdExtendConfigAb {
    public final static String NAME = "MYBATIS";

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        int deriversize = JdbcFind.getProjectJdbc().size();
        int userlclass = AnnotationTools.getRuleClassForClass(Mapper.class, Line.projectPackage);
        if (deriversize > 0 && userlclass > 0 && !Line.properties.get("home.db.url").isBlank()) {//正确配置
            LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
            map.put(NAME, Arrays.asList(new Class[]{MapperConfiguration.class, TkMybatisAutoConfiguration.class}));
            return map;
        } else if (userlclass > 0 && (Line.properties.get("home.db.url").isBlank() || deriversize > 0)) {//用户写了类,但是未引入 url 或者 jar 包
            throw new ExceptionError("未配置 home.db.url/引入驱动 jar 包!!");
        } else if (!Line.properties.get("home.db.url").isBlank() && deriversize == 0) {//引入了 url,但是没引入驱动
            if (!Line.properties.get("home.db.driverclass.downloadurl").isBlank()) {
                LoggerBaseAb.info("驱动已经采用在线下载联机方式!");
            } else {
                throw new ExceptionError("home.db.url  jar 包,请选择合适的 jar 包引入!!");
            }
        }
        return new LinkedHashMap<>();

    }

    @Override
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
//        HashMap<String, String> params = ProjectCoreConfUtils.getEnvPropertiesByCode("1000011");
//        HashMap<String, PropertiesMap<String, LinePropertiesAb>> map = new HashMap<>();
//        map.put(NAME, LinePropertiesAb.convertLineProperties(params));
        return new HashMap<>();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void before() {

    }

    @Override
    public void after() {
    }

    @Override
    public void callProperties(PropertiesMap<String, LinePropertiesAb> properties) {
        {
            String key = "home.mybatis.basepackage";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,Line.projectPackage + ".mapper"));
            }
        }
        {
            String key = "home.mybatis.typealiasespackage";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,Line.projectPackage + ".mapper"));
            }
        }
        {
            String key = "home.mybatis.mapperlocations";
            if (properties.get(key).isBlank()) {
                properties.put(key, new LinePropertiesAb(key,"classpath:**/mapper/**.xml"));
            }
        }

    }

}
