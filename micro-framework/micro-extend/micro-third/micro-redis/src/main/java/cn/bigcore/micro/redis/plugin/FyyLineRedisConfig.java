

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.redis.plugin;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.line.FyyLineThirdExtendInterface;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.setting.Setting;
import cn.bigcore.micro.config.annotation.FyyRuleInjection;
import cn.bigcore.micro.exception.re.ex.FyyExceptionError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FyyRuleInjection
public class FyyLineRedisConfig implements FyyLineThirdExtendInterface {
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
        if (FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.REDIS_ENABLE.getKey())) {
            Setting setting = new Setting();
            for (String key : FyyInitEnv.SettingInformation.setting.keySet()) {//筛选 redis 的内容
                if (StrUtil.startWith(key, StrUtil.sub(FyyConfigEntryDetailsValues.HOME_REDIS_HOST.getKey(), 0, StrUtil.ordinalIndexOf(FyyConfigEntryDetailsValues.HOME_REDIS_HOST.getKey(), ".", 1)))) {
                    setting.putByGroup(key, FyyInitEnv.SettingInformation.runEnv, FyyInitEnv.SettingInformation.setting.get(key));
                }
            }
            if (FyyInitEnv.SettingInformation.setting.containsKey(FyyConfigEntryDetailsValues.HOME_REDIS_HOST.getKey())) {
                try {
                    FyyInitEnv.SettingInformation.redisds = RedisDS.create(setting, FyyInitEnv.SettingInformation.runEnv);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new FyyExceptionError("Redis 服务 {} 连接失败!如需关闭redis功能,请直接将redis.host重置为\"\"/null", FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_REDIS_HOST.getKey()));
                }
            }
        }

    }


    @Override
    public void callSetting(Setting setting) {


    }

}
