

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.config;

import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.config.baseenv.BaseEvBuiltIn;
import com.guoshiyao.rely.config.core.home.CoreBuiltInImpl;
import com.guoshiyao.rely.config.core.project.ProjectBuiltInImpl;
import com.guoshiyao.rely.config.core.project.ProjectBuiltInPropertiesImpl;
import com.guoshiyao.rely.log.utils.LoggerUtil;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;
import com.guoshiyao.rely.snowflake.utils.IdUtils;
import com.guoshiyao.rely.utils.ResourceFindUrl;

public class StartInit {

    public static void run() {

        //
        BaseEv.SettingInformation.resourcetool = new ResourceFindUrl();
        BaseEv.SettingInformation.baseEv = new BaseEvBuiltIn();
        BaseEv.SettingInformation.homeConf = new CoreBuiltInImpl();
        BaseEv.SettingInformation.projectConf = new ProjectBuiltInPropertiesImpl();
        BaseEv.SettingInformation.init();
    }
}

