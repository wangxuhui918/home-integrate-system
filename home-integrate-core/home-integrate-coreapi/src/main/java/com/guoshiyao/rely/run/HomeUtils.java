

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.run;

import com.guoshiyao.rely.coreconf.utils.HomeCoreConfUtils;
import com.guoshiyao.rely.environment.ENV;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.line.LineAb;

import java.util.List;
import java.util.logging.Level;

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
     * @param idkey
     * @param i18n
     * @param projectPackage
     * @param mainClass
     * @param env
     * @param loglevel
     * @author 汪旭辉
     * @date 2021年12月10日
     * @readme
     */
    public static void run(String idkey, String i18n, String projectPackage, String mainClass, ENV env, Level loglevel, boolean updateProperties) {
        {
            List<LineAb> plugins = HomeCoreConfUtils.sortByDbOrRuleApi(LineAb.class);

            Line.init(mainClass, i18n, projectPackage, idkey, env, loglevel, updateProperties);
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
}
