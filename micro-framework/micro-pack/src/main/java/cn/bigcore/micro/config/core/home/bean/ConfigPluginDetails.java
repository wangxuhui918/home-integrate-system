package cn.bigcore.micro.config.core.home.bean;

import cn.bigcore.micro.data.plugin.DataExtendConfig;
import cn.bigcore.micro.dubbo.plugin.DubboExtendConfig;
import cn.bigcore.micro.flyway.plugin.FlywayExtendConfig;
import cn.bigcore.micro.log.plugin.LogExtendConfig;
import cn.bigcore.micro.message.MessageExtendConfig;
import cn.bigcore.micro.systemconfig.SystemExtendConfig;
import cn.bigcore.micro.apollo.config.ApolloExtendsConfig;
import cn.bigcore.micro.config.line.LineDefaultManager;
import cn.bigcore.micro.config.newplugin.ClassCreateConfig;
import cn.bigcore.micro.config.newplugin.RunCreateConfig;
import cn.bigcore.micro.minio.plugin.MinIOExtendConfig;
import cn.bigcore.micro.mybatis.plugin.MyBatisConfig;
import cn.bigcore.micro.mybatisplus.plugin.MyBatisPlusConfig;
import cn.bigcore.micro.port.PortConfig;
import cn.bigcore.micro.redis.plugin.RedisConfig;
import cn.bigcore.micro.swagger.plugin.SwaggerConfig;
import cn.bigcore.micro.systemconfig.SystemConfig;
import cn.bigcore.micro.utils.ResourceFindUrl;

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
