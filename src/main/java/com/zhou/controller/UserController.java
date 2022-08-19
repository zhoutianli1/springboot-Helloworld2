package com.zhou.controller;

import com.zhou.mapper.UserMapper;
import com.zhou.pojo.Department;
import com.zhou.pojo.Employee;
import com.zhou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.List;
//dao->service->controller。这个项目没有什么要处理的逻辑，service和dao层一样，
// 所以不需要写service层，去年的图书馆里系统就要service层
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    //33集合20分
    @RequestMapping("/userList")
    public String list(Model model)
    {
        //跳转到user/list.html之前要先  拿到所有的user信息
        List<User> userList=userMapper.queryUserList();
        model.addAttribute("userList",userList);
        System.out.println(userList);

        return "user/list";
        //跳转到用户列表user/list.html
    }

    //去添加员工页面
    @GetMapping("/userAdd")
    public String toAddPage(Model model)
    {

        return "emp/add";
    }
    //添加员工业务逻辑 ： 点击添加员工，跳转到"emp/add.html"页面。在add页面输入数据，点击添加，将数据保存到数据库
    @PostMapping("/userAdd")
    public String addEmp(User user)   //会自动封装成对象吗
    {
        //将添加的员工保存数据到数据库，   add.html框架自动封装了Employee对象，只要属性名一样
        userMapper.addUser(user);
        return "redirect:/empList";
    }


}
