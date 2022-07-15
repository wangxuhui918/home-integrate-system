

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 
 *
 */

package com.guoshiyao.rely.gitlabtools.local;

import lombok.Data;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.RefNotFoundException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 汪旭辉
 * @date 2021年12月17日
 * @readme
 */
@Data
public class LocalGit {
    private Git git;

    /**
     * 初始化本地Git对象
     *
     * @param gitdir
     * @throws Exception
     * @author 汪旭辉
     * @date 2021年12月20日
     * @readme mark
     */
    public LocalGit(String gitdir) throws Exception {
        open(gitdir);
    }

    /**
     * 打开git本地对象
     *
     * @throws Exception
     * @author 汪旭辉
     * @date 2021年12月20日
     * @readme
     */
    private void open(String gitdir) throws Exception {
        if (git == null) {
            git = Git.open(new File(gitdir));
        } else {
            git.close();
            git = Git.open(git.getRepository().getDirectory());
        }
    }

    private void open() throws Exception {
        open(null);
    }

    /**
     * 每次执行完成后需要close,否则会导致下次无法打开,或文件被占用无法删除
     *
     * @throws GitAPIException
     * @author 汪旭辉
     * @date 2021年12月20日
     * @readme
     */
    private void close() throws Exception {
        if (git != null) {
            git.gc().call();
        }
    }

    /**
     * 获取本地Git仓库当前所在分支
     *
     * @return
     * @author 汪旭辉
     * @date 2021年12月20日
     * @readme
     */
    public String getLineBranchName() {
        try {
            open();
            return git.getRepository().getBranch();
        } catch (Exception e) {
            throw new RuntimeException("失败");
        } finally {
            git.close();
        }
    }

    /**
     * 删除本地分支(需要先切换至另外一个分支上才可以删除指定分支)
     *
     * @param defaultBranchName
     * @param deleteBranchName
     * @author 汪旭辉
     * @date 2021年12月20日
     * @readme
     */
    public void deleteLocalBreanch(String defaultBranchName, String deleteBranchName) {
        try {
            open();
            checkOut(defaultBranchName);
            git.branchDelete().setBranchNames(deleteBranchName).setForce(true).call();
        } catch (Exception e1) {
            throw new RuntimeException("失败");
        } finally {
            git.close();
        }
    }

    /**
     * 使用本地origin/*分支检出指定分支
     *
     * @param branch
     * @author 汪旭辉
     * @date 2021年12月20日
     * @readme
     */
    public void checkOut(String branch) {
        try {
            open();
            git.checkout().setName(branch).call();
        } catch (RefNotFoundException e1) {
            try {
                git.checkout().setCreateBranch(true).setStartPoint("origin/" + branch).setName(branch).call();
                git.checkout().setName(branch).call();
            } catch (Exception e4) {
                throw new RuntimeException("失败");
            }
        } catch (Exception e1) {
            throw new RuntimeException("失败");
        } finally {
            git.close();
        }
    }

    /**
     * 使用指定分支创建新分支
     *
     * @param sourceBranch
     * @param targetBranch
     * @param originReset  是否使用对应远端分支重置
     * @param committag    是否提交
     * @author 汪旭辉
     * @date 2021年12月20日
     * @readme
     */
    public void reset(String sourceBranch, String targetBranch, boolean originReset, boolean committag) {
        try {
            open();
            checkOut(targetBranch);
            git.reset().setMode(ResetType.HARD).setRef(!originReset ? sourceBranch : "origin/" + sourceBranch).call();
            if (committag) {
                commit(targetBranch);
            }
        } catch (Exception e1) {
            throw new RuntimeException("删除分支失败");
        } finally {
            git.close();
        }
    }

    /**
     * 提交执行分支,强行添加文件以及提交(会添加未加入git index 的文件)
     *
     * @param branch
     * @author 汪旭辉
     * @date 2021年12月20日
     * @readme
     */
    public void commit(String branch) {
        try {
            open();
            checkOut(branch);
            git.add().addFilepattern(".").call();
            git.commit().setMessage("HomeStarter标签:" + new SimpleDateFormat("yyMMdd.HHmm").format(new Date()))
                    .setAuthor("admin@xxx.com", "admin@xxx.com").call();
        } catch (Exception e1) {
            throw new RuntimeException("失败");
        } finally {
            git.close();
        }
    }

    /**
     * 使用指定远端分支创建本地分支
     *
     * @param sourceBranch
     * @param targetBranch
     * @author 汪旭辉
     * @date 2021年12月20日
     * @readme
     */
    public void createBranchForRemote(String sourceBranch, String targetBranch) {
        try {
            open();
            git.checkout().setCreateBranch(true).setStartPoint("origin/" + sourceBranch).setName(targetBranch).call();
        } catch (Exception e1) {
            throw new RuntimeException("失败");
        } finally {
            git.close();
        }
    }

}
