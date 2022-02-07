package com.mattsSc.lemoncash.configuration;

import com.mattsSc.lemoncash.configuration.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

    private final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/message/**");
    }

}
