

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
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties.ConfigDetails;
import com.guoshiyao.rely.core.utils.AnnotationTools;
import com.guoshiyao.rely.core.utils.data.JdbcFind;
import com.guoshiyao.rely.coreextension.run.IThirdConfig;
import com.guoshiyao.rely.mybatisplus.starter.mybatis.config.MapperConfiguration;
import com.guoshiyao.rely.mybatisplus.starter.mybatis.config.MybatisPlusAutoConfiguration;
import com.guoshiyao.rely.plugin.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;

import java.util.*;

@RuleInjection

public class MyBatisPlusConfig implements IThirdConfig {
    public final static String NAME = "MYBATIS-PLUS";

    @Override
    public List<Class> writeClasss() {
        int deriversize = JdbcFind.getProjectJdbc().size();
        int userlclass = AnnotationTools.getRuleClassForClass(BaseMapper.class, BaseEv.WorkDir.projectPackage);
        if (deriversize > 0 && userlclass > 0 && BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_URL.getKey())) {//正确配置
            return Arrays.asList(new Class[]{MapperConfiguration.class, MybatisPlusAutoConfiguration.class});
        } else if (userlclass > 0 && (!BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_URL.getKey()) || deriversize > 0)) {//用户写了类,但是未引入 url 或者 jar 包
            throw new ExceptionError("未配置 home.db.url/引入驱动 jar 包!!");
        } else if (BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_URL.getKey()) && deriversize == 0) {//引入了 url,但是没引入驱动
            if (BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_DRIVERCLASS_DOWNLOADURL.getKey())) {
                ILoggerBaseUtils.info("驱动已经采用在线下载联机方式!");
            } else {
                throw new ExceptionError("home.db.url  jar 包,请选择合适的 jar 包引入!!");
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Map<String, String> getProperties() {
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


    }

}
