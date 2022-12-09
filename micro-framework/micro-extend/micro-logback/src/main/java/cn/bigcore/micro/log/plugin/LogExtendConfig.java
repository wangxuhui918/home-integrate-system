

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.log.plugin;

import cn.bigcore.micro.coreextension.run.ISystemConfig;
import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.core.configration.annotation.RuleInjection;
import cn.bigcore.micro.core.configration.home.impl.bean.ConfigDetails;
import cn.bigcore.micro.plugin.exception.re.ex.ExceptionError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RuleInjection
public class LogExtendConfig implements ISystemConfig {

    @Override
    public void after() {
        if (BaseEv.SettingInformation.setting.getBool(ConfigDetails.LOG_ENABLE.getKey())) {
            try {
                LogBackConfigLoader.load("ching.xml");
            } catch (Exception e) {
                throw new ExceptionError("日志管理器{}加载失败!请检查文件是否符合规范!!", "ching.xml");
            }
        }
    }

    @Override
    public void before() {

    }

    @Override
    public List<Class> writeClasss() {
        return Arrays.asList(new Class[]{});
    }

    @Override
    public Map<String, String> getProperties() {
        return new HashMap<>();
    }

}
