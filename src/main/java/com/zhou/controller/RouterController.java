package com.zhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//用于安全sec
public class RouterController {
    @RequestMapping({"/sec","/index_sec"})
    public String index_sec()
    {
        return "index_security";
    }
    @RequestMapping("/toLogin_sec")
    public String toLogin_sec()
    {
        return "views/login";
    }
    @RequestMapping("/level1/{id}")
    public String level1(@PathVariable("id") int id)
    {
        return "views/level1/"+4;
    }    //这个可以访问说明html页面有错
    @RequestMapping("/level2/{id}")
    public String level2(@PathVariable("id") int id)
    {
        return "views/level2/"+id;
    }
    @RequestMapping("/level3/{id}")
    public String level3(@PathVariable("id") int id)
    {
        return "views/level3/"+id;
    }

}
