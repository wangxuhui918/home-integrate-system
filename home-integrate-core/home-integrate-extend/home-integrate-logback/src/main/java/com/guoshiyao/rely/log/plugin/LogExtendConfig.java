

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.log.plugin;

import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties.ConfigDetails;
import com.guoshiyao.rely.coreextension.run.ISystemConfig;
import com.guoshiyao.rely.log.bean.EndLogBean;
import com.guoshiyao.rely.plugin.exception.re.ex.ExceptionError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RuleInjection
public class LogExtendConfig implements ISystemConfig {

    @Override
    public void after() {
        try {
            LogBackConfigLoader.load("ching.xml");
        } catch (Exception e) {
            throw new ExceptionError("日志管理器{}加载失败!请检查文件是否符合规范!!", "ching.xml");
        }
    }

    @Override
    public void before() {

    }

    @Override
    public List<Class> writeClasss() {
        return Arrays.asList(new Class[]{EndLogBean.class});
    }

    @Override
    public Map<String, String> getProperties() {
        {
            String key = ConfigDetails.HOME_LOG_APPID.getKey();
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, BaseEv.SettingInformation.idKey);
            }
        }
        {
            String key = ConfigDetails.HOME_LOG_ROOTLEVEL.getKey();
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, "info");
            }
        }
        {
            String key = ConfigDetails.HOME_LOG_BUSINLEVEL.getKey();
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, "info");
            }
        }
        {
            String key = ConfigDetails.HOME_LOG_BUSINPACKAGE.getKey();
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, BaseEv.WorkDir.projectPackage);
            }
        }
        {
            String key = ConfigDetails.HOME_LOG_DIR.getKey();
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, "target/log/" + BaseEv.SettingInformation.idKey);
            }
        }
        return new HashMap<>();
    }

}
