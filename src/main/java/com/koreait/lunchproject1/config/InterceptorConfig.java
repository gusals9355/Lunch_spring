package com.koreait.lunchproject1.config;

import com.koreait.lunchproject1.interceptor.LoginInterceptor;
import com.koreait.lunchproject1.interceptor.RankedUpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    RankedUpInterceptor rankedUpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**/*.do");
        registry.addInterceptor(rankedUpInterceptor)
                .addPathPatterns("/user/login.go")
                .addPathPatterns("/board/write.do")
                .addPathPatterns("/board/delBoard.do");
    }
}