

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.flyway.plugin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.setting.Setting;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.annotation.RuleInjection;
import com.guoshiyao.rely.coreextension.IThirdExtendConfig;
import com.guoshiyao.rely.flyway.bean.FlywayBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.*;

@RuleInjection
public class FlywayExtendConfig implements IThirdExtendConfig {
    public final static String filep = "db" + FileUtil.FILE_SEPARATOR + "migration" + FileUtil.FILE_SEPARATOR + "**" + FileUtil.FILE_SEPARATOR
            + "**.sql";

    @Override
    public void after() {
    }

    @Override
    public void before() {

    }


    @Override
    public List<Class> writeClasss() {
        try {
            ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
            Resource[] source = resourceLoader.getResources(filep);
            if (source.length == 0) {
                return new ArrayList<>();
            }
            if (!BaseEv.SettingInformation.setting.containsKey("home.flywaydb.url")) {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
        return Arrays.asList(new Class[]{FlywayBean.class});
    }

    @Override
    public Map<String, String> writeProperties() {
        return new HashMap<>();
    }

    @Override
    public void callSetting(Setting setting) {
        {
            String key = "home.flywaydb.url";
            if (!setting.containsKey(key)) {
                if (BaseEv.SettingInformation.setting.get("home.db.url") != null)
                    setting.put(key, (BaseEv.SettingInformation.setting.get("home.db.url")));
            }
        }
        {
            String key = "home.flywaydb.username";
            if (!setting.containsKey(key)) {
                if (BaseEv.SettingInformation.setting.get("home.db.username") != null)
                    setting.put(key, (BaseEv.SettingInformation.setting.get("home.db.username")));
            }
        }
        {
            String key = "home.flywaydb.password";
            if (!setting.containsKey(key)) {
                if (BaseEv.SettingInformation.setting.get("home.db.password") != null)
                    setting.put(key, (BaseEv.SettingInformation.setting.get("home.db.password")));
            }
        }
        {
            String key = "home.flywaydb.table";
            if (!setting.containsKey(key)) {
                if (BaseEv.SettingInformation.setting.get("home.db.table") != null)
                    setting.put(key, ("flyway_schema_history"));
            }
        }
    }

}
