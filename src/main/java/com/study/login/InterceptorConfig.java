package com.study.login;

import com.study.login.interceptor.LogInterceptor;
import com.study.login.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;
    private final LoginCheckInterceptor loginCheckInterceptor;

    @Autowired
    public InterceptorConfig(LogInterceptor logInterceptor, LoginCheckInterceptor loginCheckInterceptor) {
        this.logInterceptor = logInterceptor;
        this.loginCheckInterceptor = loginCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");
        registry.addInterceptor(loginCheckInterceptor)
                .order(2)
                .addPathPatterns("/members");
    }


}
