

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.starter.registrar;

import cn.hutool.core.util.ArrayUtil;
import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.FyyUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class FyyRegistrarBefore implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationmetadata) {
        FyyUtils.run();
        String[] all = new String[0];
        if (FyyInitEnv.ProjecEnvInformation.iocclasses != null && FyyInitEnv.ProjecEnvInformation.iocclasses.size() > 0) {
            String[] selecterExtends = new String[FyyInitEnv.ProjecEnvInformation.iocclasses.size()];
            for (int i = 0; i < FyyInitEnv.ProjecEnvInformation.iocclasses.size(); i++) {
                Class reloadClass = FyyInitEnv.ProjecEnvInformation.iocclasses.get(i);
                selecterExtends[i] = reloadClass.getTypeName();
            }
            all = ArrayUtil.addAll(all, selecterExtends);
        }
        return all;
    }


}
