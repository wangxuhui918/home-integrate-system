package com.guoshiyao.rely.config.core.home.bean;

import com.guoshiyao.rely.core.IClassModelConfig;
import com.guoshiyao.rely.core.ILineManager;
import com.guoshiyao.rely.core.IResource;
import com.guoshiyao.rely.core.IRunModelConfig;
import com.guoshiyao.rely.core.configration.home.ICoreConf;
import com.guoshiyao.rely.core.configration.project.IProjectConf;

public enum ConfigPluginType {
    CONFIG_PLUGIN_TYPE_1(ICoreConf.class.getSimpleName(), "系统配置接口"),//
    CONFIG_PLUGIN_TYPE_2(IProjectConf.class.getSimpleName(), "项目配置接口"),//
    CONFIG_PLUGIN_TYPE_3(ILineManager.class.getSimpleName(), "链式接口"),//
    CONFIG_PLUGIN_TYPE_4(IResource.class.getSimpleName(), "资源接口"),//
    CONFIG_PLUGIN_TYPE_5(IClassModelConfig.class.getSimpleName(), "类模式接口"),//
    CONFIG_PLUGIN_TYPE_6(IRunModelConfig.class.getSimpleName(), "运行模式接口"),//

    //
    ;

    private String code;
    private String mark;

    ConfigPluginType(String code, String mark) {
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
