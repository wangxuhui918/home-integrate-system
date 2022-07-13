

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.run;

import com.guoshiyao.rely.coreconf.utils.HomeCoreConfUtils;
import com.guoshiyao.rely.line.LineAb;

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
        List<LineAb> plugins = HomeCoreConfUtils.sortByDbOrRuleApi(LineAb.class);
        ///处理mac地址完成
        for (int i = 0; i < plugins.size(); i++) {
            plugins.get(i).before();
        }
        for (int i = 0; i < plugins.size(); i++) {
            plugins.get(i).start();
        }
        for (int i = 0; i < plugins.size(); i++) {
            plugins.get(i).after();
        }
    }
}
