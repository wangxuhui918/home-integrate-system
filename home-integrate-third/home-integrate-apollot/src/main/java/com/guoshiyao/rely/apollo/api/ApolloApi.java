


/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.apollo.api;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.line.Line;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ApolloApi {

    /**
     * 登陆并获取cookie
     *
     * @param apolloUrl
     * @param username
     * @param password
     * @return
     * @author 汪旭辉
     * @date 2021年7月2日
     * @readme
     */
    public static String getLoginCookie(String apolloUrl, String username, String password) {
        String targetCookieString;
        HashMap<String, Object> on = new HashMap<>();
        on.put("username", username);
        on.put("password", password);
        on.put("login-submit:", "登录");
        cn.hutool.http.HttpResponse http = HttpUtil.createPost(apolloUrl + "/signin").form(on).execute();
        targetCookieString = http.getCookies().get(0).toString() + "; JSESSIONID=" + http.getCookieValue("JSESSIONID");
        return targetCookieString;
    }

    /**
     * 退出登陆
     *
     * @param appid
     * @param apolloUrl
     * @param cookieString
     * @author 汪旭辉
     * @date 2021年7月2日
     * @readme
     */
    public static void logout(String apolloUrl, String cookieString) {
        try {
            System.out.println("注销结果如果是302表示成功");
            HttpResponse body = HttpRequest.get(apolloUrl + "/user/logout").cookie(cookieString).execute();
            System.out.println(JSONUtil.formatJsonStr(JSONUtil.toJsonStr(body)));
        } catch (Exception e) {
            System.out.println("注销失败");
        }
    }

    public static void deploy(String appid, String apolloUrl, Set<String> namespaces, String cookieString) {
        System.out.println("发布结果如果是302表示成功,200貌似表示不需要发布");
        for (String namespace : namespaces) {
            String apps = "{releaseTitle: \"" + DateUtil.now() + "-relese"
                    + "\", releaseComment: \"\", isEmergencyPublish: false}";
            HttpResponse body = HttpRequest
                    .post(apolloUrl + "/apps/" + appid + "/envs/" + namespace
                            + "/clusters/default/namespaces/application/releases")
                    .body(apps).cookie(cookieString).execute();
            System.out.println(JSONUtil.formatJsonStr(JSONUtil.toJsonStr(body)));
        }
    }

    /**
     * 查询空间名是否存在
     *
     * @param appid
     * @param apolloUrl
     * @param targetCookieString
     * @return
     * @author 汪旭辉
     * @date 2021年7月2日
     * @readme
     */
    public static boolean findNameSpaceExist(String appid, String apolloUrl, String targetCookieString) {
        boolean isexist = false;
        try {
            String url = apolloUrl + "/apps/" + appid + "/envs/" + Line.runEnv + "/clusters/default/namespaces";
            String body = HttpRequest.get(url).cookie(targetCookieString).execute().body();
            isexist = !JSONUtil.parse(body).getByPath("message").toString().contains("not exist");
        } catch (Exception e) {
            isexist = true;
        }
        return isexist;
    }

    /**
     * @param appid
     * @param desc
     * @param apolloUrl
     * @param targetCookieString
     * @return
     * @author 汪旭辉
     * @date 2021年7月2日
     * @readme
     */
    public static void createNameSpace(String appid, String desc, String apolloUrl, String targetCookieString) {
        String apps = "{\"appId\":\"" + appid + "\",\"name\":\"" + desc
                + "\",\"orgId\":\"EMR\",\"orgName\":\"EMR\",\"ownerName\":\"apollo\",\"admins\":[]}";
        String body = HttpRequest.post(apolloUrl + "/apps").body(apps).cookie(targetCookieString).execute().body();
        System.out.println(body);
    }

    /**
     * @param appid
     * @param apolloUrl
     * @param values
     * @param cookieString
     * @author 汪旭辉
     * @date 2021年7月2日
     * @readme 获取阿波罗参数
     */
    public static void getPropertieLines(String appid, String apolloUrl,
                                         HashMap<String, HashMap<String, Tree<Object>>> values, String cookieString, TreeInterface treeinterface) {
        //获取源app参数
        String[] NAP = new String[]{Line.runEnv};

        if (treeinterface != null && treeinterface.setNameSpace() != null) {
            NAP = treeinterface.setNameSpace();
        }
        for (int j = 0; j < NAP.length; j++) {
            try {
                String NA = NAP[j];
                String url = apolloUrl + "/apps/" + appid + "/envs/" + NA + "/clusters/default/namespaces";
                HttpResponse httpresponse = HttpRequest.get(url).timeout(15000).charset("utf-8").cookie(cookieString)
                        .execute();
                String body = httpresponse.body();
                values.put(NA, new HashMap<String, Tree<Object>>());
                JSONArray json = JSONUtil
                        .parseArray(JSONUtil.parse(JSONUtil.parseArray(body).get(0)).getByPath("items"));
                for (int i = 0; i < json.size(); i++) {
                    JSON json1 = JSONUtil.parse(json.get(i));
                    if (StrUtil.isNotBlank(json1.getByPath("item.key").toString())) {
                        Tree<Object> tree1 = new Tree<>();
                        try {
                            String sd = json1.getByPath("item.id") + "-" + json1.getByPath("item.namespaceId") + "-"
                                    + json1.getByPath("item.lineNum");
                            tree1.setParentId(sd);
                        } catch (Exception e) {
                        }

                        try {
                            tree1.setId(json1.getByPath("item.value"));
                        } catch (Exception e) {
                        }
                        try {
                            tree1.setName(json1.getByPath("item.comment").toString());
                        } catch (Exception e) {
                        }
                        if (treeinterface != null) {
                            boolean sd = treeinterface.setValue(json1.getByPath("item.key").toString(), tree1);
                            if (sd) {
                                try {
                                    values.get(NA).put(json1.getByPath("item.key").toString(), tree1);
                                } catch (Exception e) {
                                }
                            }
                        } else {
                            try {
                                values.get(NA).put(json1.getByPath("item.key").toString(), tree1);
                            } catch (Exception e) {
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param appid
     * @param apolloUrl
     * @param targetCookieString
     * @param sourceproperties
     * @author 汪旭辉
     * @date 2021年7月2日
     * @readme
     */
    public static void updateOneProperties(String appid, String apolloUrl, String targetCookieString,
                                           HashMap<String, HashMap<String, Tree<Object>>> sourceproperties,
                                           HashMap<String, HashMap<String, Tree<Object>>> targetproperties) {
        for (String namespace : sourceproperties.keySet()) {
            if (sourceproperties.get(namespace) != null && sourceproperties.get(namespace).keySet().size() > 0) {
                //&& targetproperties.get(namespace) != null && targetproperties.get(namespace).keySet().size() > 0
                HashMap<String, Tree<Object>> sourceValues = sourceproperties.get(namespace);
                HashMap<String, Tree<Object>> targetValues = targetproperties.get(namespace);
                Collection<String> h1 = CollUtil.unionDistinct(CollUtil.newArrayList(sourceValues.keySet()),
                        CollUtil.newArrayList(targetValues.keySet()));//交集需要修改
                if (h1 == null || h1.size() == 0) {
                    h1 = CollUtil.toCollection(CollUtil.newArrayList(sourceValues.keySet()));
                }
//                Collection<String> h2 = CollUtil.subtract(CollUtil.newArrayList(sourceValues.keySet()),
//                        CollUtil.newArrayList(targetValues.keySet()));//差集,需要新增;
//                Collection<String> h3 = CollUtil.subtract(CollUtil.newArrayList(targetValues.keySet()),
//                        CollUtil.newArrayList(sourceValues.keySet()));//差集,需要删除
                List<String> t1 = CollectionUtil.sortByPinyin(h1);
                HashMap<String, Integer> sortInt = new HashMap<>();
                for (int i = 0; i < t1.size(); i++) {
                    sortInt.put(t1.get(i), i++);
                }
                for (String key : targetValues.keySet()) {
                    try {
                        System.out.println(appid + "---" + namespace + "-----" + key + "----" + targetValues.get(key)
                                + "-----" + targetValues.get(key).getId());
                        String sd = HttpRequest
                                .delete(apolloUrl + "/apps/" + appid + "/envs/" + namespace
                                        + "/clusters/default/namespaces/application/items/"
                                        + targetValues.get(key).getParentId().toString().split("-")[0])
                                .cookie(targetCookieString).execute().body();
                        System.out.println("删除完成" + sd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < t1.size(); i++) {
                    String key = t1.get(i);
                    try {
                        System.out.println(appid + "---" + namespace + "-----" + key + "----" + sourceValues.get(key)
                                + "-----" + sourceValues.get(key).getId());
                        String createString = "{\"lineNum\":" + sortInt.get(key)
                                + ",\"tableViewOperType\":\"create\",\"key\":\"" + key + "\",\"value\":\""
                                + sourceValues.get(key).getId() + "\",\"comment\":\"" + sourceValues.get(key).getName()
                                + "\",\"addItemBtnDisabled\":true}";
                        String sd = HttpRequest
                                .post(apolloUrl + "/apps/" + appid + "/envs/" + namespace
                                        + "/clusters/default/namespaces/application/item")
                                .body(createString).cookie(targetCookieString).execute().body();
                        System.out.println("创建完成" + sd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

//                for (String key : h1) {
//                    try {
////                        if (!StrUtil.equals(sourceValues.get(key).getId().toString(),
////                                targetValues.get(key).getId().toString())) {
//                        System.out.println(
//                                appid + "---" + namespace + "-----" + key + "----" + sourceValues.get(key) + "-----"
//                                + sourceValues.get(key).getId());
//                        System.out.println(
//                                appid + "---" + namespace + "-----" + key + "----" + targetValues.get(key) + "-----"
//                                + targetValues.get(key).getId());
//                        String createString = "{\"id\":" + targetValues.get(key).getParentId().toString().split("-")[0]
//                                + ",\"namespaceId\":" + targetValues.get(key).getParentId().toString().split("-")[1]
//                                + ",\"key\":\"" + key + "\",\"value\":\"" + sourceValues.get(key).getId()
//                                + "\",\"comment\":\"" + sourceValues.get(key).getName() + "\",\"lineNum\":"
//                                + sortInt.get(key)
////                                    targetValues.get(key).getParentId().toString().split("-")[2]
//                                + ",\"dataChangeCreatedBy\":\"apollo\",\"dataChangeLastModifiedBy\":\"apollo\",\"dataChangeCreatedTime\":\"2021-08-12T17:00:53.000+0800\",\"dataChangeLastModifiedTime\":\"2021-08-12T17:00:53.000+0800\",\"tableViewOperType\":\"update\"}";
//                        System.out.println(createString);
//                        String sd = HttpRequest
//                                .put(apolloUrl + "/apps/" + appid + "/envs/" + namespace
//                                        + "/clusters/default/namespaces/application/item")
//                                .body(createString).cookie(targetCookieString).execute().body();
//                        System.out.println("更新完成" + sd);
////                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }

            }
        }
    }

}
