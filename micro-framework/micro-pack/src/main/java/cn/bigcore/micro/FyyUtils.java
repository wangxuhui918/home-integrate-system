

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro;

import cn.bigcore.micro.init.FyyInitStart;
import cn.bigcore.micro.log.FyyLogBaseUtils;
import cn.bigcore.micro.line.base.FyyLineManagerInterface;
import cn.bigcore.micro.utils.FyyConfigFrameUtils;

import java.util.List;

/**
 * 系统核心工具类
 *
 * @author 汪旭辉
 * @date 2021年12月10日
 * @readme
 */
public class FyyUtils {


    /**
     * 系统核心加载入口
     *
     * @author 汪旭辉
     * @date 2021年12月10日
     * @readme
     */
    public static void run() {
        FyyLogBaseUtils.info("[{}]开始加载", FyyProperties.setting.get("fyy.system.name.zh"));
        FyyInitStart.run();//注册表初始化
        List<FyyLineManagerInterface> plugins = FyyConfigFrameUtils.getPlugins(FyyLineManagerInterface.class);
        for (int i = 0; i < plugins.size(); i++) {
            FyyLogBaseUtils.info("总链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "before");
            plugins.get(i).before();
        }
        for (int i = 0; i < plugins.size(); i++) {
            FyyLogBaseUtils.info("总链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "start");
            plugins.get(i).start();
        }
        for (int i = 0; i < plugins.size(); i++) {
            FyyLogBaseUtils.info("总链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "after");
            plugins.get(i).after();
        }
        FyyLogBaseUtils.info("[{}]加载完成", FyyProperties.setting.get("fyy.system.name.zh"));


    }
}
