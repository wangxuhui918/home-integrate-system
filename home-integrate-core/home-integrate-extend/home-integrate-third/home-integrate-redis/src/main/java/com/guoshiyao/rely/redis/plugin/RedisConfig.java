

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.redis.plugin;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.setting.Setting;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.coreextension.run.IThirdConfig;
import com.guoshiyao.rely.plugin.exception.re.ex.ExceptionError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RuleInjection
public class RedisConfig implements IThirdConfig {
    public final static String NAME = "redis";

//    private static final Map<String, String> thisproperties = new HashMap<String, String>();

    @Override
    public List<Class> writeClasss() {
        return new ArrayList<>();
    }


    @Override
    public Map<String, String> getProperties() {
        return new HashMap<>();
    }

    @Override
    public void before() {

    }

    /**
     * 后执行器,所有的值都跑完了
     */
    @Override
    public void after() {
        Setting setting = new Setting();
        //        Map<String, String> thisproperties = new HashMap<String, String>();
        for (String key : BaseEv.SettingInformation.setting.keySet()) {//筛选 redis 的内容
            if (StrUtil.startWith(key, "home.redis.")) {
                setting.putByGroup(key, BaseEv.SettingInformation.runEnv, BaseEv.SettingInformation.setting.get(key));
//                thisproperties.put(StrUtil.sub(key, "home.redis.".length(), key.length()), Line.setting.get(key).getValue());
            }
        }
//        setting.putAll(Line.env.getName(), thisproperties);
        if (BaseEv.SettingInformation.setting.containsKey("home.redis.host")) {
            try {
                BaseEv.SettingInformation.redisds = RedisDS.create(setting, BaseEv.SettingInformation.runEnv);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExceptionError("Redis 服务 {} 连接失败!如需关闭redis功能,请直接将home.redis.host重置为\"\"/null", BaseEv.SettingInformation.setting.get("home.redis.host"))
                        ;
            }
        }
    }


    @Override
    public void callSetting(Setting setting) {


    }

}
