

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.flyway.bean;

import com.guoshiyao.rely.line.Line;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;

public class FlywayBean {

    @Bean
    public Flyway initBean() {
//        System.setProperty("spring.flyway.enabled", "true");
//        System.setProperty("spring.flyway.baseline-on-migrate", "true");
//        System.setProperty("spring.flyway.out-of-order", "true");
//        System.setProperty("spring.flyway.validate-on-migrate", "false");
        String dbUrl = Line.properties.get("home.flywaydb.url").getString();
        String dbUserName = Line.properties.get("home.flywaydb.username").getString();
        String dbPassword = Line.properties.get("home.flywaydb.password").getString();
        String table = Line.properties.get("home.flywaydb.table").getString();
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
