package cn.bigcore.micro.line;

import cn.bigcore.micro.config.FyyLineSystemConfigPort;
import cn.bigcore.micro.config.config.FyyConfigPluginType;
import cn.bigcore.micro.data.FyyLineData;
import cn.bigcore.micro.dubbo.plugin.FyyLineDubbo;
import cn.bigcore.micro.flyway.plugin.FyyLineFlyway;
import cn.bigcore.micro.log.plugin.FyyLineLogBack;
import cn.bigcore.micro.message.FyyLineMessage;
import cn.bigcore.micro.systemconfig.FyyLineSystem0;
import cn.bigcore.micro.apollo.config.FyyLineApollo;
import cn.bigcore.micro.config.newplugin.FyyLineCreateProperties;
import cn.bigcore.micro.config.newplugin.FyyLineCreateRunModel;
import cn.bigcore.micro.minio.plugin.FyyLineMinIO;
import cn.bigcore.micro.mybatis.plugin.FyyLineMybatis;
import cn.bigcore.micro.mybatisplus.plugin.FyyLineMyBatisPlus;
import cn.bigcore.micro.redis.plugin.FyyLineRedisConfig;
import cn.bigcore.micro.swagger.plugin.FyyLineSwagger;
import cn.bigcore.micro.systemconfig.FyyLineSystem1;
import cn.bigcore.micro.utils.FyyResource;

public enum FyyLineGroups {

    PLUGIN_DETAIL_2(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_3, FyyLineDefaultManager.class.getName(), true),//
    PLUGIN_DETAIL_3(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_4, FyyResource.class.getName(), true),//
    PLUGIN_DETAIL_4(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_5, FyyLineCreateProperties.class.getName(), true),//
    PLUGIN_DETAIL_4_1(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineCreateRunModel.class.getName(), true),//
    //
    PLUGIN_DETAIL_5(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineData.class.getName(), true),//
    PLUGIN_DETAIL_6(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineMinIO.class.getName(), true),//
    PLUGIN_DETAIL_7(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineMessage.class.getName(), true),//
    PLUGIN_DETAIL_8(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineFlyway.class.getName(), true),//
    PLUGIN_DETAIL_9(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineSwagger.class.getName(), true),//
    PLUGIN_DETAIL_10(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineRedisConfig.class.getName(), true),//
    PLUGIN_DETAIL_11(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineDubbo.class.getName(), true),//
    PLUGIN_DETAIL_12(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineSystem1.class.getName(), true),//
    PLUGIN_DETAIL_13(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineMybatis.class.getName(), true),//
    PLUGIN_DETAIL_14(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineMyBatisPlus.class.getName(), true),//
    PLUGIN_DETAIL_15(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineSystemConfigPort.class.getName(), true),//
    PLUGIN_DETAIL_16(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineSystem0.class.getName(), true),//
    PLUGIN_DETAIL_17(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineApollo.class.getName(), true),//
    PLUGIN_DETAIL_18(FyyConfigPluginType.CONFIG_PLUGIN_TYPE_6, FyyLineLogBack.class.getName(), true),//

    ;

    private FyyConfigPluginType type;
    private String path;
    private boolean power;

    FyyLineGroups(FyyConfigPluginType type, String path, boolean power) {
        this.setPower(power);
        this.setPath(path);
        this.setType(type);

    }


    public FyyConfigPluginType getType() {
        return type;
    }

    public void setType(FyyConfigPluginType type) {
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
