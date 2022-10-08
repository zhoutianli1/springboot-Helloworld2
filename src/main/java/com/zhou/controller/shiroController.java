package com.zhou.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class shiroController {


    @RequestMapping({"/","index"})
    public String toIndex(Model model)
    {
        model.addAttribute("msg","默认界面（即登录界面）");
        return "index";    //默认登录界面
    }

    @RequestMapping("/user/login")   //在index.html 点击登录后的处理
    public String Login(String name,String pwd,Model model)
    {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装当前用户
        UsernamePasswordToken token = new UsernamePasswordToken(name,pwd);

        System.out.println("当前用户名和密码"+name+";"+pwd);

        //执行登录在subiect这个类，但是真实的用户操作(与数据库的交互）在Myrealm的认证中
        try {
            subject.login(token); //执行登录的方法，没有异常说明“登录成功”.返回主页dashboard.html
            //return  "redirect:/main.html";
            return "dashboard.html";
        } catch (UnknownAccountException e) { //用户不存在返回默认登录页面
            model.addAttribute("msg","用户名出错");
            return "index";
        }catch (IncorrectCredentialsException e){ //密码不存在
            model.addAttribute("msg","密码出错");
            return "index";
        }
    }



    //用户权限不足时
    @RequestMapping("/unauth")
    @ResponseBody
    public String unauth(){
        return "未授权没有访问权限";
    }
    //退出操作
    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();  //注销当前用户
        subject.logout();//退出操作
        return "index";
    }
    @GetMapping("todashboard")
    public String todashboard(){

        return "dashboard.html";
    }
}
