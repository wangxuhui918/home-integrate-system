

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
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties.ConfigDetails;
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
        if ((JdbcFind.getProjectJdbc().size() > 0 || BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_DRIVERCLASS_DOWNLOADURL.getKey())) && BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_URL.getKey())) {
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
        if (BaseEv.SettingInformation.setting.containsKey(ConfigDetails.HOME_DB_DRIVERCLASS_DOWNLOADURL.getKey())) {
            String jdbcjarfilename = FileUtil.getName(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_DB_DRIVERCLASS_DOWNLOADURL.getKey()).toString());
            String jar_full_path = BaseEv.WorkDir.workHomeDir + FileUtil.FILE_SEPARATOR + jdbcjarfilename;
            if (!FileUtil.exist(jar_full_path)) {//驱动文件不存在开始下载
                HttpUtil.downloadFile(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_DB_DRIVERCLASS_DOWNLOADURL.getKey()).toString(), FileUtil.file(jar_full_path), new StreamProgress() {
                    @Override
                    public void start() {
                        Console.log("开始下载:{}", BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_DB_DRIVERCLASS_DOWNLOADURL.getKey()).toString());
                    }

                    @Override
                    public void progress(long progressSize) {
                        Console.log("已下载:{}", FileUtil.readableFileSize(progressSize));
                    }

                    @Override
                    public void finish() {
                        Console.log("下载完成:{}", BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_DB_DRIVERCLASS_DOWNLOADURL.getKey()).toString());
                    }
                });
            }
            BaseEv.WorkDir.main_jdbc_jar_fullpath = jar_full_path;

        }
    }

    @Override
    public void callSetting(Setting setting) {

//        {
//            String key = ConfigDetails.HOME_DB_FILTERS.getKey();
//            if (!setting.containsKey(key)) {
//                setting.put(key, ConfigDetails.HOME_DB_FILTERS.getValue());
//            }
//        }

    }

}
