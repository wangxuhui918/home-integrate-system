

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.annotaion.registrar;

import cn.hutool.core.util.ArrayUtil;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.run.HomeUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class BeforeRegistrar implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationmetadata) {
        HomeUtils.run();
        // 提交给springboot ioc管理类
        String[] all = new String[0];
        if (Line.iocclasses != null && Line.iocclasses.size() > 0) {
            String[] selecterExtends = new String[Line.iocclasses.size()];
            for (int i = 0; i < Line.iocclasses.size(); i++) {
                Class reloadClass = Line.iocclasses.get(i);
                selecterExtends[i] = reloadClass.getTypeName();
            }
            all = ArrayUtil.addAll(all, selecterExtends);
        }
        return all;
    }


}
