

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.message;

import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.message.ab.MessageConfigAb;
import com.guoshiyao.rely.message.utils.NodeUtils;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RuleAnnotation
public class MessageExtendConfigRe implements MessageConfigAb {

    @Override
    public void after() {

    }

    @Override
    public void before() {
        HashMap<String, HashMap<String, List<String>>> maps = new HashMap<>();
        String readString = ProjectCoreConfUtils.getZoneMessageFileContext();
        NodeUtils.getI18nMessageContext(readString, Line.i18n, maps);
        try {
            Map<String, String> othermessage = ProjectCoreConfUtils.getOtherMessageFileContext();
            for (String key : othermessage.keySet()) {
                NodeUtils.getI18nMessageContext(othermessage.get(key), key, maps);
            }
        } catch (Exception e) {

        }
        Line.messages.putAll(maps);
    }

    @Override
    public String getName() {
        return "全文消息码管理器";
    }

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        return new LinkedHashMap<>();
    }

    @Override
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
        return new LinkedHashMap<>();
    }

    @Override
    public void callProperties(PropertiesMap<String, LinePropertiesAb> properties) {
    }

}
