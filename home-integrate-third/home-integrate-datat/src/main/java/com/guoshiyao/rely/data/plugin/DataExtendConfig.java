

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.data.plugin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import cn.hutool.setting.Setting;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.data.DataSourcesConfig;
import com.guoshiyao.rely.line.Line;

import com.guoshiyao.rely.third.ThirdExtendConfigAb;
import com.guoshiyao.rely.tools.system.JdbcFind;

import java.io.File;
import java.util.*;

@RuleAnnotation
public class DataExtendConfig implements ThirdExtendConfigAb {
    public final static String NAME = "DATA_DURID";

    @Override
    public LinkedHashMap<String, List<Class>> writeClasss() {
        //第0 个数据库
        if ((JdbcFind.getProjectJdbc().size() > 0 || Line.setting.containsKey("home.db.driverclass.downloadurl")) && Line.setting.containsKey("home.db.url")) {
            LinkedHashMap<String, List<Class>> map = new LinkedHashMap<>();
            map.put(NAME, Arrays.asList(new Class[]{DataSourcesConfig.class}));
            return map;
        } else {
            return new LinkedHashMap<>();
        }

    }

    @Override
    public Map<String, String> writeProperties() {
        return new HashMap<>();
    }

    @Override
    public void before() {

    }

    @Override
    public void after() {
        if (Line.setting.containsKey("home.db.driverclass.downloadurl")) {
            String jdbcjarfilename = FileUtil.getName(Line.setting.get("home.db.driverclass.downloadurl").toString());
            String jar_full_path = Line.workHomeDir + File.separator + jdbcjarfilename;
            if (!FileUtil.exist(jar_full_path)) {//驱动文件不存在开始下载
                HttpUtil.downloadFile(Line.setting.get("home.db.driverclass.downloadurl").toString(), FileUtil.file(jar_full_path), new StreamProgress() {
                    @Override
                    public void start() {
                        Console.log("开始下载:{}", Line.setting.get("home.db.driverclass.downloadurl").toString());
                    }

                    @Override
                    public void progress(long progressSize) {
                        Console.log("已下载:{}", FileUtil.readableFileSize(progressSize));
                    }

                    @Override
                    public void finish() {
                        Console.log("下载完成:{}", Line.setting.get("home.db.driverclass.downloadurl").toString());
                    }
                });
            }
            Line.main_jdbc_jar_fullpath = jar_full_path;

        }
    }

    @Override
    public void callSetting(Setting setting) {
        {
            String key = "home.db.url";
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
        {
            String key = "home.db.username";
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
        {
            String key = "home.db.password";
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
        {
            String key = "home.db.driverclassname";
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
        {
            String key = "home.db.driverclass.downloadurl";
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
        {
            String key = "home.db.maxactive";
            if (!setting.containsKey(key)) {
                setting.put(key, ("30"));
            }
        }
        {
            String key = "home.db.initialsize";
            if (!setting.containsKey(key)) {
                setting.put(key, ("1"));
            }
        }
        {
            String key = "home.db.maxwait";
            if (!setting.containsKey(key)) {
                setting.put(key, ("60000"));
            }
        }
        {
            String key = "home.db.minidle";
            if (!setting.containsKey(key)) {
                setting.put(key, ("3"));
            }
        }
        {
            String key = "home.db.timebetweenevictionrunsmillis";
            if (!setting.containsKey(key)) {
                setting.put(key, ("60000"));
            }
        }
        {
            String key = "home.db.minevictableidletimemillis";
            if (!setting.containsKey(key)) {
                setting.put(key, ("300000"));
            }
        }
        {
            String key = "home.db.validationquery";
            if (!setting.containsKey(key)) {
                setting.put(key, (""));
            }
        }
        {
            String key = "home.db.testwhileidle";
            if (!setting.containsKey(key)) {
                setting.put(key, ("true"));
            }
        }
        {
            String key = "home.db.testonborrow";
            if (!setting.containsKey(key)) {
                setting.put(key, ("false"));
            }
        }
        {
            String key = "home.db.testonreturn";
            if (!setting.containsKey(key)) {
                setting.put(key, ("false"));
            }
        }
        {
            String key = "home.db.poolpreparedstatements";
            if (!setting.containsKey(key)) {
                setting.put(key, ("true"));
            }
        }
        {
            String key = "home.db.removeabandoned";
            if (!setting.containsKey(key)) {
                setting.put(key, ("true"));
            }
        }
        {
            String key = "home.db.removeabandonedtimeout";
            if (!setting.containsKey(key)) {
                setting.put(key, ("60"));
            }
        }
        {
            String key = "home.db.numtestsperevictionrun";
            if (!setting.containsKey(key)) {
                setting.put(key, ("60"));
            }
        }
        {
            String key = "home.db.maxopenpreparedstatements";
            if (!setting.containsKey(key)) {
                setting.put(key, ("20"));
            }
        }
        {
            String key = "home.db.filters";
            if (!setting.containsKey(key)) {
                setting.put(key, ("stat,wall"));
            }
        }

    }

}
