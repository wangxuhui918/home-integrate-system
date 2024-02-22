

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
import cn.bigcore.micro.outgoing.impl.FyyOutRe;
import cn.bigcore.micro.utils.FyyResource;

public class FyyInitStart {

    public static void run() {

        //
        FyyInitEnv.ProjectInformation.fyyMessageDataOutInterface = new FyyOutRe();
        FyyInitEnv.ProjectInformation.resourcetool = new FyyResource();
        FyyInitEnv.ProjectInformation.baseEv = new FyyInitEnvLoad();
        FyyInitEnv.ProjectInformation.homeConf = new FyyConfigFrameImpl();
        FyyInitEnv.ProjectInformation.projectConf = new FyyConfigProjectPropertiesImpl();
        FyyInitEnv.ProjectInformation.init();
    }
}

