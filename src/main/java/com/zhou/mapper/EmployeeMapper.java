package com.zhou.mapper;


import com.zhou.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {


    //获取全部员工信息
    List<Employee> getEmployeeList();
    //通过id查员工
    Employee getEmployeeById(Integer id);

    //通过id删员工
    int deleteEmployee(Integer id);

    //增
    int addEmployee(Employee employee);

    //更新
    int updateEmployee(Employee employee);
}
