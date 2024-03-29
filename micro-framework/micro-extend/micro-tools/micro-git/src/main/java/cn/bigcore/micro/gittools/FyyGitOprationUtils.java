package cn.bigcore.micro.gittools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Set;

/**
 * @author 汪旭辉
 * @date 2022/11/7
 * @readme
 */
public class FyyGitOprationUtils {

    public static boolean operation(String projectWorkDir, String code, String enName, FyyGitOperationMethod[] operationMethod, String sourcebranch, String targetbranch, String codeUrl, String username, String password, String token, Set<String> lockBranch, Set<String> allBranch, String gitlabUrl) {
        FyyGitUtilsV2 v2 = null;
        try {
            v2 = new FyyGitUtilsV2(projectWorkDir, username, password, codeUrl, code, token, gitlabUrl);
            for (int i = 0; i < operationMethod.length; i++) {
                FyyGitOperationMethod operationType = operationMethod[i];
                if (operationType == FyyGitOperationMethod.删除工作目录) {// 删除工作目录
                    if (StrUtil.isNotBlank(projectWorkDir)) {
                        try {
                            return FileUtil.del(projectWorkDir);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
                if (operationType == FyyGitOperationMethod.拉取源代码默认分支A) {// 拉取源代码默认分支A
                    if (StrUtil.isNotBlank(sourcebranch)) {
                        return v2.init(sourcebranch);
                    }
                }
                if (operationType == FyyGitOperationMethod.切换至分支A) {// 切换至分支A
                    if (StrUtil.isNotBlank(sourcebranch)) {
                        return v2.checkOut(sourcebranch);
                    }
                }
                if (operationType == FyyGitOperationMethod.更新分支A) {// 更新分支A
                    if (StrUtil.isNotBlank(sourcebranch)) {
                        return v2.pull(sourcebranch);
                    }
                }
                if (operationType == FyyGitOperationMethod.硬更新所有分支) {// 硬更新所有分支
                    for (String branch : allBranch) {
                        return v2.pull(branch);
                    }
                }
                if (operationType == FyyGitOperationMethod.以本地分支A重置本地B分支) {// 以本地分支A重置本地B分支
                    if (StrUtil.isNotBlank(sourcebranch)) {
                        return v2.reset(sourcebranch, targetbranch, true, true);
                    }
                }
                if (operationType == FyyGitOperationMethod.强推A分支) {// 强推A分支
                    if (StrUtil.isNotBlank(sourcebranch)) {
                        return v2.push(sourcebranch);
                    }
                }
                if (operationType == FyyGitOperationMethod.解除保护分支A) {// 解除保护分支A
                    if (StrUtil.isNotBlank(sourcebranch) && lockBranch.contains(sourcebranch)) {
                        return v2.unprotectBranch(sourcebranch);
                    }
                }
                if (operationType == FyyGitOperationMethod.增加保护分支A) {// 增加保护分支A
                    if (StrUtil.isNotBlank(sourcebranch) && lockBranch.contains(sourcebranch)) {
                        return v2.protectBranch(sourcebranch);
                    }
                }
                if (operationType == FyyGitOperationMethod.以远端分支A创建本地分支B) {// 以远端分支A创建本地分支B
                    if (StrUtil.isNotBlank(sourcebranch)) {
                        return v2.createBranchForRemote(sourcebranch, targetbranch);
                    }
                }
                if (operationType == FyyGitOperationMethod.删除本地分支A和远端分支A) {// 删除本地分支A和远端分支A
                    if (StrUtil.isNotBlank(sourcebranch)) {
                        return v2.deleteBreanch(sourcebranch, targetbranch);
                    }
                }
                if (operationType == FyyGitOperationMethod.删除15日前标记) {// 删除15日前标记
                    return v2.delete15Tag();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (v2 != null) {
                v2.close();
            }
        }
        return false;
    }
}
