
/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.utils.resource;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.exception.re.ex.FyyExceptionError;

import java.net.URI;
import java.util.List;

/**
 * 根据规则获取目录下文件集合
 */
public class FyyResourceFindUtils {

//    public static List<String> findClassesPath(String patternPath) {
//        if (BaseEv.SettingInformation.resourcetool == null) {
//            throw new ExceptionError(ResourceFindUtils.class.getName() + "缺失核心实现类!");
//        }
//        return BaseEv.SettingInformation.resourcetool.findClassesPath(patternPath);
//    }

    public static List<URI> findUri(String patternPath) {
        if (FyyInitEnv.SettingInformation.resourcetool == null) {
            throw new FyyExceptionError(FyyResourceFindUtils.class.getName() + "缺失核心实现类!");
        }
        return FyyInitEnv.SettingInformation.resourcetool.find(patternPath);
    }
}
