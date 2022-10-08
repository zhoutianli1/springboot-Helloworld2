package com.zhou.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//#这里使用的不是druid-spring-boot-starter，不会自动装配，需要手动写一个配置类
// # 如果导入的是starter启动器，这里可以直接使用 druid: 进行配置;   这边用druid-spring-boot-starter好些
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")   //绑定yml
    @Bean
    public DataSource druidDataSource()
    {
        return new DruidDataSource();
    }


    //Druid有后台监控功能 过去配置在web.xml
    //德鲁伊后台监控是需要配置servlet的，这里就是springboot注册servlet的方式。现在注入到容器，配置类
    @Bean
    public ServletRegistrationBean statViewServlet()
    {
        ServletRegistrationBean<StatViewServlet> bean =new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //后台需要有人登陆，账号密码登陆
        //因为springboot内置了servlet容器，所以没有web.xml，替代方法：ServletRegistrationBean
        HashMap<String ,String> initParameters  =new HashMap<>();
        //增加配置
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");
        //允许谁可以访问
        initParameters.put("allow",""); //值为空“”，任何人都可以访问
        //

        bean.setInitParameters(initParameters);
        //Ze
        return bean;
    }
    //filter，用处不大 ，Filter 通过它可以开启 Druid 的 SQL 监控 功能，对 SQL 进行 监控
    @Bean
    public FilterRegistrationBean webStatFilter()
    {

         FilterRegistrationBean bean = new FilterRegistrationBean();
         bean.setFilter(new WebStatFilter());
         //可以过滤的请求，不对这些请求监控
        Map<String,String> initParameters = new HashMap<>();
        initParameters.put("exclusion","*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        System.out.println("开启sql监控功能");
         return bean;

    }
}
