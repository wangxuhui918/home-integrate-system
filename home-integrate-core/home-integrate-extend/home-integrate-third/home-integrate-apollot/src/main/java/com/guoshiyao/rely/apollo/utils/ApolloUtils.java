


/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.apollo.utils;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.http.HttpRequest;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.apollo.api.ApolloApi;
import com.guoshiyao.rely.apollo.api.TreeInterface;

import java.util.HashMap;

/**
 * 下载git仓库
 *
 * @author 汪旭辉
 * @date 2020年12月28日
 * @readme
 */
public class ApolloUtils {

    public static boolean login(String apolloUrl, String username, String password) {
        try {
            String cookieString = ApolloApi.getLoginCookie(apolloUrl, username, password);
            ApolloApi.logout(apolloUrl, cookieString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean createApollo(String appid, String desc, String apolloUrl, String username, String password,
                                       boolean delete) {
        String cookieString = "";
        cookieString = ApolloApi.getLoginCookie(apolloUrl, username, password);
        boolean isexist = ApolloApi.findNameSpaceExist(appid, apolloUrl, cookieString);
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
            ApolloApi.createNameSpace(appid, desc, apolloUrl, cookieString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ApolloApi.logout(apolloUrl, cookieString);

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
        String cookieString = ApolloApi.getLoginCookie(apolloUrl, username, password);
        ApolloApi.updateOneProperties(appid, apolloUrl, cookieString, properties, targetproperties);
        ApolloApi.logout(apolloUrl, cookieString);

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
        String cookieString = ApolloApi.getLoginCookie(apolloUrl, username, password);
        ApolloApi.deploy(appid, apolloUrl, properties.keySet(), cookieString);
        ApolloApi.logout(apolloUrl, cookieString);
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
        String cookieString = ApolloApi.getLoginCookie(apolloUrl, username, password);
        HashMap<String, HashMap<String, Tree<Object>>> properties = new HashMap<>();
        {//预处理参数
            HashMap<String, Tree<Object>> maps = new HashMap<>();
            Tree<Object> alueO = new Tree<>();
            alueO.setId(evalue);
            alueO.setName(memo);
            maps.put(ekey, alueO);
            for (String env : BaseEv.SettingInformation.configEnv) {
                properties.put(env, maps);
            }
            properties.put(BaseEv.SettingInformation.runEnv, maps);
        }
        ApolloApi.updateOneProperties(appid, apolloUrl, cookieString, properties, null);
        ApolloApi.logout(apolloUrl, cookieString);

        return true;
    }

    public static HashMap<String, HashMap<String, Tree<Object>>> getLineValues(String appid, String apolloUrl,
                                                                               String username, String password, TreeInterface treeinterface) {
        HashMap<String, HashMap<String, Tree<Object>>> values = new HashMap<>();
        String cookieString = ApolloApi.getLoginCookie(apolloUrl, username, password);
        try {
            ApolloApi.getPropertieLines(appid, apolloUrl, values, cookieString, treeinterface);
        } catch (Exception e) {
        }
        ApolloApi.logout(apolloUrl, cookieString);
        return values;
    }
}
