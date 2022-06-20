

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.redis.plugin;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.setting.Setting;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;

import java.util.*;

@RuleAnnotation
public class RedisConfig implements ThirdExtendConfigAb {
    public final static String NAME = "redis";

//    private static final Map<String, String> thisproperties = new HashMap<String, String>();

    @Override
    public List<Class> writeClasss() {
        return new ArrayList<>();
    }


    @Override
    public Map<String, String> writeProperties() {
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
        for (String key : Line.setting.keySet()) {//筛选 redis 的内容
            if (StrUtil.startWith(key, "home.redis.")) {
                setting.putByGroup(key, Line.env.getName(), Line.setting.get(key));
//                thisproperties.put(StrUtil.sub(key, "home.redis.".length(), key.length()), Line.setting.get(key).getValue());
            }
        }
//        setting.putAll(Line.env.getName(), thisproperties);
        if (Line.setting.containsKey("home.redis.host")) {
            try {
                Line.redisds = RedisDS.create(setting, Line.env.getName());
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExceptionError("Redis 服务 {} 连接失败!如需关闭redis功能,请直接将home.redis.host重置为\"\"/null", Line.setting.get("home.redis.host"))
                        ;
            }
        }
    }


    @Override
    public void callSetting(Setting setting) {


    }

}
