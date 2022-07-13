

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.dubbo.config.tx;

import com.alibaba.dubbo.config.ConsumerConfig;
import com.guoshiyao.rely.line.Line;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

// @ComponentScan("com.codingapi.tx")
public class DubboAutoConfiguration3 {

    @Bean
    @ConditionalOnMissingBean(ConsumerConfig.class) // 容器中如果没有这个类,那么自动配置这个类
    public ConsumerConfig consumerconfig() {
        ConsumerConfig sddf = new ConsumerConfig();
        sddf.setTimeout(Line.setting.getInt("home.dubbo.reference.timeout"));
        sddf.setCheck(false);
        sddf.setFilter("transactionFilter");
        return sddf;
    }

}
