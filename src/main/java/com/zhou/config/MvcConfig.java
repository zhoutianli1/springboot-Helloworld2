package com.zhou.config;


import org.apache.tomcat.jni.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
//扩展springmvc,关于首页的默认访问路径

public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");          //关于首页的默认访问路径

    }

    @Bean //自定义的国际化组件放到这里就就可以生效了  ,对应config里的MyLocaleResolver
    public LocaleResolver localeResolver()
    {
        return new MyLocaleResolver();
    }


}
