

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.dubbo.config;

import com.alibaba.dubbo.config.ConsumerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class DubboConsumerConfig {

    @Bean
    @ConditionalOnMissingBean(ConsumerConfig.class) // 容器中如果没有这个类,那么自动配置这个类
    public ConsumerConfig consumerconfig() {
        ConsumerConfig sddf = new ConsumerConfig();
        sddf.setTimeout(12000);
        sddf.setCheck(false);
//        ServiceLoader<Filter> serviceloader = ServiceLoader.load(Filter.class);
//        for (Filter filter : serviceloader) {
//            if (filter.getClass().getInterfaces()[0].getName().equals(DubboServiceFilterLine.class.getName())) {
//                DubboServiceFilterLine filter1 = (DubboServiceFilterLine) filter;
//                System.out.println("out." + filter1.getName());
//                sddf.setFilter(filter1.getName());
//            }
//        }

//		sddf.setFilter("dubboServiceFilter");
        return sddf;
    }

}
