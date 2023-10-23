

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */


package cn.bigcore.micro.line;

import cn.bigcore.micro.line.base.FyyLineSourceModelInterface;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.line.base.FyyLineManagerInterface;
import cn.bigcore.micro.line.base.FyyLineRuntimeModelInterface;
import cn.bigcore.micro.annotation.type.FyyRuleInjection;
import cn.bigcore.micro.utils.FyyConfigFrameUtils;

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
@FyyRuleInjection
public class FyyLineDefaultManager implements FyyLineManagerInterface {
    //<Line>
    private static List<FyyLineSourceModelInterface> classmodelconfigrerules = FyyConfigFrameUtils.getPlugins(FyyLineSourceModelInterface.class);

    private static List<FyyLineRuntimeModelInterface> runmodelconfigrerules = FyyConfigFrameUtils.getPlugins(FyyLineRuntimeModelInterface.class);


    /**
     * 处理变量,处理系统运行前的一些配置准备
     */
    @Override
    public void before() {

        if (FyyInitEnv.SettingInformation.isClassModel) {//仅在class模式下需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                FyyLineSourceModelInterface info = classmodelconfigrerules.get(i);
                FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "before");
                info.before();
            }
        }
        {//运行时模式需要处理的部分
            for (int i = 0; i < runmodelconfigrerules.size(); i++) {
                FyyLineRuntimeModelInterface info = runmodelconfigrerules.get(i);
                FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "before");
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
                FyyLineRuntimeModelInterface info = runmodelconfigrerules.get(i);
                FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeProperties");
                if (info instanceof FyyLineSystemInterface) {
                    FyyInitEnv.SettingInformation.setting.putAll(info.getProperties());
                }
            }
            //读取第三方参数
            for (int i = 0; i < runmodelconfigrerules.size(); i++) {
                FyyLineRuntimeModelInterface info = runmodelconfigrerules.get(i);
                FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeProperties");
                if (info instanceof FyyLineThirdExtendInterface) {
                    FyyInitEnv.SettingInformation.setting.putAll(info.getProperties());
                }
            }
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                FyyLineSourceModelInterface info = classmodelconfigrerules.get(i);
                FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeProperties");
                FyyInitEnv.SettingInformation.setting.putAll(info.getProperties());
            }
        }
        //额外参数处理器,比如配置参数变动值
        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            FyyLineRuntimeModelInterface info1 = runmodelconfigrerules.get(i);
            FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info1.getClass(), "callSetting");
            if (info1 instanceof FyyLineThirdExtendInterface) {
                for (FyyLineRuntimeModelInterface info : runmodelconfigrerules) {
                    if (info instanceof FyyLineThirdExtendInterface) {
                        ((FyyLineThirdExtendInterface) info).callSetting(FyyInitEnv.SettingInformation.setting);
                    }
                }
            }
        }
        {
            FyyLogBaseUtils.debug("首次压入环境变量[{}]开始", "System.setProperty");
            {
                Map<String, String> notBlankMap = MapUtil.filter(new HashMap<>(FyyInitEnv.SettingInformation.setting), t -> StrUtil.isNotBlank(t.getValue()));
                FyyLogBaseUtils.debug("清空并重新压入项目变量");
                FyyInitEnv.SettingInformation.setting.clear();
                FyyInitEnv.SettingInformation.setting.putAll(notBlankMap);
            }
            //写入系统变量
            for (String key : FyyInitEnv.SettingInformation.setting.keySet()) {//防止一些额外的变量未写入到系统参数中
                FyyLogBaseUtils.debug("压入系统变量[{}]:[{}]", key, FyyInitEnv.SettingInformation.setting.get(key));
                System.setProperty(key, FyyInitEnv.SettingInformation.setting.get(key));
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
            FyyLineRuntimeModelInterface info = runmodelconfigrerules.get(i);
            FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeClasss");
            if (info instanceof FyyLineSystemInterface) {
                FyyInitEnv.SettingInformation.iocclasses.addAll(info.writeClasss());
            }
        }
        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            FyyLineRuntimeModelInterface info = runmodelconfigrerules.get(i);
            FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeClasss");
            if (info instanceof FyyLineThirdExtendInterface) {
                FyyInitEnv.SettingInformation.iocclasses.addAll(info.writeClasss());
            }
        }
        if (FyyInitEnv.SettingInformation.isClassModel) {//提前处理非Jar模式需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                FyyLineSourceModelInterface info = classmodelconfigrerules.get(i);
                FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "writeClasss");
                FyyInitEnv.SettingInformation.iocclasses.addAll(info.writeClasss());
            }
        }

        for (int i = 0; i < runmodelconfigrerules.size(); i++) {
            FyyLineRuntimeModelInterface info = runmodelconfigrerules.get(i);
            FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "after");
            info.after();
        }

        if (FyyInitEnv.SettingInformation.isClassModel) {//提前处理非Jar模式需要处理的东西
            for (int i = 0; i < classmodelconfigrerules.size(); i++) {
                FyyLineSourceModelInterface info = classmodelconfigrerules.get(i);
                FyyLogBaseUtils.debug("功能处理器[{}]开始处理[{}]", info.getClass(), "after");
                info.after();
            }
        }
        {
            FyyLogBaseUtils.debug("二次压入系统变量[{}]开始", "System.setProperty");
            {
                Map<String, String> notBlankMap = MapUtil.filter(new HashMap<>(FyyInitEnv.SettingInformation.setting), t -> StrUtil.isNotBlank(t.getValue()));
                FyyLogBaseUtils.debug("清空并重新压入项目变量");
                FyyInitEnv.SettingInformation.setting.clear();
                FyyInitEnv.SettingInformation.setting.putAll(notBlankMap);
            }
            //写入系统变量
            for (String key : FyyInitEnv.SettingInformation.setting.keySet()) {//防止一些额外的变量未写入到系统参数中
                FyyLogBaseUtils.debug("压入系统变量[{}]:[{}]", key, FyyInitEnv.SettingInformation.setting.get(key));
                System.setProperty(key, FyyInitEnv.SettingInformation.setting.get(key));
            }
        }
    }

}
