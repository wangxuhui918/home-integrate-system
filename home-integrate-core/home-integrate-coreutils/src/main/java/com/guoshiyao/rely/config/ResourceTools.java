

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 
 *
 */

package com.guoshiyao.rely.config;

import cn.hutool.core.io.resource.ResourceUtil;

import java.io.InputStream;

@Deprecated
public class ResourceTools {

    /**
     * 查询资源文件是否存在,该方法不再建议使用,建议直接引用ResourceUtil.getUtf8Reader(resourcePath),通过文件流的方式判定
     *
     * @param mainClass
     * @param resourcePath
     * @return
     * @author 郭诗瑶
     * @date 2021年12月8日
     * @readme
     */
    @Deprecated
    public static boolean exFile(String mainClass, String resourcePath) {
        try {
            return ResourceUtil.getUtf8Reader(resourcePath) != null;
        } catch (Exception e) {
            // mark: handle exception
        }
        return false;
    }

    /**
     * 获取文件流
     *
     * @param mainClass
     * @param resourcePath
     * @return
     * @author 郭诗瑶
     * @date 2021年12月6日
     * @readme
     */
    @Deprecated
    public static InputStream getContextStream(String mainClass, String resourcePath) {
        return ResourceUtil.getStream(resourcePath);
        //        return ClassUtil.loadClass(mainClass).getClassLoader().getResourceAsStream(resourcePath);
    }

    /**
     * 通过系统启动类来判定resource文件stream位置!!并读取文件流
     *
     * @param resourcePath
     * @return
     * @author 郭诗瑶
     * @date 2020年5月18日
     * @readme TODO
     */
    @Deprecated
    public static String getContext(String mainClass, String resourcePath) {
        return ResourceUtil.readUtf8Str(resourcePath);
    }

    /**
     * 通过系统启动类来判定resource文件stream位置!!并读取文件流
     * 方法同getContext
     *
     * @param resourcePath
     * @return
     * @author 郭诗瑶
     * @date 2020年5月18日
     * @readme TODO
     */
    @Deprecated
    public static String getContextNl(String mainClass, String resourcePath) {
        return getContext(mainClass, resourcePath);
    }

}
