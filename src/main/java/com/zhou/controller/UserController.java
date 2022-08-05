package com.zhou.controller;


import com.zhou.mapper.UserMapper;
import com.zhou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//dao->service->controller。这个项目没有什么要处理的逻辑，service和dao层一样，
// 所以不需要写service层，去年的图书馆里系统就要service层
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<User> queryUserList()
    {
        List<User> userList=userMapper.queryUserList();
        for (User user : userList)
        {
            System.out.println(user);
        }
        return userList;
        //33集合20分
    }
}
