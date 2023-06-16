/**
 * 医惠科技版权所有
 */
package cn.bigcore.micro.gittools;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.bigcore.micro.gittools.vo.FyyGitLabTag;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectIdRef.PeeledNonTag;
import org.eclipse.jgit.lib.ObjectIdRef.PeeledTag;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 汪旭辉
 * @date 2020年5月9日
 * @readme TODO 使用该工具前保证代码均已提交,否则reset会丢失代码
 */
public class FyyGitUtilsV2 {

    private Git gitv2;
    private String gitDir;
    private String name;
    private String desc;
    private String[] groups;
    private String codeHttpUrl;
    private String gitlabUrl;
    private String token;
    private String code;
    private String username;
    private String password;
    private UsernamePasswordCredentialsProvider user;

    /**
     * @deprecated 改为使用 {@link #GitUtilsV2(String, String, String, String, String)}
     */
    @Deprecated
    public FyyGitUtilsV2(String gitDir, String username, String password, String codeHttpUrl, String code, String token, String gitlabUrl) {
        this(gitDir, username, password, codeHttpUrl, token, null, null, null, gitlabUrl, code);
    }

    public FyyGitUtilsV2(String gitDir, String username, String password, String codeHttpUrl, String token, String name,
                         String desc, String[] groups, String gitlabUrl, String code) {
        this.gitDir = gitDir;
        this.codeHttpUrl = codeHttpUrl;
        this.token = token;
        this.username = username;
        this.password = password;
        this.groups = groups;
        this.desc = desc;
        this.name = name;
        this.gitlabUrl = gitlabUrl;
        this.code = code;
        user = new UsernamePasswordCredentialsProvider(username, password);
    }

    public Git getGitv2() {
        return gitv2;
    }

