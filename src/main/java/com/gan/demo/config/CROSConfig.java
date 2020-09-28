package com.gan.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CROSConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/**")//可以跨域访问的路径
                .allowedOrigins("*")//可以跨域访问的源
                .allowedMethods("POST", "GET", "PUT", "DELETE")//可以请求的方式
                .maxAge(3600)
                .allowCredentials(true);//是否允许发送cookie
    }
}
