package cn.bigcore.micro.config.config;

import cn.bigcore.micro.line.base.FyyLineSourceModelInterface;
import cn.bigcore.micro.config.FyyReourceInterface;
import cn.bigcore.micro.line.base.FyyLineManagerInterface;
import cn.bigcore.micro.line.base.FyyLineRuntimeModelInterface;
import cn.bigcore.micro.config.FyyConfigFrameInterface;
import cn.bigcore.micro.config.FyyConfigProjectInterface;

public enum FyyConfigPluginType {
    CONFIG_PLUGIN_TYPE_1(FyyConfigFrameInterface.class.getSimpleName(), "系统配置接口"),//
    CONFIG_PLUGIN_TYPE_2(FyyConfigProjectInterface.class.getSimpleName(), "项目配置接口"),//
    CONFIG_PLUGIN_TYPE_3(FyyLineManagerInterface.class.getSimpleName(), "链式接口"),//
    CONFIG_PLUGIN_TYPE_4(FyyReourceInterface.class.getSimpleName(), "资源接口"),//
    CONFIG_PLUGIN_TYPE_5(FyyLineSourceModelInterface.class.getSimpleName(), "类模式接口"),//
    CONFIG_PLUGIN_TYPE_6(FyyLineRuntimeModelInterface.class.getSimpleName(), "运行模式接口"),//

    //
    ;

    private String code;
    private String mark;

    FyyConfigPluginType(String code, String mark) {
        this.setMark(mark);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
