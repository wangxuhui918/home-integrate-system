


/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.annotaion.registrar;

import com.guoshiyao.rely.config.EndInit;
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
        return new String[]{EndInit.class.getName()};
    }

}
