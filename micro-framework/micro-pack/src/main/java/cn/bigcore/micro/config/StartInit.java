

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.config;

import cn.bigcore.micro.config.baseenv.BaseEvBuiltIn;
import cn.bigcore.micro.config.core.project.ProjectBuiltInPropertiesImpl;
import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.config.core.home.CoreBuiltInImpl;
import cn.bigcore.micro.utils.ResourceFindUrl;

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

