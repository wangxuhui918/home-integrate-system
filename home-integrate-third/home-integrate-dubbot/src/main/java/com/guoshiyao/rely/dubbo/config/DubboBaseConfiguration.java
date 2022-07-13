

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 郭诗瑶 18671020380@163.com
 *
 */

package com.guoshiyao.rely.dubbo.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.guoshiyao.rely.line.Line;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

//@EnableDubbo
public class DubboBaseConfiguration {

    @Bean
    @ConditionalOnMissingBean(ProtocolConfig.class) // 容器中如果没有这个类,那么自动配置这个类
    public ProtocolConfig protocolconfig() {
        ProtocolConfig sddf = new ProtocolConfig(Line.setting.get("home.dubbo.reference.agreement"),
                Line.setting.getInt("home.dubbo.reference.port"));
        sddf.setHost(Line.setting.get("home.dubbo.reference.host"));

        //		Dispatcher
//		all 所有消息都派发到线程池，包括请求，响应，连接事件，断开事件，心跳等。
//		direct 所有消息都不派发到线程池，全部在IO线程上直接执行。
//		message 只有请求响应消息派发到线程池，其它连接断开事件，心跳等消息，直接在IO线程上执行。
//		execution 只请求消息派发到线程池，不含响应，响应和其它连接断开事件，心跳等消息，直接在IO线程上执行。
//		connection 在IO线程上，将连接断开事件放入队列，有序逐个执行，其它消息派发到线程池。
//		ThreadPool
//		fixed 固定大小线程池，启动时建立线程，不关闭，一直持有。(缺省)
//		cached 缓存线程池，空闲一分钟自动删除，需要时重建。
//		limited 可伸缩线程池，但池中的线程数只会增长不会收缩。(为避免收缩时突然来了大流量引起的性能问题)
        Integer protocolthreadsnum = Line.setting.getInt("home.dubbo.protocolconfig.threads");
//        if (protocolthreadsnum != null) {
        sddf.setThreads(protocolthreadsnum);
        sddf.setDispatcher("all");
//        }
        String protocolthreadpool = Line.setting.get("home.dubbo.protocolconfig.threadpool");
//        if (protocolthreadpool != null && !protocolthreadpool.trim().equals("")) {
        sddf.setThreadpool(protocolthreadpool);
//        } else {
//            sddf.setThreadpool("fixed");
//        }
        sddf.setPayload(83886080);
        return sddf;
    }

    @Bean
    @ConditionalOnMissingBean(AnnotationBean.class) // 容器中如果没有这个类,那么自动配置这个类
    public AnnotationBean annotationbean() {
        AnnotationBean sddf = new AnnotationBean();
        sddf.setPackage(Line.setting.get("home.dubbo.reference.package"));
        return sddf;
    }

    @Bean
    @ConditionalOnMissingBean(ProviderConfig.class) // 容器中如果没有这个类,那么自动配置这个类
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setWait(3000);
        providerConfig.setPayload(128 * 1024 * 1024);
        providerConfig.setThreads(400);
        return providerConfig;
    }


    @Bean
    @ConditionalOnMissingBean(value = {RegistryConfig.class}) // 容器中如果没有这个类,那么自动配置这个类
    public RegistryConfig registryconfig() {
        RegistryConfig sddf = new RegistryConfig(Line.setting.get("home.dubbo.reference.url"));
        sddf.setProtocol(Line.setting.get("home.dubbo.reference.agreement"));
        sddf.setTimeout(Line.setting.getInt("home.dubbo.reference.timeout"));
        sddf.setClient("curator");
        sddf.setCheck(false);
        return sddf;
    }

    @Bean
    @ConditionalOnMissingBean(ApplicationConfig.class) // 容器中如果没有这个类,那么自动配置这个类
    public ApplicationConfig applicationconfig(RegistryConfig registryconfig) {
        ApplicationConfig sddf = new ApplicationConfig();
        sddf.setName(Line.setting.get("home.dubbo.reference.agreement"));
        sddf.setRegistry(registryconfig);
        sddf.setQosEnable(false);
        //		sddf.setQosAcceptForeignIp(false);
//		sddf.setQosPort(23455);
        return sddf;
    }

}
