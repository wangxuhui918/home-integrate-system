

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.annotaion.registrar;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import com.guoshiyao.rely.environment.ENV;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.message.i18n.I18n;
import com.guoshiyao.rely.run.HomeUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.logging.Level;

public class BeforeRegistrar implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationmetadata) {
        String idkey = "", i18n = "", projectPackage = "", mainClass = "";
        Level loglevel = Level.FINER;//默认日志级别
        ENV env = null;
        boolean updateProperties = false;

        {
            AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationmetadata
                    .getAnnotationAttributes(annotationmetadata.getAnnotationTypes().iterator().next(), false));
            try {
                i18n = ((I18n) attributes.get("i18n")).getI18nCode();
            } catch (Exception e) {
            }
            try {
                ENV aEnv = null;
                try {
                    aEnv = ((ENV) attributes.get("env"));
                } catch (Exception e) {
                }
                if (aEnv == ENV.LOCAL) {//自动选择根据环境变量确定
                    if (ENV.having(SystemUtil.get("env"))) {
                        env = ENV.getEnv(SystemUtil.get("env"));
                    } else if (ENV.having(SystemUtil.get("ENV"))) {
                        env = ENV.getEnv(SystemUtil.get("ENV"));
                    } else if (ENV.having(SystemUtil.get("Env"))) {
                        env = ENV.getEnv(SystemUtil.get("Env"));
                    } else if (ENV.having(SystemUtil.get("ENv"))) {
                        env = ENV.getEnv(SystemUtil.get("ENv"));
                    } else {//得不到结果使用DEV默认
                        env = aEnv;
                    }
                } else {//手工指定
                    env = aEnv;
                }
            } catch (Exception e) {
            }
            try {
                if (StrUtil.isNotBlank(SystemUtil.get("loglevel"))) {//如果日志界别不为空则直接查找,根据名字和值自动查找
                    loglevel = Level.parse(SystemUtil.get("loglevel"));
                }
            } catch (Exception e) {
            }
            try {
                mainClass = ((String) attributes.get("mainClass"));
            } catch (Exception e) {
            }
            try {
                if (StrUtil.isBlank(mainClass)) {
                    mainClass = ClassUtil.loadClass(annotationmetadata.getClassName()).getName();
                }
            } catch (Exception e) {
            }
            try {
                idkey = attributes.getString("idkey");
            } catch (Exception e) {
            }
            try {
                projectPackage = attributes.getStringArray("scanBasePackages")[0];
            } catch (Exception e) {
            }

            try {
                updateProperties = attributes.getBoolean("updateProperties");
            } catch (Exception e) {
            }
        }
        HomeUtils.run(idkey, i18n, projectPackage, mainClass, env, loglevel, updateProperties);
        // 提交给springboot ioc管理类
        return getIocClasses();
    }

    private String[] getIocClasses() {
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
