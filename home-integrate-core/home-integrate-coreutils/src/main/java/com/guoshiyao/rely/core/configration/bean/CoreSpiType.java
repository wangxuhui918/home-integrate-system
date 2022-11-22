package com.guoshiyao.rely.core.configration.bean;

import com.guoshiyao.rely.core.ClassModelConfigRe;
import com.guoshiyao.rely.core.ILineManager;
import com.guoshiyao.rely.core.IResource;
import com.guoshiyao.rely.core.RunModelConfigRe;
import com.guoshiyao.rely.core.configration.home.ICoreConf;
import com.guoshiyao.rely.core.configration.project.IProjectConf;

public enum CoreSpiType {
    系统配置接口(ICoreConf.class.getSimpleName(), "系统配置接口", "系统配置接口"),//
    项目配置接口(IProjectConf.class.getSimpleName(), "项目配置接口", "项目配置接口"),//
    链式接口(ILineManager.class.getSimpleName(), "链式接口", "链式接口"),//
    资源接口(IResource.class.getSimpleName(), "资源接口", "资源接口"),//
    类模式接口(ClassModelConfigRe.class.getSimpleName(), "类模式接口", "类模式接口"),//
    运行模式接口(RunModelConfigRe.class.getSimpleName(), "运行模式接口", "运行模式接口"),//

    //
    ;

    private String code;
    private String name;
    private String mark;

    CoreSpiType(String code, String name, String mark) {
        this.setMark(mark);
        this.setName(name);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
