package com.zhou.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/*
@Controller标识一个Spring类是Spring MVC controller处理器
@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
1) 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html，配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
2) 如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
    如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
3)如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
（4）@Controller类中的方法可以直接通过返回String跳转到jsp、ftl、html等模版页面。
在方法上加@ResponseBody注解，也可以返回实体对象。@RestController类中的所有方法只能返回String、Object、Json等实体对象，不能跳转到模版页面。
例如：
1.使用@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面
若返回json等内容到页面，则需要加@ResponseBody注解
 */
@RestController

public class JDBCController {
    // @Autowired报错
    // https://blog.csdn.net/Hellowenpan/article/details/85249679?spm=1001.2101.3001.6650.5&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-5-85249679-blog-109328754.pc_relevant_multi_platform_featuressortv2dupreplace&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-5-85249679-blog-109328754.pc_relevant_multi_platform_featuressortv2dupreplace&utm_relevant_index=10
    //
    @Resource
    JdbcTemplate jdbcTemplate;  //调用里面方法进行CUDR,   spring将很多操作封装在了JdbcTemplate

    //没有对应数据库实体类，利用map 获取数据库中实体类
    //查询数据库所有信息
    @GetMapping("/userList")
    public List<Map<String,Object>> userList()
    {
        String sql = "select * from user";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
        System.out.println("数据库连接成功");
        return list_maps;
    }
    @GetMapping("/deleteUser")
    public String updateUser(@PathVariable("id") int id)
    {
        String sql = "update mybatis.user set name=?,pwd=? where id="+id;
        //封装
        Object[] objects = new Object[2];
        objects[1] = "小米";
        objects[2] = "1111111";
        jdbcTemplate.update(sql,objects);
        return "update ok";
    }
}
