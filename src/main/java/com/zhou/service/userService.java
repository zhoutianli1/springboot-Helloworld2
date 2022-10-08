package com.zhou.service;

import com.zhou.pojo.User;

import java.util.List;

public interface userService {
    public List<User> queryUserList();

    public User queryUserById(int id);
    //存
    public int addUser(User user);

    public int updateUser(User User);

    public int deleteUser(int id);
}
