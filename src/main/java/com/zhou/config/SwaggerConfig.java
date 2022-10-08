package com.zhou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import java.util.ArrayList;

@Configuration //等价于@component
@EnableSwagger2 //开启Swagger2
public class SwaggerConfig {
    /**
        这里配置多个分组，docket，docket1。。。。。到时候多人开发，每个人在不同的Config下面写自己的docket，但是通过@bean注册到一起
     */
    @Bean
    public Docket docket(Environment environment)   //Docket implements DocumentationPlugin 继承了一个文档插件
    {
        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev");
        // 判断当前是否处于该环境
        // 通过 enable() 接收此参数判断是否要显示
        boolean flag = environment.acceptsProfiles(of);  //flag值为：true 和 flase

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)//是否启动Swagger，值为flase和true 。如果当前环境为dev，flag=true，开启swagger
                .select()
                //RequestHandlerselectors,配置要扫描接口的方式
                //basePackage:指定要扫描的包,一般用这个
                //any():扫描全部
                //none():不扫描
                //withClassAnnotation:扫描类上的注解，多数是一个注解的反射对象
                //withMethodAnnotation:扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.zhou.config.shiroConfig"))
                //过滤路径,把这个路径下面的接口忽视
                .paths(PathSelectors.ant("/zhou/**"))
                .build()
                .groupName("周");


    }

    @Bean
    public Docket docket1()   //Docket implements DocumentationPlugin 继承了一个文档插件
    {

        return new Docket(DocumentationType.SWAGGER_2).groupName("a");

    }

    private ApiInfo apiInfo()
    {
        Contact contact = new Contact("zhou", "http://xxx.xxx.com/联系人访问链接", "2911479713@qq.com");
        return new ApiInfo(
                "周-swagger API文档日志",
                "学习中",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
