package cn.bigcore.micro.exception;

import cn.bigcore.micro.base.message.FyyMessageEnum;
import cn.bigcore.micro.exception.impl.FyyCodeImpl;
import cn.bigcore.micro.base.message.FyyMessageType;
import cn.bigcore.micro.i18n.FyyI18n;
import cn.hutool.core.util.StrUtil;

/**
 * 获取基础消息码工具类
 */
public class FyyCodeUtils {
    /**
     * 内置消息码成功
     *
     * @return
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme
     */
    public static FyyCodeImpl getinfo() {
        return getinfo(FyyMessageEnum.SUCCESS.getMark());
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
    public static FyyCodeImpl getinfo(String text, String... format) {
        return getBuiltinCode(FyyMessageEnum.SUCCESS.getMessageTypeVo(), FyyI18n.defaultI18n.getI18nCode(), FyyMessageEnum.SUCCESS.getTypeStateCode(), text, format);
    }

    /**
     * 内置消息码异常
     *
     * @return
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme
     */
    public static FyyCodeImpl getError() {
        return getError(FyyMessageEnum.ERR.getMark());
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
    public static FyyCodeImpl getError(String text, Object... format) {
        return getBuiltinCode(FyyMessageEnum.ERR.getMessageTypeVo(), FyyI18n.defaultI18n.getI18nCode(), FyyMessageEnum.ERR.getTypeStateCode(), text, format);
    }

    /**
     * 内置消息码异常
     *
     * @return
     * @author 汪旭辉
     * @date 2022年1月21日
     * @readme
     */
    public static FyyCodeImpl getWarn() {
        return getWarn(FyyMessageEnum.WARING.getMark());
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
    public static FyyCodeImpl getWarn(String text, String... format) {
        return getBuiltinCode(FyyMessageEnum.WARING.getMessageTypeVo(), FyyI18n.defaultI18n.getI18nCode(), FyyMessageEnum.WARING.getTypeStateCode(), text, format);
    }

    public static FyyCodeImpl getBuiltinCode(FyyMessageType type, String i18n, String code, String text, Object... format) {
        return new FyyCodeImpl() {
            @Override
            public FyyMessageType getType() {
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
                return FyyCodeImpl.class.getName();
            }
        };
    }
}
