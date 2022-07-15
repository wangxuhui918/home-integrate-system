


/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 
 *
 */

package com.guoshiyao.rely.annotaion.registrar;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 汪旭辉
 * @date 2021年12月10日
 * @readme
 */
@AutoConfigureAfter(value = BeforeRegistrar.class)
public class AfterRegistrar implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationmetadata) {
//        String BASE_PACKAGE = "com.home.demo";
//        GenericApplicationContext context = new GenericApplicationContext();
//        HomeDefinitonScanner homesca = new HomeDefinitonScanner(context,
//                ClassUtil.loadClass("com.home.demo.api.UserApi"));
//        homesca.registerTypeFilter();
//        int beanCount = homesca.scan(BASE_PACKAGE);
//        context.refresh();

//        return new String[] { "com.home.demo.service.impl.UserServiceImpl" };
        return new String[]{};
    }

}
