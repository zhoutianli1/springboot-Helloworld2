package com.zhou.controller;

import com.zhou.mapper.DepartmentMapper;
import com.zhou.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;
    //显示部门类型列表==================================================================
    @GetMapping("/DepartmentsList")
    public String getDepartmentsList(Model model)
    {
        List<Department> departments = departmentMapper.getDepartmentsList();
        model.addAttribute("departments",departments);
        return "dep/list";
    }
    //添加类型==================================================================
    @RequestMapping ("dep/toAdd")
    //在部门列表页面点击添加，去添加部门类型页面
    public String toAddDepPage()
    {

        return "dep/add";
    }
    @PostMapping("dep/add")
    //填写部门信息，重定向到部门列表页面
    public String depAddd(Department department)
    {
        departmentMapper.addDepartment(department);
        return  "redirect:/DepartmentsList";
    }
    //修改部门类型
    @GetMapping("dep/add")
    public String toUpdatePage()
    {
        return "dep/update";
    }

}
