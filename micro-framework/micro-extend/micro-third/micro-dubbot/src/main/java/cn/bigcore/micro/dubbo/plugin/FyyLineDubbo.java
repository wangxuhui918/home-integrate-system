

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.dubbo.plugin;

import cn.bigcore.micro.dubbo.config.FyyLineDubboBase;
import cn.bigcore.micro.dubbo.config.FyyLineDubboConsumer;
import cn.hutool.setting.Setting;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.annotation.type.FyyRuleInjection;
import cn.bigcore.micro.base.frame.impl.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.line.FyyLineThirdExtendInterface;

import java.util.*;

@FyyRuleInjection
@EnableDubbo
public class FyyLineDubbo implements FyyLineThirdExtendInterface {
    public final static String NAME = "DUBBO";

    @Override
    public List<Class> writeClasss() {
//        if (AnnotationTools.getRuleClassForAnno(Service.class, BaseEv.WorkDir.projectPackage) > 0) {
        if (FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.DUBBO_ENABLED.getKey())) {
            return Arrays.asList(new Class[]{FyyLineDubboBase.class, FyyLineDubboConsumer.class});
        } else {
            return new ArrayList<>();
        }

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
