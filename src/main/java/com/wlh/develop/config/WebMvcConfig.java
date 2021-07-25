package com.wlh.develop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport  {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源映射 url /**的请求 都映射到 classpath:/static/目录 即指定静态资源的根目录为/static目录
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 后续一些http拦截器可以定义在这里
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }
}
