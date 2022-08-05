package com.zhou.dao;

import com.zhou.pojo.Department;
import com.zhou.pojo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Repository
public class EmployeeDao
{
    private static Map<Integer, Employee> employees=null;   //员工id 与员工对应
    //员工有所属部门 （外表)
    @Autowired
    private DepartmentDao departmentDao;
    static
    {
        employees = new HashMap<Integer, Employee>();//创建一个部门表

        employees.put(1,new Employee(1,"AA","11111@qq.com",1,new Department(101,"11部")));
        employees.put(2,new Employee(2,"BB","22222@qq.com",1,new Department(102,"22部")));
        employees.put(3,new Employee(3,"CC","33333@qq.com",1,new Department(103,"33部")));
        employees.put(4,new Employee(4,"DD","44444@qq.com",1,new Department(104,"44部")));
        employees.put(5,new Employee(5,"EE","55555@qq.com",1,new Department(105,"55部")));
    }
    //增删改查
    //主键自增，用static
    private static Integer initID =10;
    public void save(Employee employee)
    {
        if(employee.getId()==null)
        {
            employee.setId(initID++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    //获取全部员工信息
    public Collection<Employee> getAll()
    {
        return employees.values();
    }
    //通过id查员工
    public Employee getEmployeeById(Integer id)
    {
        return employees.get(id);
    }
    //通过id删员工
    public void delete(Integer id)
    {
        employees.remove(id);
    }

}
