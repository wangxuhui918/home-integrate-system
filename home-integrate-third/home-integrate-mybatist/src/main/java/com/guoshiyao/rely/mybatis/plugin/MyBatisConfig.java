

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.mybatis.plugin;

import cn.hutool.setting.Setting;
import com.guoshiyao.rely.coreannotation.AnnotationTools;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.log.base.LoggerBaseAb;
import com.guoshiyao.rely.mybatis.automethod.MybatisAutoMethod;
import com.guoshiyao.rely.mybatis.starter.mybatis.config.MapperConfiguration;
import com.guoshiyao.rely.mybatis.starter.mybatis.config.TkMybatisAutoConfiguration;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;
import com.guoshiyao.rely.tools.system.JdbcFind;
import tk.mybatis.mapper.common.Mapper;

import java.util.*;

@RuleAnnotation

public class MyBatisConfig implements ThirdExtendConfigAb {
    public final static String NAME = "MYBATIS";

    @Override
    public List<Class> writeClasss() {
        int deriversize = JdbcFind.getProjectJdbc().size();
        int userlclass = AnnotationTools.getRuleClassForClass(Mapper.class, Line.projectPackage);
        if (deriversize > 0 && userlclass > 0 && Line.setting.containsKey("home.db.url")) {//正确配置
            return Arrays.asList(new Class[]{MapperConfiguration.class, TkMybatisAutoConfiguration.class, MybatisAutoMethod.class});
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
            String key = "home.mybatis.basepackage";
            if (!setting.containsKey(key)) {
                setting.put(key, (Line.projectPackage + ".mapper"));
            }
        }
        {
            String key = "home.mybatis.typealiasespackage";
            if (!setting.containsKey(key)) {
                setting.put(key, (Line.projectPackage + ".mapper"));
            }
        }
        {
            String key = "home.mybatis.mapperlocations";
            if (!setting.containsKey(key)) {
                setting.put(key, ("classpath:**/mapper/**.xml"));
            }
        }

    }

}
