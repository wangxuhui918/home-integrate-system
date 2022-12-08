

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package com.guoshiyao.rely.config.line;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.IClassModelConfig;
import com.guoshiyao.rely.core.ILineManager;
import com.guoshiyao.rely.core.IRunModelConfig;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.utils.CoreConfUtils;
import com.guoshiyao.rely.coreextension.run.ISystemConfig;
import com.guoshiyao.rely.coreextension.run.IThirdConfig;
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
    private static List<IClassModelConfig> classmodelconfigrerules = CoreConfUtils.getPlugins(IClassModelConfig.class);

    private static List<IRunModelConfig> runmodelconfigrerules = CoreConfUtils.getPlugins(IRunModelConfig.class);


    /**
     * 处理变量,处理系统运行前的一些配置准备
     */
    @Override
    public void before() {

        if (BaseEv.SettingInformation.isClassModel) {//仅在class模式下需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                IClassModelConfig info = classmodelconfigrerules.get(i);
                ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "before");
                info.before();
            }
        }
        {//运行时模式需要处理的部分
            for (int i = 0; i < runmodelconfigrerules.size(); i++) {
                IRunModelConfig info = runmodelconfigrerules.get(i);
                ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "before");
                info.before();
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
                ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeProperties");
                if (info instanceof ISystemConfig) {
                    BaseEv.SettingInformation.setting.putAll(info.getProperties());
                }
            }
            //读取第三方参数
            for (int i = 0; i < runmodelconfigrerules.size(); i++) {
                IRunModelConfig info = runmodelconfigrerules.get(i);
                ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeProperties");
                if (info instanceof IThirdConfig) {
                    BaseEv.SettingInformation.setting.putAll(info.getProperties());
                }
            }
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                IClassModelConfig info = classmodelconfigrerules.get(i);
                ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeProperties");
                BaseEv.SettingInformation.setting.putAll(info.getProperties());
            }
        }
        //额外参数处理器,比如配置参数变动值
        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            IRunModelConfig info1 = runmodelconfigrerules.get(i);
            ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info1.getClass(), "callSetting");
            if (info1 instanceof IThirdConfig) {
                for (IRunModelConfig info : runmodelconfigrerules) {
                    if (info instanceof IThirdConfig) {
                        ((IThirdConfig) info).callSetting(BaseEv.SettingInformation.setting);
                    }
                }
            }
        }
        {
            ILoggerBaseUtils.debug("首次压入环境变量[{}]开始", "System.setProperty");
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
            ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeClasss");
            if (info instanceof ISystemConfig) {
                BaseEv.SettingInformation.iocclasses.addAll(info.writeClasss());
            }
        }
        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            IRunModelConfig info = runmodelconfigrerules.get(i);
            ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeClasss");
            if (info instanceof IThirdConfig) {
                BaseEv.SettingInformation.iocclasses.addAll(info.writeClasss());
            }
        }
        if (BaseEv.SettingInformation.isClassModel) {//提前处理非Jar模式需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                IClassModelConfig info = classmodelconfigrerules.get(i);
                ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeClasss");
                BaseEv.SettingInformation.iocclasses.addAll(info.writeClasss());
            }
        }

        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            IRunModelConfig info = runmodelconfigrerules.get(i);
            ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "after");
            info.after();
        }

        if (BaseEv.SettingInformation.isClassModel) {//提前处理非Jar模式需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                IClassModelConfig info = classmodelconfigrerules.get(i);
                ILoggerBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "after");
                info.after();
            }
        }
        {
            ILoggerBaseUtils.debug("二次压入系统变量[{}]开始", "System.setProperty");
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
        }
    }

}
