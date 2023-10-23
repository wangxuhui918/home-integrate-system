

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.mybatisplus.plugin;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.base.frame.impl.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.line.FyyLineThirdExtendInterface;
import cn.bigcore.micro.mybatisplus.starter.mybatis.config.FyyMybatisMapperConfiguration;
import cn.hutool.setting.Setting;
import cn.bigcore.micro.annotation.type.FyyRuleInjection;
import cn.bigcore.micro.mybatisplus.starter.mybatis.config.FyyMybatisPlusAutoConfiguration;

import java.util.*;

@FyyRuleInjection

public class FyyLineMyBatisPlus implements FyyLineThirdExtendInterface {
    public final static String NAME = "MYBATIS-PLUS";

    @Override
    public List<Class> writeClasss() {
//        int deriversize = BaseEv.SettingInformation.driverClasses.size();
//        int userlclass = AnnotationTools.getRuleClassForClass(BaseMapper.class, BaseEv.WorkDir.projectPackage);
//        if (deriversize > 0 && userlclass > 0 && BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_URL.getKey())) {//正确配置
//            return Arrays.asList(new Class[]{MapperConfiguration.class, MybatisPlusAutoConfiguration.class});
//        } else if (userlclass > 0 && (!BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_URL.getKey()) || deriversize > 0)) {//用户写了类,但是未引入 url 或者 jar 包
//            throw new ExceptionError("未配置 home.db.url/引入驱动 jar 包!!");
//        } else if (BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_URL.getKey()) && deriversize == 0) {//引入了 url,但是没引入驱动
//            if (BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_DRIVERCLASS_DOWNLOADURL.getKey())) {
//                ILoggerBaseUtils.info("驱动已经采用在线下载联机方式!");
//            } else {
//                throw new ExceptionError("db.url  jar 包,请选择合适的 jar 包引入!!");
//            }
//        }
        if (FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.MYBATISPLUS_ENABLE.getKey())) {
            return Arrays.asList(new Class[]{FyyMybatisMapperConfiguration.class, FyyMybatisPlusAutoConfiguration.class});
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
