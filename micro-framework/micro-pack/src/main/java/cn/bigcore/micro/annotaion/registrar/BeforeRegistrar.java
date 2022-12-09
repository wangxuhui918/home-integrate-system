

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.annotaion.registrar;

import cn.hutool.core.util.ArrayUtil;
import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.HomeUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class BeforeRegistrar implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationmetadata) {
        HomeUtils.run();
        String[] all = new String[0];
        if (BaseEv.SettingInformation.iocclasses != null && BaseEv.SettingInformation.iocclasses.size() > 0) {
            String[] selecterExtends = new String[BaseEv.SettingInformation.iocclasses.size()];
            for (int i = 0; i < BaseEv.SettingInformation.iocclasses.size(); i++) {
                Class reloadClass = BaseEv.SettingInformation.iocclasses.get(i);
                selecterExtends[i] = reloadClass.getTypeName();
            }
            all = ArrayUtil.addAll(all, selecterExtends);
        }
        return all;
    }


}
