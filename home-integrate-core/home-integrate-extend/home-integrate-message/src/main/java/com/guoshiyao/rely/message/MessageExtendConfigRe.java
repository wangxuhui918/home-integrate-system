

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.message;

import cn.hutool.setting.Setting;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.utils.conf.ProjectConfUtils;
import com.guoshiyao.rely.core.utils.node.NodeUtils;
import com.guoshiyao.rely.coreextension.IMessageConfig;

import java.util.*;

@RuleInjection
public class MessageExtendConfigRe implements IMessageConfig {

    @Override
    public void after() {

    }

    @Override
    public void before() {
        HashMap<String, HashMap<String, List<String>>> maps = new HashMap<>();
        String readString = ProjectConfUtils.getZoneMessageFileContext();
        NodeUtils.getI18nMessageContext(readString, BaseEv.SettingInformation.i18n, maps);
        try {
            Map<String, String> othermessage = ProjectConfUtils.getOtherMessageFileContext();
            for (String key : othermessage.keySet()) {
                NodeUtils.getI18nMessageContext(othermessage.get(key), key, maps);
            }
        } catch (Exception e) {

        }
        BaseEv.SettingInformation.messages.putAll(maps);
    }

    @Override
    public List<Class> writeClasss() {
        return new ArrayList<>();
    }

    @Override
    public Map<String, String> writeProperties() {
        return new LinkedHashMap<>();
    }

    @Override
    public void callSetting(Setting setting) {
    }

}
