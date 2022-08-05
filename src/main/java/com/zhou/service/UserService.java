package com.zhou.service;

//怎么写？？？？？？？？？？？？？？？？？？？？？？？？？？？
import com.zhou.mapper.UserMapper;
import com.zhou.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User User);

    int deleteUser(int id);

}
