

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.flyway.plugin;

import cn.bigcore.micro.flyway.bean.FyyLineFlywayBean;
import cn.hutool.core.io.FileUtil;
import cn.hutool.setting.Setting;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.annotation.FyyRuleInjection;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import cn.bigcore.micro.line.FyyLineThirdExtendInterface;

import java.util.*;

@FyyRuleInjection
public class FyyLineFlyway implements FyyLineThirdExtendInterface {
    public final static String filep = "db" + FileUtil.FILE_SEPARATOR + "migration" + FileUtil.FILE_SEPARATOR + "**" + FileUtil.FILE_SEPARATOR + "**.sql";

    @Override
    public void after() {
    }

    @Override
    public void before() {

    }


    @Override
    public List<Class> writeClasss() {
//        try {
//            ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
//            Resource[] source = resourceLoader.getResources(filep);
//            if (source.length == 0) {
//                return new ArrayList<>();
//            }
//            if (!BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_FLYWAYDB_URL.getKey())) {
//                return new ArrayList<>();
//            }
//        } catch (Exception e) {
//            return new ArrayList<>();
//        }
        if (FyyInitEnv.SettingInformation.setting.getBool(FyyConfigEntryDetailsValues.FLYWAYDB_ENABLE.getKey())) {
            return Arrays.asList(new Class[]{FyyLineFlywayBean.class});
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public Map<String, String> getProperties() {
        return new HashMap<>();
    }

    @Override
    public void callSetting(Setting setting) {
        {
            String key = FyyConfigEntryDetailsValues.HOME_FLYWAYDB_URL.getKey();
            if (!setting.containsKey(key)) {
                if (FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_URL.getKey()) != null)
                    setting.put(key, (FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_URL.getKey())));
            }
        }
        {
            String key = FyyConfigEntryDetailsValues.HOME_FLYWAYDB_USERNAME.getKey();
            if (!setting.containsKey(key)) {
                if (FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_USERNAME.getKey()) != null)
                    setting.put(key, (FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_USERNAME.getKey())));
            }
        }
        {
            String key = FyyConfigEntryDetailsValues.HOME_FLYWAYDB_PASSWORD.getKey();
            if (!setting.containsKey(key)) {
                if (FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_PASSWORD.getKey()) != null)
                    setting.put(key, (FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_DB_PASSWORD.getKey())));
            }
        }

    }

}
