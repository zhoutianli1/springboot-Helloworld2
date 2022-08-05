package com.zhou.mapper;

import com.zhou.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {

    //List接口是继承Collection接口，也就是说，可以使用Collection接口下的所有方法:
    //List，Set都是接口，它们都继承与接口Collection，List是一个有序可重复的集合，而Set则是无序不可重复的。Collection是集合的顶层接口，
    // Collections是一个封装了众多关于集合操作的静态方法的工具类，因为构造方法是私有的，所以不能实例化；
//查
    //获取所有部门信息
    List<Department> getDepartmentsList();

    //通过id获取部门
    String getDepartmentById(Integer id);
//存
    //通过id删除部门
    int deleteDepartment(int id);
    //add
    int addDepartment(Department department);

    //通过id修改部门
    int updateDepartment(Department department);
}