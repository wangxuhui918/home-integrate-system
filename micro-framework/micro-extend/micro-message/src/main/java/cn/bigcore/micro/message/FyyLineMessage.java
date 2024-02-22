

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.message;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.annotation.type.FyyRuleInjection;
import cn.bigcore.micro.base.message.FyyMessageCode;
import cn.bigcore.micro.line.FyyLineMessageInterface;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.bigcore.micro.message.utils.FyyNodeUtils;
import cn.bigcore.micro.utils.FyyConfigProjectUtils;
import cn.hutool.setting.Setting;

import java.util.*;

@FyyRuleInjection
public class FyyLineMessage implements FyyLineMessageInterface {

    @Override
    public void after() {

    }

    @Override
    public void before() {
        HashMap<String, HashMap<String, FyyMessageCode>> maps = new HashMap<>();
        String readString = FyyConfigProjectUtils.getZoneMessageFileContext();
        FyyNodeUtils.getI18nMessageContext(readString, FyyInitEnv.ProjectInformation.i18n, maps);
        try {
            Map<String, String> othermessage = FyyConfigProjectUtils.getAllMessageXmlContexts();
            for (String key : othermessage.keySet()) {
                FyyNodeUtils.getI18nMessageContext(othermessage.get(key), key, maps);
                FyyLogBaseUtils.debug(FyyLineMessage.class, "读取到{}文件中的消息码,共计{}条", key, othermessage.size() + "");
            }
        } catch (Exception e) {

        }
        FyyInitEnv.ProjectInformation.messages.putAll(maps);
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
