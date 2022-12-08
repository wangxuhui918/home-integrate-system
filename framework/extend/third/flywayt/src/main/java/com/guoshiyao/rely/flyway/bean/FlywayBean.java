

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.flyway.bean;

import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.home.impl.bean.ConfigDetails;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;

public class FlywayBean {

    @Bean
    public Flyway initBean() {
//        System.setProperty("spring.flyway.enabled", "true");
//        System.setProperty("spring.flyway.baseline-on-migrate", "true");
//        System.setProperty("spring.flyway.out-of-order", "true");
//        System.setProperty("spring.flyway.validate-on-migrate", "false");
        String dbUrl = BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_FLYWAYDB_URL.getKey());
        String dbUserName = BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_FLYWAYDB_USERNAME.getKey());
        String dbPassword = BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_FLYWAYDB_PASSWORD.getKey());
        String table = BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_FLYWAYDB_TABLE.getKey());
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