    /**
     * 打开仓库
     *
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public boolean open() {
        try {
            if (this.gitv2 == null) {
                this.gitv2 = Git.open(new File(gitDir));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean close() {
        if (gitv2 != null) {
            try {
                gitv2.gc().call();
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
     * @param gitv2
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     */
    public String getBranch(Git gitv2) {
        try {
            this.open();
            return gitv2.getRepository().getBranch();
        } catch (IOException e) {
            throw new RuntimeException("仓库:" + gitv2.getRepository().getDirectory() + ",执行操作:`获取分支` 失败.");
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
                gitv2.branchDelete().setBranchNames(target).setForce(true).call();
                RefSpec refSpec = new RefSpec().setSource(null).setDestination(target);
                gitv2.push().setRemote("origin/").setRefSpecs(refSpec).setCredentialsProvider(user).call();
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;

    }

    public boolean delete15Tag() {
        try {
            Date time = new Date(DateUtil.offsetDay(new Date(), -90).getTime());
            this.open();
            List<Ref> refs = gitv2.tagList().call();
            List<FyyGitLabTag> listTags = new ArrayList<>();
            int s = 0;
            for (int i = 0; i < refs.size(); i++) {
                ObjectId objecid = null;
                if ((refs.get(i) instanceof PeeledNonTag)) {
                    objecid = refs.get(i).getObjectId();
                }
                if ((refs.get(i) instanceof PeeledTag)) {
                    objecid = refs.get(i).getPeeledObjectId();
                }
                Ref ref = refs.get(i);
                if (objecid != null) {
                    Iterable<RevCommit> logs = gitv2.log().add(objecid).call();
                    Iterator<RevCommit> iter = logs.iterator();
                    if (iter.hasNext()) {
                        RevCommit commit = iter.next();
                        Date comdate = new Date(commit.getCommitTime() * 1000L);
                        if (comdate != null && ref.getName().startsWith("refs/tags/")) {
                            FyyGitLabTag tag = new FyyGitLabTag();
                            tag.setTagname(ref.getName());
                            tag.setTime(comdate.getTime());
                            tag.setId((s++) + "");
                            tag.setRef(ref);
                            listTags.add(tag);
                        }
                    }
                }
            }

            List<FyyGitLabTag> sortTags = listTags.stream().sorted(Comparator.comparing(FyyGitLabTag::getTime))
                    .collect(Collectors.toList());
            Collections.reverse(sortTags);
            List<FyyGitLabTag> dontdelete = new ArrayList<>();
            List<FyyGitLabTag> masterTags = new ArrayList<>();
            List<FyyGitLabTag> mastertestTags = new ArrayList<>();
            List<FyyGitLabTag> releaseTags = new ArrayList<>();
            List<FyyGitLabTag> developTags = new ArrayList<>();
            {// master
                // 90天内的tag
                List<FyyGitLabTag> master = sortTags.stream().filter(st -> {
                    return st.getTime() >= time.getTime() && st.getTagname().startsWith("refs/tags/master/");
                }).collect(Collectors.toList());
                // 90天外的tag
                List<FyyGitLabTag> tags90waiday = sortTags.stream().filter(st -> {
                    return st.getTime() < time.getTime() && st.getTagname().startsWith("refs/tags/master/");
                }).collect(Collectors.toList());
                // 如果90天内少于10个,则补全10个
                if (master.size() < 10) {
                    for (int i = 0; i < 10 - master.size() && i < tags90waiday.size(); i++) {
                        master.add(tags90waiday.get(i));
                    }
                }
                masterTags.addAll(master);
            }
            {// mastertest
                List<FyyGitLabTag> mastertest = sortTags.stream().filter(st -> {
                    return st.getTagname().startsWith("refs/tags/mastertest/");
                }).collect(Collectors.toList());
                for (int i = 0; i < 10 && i < mastertest.size(); i++) {
                    mastertestTags.add(mastertest.get(i));
                }
            }
            {// release
                List<FyyGitLabTag> release = sortTags.stream().filter(st -> {
                    return st.getTagname().startsWith("refs/tags/release/");
                }).collect(Collectors.toList());
                for (int i = 0; i < 10 && i < release.size(); i++) {
                    releaseTags.add(release.get(i));
                }
            }
            {// develop
                List<FyyGitLabTag> develop = sortTags.stream().filter(st -> {
                    return st.getTagname().startsWith("refs/tags/develop/");
                }).collect(Collectors.toList());
                for (int i = 0; i < 10 && i < develop.size(); i++) {
                    developTags.add(develop.get(i));
                }
            }
            {
                dontdelete.addAll(masterTags);
                dontdelete.addAll(mastertestTags);
                dontdelete.addAll(releaseTags);
                dontdelete.addAll(developTags);
            }

            List<FyyGitLabTag> deleteTag = sortTags.stream().filter(item -> !dontdelete.stream().map(e -> e.getId())
                    .collect(Collectors.toList()).contains(item.getId())).collect(Collectors.toList());
            {
                System.out.println("总计Tag:" + sortTags.size());
                System.out.println("保留总计:" + dontdelete.size());
                System.out.println("删除总计:" + deleteTag.size());
                System.out.println("保留masterTags:" + masterTags.size());
                System.out.println("保留mastertestTags:" + mastertestTags.size());
                System.out.println("保留releaseTags:" + releaseTags.size());
                System.out.println("保留developTags:" + developTags.size());
            }
            System.out.println("===================================");

            List<RefSpec> refs1 = new ArrayList<>();
            for (FyyGitLabTag delete : deleteTag) {
                RefSpec refSpec = new RefSpec().setSource(null).setDestination(delete.getTagname());
                refs1.add(refSpec);
            }
            List<String> deleteTagString = deleteTag.stream().map(p -> p.getTagname()).collect(Collectors.toList());
//			gitv2.tagDelete().setTags(ArrayUtil.toArray(deleteTagString, String.class)).call();
//			gitv2.push().setPushTags().setPushAll().setCredentialsProvider(user).call();
//.setRefSpecs(refs1)
            gitv2.branchDelete().setBranchNames(ArrayUtil.toArray(deleteTagString, String.class)).setForce(true).call();
            gitv2.push().setRefSpecs(refs1).setCredentialsProvider(user).call();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return true;
    }

    /**
     * 初始化仓库
     *
     * @param url
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     * @deprecated 改为使用 {@link #init(String, boolean)}
     */
    @Deprecated
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
                FileUtil.del(this.gitDir);
            }
            this.gitv2 = Git.cloneRepository().setURI(codeHttpUrl).setCloneAllBranches(true).setTimeout(600)
                    .setDirectory(new File(this.gitDir)).setCredentialsProvider(user).call();
            this.open();
            if (checkout) {
                checkOut(branch);
            }
            return true;
        } catch (Exception e1) {
            // 仓库初始化失败删除
            if (deleteFile) {
                FileUtil.del(this.gitDir);
            }
            e1.printStackTrace();
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
            gitv2.checkout().setName(branch).call();
            return true;
        } catch (RefNotFoundException e1) {
            try {
                gitv2.checkout().setCreateBranch(true).setStartPoint("origin/" + branch).setName(branch).call();
                gitv2.checkout().setName(branch).call();
                return true;
            } catch (Exception e4) {
                e1.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
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
                gitv2.reset().setMode(ResetType.HARD).setRef(!originReset ? sourceBranch : "origin/" + sourceBranch)
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
                gitv2.pull().setCredentialsProvider(user).call();// setRemote("origin/" + branch)
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
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
                gitv2.add().addFilepattern(".").call();
                gitv2.commit().setMessage("EwellStarter标签:" + new SimpleDateFormat("yyMMdd.HHmm").format(new Date()))
                        .setAuthor("EWELL@EWELL.CC", "EWELL@EWELL.CC").call();
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;

    }

    /**
     * 强制推送分支
     *
     * @param branch
     * @param force
     * @return
     * @author 汪旭辉
     * @date 2020-7-14
     * @readme TODO
     * @deprecated 改为使用 {@link #push(String, int)}
     */
    @Deprecated
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
                gitv2.push().add(branch).setTimeout(timeout).setForce(true).setCredentialsProvider(user).call();
                return true;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
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

            gitv2.checkout().setCreateBranch(true).setStartPoint("origin/" + sourceBranch).setName(targetBranch).call();
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return false;

    }

