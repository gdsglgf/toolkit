package com.howtodoinjava.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.howtodoinjava.demo.mapper.UserMapper;
import com.howtodoinjava.demo.model.User;

@Service
@Transactional
public class UserService {
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User getUserUsingid(int id) {
        return userMapper.getUserUsingid(id);
    }

    public User getUserUsingUsername(String username) {
        return userMapper.getUserUsingUsername(username);
    }

    public void createUser(User user) {
        userMapper.createUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    @Autowired
    private UserMapper userMapper;
}
