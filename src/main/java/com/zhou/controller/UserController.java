package com.zhou.controller;

import com.zhou.mapper.UserMapper;
import com.zhou.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.List;
//dao->service->controller。这个项目没有什么要处理的逻辑，service和dao层一样，
// 所以不需要写service层，去年的图书馆里系统就要service层
@Api(tags="用户操作controller")
@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    //用户列表
    @RequestMapping("/userList")
    public String list(Model model)
    {

        //跳转到user/list.html之前要先  拿到所有的user信息
        List<User> userList=userMapper.queryUserList();
        model.addAttribute("userList",userList);
        System.out.println("成功拿到用户");

        return "user/list";
        //跳转到用户列表user/list.html
    }


    //去添加员工页面
    @GetMapping("/userAdd")
    public String toAddPage(Model model)
    {
        //
        System.out.println("save=>employee");

        return "user/add";
    }
    //添加员工业务逻辑 ： 点击添加员工，跳转到"emp/add.html"页面。在add页面输入数据，点击添加，将数据保存到数据库
    @PostMapping("/userAdd")
    public String addEmp(User user)   //会自动封装成对象吗。将添加的员工保存数据到数据库，   add.html框架自动封装了对象，只要属性名一样
    {
        //将添加的员工保存数据到数据库，   add.html框架自动封装了Employee对象，只要属性名一样
        userMapper.addUser(user);
        return "redirect:/userList";
    }


    /**
     @PathVariable是spring3.0的一个新功能：接收请求路径中占位符的值
     @PathVariable("xxx")
     通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“)
     @RequestMapping(value=”user/{id}/{name}”)
     */
    //修改，去修改页面
    @GetMapping("/userUpdate/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model)
    {
        //在修改之前得先查出待修改员工的数据
        User user= userMapper.queryUserById(id);  //一个员工数据
        System.out.println(user);
        model.addAttribute("user",user);

        return "user/update";
    }

    @PostMapping("/userUpdate")
    public String updateEmp(User user)
    {
        userMapper.updateUser(user);

        return "redirect:/userList";
    }


    //删除
    @ApiOperation("删除用户根据Id")  //给这个方法加上注释在Swagger
    @GetMapping("/userDel/{id}")
    public String delEmp(@PathVariable("id") Integer id)
    {

        userMapper.deleteUser(id);  //一个员工数据

        return "redirect:/userList";
    }

    //只要我们接口返回值存在改实体类，这个实体类就会被扫描到Swagger的model中
    @ApiOperation("Swagger的试验")  //给这个方法加上注释在Swagger
    @GetMapping("/sw")
    public User sw() {
        return new User();
    }


}
