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
import com.guoshiyao.rely.HomePosition;

public class BaseEv {
    public static final boolean I18N_THREAD_POWER = true;//出入参i18n自动判定,否则需要手工指定,不指定则为系统默认
    public static final boolean USER_THREAD_POWER = true;//出入参user自动判定,否则需要手工指定,不指定则为系统默认
    public static final String CORE_PACKAGE = ClassUtil.getPackage(HomePosition.class);
    public static final String INIT_KEY = "model-project-company";
    public static final String HOME_TAG = "home";
    public static final String HOME_NAME = "帆有云系统";
    public static final String UK_NAME = HOME_TAG.toUpperCase() + "_UK";
    public static final String FILE_SEPARATOR = FileUtil.FILE_SEPARATOR;


}
