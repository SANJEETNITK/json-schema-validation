package com.springboot.redisexample.repository;

import com.springboot.redisexample.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    RedisTemplate redisTemplate;

    private String HASH_KEY = "USER";


    @Override
    public boolean saveUser(User user) {
        try {
            redisTemplate.opsForHash().put(HASH_KEY, user.getId(), user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            if (redisTemplate.opsForHash().hasKey(HASH_KEY, user.getId())) {
                redisTemplate.opsForHash().put(HASH_KEY, user.getId(), user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> getUsers() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    @Override
    public User getUserById(String id) {
        return (User) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    @Override
    public boolean deleteUserById(String id) {
        redisTemplate.opsForHash().delete(HASH_KEY,id);
        return true;
    }
}
