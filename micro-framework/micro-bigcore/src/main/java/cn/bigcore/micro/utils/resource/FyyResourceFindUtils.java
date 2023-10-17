
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


    public static List<URI> findUri(String patternPath) {
        return FyyInitEnv.resourcetool.find(patternPath);
    }
}
