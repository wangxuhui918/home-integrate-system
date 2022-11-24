package com.guoshiyao.rely.core.configration.home.impl.enumtype.bean.plugin;

import com.guoshiyao.rely.core.configration.home.impl.enumtype.CoreBuiltInImpl;
import com.guoshiyao.rely.core.configration.project.impl.enumtype.ProjectBuiltInImpl;

public enum ConfigPluginDetails {
    PLUGIN_DETAIL_0(ConfigPluginType.CONFIG_PLUGIN_TYPE_1, CoreBuiltInImpl.class.getTypeName(), true),//
    PLUGIN_DETAIL_1(ConfigPluginType.CONFIG_PLUGIN_TYPE_2, ProjectBuiltInImpl.class.getTypeName(), true),//
    PLUGIN_DETAIL_2(ConfigPluginType.CONFIG_PLUGIN_TYPE_3, "com.guoshiyao.rely.line.re.LineDefaultManager", true),//
    PLUGIN_DETAIL_3(ConfigPluginType.CONFIG_PLUGIN_TYPE_4, "com.guoshiyao.rely.utils.ResourceFindUrl", true),//
    PLUGIN_DETAIL_4(ConfigPluginType.CONFIG_PLUGIN_TYPE_5, "com.guoshiyao.rely.config.newplugin.CreateConfig", true),//
    //
    PLUGIN_DETAIL_5(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.data.plugin.DataExtendConfig", true),//
    PLUGIN_DETAIL_6(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.minio.plugin.MinIOExtendConfig", true),//
    PLUGIN_DETAIL_7(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.message.MessageExtendConfig", true),//
    PLUGIN_DETAIL_8(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.flyway.plugin.FlywayExtendConfig", true),//
    PLUGIN_DETAIL_9(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.swagger.plugin.SwaggerConfig", true),//
    PLUGIN_DETAIL_10(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.redis.plugin.RedisConfig", true),//
    PLUGIN_DETAIL_11(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.dubbo.plugin.DubboExtendConfig", true),//
    PLUGIN_DETAIL_12(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.systemconfig.SystemConfig", true),//
    PLUGIN_DETAIL_13(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.mybatis.plugin.MyBatisConfig", true),//
    PLUGIN_DETAIL_14(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.mybatisplus.plugin.MyBatisPlusConfig", true),//
    PLUGIN_DETAIL_15(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.port.PortConfig", true),//
    PLUGIN_DETAIL_16(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.systemconfig.SystemExtendConfig", true),//
    PLUGIN_DETAIL_17(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.apollo.config.ApolloExtendsConfig", true),//
    PLUGIN_DETAIL_18(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, "com.guoshiyao.rely.log.plugin.LogExtendConfig", true),//

    ;

    private ConfigPluginType type;
    private String path;
    private boolean power;

    ConfigPluginDetails(ConfigPluginType type, String path, boolean power) {
        this.setPower(power);
        this.setPath(path);
        this.setType(type);

    }


    public ConfigPluginType getType() {
        return type;
    }

    public void setType(ConfigPluginType type) {
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
