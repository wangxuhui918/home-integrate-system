

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
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties.ConfigDetails;
import com.guoshiyao.rely.coreextension.run.ISystemConfig;
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
    public Map<String, String> getProperties() {
        {
            String key = ConfigDetails.SYSTEM_SERVLET_PORT.getKey();
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, 8080 + "");
            }
        }
        {
            String key = ConfigDetails.SYSTEM_SERVLET_MULTIPART_LOCATION.getKey();
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, (BaseEv.WorkDir.workHomeDir + FileUtil.FILE_SEPARATOR + "temp" + FileUtil.FILE_SEPARATOR + "001"));
            }
        }
        {
            String key = ConfigDetails.SYSTEM_SERVLET_MULTIPART_MAX_FILE_SIZE.getKey();
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, ("-1"));
            }
        }
        {
            String key = ConfigDetails.SYSTEM_SERVLET_MULTIPART_MAX_REQUEST_SIZE.getKey();
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, ("-1"));
            }
        }
        {
            String key = ConfigDetails.SYSTEM_SERVLET_MULTIPART_FILE_SIZE_THRESHOLD.getKey();
            if (!BaseEv.SettingInformation.setting.containsKey(key)) {
                BaseEv.SettingInformation.setting.put(key, ("1"));
            }
        }
        {
            String key = ConfigDetails.SYSTEM_INPUTPARAMAB_CLASS.getKey();
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
