


/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.starter.registrar;

import cn.bigcore.micro.init.FyyInitEnd;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 汪旭辉
 * @date 2021年12月10日
 * @readme
 */
@AutoConfigureAfter(value = FyyRegistrarBefore.class)
public class FyyRegistrarAfter implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationmetadata) {
        return new String[]{FyyInitEnd.class.getName()};
    }

}
