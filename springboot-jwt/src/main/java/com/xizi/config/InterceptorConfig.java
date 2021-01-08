package com.xizi.config;

import com.xizi.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    //添加自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
//                .addPathPatterns("/user/test")          //其他接口token都验证
                .addPathPatterns("/**")          //所有接口token都验证
                .excludePathPatterns("/user/**");  //所有的用户接口都放行
    }
}
