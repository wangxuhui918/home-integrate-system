

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.dubbo.config.tx;

import com.alibaba.dubbo.config.ConsumerConfig;
import cn.bigcore.micro.BaseEv;
import cn.bigcore.micro.core.configration.home.impl.bean.ConfigDetails;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

// @ComponentScan("com.codingapi.tx")
public class DubboAutoConfiguration3 {

    @Bean
    @ConditionalOnMissingBean(ConsumerConfig.class) // 容器中如果没有这个类,那么自动配置这个类
    public ConsumerConfig consumerconfig() {
        ConsumerConfig sddf = new ConsumerConfig();
        sddf.setTimeout(BaseEv.SettingInformation.setting.getInt(ConfigDetails.HOME_DUBBO_REFERENCE_TIMEOUT.getKey()));
        sddf.setCheck(false);
        sddf.setFilter("transactionFilter");
        return sddf;
    }

}
