/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.base;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.HomePosition;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;

public class BaseEv {

    public static final String CORE_PACKAGE = ClassUtil.getPackage(HomePosition.class);
    public static final String INIT_KEY = "model-project-company";
    public static final String HOME_TAG = "home";
    public static final String UK_NAME = HOME_TAG.toUpperCase() + "_UK";

    //避免windows 10出现问题,兼容低版本jdk
    public static final String FILE_SEPARATOR = FileUtil.FILE_SEPARATOR;

    public static void main(String[] args) {
        String f1 = FileUtil.getAbsolutePath("", HomePosition.class);

        for (int i = 0; ; i++) {
            String classes = FileUtil.getParent(f1, i);
            String target = FileUtil.getParent(f1, i + 1);
            String root = FileUtil.getParent(f1, i + 2);
            if (StrUtil.endWith(classes, "classes") && StrUtil.endWith(target, "target")) {
                Line.projectresourcepath = root + BaseEv.FILE_SEPARATOR + "src" + BaseEv.FILE_SEPARATOR + "main" + BaseEv.FILE_SEPARATOR + "resources" + BaseEv.FILE_SEPARATOR;
                Line.projectcodesourcepath = root + BaseEv.FILE_SEPARATOR + "src" + BaseEv.FILE_SEPARATOR + "main" + BaseEv.FILE_SEPARATOR + "java" + BaseEv.FILE_SEPARATOR;
                break;
            }
            if (classes == null) {
                throw new ExceptionError("获取根目录{}失败", f1);
            }
        }
//        String f3 = FileUtil.getAbsolutePath("", BaseEv.class);
//        String f4 = FileUtil.getAbsolutePath("", BaseEv.class);
        //        String sd = StrUtil.subBefore(p1, "target" + BaseEv.FILE_SEPARATOR + "classes", true) + BaseEv.FILE_SEPARATOR + "src"
//                + BaseEv.FILE_SEPARATOR + "main" + BaseEv.FILE_SEPARATOR + "resources" + BaseEv.FILE_SEPARATOR;

//        System.out.println(f1);
    }
}
