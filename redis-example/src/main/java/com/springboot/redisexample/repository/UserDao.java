package com.springboot.redisexample.repository;

import com.springboot.redisexample.entity.User;

import java.util.List;

public interface UserDao {
    boolean saveUser(User user);
    boolean updateUser(User user);
    List<User> getUsers();
    User getUserById(String id);
    boolean deleteUserById(String id);
}
