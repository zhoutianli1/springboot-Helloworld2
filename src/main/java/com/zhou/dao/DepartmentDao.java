package com.zhou.dao;

import com.zhou.pojo.Department;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {

    //模拟数据库中的数据,字典，键值对
    private static Map<Integer, Department> departments=null;
    static    //放在static 初始化 数据就加载
    {
         departments = new HashMap<Integer, Department>();//创建一个部门表

        departments.put(101,new Department(101,"11部"));
        departments.put(102,new Department(102,"22部"));
        departments.put(103,new Department(103,"33部"));
        departments.put(104,new Department(104,"44部"));
        departments.put(105,new Department(104,"55部"));
    }

    //获取所有部门信息
    public Collection<Department> getDepartments()
    {
        return departments.values();
    }

    //通过id获取部门
    public Department getDepartmentById(Integer id)
    {
        return departments.get(id);
    }



}
