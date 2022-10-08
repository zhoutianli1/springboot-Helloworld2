package com.zhou.service;

import com.zhou.mapper.UserMapper;
import com.zhou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService{
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> queryUserList()
    {
        return userMapper.queryUserList();
    }
    public User queryUserById(int id)
    {
        return userMapper.queryUserById(id);
    }
    //存
    public int addUser(User user)
    {
        return userMapper.addUser(user);
    }

    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    public  int deleteUser(int id)
    {
        return userMapper.deleteUser(id);
    }


}
