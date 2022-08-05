package com.zhou.mapper;

import com.zhou.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
@Repository和@Controller、@Service、@Component的作用差不多，都是把对象交给spring管理。@Repository用在持久层的接口上，这个注解是将接口的一个实现类交给spring管理。
为什么有时候我们不用@Repository来注解接口,我们照样可以注入到这个接口的实现类呢?
1、spring配置文件中配置了MapperScannerConfigurer这个bean，它会扫描持久层接口创建实现类并交给spring管理。
2、接口上使用了@Mapper注解或者springboot中主类上使用了@MapperScan注解，和MapperScannerConfigurer作用一样。

@Component是一个通用的Spring容器管理的单例bean组件。而@Repository, @Service, @Controller就是针对不同的使用场景所采取的特定功能化的注解组件。
 */
@Mapper  //表示本类是一个mybatis的mapper类。对应之前Dao层
@Repository
public interface UserMapper {
    //接口中只能定义常量和抽象的方法, public static final int age =18
    //增删改查
//查
    List<User> queryUserList();

    User queryUserById(int id);
//存
    int addUser(User user);

    int updateUser(User User);

    int deleteUser(int id);

    //之前在spring，是在mapper下建一个mapper.xml
    //现在 写在resources下面，但不能用class访问。不能将注解 与它同时使用
}
