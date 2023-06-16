

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.swagger.bean;

import cn.bigcore.micro.FyyInitEnv;
import cn.bigcore.micro.config.config.impl.bean.FyyConfigEntryDetailsValues;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
public class FyySwaggerConfigBean implements WebMvcConfigurer {

    @Bean
    public Docket docket() {
        List<RequestParameter> parameters = new ArrayList<>();
//        RequestParameter p = new RequestParameterBuilder().name("dataset").description("统一JSONString数据入参")
//                .required(true).in(ParameterType.QUERY).required(true).build();
//        RequestParameter p1 = new RequestParameterBuilder().name("i18n").description("统一国际化编码").required(true)
//                .in(ParameterType.QUERY).required(true).build();
//        parameters.add(p);
//        parameters.add(p1);

        Docket doc = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                //  定义是否开启swagger，false为关闭，可以通过变量控制,默认为true
                .enable(true).select()
                //RequestHandlerSelectors 配置要扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_SWAGGER_BASEPACKAGE.getKey())))
                //paths() 过滤什么路径
                .paths(PathSelectors.any()).build().globalRequestParameters(parameters);
        return doc;
    }

    private ApiInfo apiInfo() {
        //作者信息
        springfox.documentation.service.Contact contact = new springfox.documentation.service.Contact("wanguhui918",
                "www.guoshiyao.com", "wangxuhui918@163.om");
        return new ApiInfo(FyyInitEnv.SettingInformation.setting.get(FyyConfigEntryDetailsValues.HOME_SWAGGER_NAME.getKey()), "你要快快长大哦", "1.0", "urn:tos", contact,
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }

    /**
     * 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            Field registrationsField = org.apache.commons.lang3.reflect.FieldUtils.getField(InterceptorRegistry.class,
                    "registrations", true);
            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils
                    .getField(registrationsField, registry);
            if (registrations != null) {
                for (InterceptorRegistration interceptorRegistration : registrations) {
                    interceptorRegistration.excludePathPatterns("/swagger**/**").excludePathPatterns("/webjars/**")
                            .excludePathPatterns("/v3/**").excludePathPatterns("/doc.html");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        registry.addResourceHandler("favicon.ico").addResourceLocations("classpath:/static/favicon.ico");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
