package com.example.apidemo.config;

import com.example.apidemo.interceptor.APIInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Resource
    private APIInterceptor apiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor).addPathPatterns(APIInterceptor.API_PREFIX + "/**");
    }
}
