


/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.apollo.utils;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.apollo.api.FyyLineApolloBean;
import cn.bigcore.micro.apollo.api.TreeInterface;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.http.HttpRequest;

import java.util.HashMap;

/**
 * 下载git仓库
 *
 * @author 汪旭辉
 * @date 2020年12月28日
 * @readme
 */
public class FyyLineApolloUtils {

    public static boolean login(String apolloUrl, String username, String password) {
        try {
            String cookieString = FyyLineApolloBean.getLoginCookie(apolloUrl, username, password);
            FyyLineApolloBean.logout(apolloUrl, cookieString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean createApollo(String appid, String desc, String apolloUrl, String username, String password,
                                       boolean delete) {
        String cookieString = "";
        cookieString = FyyLineApolloBean.getLoginCookie(apolloUrl, username, password);
        boolean isexist = FyyLineApolloBean.findNameSpaceExist(appid, apolloUrl, cookieString);
        if (!isexist) {//不存在,新建
        } else {
            try {
                if (delete) {
                    HttpRequest.delete(apolloUrl + "/apps/" + appid).cookie(cookieString).execute().body();
                }
            } catch (Exception e) {
            }
        }
        try {
            FyyLineApolloBean.createNameSpace(appid, desc, apolloUrl, cookieString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FyyLineApolloBean.logout(apolloUrl, cookieString);

        return true;
    }

    /**
     * 更新参数
     *
     * @param appid
     * @param desc
     * @param apolloUrl
     * @param username
     * @param password
     * @param properties
     * @return
     * @author 汪旭辉
     * @date 2021年6月24日
     * @readme
     */
    public static boolean updatePriperties(String appid, String desc, String apolloUrl, String username,
                                           String password, HashMap<String, HashMap<String, Tree<Object>>> properties,
                                           HashMap<String, HashMap<String, Tree<Object>>> targetproperties) {
        String cookieString = FyyLineApolloBean.getLoginCookie(apolloUrl, username, password);
        FyyLineApolloBean.updateOneProperties(appid, apolloUrl, cookieString, properties, targetproperties);
        FyyLineApolloBean.logout(apolloUrl, cookieString);

        return true;
    }

    /**
     * 发布
     *
     * @param appid
     * @param apolloUrl
     * @param username
     * @param password
     * @param properties
     * @author 汪旭辉
     * @date 2021年7月2日
     * @readme
     */
    public static void deploy(String appid, String apolloUrl, String username, String password,
                              HashMap<String, HashMap<String, Tree<Object>>> properties) {
        String cookieString = FyyLineApolloBean.getLoginCookie(apolloUrl, username, password);
        FyyLineApolloBean.deploy(appid, apolloUrl, properties.keySet(), cookieString);
        FyyLineApolloBean.logout(apolloUrl, cookieString);
    }

    /**
     * 更新参数
     *
     * @param appid
     * @param desc
     * @param apolloUrl
     * @param username
     * @param password
     * @return
     * @author 汪旭辉
     * @date 2021年6月24日
     * @readme
     */
    public static boolean updatePriperties(String appid, String desc, String apolloUrl, String username,
                                           String password, String ekey, Object evalue, String memo) {
        String cookieString = FyyLineApolloBean.getLoginCookie(apolloUrl, username, password);
        HashMap<String, HashMap<String, Tree<Object>>> properties = new HashMap<>();
        {//预处理参数
            HashMap<String, Tree<Object>> maps = new HashMap<>();
            Tree<Object> alueO = new Tree<>();
            alueO.setId(evalue);
            alueO.setName(memo);
            maps.put(ekey, alueO);
            for (String env : FyyInitEnv.configEnv) {
                properties.put(env, maps);
            }
            properties.put(FyyInitEnv.runEnv, maps);
        }
        FyyLineApolloBean.updateOneProperties(appid, apolloUrl, cookieString, properties, null);
        FyyLineApolloBean.logout(apolloUrl, cookieString);

        return true;
    }

    public static HashMap<String, HashMap<String, Tree<Object>>> getLineValues(String appid, String apolloUrl,
                                                                               String username, String password, TreeInterface treeinterface) {
        HashMap<String, HashMap<String, Tree<Object>>> values = new HashMap<>();
        String cookieString = FyyLineApolloBean.getLoginCookie(apolloUrl, username, password);
        try {
            FyyLineApolloBean.getPropertieLines(appid, apolloUrl, values, cookieString, treeinterface);
        } catch (Exception e) {
        }
        FyyLineApolloBean.logout(apolloUrl, cookieString);
        return values;
    }
}
