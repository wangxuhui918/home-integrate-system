

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 
 *
 */

package com.guoshiyao.rely.gitlabtools.old;


import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.util.*;

public class GitLabTools {


    public static List<GitLabVo> getProjecs(Set<String> projectids) {

        List<GitLabVo> al = new ArrayList<>();
        for (String id : projectids) {
            String url2 = "http://192.168.150.61:2080/api/v4/projects/" + id
                    + "?simple=true&private_token=i3S7oaT7wTd_TLnBDKta";
            String result = HttpUtil.get(url2);
            System.out.println(id + "" + result);
            GitLabVo gitlabvo = new GitLabVo(id, JSONUtil.parse(result).getByPath("name").toString(),
                    JSONUtil.parse(result).getByPath("description").toString(),
                    JSONUtil.parse(result).getByPath("http_url_to_repo").toString());
            al.add(gitlabvo);
        }
        return al;
    }

    public static List<GitLabVo> getAllProjecs() {
        List<GitLabVo> al = new ArrayList<>();
        for (int i = 1; ; i++) {
            String url2 = "http://192.168.150.61:2080/api/v4/projects/?simple=true&page=" + i + "&per_page=" + 300
                    + "&private_token=i3S7oaT7wTd_TLnBDKta";
            String result = HttpUtil.get(url2);
            JSONArray json = JSONUtil.parseArray(result);
            if (result == null || json == null || json.size() == 0) {
                break;
            }
            for (Object object : json) {
                String id = JSONUtil.parse(object).getByPath("id").toString();
                GitLabVo gitlabvo = new GitLabVo(id, JSONUtil.parse(object).getByPath("name").toString(),
                        JSONUtil.parse(object).getByPath("description").toString(),
                        JSONUtil.parse(object).getByPath("http_url_to_repo").toString());
                al.add(gitlabvo);

            }
        }
        return al;
    }

    public static boolean getbreanchprotectstate(String projectid, String ref) {

        String val4 = "";
        String url2 = "http://192.168.150.61:2080/api/v4/projects/" + projectid + "/protected_branches/" + ref
                + "?private_token=i3S7oaT7wTd_TLnBDKta";
        val4 = HttpUtil.get(url2);
        return val4.contains("access_level");
    }

    public static void unprotectv1(String projectid, String ref) {
        if (getbreanchprotectstate(projectid, ref)) {
            String val4 = "";
            String url2 = "http://192.168.150.61:2080/api/v4/projects/" + projectid + "/protected_branches/" + ref
                    + "?private_token=i3S7oaT7wTd_TLnBDKta";
            String data = "{\"id\": \"" + projectid + "\", \"name\": \"" + ref + "\" }\n";
            String[] headser = new String[]{"Content-Type: application/json", "PRIVATE-TOKEN:i3S7oaT7wTd_TLnBDKta"};
//            val4 = HttpUtils.httpDELETE(url2, data, "utf-8", headser).get(0);
            System.out.println("仓库:" + projectid + ",执行操作:`祛除保护分支` 成功.分支:" + ref);
            System.out.println(val4);
        }
    }

    public static void protectv1(String projectid, String ref) {
        if (!getbreanchprotectstate(projectid, ref)) {
            String val4 = "";
            Map<String, Object> maps1 = new HashMap<>();
            maps1.put("id", projectid);
            maps1.put("name", ref);
            maps1.put("ref", ref);
            String issus4 = "http://192.168.150.61:2080/api/v4/projects/" + projectid
                    + "/protected_branches/?private_token=i3S7oaT7wTd_TLnBDKta";
            val4 = HttpUtil.post(issus4, maps1);
            System.out.println("仓库:" + projectid + ",执行操作:`增加保护分支` 成功.分支:" + ref);
            System.out.println(val4);
        }

    }

}
