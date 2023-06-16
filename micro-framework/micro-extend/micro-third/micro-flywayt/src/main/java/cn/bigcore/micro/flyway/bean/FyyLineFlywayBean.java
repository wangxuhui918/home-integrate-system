

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.flyway.bean;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;

public class FyyLineFlywayBean {

    @Bean
    public Flyway initBean() {
//        System.setProperty("spring.flyway.enabled", "true");
//        System.setProperty("spring.flyway.baseline-on-migrate", "true");
//        System.setProperty("spring.flyway.out-of-order", "true");
//        System.setProperty("spring.flyway.validate-on-migrate", "false");
        String dbUrl = FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_FLYWAYDB_URL.getKey());
        String dbUserName = FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_FLYWAYDB_USERNAME.getKey());
        String dbPassword = FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_FLYWAYDB_PASSWORD.getKey());
        String table = FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_FLYWAYDB_TABLE.getKey());
        //flyway_schema_history
        // 创建Flyway实例
        Flyway flyway = Flyway.configure().dataSource(dbUrl, dbUserName, dbPassword).table(table).load();
        flyway.baseline();
        // 开始更新管理
        flyway.repair();
        flyway.migrate();
        return flyway;
    }

}
