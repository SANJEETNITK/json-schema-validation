package com.springboot.redisexample.service;

import com.springboot.redisexample.entity.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);

    User getUserById(String id);

    List<User> getUsers();

    boolean updateUser(User user);

    boolean deleteUserById(String id);
}
