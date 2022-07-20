

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.run;

import com.guoshiyao.rely.base.BaseEv;
import com.guoshiyao.rely.coreconf.utils.HomeCoreConfUtils;
import com.guoshiyao.rely.line.LineAb;
import com.guoshiyao.rely.log.base.LoggerBaseAb;

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
        LoggerBaseAb.info("[{}]开始加载", BaseEv.HOME_NAME);

        List<LineAb> plugins = HomeCoreConfUtils.sortByDbOrRuleApi(LineAb.class);
        ///处理mac地址完成
        for (int i = 0; i < plugins.size(); i++) {
            LoggerBaseAb.info("链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "before");
            plugins.get(i).before();
            LoggerBaseAb.info("链式处理器[{}]处理完成[{}]", plugins.get(i).getClass(), "before");
        }
        for (int i = 0; i < plugins.size(); i++) {
            LoggerBaseAb.info("链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "start");
            plugins.get(i).start();
            LoggerBaseAb.info("链式处理器[{}]处理完成[{}]", plugins.get(i).getClass(), "start");
        }
        for (int i = 0; i < plugins.size(); i++) {
            LoggerBaseAb.info("链式处理器[{}]开始处理[{}]", plugins.get(i).getClass(), "after");
            plugins.get(i).after();
            LoggerBaseAb.info("链式处理器[{}]处理完成[{}]", plugins.get(i).getClass(), "after");
        }

        LoggerBaseAb.info("[{}]加载完成", BaseEv.HOME_NAME);

    }
}
