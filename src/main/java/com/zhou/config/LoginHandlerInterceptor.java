package com.zhou.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//拦截器只能对action请求起作用，而过滤器则可以对几乎所有的请求起作用。
//在action的生命周期中，拦截器可以多次被调用，而过滤器只能在容器初始化时被调用一次。
//拦截器可以获取IOC容器中的各个bean，而过滤器就不行，这点很重要，在拦截器里注入一个service，可以调用业务逻辑。
public class LoginHandlerInterceptor implements HandlerInterceptor{ //拦截器要实现这个接口
    @Override
    //登陆拦截器
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //登陆成功后 ,用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");

        if(loginUser==null) //没有登陆
        {
            request.setAttribute("msg","，没有登陆，请先登陆，被拦截器拦截了");
            request.getRequestDispatcher("index.html").forward(request,response); //转发
            return false;
        }
        else
        {
            return true;
        }//拦截器写好了下一步，到bean里面注册

    }

}