    public boolean unprotectBranch(String sourceBranch) {
        String[] groupNames = groups;
        GitlabAPI api = GitlabAPI.connect(this.gitlabUrl, token);
        try {
            GitlabProject project = api.getProject(Integer.parseInt(code));
            api.unprotectBranch(project, sourceBranch);
            return true;
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return false;
        }
    }

    public boolean protectBranch(String sourceBranch) {
        String[] groupNames = groups;
        GitlabAPI api = GitlabAPI.connect(this.gitlabUrl, token);
        try {
            GitlabProject project = api.getProject(Integer.parseInt(code));
            api.protectBranch(project, sourceBranch);
            return true;
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return false;
        }
    }

    public String createProject() {
        String[] groupNames = groups;
        GitlabAPI api = GitlabAPI.connect(this.gitlabUrl, token);
        StringBuffer groupNamePath = new StringBuffer("");
        GitlabGroup group = null;
        GitlabGroup eachGroup = null;
        GitlabGroup secGroup = null;
        GitlabProject gitlab = null;
        GitlabUser gitlabuser = new GitlabUser();
        gitlabuser.setUsername(username);
        gitlabuser.setPrivateToken(token);
        for (int i = 0; i < groupNames.length; i++) {
            String groupName = groupNames[i];
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
        this.codeHttpUrl = gitlab.getHttpUrl();
        try {
            api.createRepositoryFile(gitlab, "" + new Date().getTime() + ".md", "develop", "项目初始化", "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("本地初始化项目失败!!");
        }
        return gitlab.getHttpUrl();

    }
}
