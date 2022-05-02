package com.brave.token.config;

import com.brave.token.intercept.LoginIntercept;
import com.brave.token.intercept.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author jbrave
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginIntercept loginIntercept;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(loginIntercept).addPathPatterns("/api/token");
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/api/token");
    }
}
