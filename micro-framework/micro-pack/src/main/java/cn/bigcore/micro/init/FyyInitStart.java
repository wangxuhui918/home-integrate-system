

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.init;

import cn.bigcore.micro.config.FyyConfigProjectPropertiesImpl;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.FyyConfigFrameImpl;
import cn.bigcore.micro.utils.FyyResource;

public class FyyInitStart {

    public static void run() {

        //
        FyyInitEnv.SettingInformation.resourcetool = new FyyResource();
        FyyInitEnv.SettingInformation.baseEv = new FyyInitEnvLoad();
        FyyInitEnv.SettingInformation.homeConf = new FyyConfigFrameImpl();
        FyyInitEnv.SettingInformation.projectConf = new FyyConfigProjectPropertiesImpl();
        FyyInitEnv.SettingInformation.init();
    }
}

