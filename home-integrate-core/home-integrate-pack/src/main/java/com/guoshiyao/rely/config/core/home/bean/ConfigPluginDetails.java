package com.guoshiyao.rely.config.core.home.bean;

import com.guoshiyao.rely.apollo.config.ApolloExtendsConfig;
import com.guoshiyao.rely.config.line.LineDefaultManager;
import com.guoshiyao.rely.config.newplugin.ClassCreateConfig;
import com.guoshiyao.rely.config.newplugin.RunCreateConfig;
import com.guoshiyao.rely.data.plugin.DataExtendConfig;
import com.guoshiyao.rely.dubbo.plugin.DubboExtendConfig;
import com.guoshiyao.rely.flyway.plugin.FlywayExtendConfig;
import com.guoshiyao.rely.log.plugin.LogExtendConfig;
import com.guoshiyao.rely.message.MessageExtendConfig;
import com.guoshiyao.rely.minio.plugin.MinIOExtendConfig;
import com.guoshiyao.rely.mybatis.plugin.MyBatisConfig;
import com.guoshiyao.rely.mybatisplus.plugin.MyBatisPlusConfig;
import com.guoshiyao.rely.port.PortConfig;
import com.guoshiyao.rely.redis.plugin.RedisConfig;
import com.guoshiyao.rely.swagger.plugin.SwaggerConfig;
import com.guoshiyao.rely.systemconfig.SystemConfig;
import com.guoshiyao.rely.systemconfig.SystemExtendConfig;
import com.guoshiyao.rely.utils.ResourceFindUrl;

public enum ConfigPluginDetails {

    PLUGIN_DETAIL_2(ConfigPluginType.CONFIG_PLUGIN_TYPE_3, LineDefaultManager.class.getName(), true),//
    PLUGIN_DETAIL_3(ConfigPluginType.CONFIG_PLUGIN_TYPE_4, ResourceFindUrl.class.getName(), true),//
    PLUGIN_DETAIL_4(ConfigPluginType.CONFIG_PLUGIN_TYPE_5, ClassCreateConfig.class.getName(), true),//
    PLUGIN_DETAIL_4_1(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, RunCreateConfig.class.getName(), true),//
    //
    PLUGIN_DETAIL_5(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, DataExtendConfig.class.getName(), true),//
    PLUGIN_DETAIL_6(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, MinIOExtendConfig.class.getName(), true),//
    PLUGIN_DETAIL_7(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, MessageExtendConfig.class.getName(), true),//
    PLUGIN_DETAIL_8(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, FlywayExtendConfig.class.getName(), true),//
    PLUGIN_DETAIL_9(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, SwaggerConfig.class.getName(), true),//
    PLUGIN_DETAIL_10(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, RedisConfig.class.getName(), true),//
    PLUGIN_DETAIL_11(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, DubboExtendConfig.class.getName(), true),//
    PLUGIN_DETAIL_12(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, SystemConfig.class.getName(), true),//
    PLUGIN_DETAIL_13(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, MyBatisConfig.class.getName(), true),//
    PLUGIN_DETAIL_14(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, MyBatisPlusConfig.class.getName(), true),//
    PLUGIN_DETAIL_15(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, PortConfig.class.getName(), true),//
    PLUGIN_DETAIL_16(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, SystemExtendConfig.class.getName(), true),//
    PLUGIN_DETAIL_17(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, ApolloExtendsConfig.class.getName(), true),//
    PLUGIN_DETAIL_18(ConfigPluginType.CONFIG_PLUGIN_TYPE_6, LogExtendConfig.class.getName(), true),//

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
