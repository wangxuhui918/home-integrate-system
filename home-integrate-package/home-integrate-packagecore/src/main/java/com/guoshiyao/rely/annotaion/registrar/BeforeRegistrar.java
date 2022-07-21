

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
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
//        List<URI> listUrl = ResourceFindUtils.findUri(BaseEv.HOME_TAG + "-*.ini");//Line.env.getName()
//        for (int i = 0; i < listUrl.size(); i++) {
//            LoggerBaseAb.info("读取到[{}]配置文件", listUrl.get(i).toString());
//            Setting o = new Setting(listUrl.get(i).toURL(), CharsetUtil.CHARSET_UTF_8, true);
        HomeUtils.run();
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
