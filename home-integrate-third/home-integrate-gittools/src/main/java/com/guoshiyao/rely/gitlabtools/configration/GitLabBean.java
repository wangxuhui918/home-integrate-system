

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.gitlabtools.configration;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.line.Line;
import lombok.Data;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 汪旭辉
 * @date 2021年12月17日
 * @readme
 */
@Data
public class GitLabBean {
    private Git git;
    private String name;
    private String desc;
    private String githttpurl;
    private String branch = Line.setting.get("home.gitlab.branch");
    private String gitdir;
    private String gitlaburl = Line.setting.get("home.gitlab.gitlaburl");
    private String token = Line.setting.get("home.gitlab.token");
    private UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider(
            Line.setting.get("home.gitlab.username"),
            Line.setting.get("home.gitlab.password"));

    public GitLabBean(String githttpurl) {
        this.githttpurl = githttpurl;
        this.name = StrUtil.subBefore(StrUtil.subAfter(this.githttpurl, "/", true), ".git", true);
        this.gitdir = Line.setting.get("home.gitlab.gitdir") + File.separator + this.name;
        init(branch);
    }

    public boolean open() {
        try {
            if (this.git == null) {
                this.git = Git.open(new File(gitdir));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.git.close();
        }
        return false;
    }

    public boolean close() {
        if (git != null) {
            try {
                git.gc().call();
                return true;
            } catch (GitAPIException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    /**
     * 获取当前分支
     *
     * @param git
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public String getBranch() {
        try {
            this.open();
            return git.getRepository().getBranch();
        } catch (IOException e) {
            throw new RuntimeException("仓库:" + git.getRepository().getDirectory() + ",执行操作:`获取分支` 失败.");
        } finally {
            this.git.close();
        }
    }

    /**
     * 强制删除远端以及本地分支
     *
     * @param branch
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public boolean deleteBreanch(String source, String target) {
        try {
            this.open();
            if (checkOut(source)) {
                git.branchDelete().setBranchNames(target).setForce(true).call();
                RefSpec refSpec = new RefSpec().setSource(null).setDestination(target);
                git.push().setRemote("origin/").setRefSpecs(refSpec).setCredentialsProvider(user).call();
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            this.git.close();
        }
        return false;

    }

    public boolean init() {
        return init(branch);
    }

    public boolean init(String branch) {
        return init(branch, true, true);
    }

    /**
     * 初始化仓库
     *
     * @param checkout mark
     * @param url
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public boolean init(String branch, boolean checkout, boolean deleteFile) {
        try {
            if (deleteFile) {
                FileUtil.del(this.gitdir);
            }
            this.git = Git.cloneRepository().setURI(githttpurl).setCloneAllBranches(true).setTimeout(600)
                    .setDirectory(new File(this.gitdir)).setCredentialsProvider(user).call();
            this.open();
            if (checkout) {
                checkOut(branch);
            }
            return true;
        } catch (Exception e1) {
            // 仓库初始化失败删除
            if (deleteFile) {
                FileUtil.del(this.gitdir);
            }
            e1.printStackTrace();
        } finally {
            this.git.close();
        }
        return false;
    }

    /**
     * 检出某个分支代码
     *
     * @param branch
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public boolean checkOut(String branch) {
        try {
            this.open();
            git.checkout().setName(branch).call();
            return true;
        } catch (RefNotFoundException e1) {
            try {
                git.checkout().setCreateBranch(true).setStartPoint("origin/" + branch).setName(branch).call();
                git.checkout().setName(branch).call();
                return true;
            } catch (Exception e4) {
                e1.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            this.git.close();
        }
        return false;
    }

    /**
     * 以sourceBranch分支重置分支targetBranch
     *
     * @param originReset
     * @param committag   TODO
     * @param branch
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public boolean reset(String sourceBranch, String targetBranch, boolean originReset, boolean committag) {
        try {
            this.open();

            if (checkOut(targetBranch)) {
                git.reset().setMode(ResetType.HARD).setRef(!originReset ? sourceBranch : "origin/" + sourceBranch)
                        .call();
                if (committag) {
                    if (commit(targetBranch)) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            this.git.close();
        }
        return false;
    }

    /**
     * 强制更新当前分支
     *
     * @param branch
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public boolean pull(String branch) {
        try {
            this.open();
            if (this.reset(branch, branch, true, false)) {
                git.pull().setCredentialsProvider(user).call();// setRemote("origin/" + branch)
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            this.git.close();
        }
        return false;

    }

    /**
     * 提交某个分支
     *
     * @param branch
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public boolean commit(String branch) {
        try {
            this.open();

            if (this.checkOut(branch)) {
                git.add().addFilepattern(".").call();
                git.commit().setMessage("HomeStarter标签:" + new SimpleDateFormat("yyMMdd.HHmm").format(new Date()))
                        .setAuthor("admin@", "admin@123.com").call();
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            this.git.close();
        }
        return false;

    }

    /**
     * 强制推送分支
     *
     * @param branch
     * @param force
     * @return 改为使用 {@link #push(String, int)}
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */

    public boolean push(String branch) {
        return push(branch, 600);
    }

    /**
     * 强制推送分支
     *
     * @param branch
     * @param timeout mark
     * @param force
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public boolean push(String branch, int timeout) {
        try {
            this.open();

            if (checkOut(branch)) {
                git.push().add(branch).setTimeout(timeout).setForce(true).setCredentialsProvider(user).call();
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            this.git.close();
        }
        return false;

    }

    /**
     * 以sourceBranch远端分支为基础创建分支
     *
     * @param sourceBranch
     * @param targetBranch
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public boolean createBranchForRemote(String sourceBranch, String targetBranch) {
        try {
            this.open();

            git.checkout().setCreateBranch(true).setStartPoint("origin/" + sourceBranch).setName(targetBranch).call();
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            this.git.close();
        }
        return false;
    }

    public String createProject(String[] fullgroup) {
        GitlabAPI api = GitlabAPI.connect(this.gitlaburl, token);
        StringBuffer groupNamePath = new StringBuffer();
        GitlabGroup group = null;
        GitlabGroup eachGroup = null;
        GitlabGroup secGroup = null;
        GitlabProject gitlab = null;
        GitlabUser gitlabuser = new GitlabUser();
//        gitlabuser.setUsername(username);
        gitlabuser.setPrivateToken(token);
        for (int i = 0; i < fullgroup.length; i++) {
            String groupName = fullgroup[i];
            groupNamePath.append((StrUtil.isNotBlank(groupNamePath.toString()) ? "/" : "") + groupName);
            if (i == 0) {
                try {
                    eachGroup = group = api.getGroup(groupName);
                } catch (Exception e) {
                    try {
                        if (group == null) {
                            eachGroup = group = api.createGroup(groupName, groupName);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        throw new RuntimeException("007");
                    }
                }
            } else {
                try {
                    eachGroup = secGroup = api.getGroup(groupNamePath.toString());
                } catch (Exception e) {
                    try {
                        if (secGroup == null) {
                            CreateGroupRequest info = new CreateGroupRequest(groupName);
                            info.setDescription(desc);
                            info.setParentId(eachGroup.getId());
                            info.setName(groupName);
                            info.setPath(groupName);
                            info.setDescription("");
                            info.setVisibility(GitlabVisibility.PRIVATE);
                            eachGroup = secGroup = api.createGroup(info, gitlabuser);
                        }
                    } catch (Exception e2) {
                        throw new RuntimeException("007");
                    }
                }
            }
        }

        try {
            gitlab = api.getProject(eachGroup.getFullPath(), name);
        } catch (Exception e) {
            try {
                gitlab = api.createProjectForGroup(name, secGroup, desc);
            } catch (IOException e1) {
                throw new RuntimeException("创建GIt项目失败!!");
            }
        }
        this.githttpurl = gitlab.getHttpUrl();
        try {
            api.createRepositoryFile(gitlab, "" + new Date().getTime() + ".md", "develop", "项目初始化", "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("本地初始化项目失败!!");
        }
        return gitlab.getHttpUrl();

    }
}
