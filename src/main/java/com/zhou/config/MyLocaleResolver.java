package com.zhou.config;



import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {


    @Override//解析请求
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求中的语言参数
        String language=request.getParameter("l");
        Locale locale = Locale.getDefault();//若没有使用默认的
        //若请求的链接携带了国际化参数
        if(!StringUtils.isEmpty(language))
        {
            //zh_CN
            String[] split = language.split("_");  //["zh","CN"]
            locale= new Locale(split[0],split[1]);
        }
        return locale;      //得到后放到bean里面
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
