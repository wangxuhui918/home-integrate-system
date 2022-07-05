

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉 wangxuhui918@163.com
 *
 */

package com.guoshiyao.rely.exception.code.re;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.guoshiyao.rely.exception.code.CodeAb;
import com.guoshiyao.rely.message.i18n.I18n;
import lombok.Data;

/**
 * 消息码处理的基础实现类
 *
 * @author 汪旭辉
 * @date 2022年1月21日
 * @readme
 */
@Data
public class CodeAbE implements CodeAb {
    private String code;
    private String i18n;
    private String type;
    private String text;
    private String className;
    public static  final String SUCCESS = "true";
    public static  final String ERROR = "false";

    public CodeAbE() {
        super();
    }

    /**
     * 内置消息码成功
     *
     * @return
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme
     */
    public static CodeAbE getinfo() {
        return getinfo("业务处理成功");
    }

    /**
     * 内置自定义消息码成功
     *
     * @param text
     * @return
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme
     */
    public static CodeAbE getinfo(String text, String... format) {
        return getBuiltinCode(SUCCESS, I18n.defaultI18n.getI18nCode(), "111111", text, format);
    }

    /**
     * 内置消息码异常
     *
     * @return
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme
     */
    public static CodeAbE getError() {
        return getError("业务处理失败");
    }

    /**
     * 内置自定义消息码异常
     *
     * @param text
     * @return
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme
     */
    public static CodeAbE getError(String text, String... format) {
        return getBuiltinCode(ERROR, I18n.defaultI18n.getI18nCode(), "000000", text, format);
    }


    public static CodeAbE getBuiltinCode(String type, String i18n, String code, String text, String... format) {
        return new CodeAbE() {
            @Override
            public String getType() {
                return type;
            }

            @Override
            public String getText() {
                if (format != null && format.length > 0) {
                    return StrUtil.format(text, format);
                }
                return text;
            }

            @Override
            public String getI18n() {
                return i18n;
            }

            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getClassName() {
                return CodeAbE.class.getName();
            }
        };
    }

    /**
     * 重写 toString 为 jsonstr
     */
    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
