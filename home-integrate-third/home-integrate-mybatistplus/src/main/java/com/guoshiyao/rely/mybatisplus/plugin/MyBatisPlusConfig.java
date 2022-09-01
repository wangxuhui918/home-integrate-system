

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.mybatisplus.plugin;

import cn.hutool.setting.Setting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guoshiyao.rely.coreannotation.AnnotationTools;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.mybatisplus.starter.mybatis.config.MapperConfiguration;
import com.guoshiyao.rely.mybatisplus.starter.mybatis.config.MybatisPlusAutoConfiguration;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;
import com.guoshiyao.rely.tools.system.JdbcFind;

import java.util.*;

@RuleAnnotation

public class MyBatisPlusConfig implements ThirdExtendConfigAb {
    public final static String NAME = "MYBATIS-PLUS";

    @Override
    public List<Class> writeClasss() {
        int deriversize = JdbcFind.getProjectJdbc().size();
        int userlclass = AnnotationTools.getRuleClassForClass(BaseMapper.class, Line.projectPackage);
        if (deriversize > 0 && userlclass > 0 && Line.setting.containsKey("home.db.url")) {//正确配置
            return Arrays.asList(new Class[]{MapperConfiguration.class, MybatisPlusAutoConfiguration.class});
        } else if (userlclass > 0 && (!Line.setting.containsKey("home.db.url") || deriversize > 0)) {//用户写了类,但是未引入 url 或者 jar 包
            throw new ExceptionError("未配置 home.db.url/引入驱动 jar 包!!");
        } else if (Line.setting.containsKey("home.db.url") && deriversize == 0) {//引入了 url,但是没引入驱动
            if (Line.setting.containsKey("home.db.driverclass.downloadurl")) {
                LoggerBaseAb.info("驱动已经采用在线下载联机方式!");
            } else {
                throw new ExceptionError("home.db.url  jar 包,请选择合适的 jar 包引入!!");
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Map<String, String> writeProperties() {
        return new HashMap<>();
    }

    @Override
    public void before() {

    }

    @Override
    public void after() {
    }

    @Override
    public void callSetting(Setting setting) {
        {
            String key = "home.mybatisplus.basepackage";
            if (!setting.containsKey(key)) {
                setting.put(key, (Line.projectPackage + ".mapper"));
            }
        }
        {
            String key = "home.mybatisplus.typealiasespackage";
            if (!setting.containsKey(key)) {
                setting.put(key, (Line.projectPackage + ".mapper"));
            }
        }
        {
            String key = "home.mybatisplus.mapperlocations";
            if (!setting.containsKey(key)) {
                setting.put(key, ("classpath:**/mapper/**.xml"));
            }
        }

    }

}
