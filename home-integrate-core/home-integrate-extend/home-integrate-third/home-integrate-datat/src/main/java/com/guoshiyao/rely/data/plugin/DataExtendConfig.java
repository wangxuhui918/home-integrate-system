

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.data.plugin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import cn.hutool.setting.Setting;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.annotation.RuleInjection;
import com.guoshiyao.rely.core.utils.data.JdbcFind;
import com.guoshiyao.rely.coreextension.run.IThirdConfig;
import com.guoshiyao.rely.data.DataSourcesConfig;

import java.util.*;

@RuleInjection
public class DataExtendConfig implements IThirdConfig {
    public final static String NAME = "DATA_DURID";

    @Override
    public List<Class> writeClasss() {
        //第0 个数据库
        if ((JdbcFind.getProjectJdbc().size() > 0 || BaseEv.SettingInformation.setting.containsKey("home.db.driverclass.downloadurl")) && BaseEv.SettingInformation.setting.containsKey("home.db.url")) {
            return Arrays.asList(new Class[]{DataSourcesConfig.class});
        } else {
            return new ArrayList<>();
        }

    }

    @Override
    public Map<String, String> getProperties() {
        return new HashMap<>();
    }

    @Override
    public void before() {

    }

    @Override
    public void after() {
        if (BaseEv.SettingInformation.setting.containsKey("home.db.driverclass.downloadurl")) {
            String jdbcjarfilename = FileUtil.getName(BaseEv.SettingInformation.setting.get("home.db.driverclass.downloadurl").toString());
            String jar_full_path = BaseEv.WorkDir.workHomeDir + FileUtil.FILE_SEPARATOR + jdbcjarfilename;
            if (!FileUtil.exist(jar_full_path)) {//驱动文件不存在开始下载
                HttpUtil.downloadFile(BaseEv.SettingInformation.setting.get("home.db.driverclass.downloadurl").toString(), FileUtil.file(jar_full_path), new StreamProgress() {
                    @Override
                    public void start() {
                        Console.log("开始下载:{}", BaseEv.SettingInformation.setting.get("home.db.driverclass.downloadurl").toString());
                    }

                    @Override
                    public void progress(long progressSize) {
                        Console.log("已下载:{}", FileUtil.readableFileSize(progressSize));
                    }

                    @Override
                    public void finish() {
                        Console.log("下载完成:{}", BaseEv.SettingInformation.setting.get("home.db.driverclass.downloadurl").toString());
                    }
                });
            }
            BaseEv.WorkDir.main_jdbc_jar_fullpath = jar_full_path;

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
