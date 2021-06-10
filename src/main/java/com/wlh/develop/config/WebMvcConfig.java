package com.wlh.develop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport  {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源映射 url /static/**的请求 都映射到 classpath:/static/目录 即指定静态资源的根目录为/static目录
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(registry);
//    }

}
