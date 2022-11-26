

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
import com.guoshiyao.rely.message.utils.NodeUtils;
import com.guoshiyao.rely.core.configration.utils.ProjectConfUtils;
import com.guoshiyao.rely.coreextension.third.IMessageConfig;
import com.guoshiyao.rely.plugin.exception.code.bean.MessageCodeVo;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;

import java.util.*;

@RuleInjection
public class MessageExtendConfig implements IMessageConfig {

    @Override
    public void after() {

    }

    @Override
    public void before() {
        HashMap<String, HashMap<String, MessageCodeVo>> maps = new HashMap<>();
        String readString = ProjectConfUtils.getZoneMessageFileContext();
        NodeUtils.getI18nMessageContext(readString, BaseEv.SettingInformation.i18n, maps);
        try {
            Map<String, String> othermessage = ProjectConfUtils.getAllMessageXmlContexts();
            for (String key : othermessage.keySet()) {
                NodeUtils.getI18nMessageContext(othermessage.get(key), key, maps);
                ILoggerBaseUtils.debug(MessageExtendConfig.class, "读取到{}文件中的消息码,共计{}条", key, othermessage.size() + "");
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
    public Map<String, String> getProperties() {
        return new LinkedHashMap<>();
    }

    @Override
    public void callSetting(Setting setting) {
    }

}
