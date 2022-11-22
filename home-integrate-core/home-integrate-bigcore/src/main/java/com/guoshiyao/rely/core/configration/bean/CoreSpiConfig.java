package com.guoshiyao.rely.core.configration.bean;

import com.guoshiyao.rely.core.configration.home.impl.CoreBuiltInImpl;
import com.guoshiyao.rely.core.configration.project.impl.ProjectBuiltInImpl;

public enum CoreSpiConfig {
    系统配置接口(CoreSpiType.系统配置接口, CoreBuiltInImpl.class.getTypeName(), 0, true),
    //
    项目配置接口(CoreSpiType.项目配置接口, ProjectBuiltInImpl.class.getTypeName(), 0, true),

    链式接口(CoreSpiType.链式接口, "com.guoshiyao.rely.line.re.LineDefaultManager", 0, true),

    资源接口(CoreSpiType.资源接口, "com.guoshiyao.rely.utils.ResourceFindUrl", 0, true),

    类模式接口(CoreSpiType.类模式接口, "com.guoshiyao.rely.config.newplugin.CreateConfig", 0, true),

    运行模式接口200(CoreSpiType.运行模式接口, "com.guoshiyao.rely.data.plugin.DataExtendConfig", 200, true),//
    运行模式接口300(CoreSpiType.运行模式接口, "com.guoshiyao.rely.minio.plugin.MinIOExtendConfig", 300, true),//
    运行模式接口400(CoreSpiType.运行模式接口, "com.guoshiyao.rely.message.MessageExtendConfig", 400, true),//
    运行模式接口500(CoreSpiType.运行模式接口, "com.guoshiyao.rely.flyway.plugin.FlywayExtendConfig", 500, true),//
    运行模式接口600(CoreSpiType.运行模式接口, "com.guoshiyao.rely.swagger.plugin.SwaggerConfig", 600, true),//
    运行模式接口700(CoreSpiType.运行模式接口, "com.guoshiyao.rely.redis.plugin.RedisConfig", 700, true),//
    运行模式接口800(CoreSpiType.运行模式接口, "com.guoshiyao.rely.dubbo.plugin.DubboExtendConfig", 800, true),//
    运行模式接口900(CoreSpiType.运行模式接口, "com.guoshiyao.rely.systemconfig.SystemConfig", 900, true),//
    运行模式接口1000(CoreSpiType.运行模式接口, "com.guoshiyao.rely.mybatis.plugin.MyBatisConfig", 1000, true),//
    运行模式接口1100(CoreSpiType.运行模式接口, "com.guoshiyao.rely.mybatisplus.plugin.MyBatisPlusConfig", 1100, true),//
    运行模式接口1200(CoreSpiType.运行模式接口, "com.guoshiyao.rely.port.PortConfig", 1200, true),//
    运行模式接口1300(CoreSpiType.运行模式接口, "com.guoshiyao.rely.systemconfig.SystemExtendConfig", 1300, true),//
    运行模式接口1400(CoreSpiType.运行模式接口, "com.guoshiyao.rely.apollo.config.ApolloExtendsConfig", 1400, true),//
    运行模式接口1500(CoreSpiType.运行模式接口, "com.guoshiyao.rely.log.plugin.LogExtendConfig", 1500, true),//


    ;

    private CoreSpiType type;
    private String path;
    private Integer sort;
    private boolean power;


    CoreSpiConfig(CoreSpiType type, String path, Integer sort, boolean power) {
        this.setPower(power);
        this.setPath(path);
        this.setType(type);
        this.setSort(sort);

    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public CoreSpiType getType() {
        return type;
    }

    public void setType(CoreSpiType type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }
}
