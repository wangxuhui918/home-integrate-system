

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.port;

import cn.hutool.core.io.FileUtil;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.coreextension.ISystemConfig;
import com.guoshiyao.rely.port.config.SystemServletPort;

import java.util.*;

/**
 * 读取系统配置文件
 *
 * @author 汪旭辉
 * @date 2021年9月27日
 * @readme
 */
@RuleInjection
public class PortConfig implements ISystemConfig {
    private static Object conf;

    @Override
    public void before() {
    }

    @Override
    public void after() {
    }


    @Override
    public Map<String, String> writeProperties() {
        {
            String key = "system.servlet.port";
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, 8080 + "");
            }
        }
        {
            String key = "system.servlet.multipart.location";
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, (BaseEv.WorkDir.workHomeDir + FileUtil.FILE_SEPARATOR + "temp" + FileUtil.FILE_SEPARATOR + "001"));
            }
        }
        {
            String key = "system.servlet.multipart.max-file-size";
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, ("-1"));
            }
        }
        {
            String key = "system.servlet.multipart.max-request-size";
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, ("-1"));
            }
        }
        {
            String key = "system.servlet.multipart.file-size-threshold";
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, ("1"));
            }
        }
        {
            String key = "system.inputparamab.class";
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, "com.guoshiyao.rely.outgoing.InputParamRe");
            }
        }
        return new HashMap<>();
    }

    @Override
    public List<Class> writeClasss() {
        LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
        return Arrays.asList(new Class[]{SystemServletPort.class});
    }

}
