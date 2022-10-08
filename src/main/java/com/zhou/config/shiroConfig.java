package com.zhou.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zhou.shiro.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
/**
 在里面3个对象：
 ShiroFilterFactoryBean
 DefaultWebSecurityManager
 realm对象，需要自己定义这个类

 分别对应shiro3大核心对象：
 subject
 securityManager
 realm

 使用时配置顺序 倒着配  ：realm对象，需要自己定义这个类-》DefaultWebSecurityManager-》ShiroFilterFactoryBeanShiroFilterFactoryBean
 */
@Configuration
public class shiroConfig {

    @Bean//注意：用来设置哪些请求过滤
    //3装配第三个 bean ShiroFilterFactoryBean，这是 Shiro 自带的一个 Filter 工厂实例，所有的“认证和授权”判断都是由这个 bean 生成的 Filter 对象来完成的，
    // 开发者只需要定义规则，进行配置，具体的执行者全部由 Shiro 自己创建的 Filter 来完成。 （参数需要第2步的 manager——管理用户对象）
    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //关联manager，设置安全管理器
        factoryBean.setSecurityManager(manager); //注意授权过滤器要放在拦截过滤器前面

        Map<String,String> map = new HashMap<>();

        //授权过滤器：
        map.put("/userList","perms[LV1]");   //去用户管理必须要，有管理员权限


        /**
         * 没有登陆前，考研访问的页面包括 登录界面、img、/js这些
         * 其他的路径，页面只能登陆后才可以访问
         */
        //拦截过滤器:，shiro只拦截设置的路径。 shiro默认登录页面login.html
        // 所有的css文件，此过滤器代表放过拦截 不需要登录也能访问
        map.put("/css/**", "anon");
        // 放过登录页面拦截
        map.put("/index.html", "anon");
        map.put("/", "anon");
        map.put("/img/**", "anon");
        map.put("/js/**", "anon");
        /// **代表所有路径 除以上路径外都拦截 。要登录了才能访问
        map.put("/**", "authc");

        factoryBean.setFilterChainDefinitionMap(map);    //设置一个过滤器链，需要参数 map：Map<String, String> filterChainDefinitionMap


        //authc：必须认证（登录）。设置登录页面，通过/user/login请求跳转到login.html
        factoryBean.setLoginUrl("/user/login");
        //未授权页面
        factoryBean.setUnauthorizedUrl("/unauth");
        return factoryBean;
        /**
         * 认证过滤器：
         * anon：无需认证即可访问，游客身份。
         * authc：必须认证（登录）才能访问。
         * authcBasic：需要通过 httpBasic 认证。
         * user：不一定已通过认证，只要是曾经被 Shiro 记住过登录状态的用户就可以正常发起请求，比如 rememberMe。
         *
         * 授权过滤器:
         * perms：必须拥有对某个资源的访问权限（授权）才能访问。
         * role：必须拥有某个角色权限才能访问。
         * port：请求的端口必须为指定值才可以访问。
         * rest：请求必须是 RESTful，method 为 post、get、delete、put。
         * ssl：必须是安全的 URL 请求，协议为 HTTPS。
         */
    }
    @Bean//2然后需要创建第二个 bean示例 DefaultWebSecurityManager，并且将 MyRealm注入到 DefaultWebSecurityManager bean 中，完成注册。（需要MyRealm）
    public DefaultWebSecurityManager manager(@Qualifier("myRealm") MyRealm myRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //关联用户的Realm
        manager.setRealm(myRealm);
        return manager;
    }

    @Bean //1第一个是自定义过滤器 MyRealm，我们的业务逻辑 "全部定义"在这个 bean 中。
    public MyRealm myRealm(){
        return new MyRealm();
    }


    //整合shiro和thymeleaf
    @Bean
    public ShiroDialect getShiroDialect()
    {
        return new ShiroDialect();
    }


}