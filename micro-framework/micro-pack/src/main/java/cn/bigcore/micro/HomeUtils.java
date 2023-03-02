

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro;

import cn.bigcore.micro.config.StartInit;
import cn.bigcore.micro.plugin.log.ILoggerBaseUtils;
import cn.bigcore.micro.core.ILineManager;
import cn.bigcore.micro.core.configration.utils.CoreConfUtils;

import java.util.List;

/**
 * 系统核心工具类
 *
 * @author 汪旭辉
 * @date 2021年12月10日
 * @readme
 */
public class HomeUtils {


    /**
     * 系统核心加载入口
     *
     * @author 汪旭辉
     * @date 2021年12月10日
     * @readme
     */
    public static void run() {
        ILoggerBaseUtils.info("[{}]开始加载", BaseEv.SystemInformation.SYSTEM_CHINA_NAME);
        StartInit.run();//注册表初始化
        List<ILineManager> plugins = CoreConfUtils.getPlugins(ILineManager.class);
        for (int i = 0; i < plugins.size(); i++) {
            ILoggerBaseUtils.info("总链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "before");
            plugins.get(i).before();
        }
        for (int i = 0; i < plugins.size(); i++) {
            ILoggerBaseUtils.info("总链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "start");
            plugins.get(i).start();
        }
        for (int i = 0; i < plugins.size(); i++) {
            ILoggerBaseUtils.info("总链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "after");
            plugins.get(i).after();
        }
        ILoggerBaseUtils.info("[{}]加载完成", BaseEv.SystemInformation.SYSTEM_CHINA_NAME);


    }
}
