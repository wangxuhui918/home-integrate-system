package cn.bigcore.micro.plugin.exception.code.impl;

import cn.bigcore.micro.plugin.exception.code.bean.MessageType;
import cn.bigcore.micro.plugin.exception.code.bean.MessageTypeVo;
import cn.bigcore.micro.plugin.i18n.I18n;
import cn.hutool.core.util.StrUtil;

/**
 * 获取基础消息码工具类
 */
public class BaseCodeUtils {
    /**
     * 内置消息码成功
     *
     * @return
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme
     */
    public static CodeImpl getinfo() {
        return getinfo(MessageType.SUCCESS.getMark());
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
    public static CodeImpl getinfo(String text, String... format) {
        return getBuiltinCode(MessageType.SUCCESS.getMessageTypeVo(), I18n.defaultI18n.getI18nCode(), MessageType.SUCCESS.getTypeStateCode(), text, format);
    }

    /**
     * 内置消息码异常
     *
     * @return
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme
     */
    public static CodeImpl getError() {
        return getError(MessageType.ERR.getMark());
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
    public static CodeImpl getError(String text, Object... format) {
        return getBuiltinCode(MessageType.ERR.getMessageTypeVo(), I18n.defaultI18n.getI18nCode(), MessageType.ERR.getTypeStateCode(), text, format);
    }

    /**
     * 内置消息码异常
     *
     * @return
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme
     */
    public static CodeImpl getWarn() {
        return getWarn(MessageType.WARING.getMark());
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
    public static CodeImpl getWarn(String text, String... format) {
        return getBuiltinCode(MessageType.WARING.getMessageTypeVo(), I18n.defaultI18n.getI18nCode(), MessageType.WARING.getTypeStateCode(), text, format);
    }

    public static CodeImpl getBuiltinCode(MessageTypeVo type, String i18n, String code, String text, Object... format) {
        return new CodeImpl() {
            @Override
            public MessageTypeVo getType() {
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
                return CodeImpl.class.getName();
            }
        };
    }
}
