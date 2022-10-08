package com.zhou.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//员工
//在java bean定义的时候，需要给每个字段提供set和get属性。lombok这个插件@Data所做的事情就是在编译期间替我们干了这件事。
@Data
//@AllArgsConstructor//提供有参构造方法
@NoArgsConstructor //无参构造方法
public class Employee {
    private Integer id;
    private  String lastName;
    private String email;
    private  Integer gender;
    private Integer departmentId;
    private Date birth;

    public Employee(Integer id, String lastName, String email, Integer gender, Integer departmentId) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.departmentId = departmentId;

        //默认创建日期，创建日期默认创建，生日手动在前台填表单。
        this.birth = new Date(); //new Date(）为现在时间
    }
}
