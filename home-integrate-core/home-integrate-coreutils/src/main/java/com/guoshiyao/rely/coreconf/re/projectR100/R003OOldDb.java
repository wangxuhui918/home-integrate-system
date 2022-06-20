/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.coreconf.re.projectR100;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.guoshiyao.rely.coreannotation.rule.RuleAnnotation;
import com.guoshiyao.rely.coreconf.ab.ProjectCoreConfAb;
import com.guoshiyao.rely.line.Line;
import com.guoshiyao.rely.resource.ResourceFindUtils;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * @author 汪旭辉
 * @date 2022/5/25
 * @readme
 */
@RuleAnnotation
public class R003OOldDb implements ProjectCoreConfAb {

    public static String getEnvPath() {
        String envPath = "";
        if (Line.UK.equals(Line.runEnv)) {
            envPath = "";
        } else {
            envPath = Line.runEnv.toUpperCase() + File.separator;
        }
        return envPath;
    }

    @Override
    public Map<String, String> getotherMessageFileContext() {

        Map<String, String> map = new HashMap<>();
        try {
            List<URL> listUrl = ResourceFindUtils.find(getEnvPath() + StrUtil.format("message-*.xml"));//Line.env.getName()
            for (int i = 0; i < listUrl.size(); i++) {
                URL url = listUrl.get(i);
                if (url.getPath().contains("-" + Line.i18n)) {
                    String name = StrUtil.subBetween(url.getPath(), "message-", ".xml");
                    String context = FileUtil.readString(url, CharsetUtil.CHARSET_UTF_8);
                    map.put(name, context);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    @Override
    public String getZoneMessageFileContext() {
        try {
            return ResourceUtil.readUtf8Str(getEnvPath() + StrUtil.format("message-{}.xml", Line.i18n));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @Override
    public HashMap<String, String> getEnvAllProperties() {
        HashMap<String, String> map = new HashMap<>();
        List<URL> listUrl = ResourceFindUtils.find(StrUtil.format(getEnvPath() + "*.properties"));//Line.env.getName()
        if (Line.UK.equals(Line.runEnv)) {
            listUrl.addAll(ResourceFindUtils.find(StrUtil.format(Line.runEnv + File.separator + "extend.properties")));
        }
        for (int i = 0; i < listUrl.size(); i++) {
            URL url = listUrl.get(i);
            try {
                Properties p = new Properties();
                p.load(FileUtil.getInputStream(new File(url.toURI())));
                Set<Map.Entry<Object, Object>> entrySet = p.entrySet();
                for (Map.Entry<Object, Object> entry : entrySet) {
                    map.put((String) entry.getKey(), (String) entry.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    @Override
    public Map<String, String> getEnvPropertiesByCode(String code) {
        return new HashMap<>();
    }

    @Override
    public boolean getDbInit() {
        return false;
    }

    @Override
    public boolean setDbInit() {
        return false;
    }

    @Override
    public void writeReadMes() {

    }

    @Override
    public void copyQuasiproduction() {

    }

    @Override
    public void writeModelConfig() {

    }

}
