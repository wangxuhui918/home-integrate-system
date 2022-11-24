
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.gitlabtools.plugin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.BaseEv;
import com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.properties.ConfigDetails;
import com.guoshiyao.rely.gitlabtools.configration.GitLabBean;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;

import java.util.ArrayList;
import java.util.List;

public class GitGTools {

    public static void downloadUserAllForContains(String string) {
        GitlabAPI api = GitlabAPI.connect(BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_GITLAB_GITLABURL.getKey()),
                BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_GITLAB_TOKEN.getKey()));
        List<GitlabProject> group = api.getAllProjects();
        List<GitlabProject> gits = new ArrayList<>();
        for (GitlabProject gitlabProject : group) {
            if (StrUtil.contains(gitlabProject.getHttpUrl(), string)) {
                gits.add(gitlabProject);
            }
        }
        downloadManyGit(gits);
    }

    public static void downloadManyGit(List<GitlabProject> gits) {
        for (GitlabProject git : gits) {
            downloadOneGit(git);
        }
    }

    public static void downloadOneGit(GitlabProject git) {
        String line = "giturl:----" + git.getHttpUrl() + "gitname:----" + git.getName() + "gitdesc:----"
                + git.getDescription() + "\n";
        GitLabBean v2 = new GitLabBean(git.getHttpUrl());
        FileUtil.appendUtf8String(line, BaseEv.SettingInformation.setting.get(ConfigDetails.HOME_GITLAB_LOG.getKey()));
        v2.init();
    }
}
