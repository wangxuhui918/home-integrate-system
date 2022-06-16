

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
import com.guoshiyao.rely.coreconf.utils.ProjectCoreConfUtils;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.line.ab.re.LinePropertiesAb;
import com.guoshiyao.rely.line.propertiesmap.PropertiesMap;
import com.guoshiyao.rely.third.ThirdExtendConfigAb;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RuleAnnotation
public class RedisConfig implements ThirdExtendConfigAb {
    public final static String NAME = "redis";

    private static final Map<String, String> thisproperties = new HashMap<String, String>();

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        return new LinkedHashMap<>();
    }


    @Override
    public HashMap<String, PropertiesMap<String, LinePropertiesAb>> writeProperties() {
        HashMap<String, String> params = ProjectCoreConfUtils.getEnvPropertiesByCode("1000012");
        for (String key : params.keySet()) {//筛选 redis 的内容
            if (StrUtil.startWith(key, "home.redis.")) {
                thisproperties.put(StrUtil.sub(key, "home.redis.".length(), key.length()), params.get(key));
            }
        }
        HashMap<String, PropertiesMap<String, LinePropertiesAb>> map = new HashMap<>();
        map.put(NAME, LinePropertiesAb.convertLineProperties(params));
        return map;
    }

    @Override
    public String getName() {
        return NAME;
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
        setting.putAll(Line.env.getName(), thisproperties);
        if (!Line.properties.get("home.redis.host").isBlank()) {
            try {
                Line.redisds = RedisDS.create(setting, Line.env.getName());
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExceptionError("Redis 服务 {} 连接失败!如需关闭redis功能,请直接将home.redis.host重置为\"\"/null", Line.properties.get("home.redis.host").getString());
            }
        }
    }


    @Override
    public void callProperties(PropertiesMap<String, LinePropertiesAb> properties) {


    }

}
