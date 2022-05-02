package com.brave.token.config;

import com.brave.token.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jbrave
 */

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        LoginFilter loginFilter = new LoginFilter();
        registrationBean.setFilter(loginFilter);
        List<String> urlList = new ArrayList<String>();
        urlList.add("/apia/token");
        urlList.add("/api/token");
        registrationBean.setUrlPatterns(urlList);
        return registrationBean;
    }
}
