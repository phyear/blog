package com.code.report.blog.config;

import com.code.report.blog.infra.util.SpringUtil;
import com.code.report.blog.interceptor.VerifyTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhaotianxin
 * @date 2021-02-07 10:46
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements  WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VerifyTokenInterceptor());
    }

    @Bean
    public SpringUtil beanUtil(){
        return new SpringUtil();
    }
}
