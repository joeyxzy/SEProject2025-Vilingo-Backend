package com.Vilingo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:8081") // 明确授权了 localhost:8081
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许了所有常用方法
                .allowedHeaders("*") // 允许了所有请求头
                .allowCredentials(true)
                .maxAge(3600);
    }
}