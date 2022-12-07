

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
import com.guoshiyao.rely.core.configration.home.impl.bean.ConfigDetails;
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
        if (BaseEv.SettingInformation.setting.getBool(ConfigDetails.REDIS_ENABLE.getKey())) {
            Setting setting = new Setting();
            for (String key : BaseEv.SettingInformation.setting.keySet()) {//筛选 redis 的内容
                if (StrUtil.startWith(key, StrUtil.sub(ConfigDetails.HOME_REDIS_HOST.getKey(), 0, StrUtil.ordinalIndexOf(ConfigDetails.HOME_REDIS_HOST.getKey(), ".", 1)))) {
                    setting.putByGroup(key, BaseEv.SettingInformation.runEnv, BaseEv.SettingInformation.setting.get(key));
                }
            }
            if (BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_REDIS_HOST.getKey())) {
                try {
                    BaseEv.SettingInformation.redisds = RedisDS.create(setting, BaseEv.SettingInformation.runEnv);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ExceptionError("Redis 服务 {} 连接失败!如需关闭redis功能,请直接将redis.host重置为\"\"/null", BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_REDIS_HOST.getKey()));
                }
            }
        }

    }


    @Override
    public void callSetting(Setting setting) {


    }

}
