package com.zhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @RequestMapping("/user/login")

    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session)
    {
        //业务   用户名任意 ；密码123456
        if(!StringUtils.isEmpty(username)&& "123456".equals(password))
        {
            //登陆时拿到用户session
            //看情况用token，前后端都不分离用啥token，又不用共享session为啥用啥redis存，看项目大小来顶，别盲目跟风
           session.setAttribute("loginUser",username);
            return  "redirect:/main.html";   //用form表单时必须需要用redirect
           // return "redirect:dashboard";
        }
        else
        {
            model.addAttribute("msg","用户名或者密码错误");
            return "index";  //如果使用了thymeleaf直接return和forward就不一样了，一个找模板，一个找控制器方法
        }
        //关于springboot的转发forward，重定向redirect，和直接return的区别
        //https://blog.csdn.net/yirandezhanghao/article/details/109307707
    }
}
