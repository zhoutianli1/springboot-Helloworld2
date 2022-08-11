package com.zhou.controller;

import com.zhou.dao.DepartmentDao;
import com.zhou.dao.EmployeeDao;
import com.zhou.pojo.Department;
import com.zhou.pojo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Collection;
//显示员工列表 跳转到emp/list.html
//　RestController = Controller + ResponseBody，例如在项目当中，你加上的是RestController,那么返回的内容是你return中的内容，如果是return "Hello World"，页面显示的就是Hello World。
// 但是如果你加上Controller，返回的是return中对应的页面,比如return “hello”,页面的名称是hello，此时如果你的项目中没有hello这个页面，那么必然会出现404的错误，
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @RequestMapping("/empList")
    public String list(Model model)
    {
        Collection<Employee> employees=employeeDao.getAll();
        model.addAttribute("employees",employees);
        System.out.println(employees);
        return "emp/list";
    }
    //去添加员工页面
    @GetMapping("/empAdd")
    public String toAddPage(Model model)
    {
        //	<label>department</label>
        //	<select class="form-control" name="department.id">
        //要在添加之前拿到所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }
    //添加员工业务逻辑 ： 点击添加员工，跳转到"emp/add.html"页面。在add页面输入数据，点击添加，将数据保存到数据库
    @PostMapping("/empAdd")
    public String addEmp(Employee employee)
    {
        //将添加的员工保存数据到数据库，   add.html框架自动封装了Employee对象，只要属性名一样
        System.out.println("save=>employee");
        employeeDao.save(employee);
        return "redirect:/empList";
    }

    //修改员工， 去员工的修改页面
    @GetMapping("/empUpdate/{id}")
     public String toUpdateEmp(@PathVariable("id") Integer id,Model model)
     {
         //在修改之前得先查出待修改员工的数据
         Employee employee= employeeDao.getEmployeeById(id);  //一个员工数据
         model.addAttribute("employee",employee);

         //要在之前拿到所有部门的信息
         Collection<Department> departments = departmentDao.getDepartments();
         model.addAttribute("departments",departments);
         return "emp/update";
     }

     @PostMapping("/empUpdate")
     public String updateEmp(Employee employee)
     {
         employeeDao.save(employee);

         return "redirect:/empList";
     }

    //删除
    @GetMapping("/empDel/{id}")
    public String delEmp(@PathVariable("id") Integer id)
    {

        employeeDao.delete(id);  //一个员工数据

        return "redirect:/empList";
    }
    //退出登录(删除用户session）
    @RequestMapping("/user/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/index.html";
    }
}
