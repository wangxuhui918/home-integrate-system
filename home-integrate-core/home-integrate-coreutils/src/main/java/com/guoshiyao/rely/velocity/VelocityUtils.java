

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.velocity;

import com.guoshiyao.rely.log.base.LoggerBaseAb;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class VelocityUtils {

    public static String convert(String vlc, VelocityContext context) {
        StringWriter writer = new StringWriter();
        try {
            VelocityEngine ve = new VelocityEngine();
            String content = vlc;
            ve.evaluate(context, writer, "", content);
        } catch (Exception e) {
            LoggerBaseAb.warn("序列化引擎错误!!!");
        }
        return writer.toString();
    }

}
