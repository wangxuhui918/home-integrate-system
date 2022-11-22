

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely;

import com.guoshiyao.rely.core.ILineManager;
import com.guoshiyao.rely.core.configration.utils.CoreConfUtils;
import com.guoshiyao.rely.plugin.log.ILoggerBaseUtils;

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

        List<ILineManager> plugins = CoreConfUtils.sortByDbOrRuleApi(ILineManager.class);
        ///处理mac地址完成
        for (int i = 0; i < plugins.size(); i++) {
            ILoggerBaseUtils.info("链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "before");
            plugins.get(i).before();
            ILoggerBaseUtils.info("链式处理器[{}]处理完成[{}]", plugins.get(i).getClass(), "before");
        }
        for (int i = 0; i < plugins.size(); i++) {
            ILoggerBaseUtils.info("链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "start");
            plugins.get(i).start();
            ILoggerBaseUtils.info("链式处理器[{}]处理完成[{}]", plugins.get(i).getClass(), "start");
        }

        for (int i = 0; i < plugins.size(); i++) {
            ILoggerBaseUtils.info("链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "after");
            plugins.get(i).after();
            ILoggerBaseUtils.info("链式处理器[{}]处理完成[{}]", plugins.get(i).getClass(), "after");
        }

        ILoggerBaseUtils.info("[{}]加载完成", BaseEv.SystemInformation.SYSTEM_CHINA_NAME);


    }
}
