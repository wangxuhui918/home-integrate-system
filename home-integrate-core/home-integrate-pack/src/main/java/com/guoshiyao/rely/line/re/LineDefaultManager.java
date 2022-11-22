

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package com.guoshiyao.rely.line.re;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.IClassModelConfig;
import com.guoshiyao.rely.core.ILineManager;
import com.guoshiyao.rely.core.IRunModelConfig;
import com.guoshiyao.rely.core.configration.utils.CoreConfUtils;
import com.guoshiyao.rely.coreextension.ISystemConfig;
import com.guoshiyao.rely.coreextension.IThirdExtendConfig;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首选链式处理器
 *
 * @author 汪旭辉
 * @date 2021年11月30日
 * @readme
 */
@RuleInjection
public class LineDefaultManager implements ILineManager {
    //<Line>
    private static List<IClassModelConfig> classmodelconfigrerules = CoreConfUtils.sortByDbOrRuleApi(IClassModelConfig.class);

    private static List<IRunModelConfig> runmodelconfigrerules = CoreConfUtils.sortByDbOrRuleApi(IRunModelConfig.class);


    /**
     * 处理变量,处理系统运行前的一些配置准备
     */
    @Override
    public void before() {

        if (BaseEv.SettingInformation.isClassModel) {//仅在class模式下需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                IClassModelConfig info = classmodelconfigrerules.get(i);
                ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "class", info.getClass(), "before");
                info.before();
                ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "class", info.getClass(), "before");
            }
        }
        {//运行时模式需要处理的部分
            for (int i = 0; i < runmodelconfigrerules.size(); i++) {
                IRunModelConfig info = runmodelconfigrerules.get(i);
                ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "jar", info.getClass(), "before");
                info.before();
                ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "jar", info.getClass(), "before");
            }
        }
    }

    /**
     * 加载配置
     */
    @Override
    public void start() {
        //读取配置参数
        {
            //读取系统参数
            for (int i = 0; i < runmodelconfigrerules.size(); i++) {
                IRunModelConfig info = runmodelconfigrerules.get(i);
                ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "jar", info.getClass(), "writeProperties");
                if (info instanceof ISystemConfig) {
                    BaseEv.SettingInformation.setting.putAll(info.writeProperties());
                }
                ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "jar", info.getClass(), "writeProperties");
            }
            //读取第三方参数
            for (int i = 0; i < runmodelconfigrerules.size(); i++) {
                IRunModelConfig info = runmodelconfigrerules.get(i);
                ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "jar", info.getClass(), "writeProperties");
                if (info instanceof IThirdExtendConfig) {
                    BaseEv.SettingInformation.setting.putAll(info.writeProperties());
                }
                ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "jar", info.getClass(), "writeProperties");
            }
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                IClassModelConfig info = classmodelconfigrerules.get(i);
                ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "class", info.getClass(), "writeProperties");
                BaseEv.SettingInformation.setting.putAll(info.writeProperties());
                ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "class", info.getClass(), "writeProperties");
            }
        }
        //额外参数处理器,比如配置参数变动值
        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            IRunModelConfig info1 = runmodelconfigrerules.get(i);
            ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "jar", info1.getClass(), "callSetting");
            if (info1 instanceof IThirdExtendConfig) {
                for (IRunModelConfig info : runmodelconfigrerules) {
                    if (info instanceof IThirdExtendConfig) {
                        ((IThirdExtendConfig) info).callSetting(BaseEv.SettingInformation.setting);
                    }
                }
            }
            ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "jar", info1.getClass(), "callSetting");
        }
        {
            ILoggerBaseUtils.info("首次压入环境变量[{}]开始", "System.setProperty");
            {
                Map<String, String> notBlankMap = MapUtil.filter(new HashMap<>(BaseEv.SettingInformation.setting), t -> StrUtil.isNotBlank(t.getValue()));
                ILoggerBaseUtils.debug("清空并重新压入项目变量");
                BaseEv.SettingInformation.setting.clear();
                BaseEv.SettingInformation.setting.putAll(notBlankMap);
            }
            //写入系统变量
            for (String key : BaseEv.SettingInformation.setting.keySet()) {//防止一些额外的变量未写入到系统参数中
                ILoggerBaseUtils.debug("压入系统变量[{}]:[{}]", key, BaseEv.SettingInformation.setting.get(key));
                System.setProperty(key, BaseEv.SettingInformation.setting.get(key));
            }
            ILoggerBaseUtils.info("首次压入环境变量[{}]完成", "System.setProperty");
        }

    }

    /**
     *
     */
    @Override
    public void after() {
        //IOC对象加载,按照顺序
        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            IRunModelConfig info = runmodelconfigrerules.get(i);
            ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "jar", info.getClass(), "writeClasss");
            if (info instanceof ISystemConfig) {
                BaseEv.SettingInformation.iocclasses.addAll(info.writeClasss());
            }
            ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "jar", info.getClass(), "writeClasss");
        }
        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            IRunModelConfig info = runmodelconfigrerules.get(i);
            ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "jar", info.getClass(), "writeClasss");
            if (info instanceof IThirdExtendConfig) {
                BaseEv.SettingInformation.iocclasses.addAll(info.writeClasss());
            }
            ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "jar", info.getClass(), "writeClasss");
        }
        if (BaseEv.SettingInformation.isClassModel) {//提前处理非Jar模式需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                IClassModelConfig info = classmodelconfigrerules.get(i);
                ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "class", info.getClass(), "writeClasss");
                BaseEv.SettingInformation.iocclasses.addAll(info.writeClasss());
                ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "class", info.getClass(), "writeClasss");
            }
        }

        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            IRunModelConfig info = runmodelconfigrerules.get(i);
            ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "jar", info.getClass(), "after");
            info.after();
            ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "jar", info.getClass(), "after");
        }

        if (BaseEv.SettingInformation.isClassModel) {//提前处理非Jar模式需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                IClassModelConfig info = classmodelconfigrerules.get(i);
                ILoggerBaseUtils.info("[{}]功能处理器[{}]开始处理[{}]", "class", info.getClass(), "after");
                info.after();
                ILoggerBaseUtils.info("[{}]功能处理器[{}]处理完成[{}]", "class", info.getClass(), "after");
            }
        }
        {
            ILoggerBaseUtils.info("二次压入系统变量[{}]开始", "System.setProperty");
            {
                Map<String, String> notBlankMap = MapUtil.filter(new HashMap<>(BaseEv.SettingInformation.setting), t -> StrUtil.isNotBlank(t.getValue()));
                ILoggerBaseUtils.debug("清空并重新压入项目变量");
                BaseEv.SettingInformation.setting.clear();
                BaseEv.SettingInformation.setting.putAll(notBlankMap);
            }
            //写入系统变量
            for (String key : BaseEv.SettingInformation.setting.keySet()) {//防止一些额外的变量未写入到系统参数中
                ILoggerBaseUtils.debug("压入系统变量[{}]:[{}]", key, BaseEv.SettingInformation.setting.get(key));
                System.setProperty(key, BaseEv.SettingInformation.setting.get(key));
            }
            ILoggerBaseUtils.info("二次压入系统变量[{}]完成", "System.setProperty");
        }
    }

}
