

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
import cn.hutool.json.JSONUtil;
import cn.hutool.system.SystemUtil;
import com.guoshiyao.rely.exception.re.ex.ExceptionError;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.message.i18n.I18n;
import com.guoshiyao.rely.run.HomeUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class BeforeRegistrar implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationmetadata) {
        String idkey = "", i18n = "", projectPackage = "", mainClass = "";
        Level loglevel = Level.FINER;//默认日志级别
        String runEnv = "";
        boolean updateProperties = false;
        String[] configEnv = null;
        {
            AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationmetadata
                    .getAnnotationAttributes(annotationmetadata.getAnnotationTypes().iterator().next(), false));
            try {
                i18n = ((I18n) attributes.get("i18n")).getI18nCode();
            } catch (Exception e) {
            }
            try {
                configEnv = (attributes.getStringArray("configEnv"));
                if (configEnv == null || configEnv.length == 0) {
                    throw new ExceptionError("参数{}不能为空", "configEnv");
                }
            } catch (Exception e) {
            }

            try {
                runEnv = attributes.getString("runEnv");
                if (StrUtil.isBlank(runEnv)) {//自动选择根据环境变量确定
                    List<String> lista = Arrays.asList(SystemUtil.get("env"), SystemUtil.get("ENV"), SystemUtil.get("Env"), SystemUtil.get("ENv"), SystemUtil.get("ENv"));
                    for (int i = 0; i < lista.size(); i++) {
                        String lineEnv = lista.get(i);
                        if (StrUtil.isNotBlank(lineEnv)) {
                            runEnv = lineEnv;
                            break;
                        }
                    }
                }
                runEnv = StrUtil.blankToDefault(runEnv, "");
            } catch (Exception e) {
            }
            try {
                if (StrUtil.isNotBlank(SystemUtil.get("loglevel"))) {//如果日志界别不为空则直接查找,根据名字和值自动查找
                    loglevel = Level.parse(SystemUtil.get("loglevel"));
                }
            } catch (Exception e) {
            }
            try {
                mainClass = attributes.getString("mainClass");
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
            if (StrUtil.isNotBlank(runEnv) && configEnv != null && configEnv.length > 0 && !ArrayUtil.contains(configEnv, runEnv)) {
                throw new ExceptionError("参数{}[{}]需在参数{}[{}]范围内", "runEnv", runEnv, "configEnv", JSONUtil.toJsonStr(configEnv));
            }
        }
        HomeUtils.run(idkey, i18n, projectPackage, mainClass, runEnv, configEnv, loglevel, updateProperties);
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
