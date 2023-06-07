package com.example.petshop.common.config;

import com.example.petshop.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry reg) {
        reg.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//拦截哪些地址
                .excludePathPatterns(excludePattern());//排出哪些地址
    }

    public List<String> excludePattern(){
        List<String> ret = new ArrayList<String>();
        //ret.add("/Test/**");
        return ret;
    }
}