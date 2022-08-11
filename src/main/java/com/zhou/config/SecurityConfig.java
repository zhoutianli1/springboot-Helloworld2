package com.zhou.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//AOP面向切面编程 ： 作为拦截器功能，这里和之前congig中写的的 LoginHandlerInterceptor功能冲突
@EnableWebSecurity //security配置类
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        //功能：首页所有人都可以访问， 功能页面只能对应有权限的人可以访问
        //请求授权规则
        http.authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        //没有权限，默认跳转到 /login  ，security是有一个默认的登录login页面
        //http.formLogin();
        http.formLogin().loginPage("/toLogin_sec"); //
        //开启注销
        http.logout().logoutSuccessUrl("/sec");

        //开启记住我功能   实现了cookie，默认保存2周
        http.rememberMe();
    }

    //默认情况下，登录的用户名是 user ，密码则是项目启动时随机生成的字符串，可以从启动的控制台日志中看到默认密码
    //1.配置文件配置用户名/密码
    //2. Java 配置用户名/密码   ;登录403把application配置文件里的security 账号密码删了.不要同时在2个位置配置
    //认证用户的角色信息
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //下面这两行配置表示在内存中配置了两个用户，也可以在jdbc数据库中认证 用户
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("root").roles("vip1","vip2","vip3").password(new BCryptPasswordEncoder().encode("123456"))
                .and()
                .withUser("admin1").roles("vip1").password(new BCryptPasswordEncoder().encode("123456"))
                .and()
                .withUser("admin2").roles("vip2").password(new BCryptPasswordEncoder().encode("123456"))
                .and()
                .withUser("admin3").roles("vip3").password(new BCryptPasswordEncoder().encode("123456"));
    }

}

