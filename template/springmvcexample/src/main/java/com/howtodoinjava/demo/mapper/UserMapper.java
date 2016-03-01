package com.howtodoinjava.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.howtodoinjava.demo.model.User;

@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface UserMapper {
    public List<User> getAllUsers();
    public User getUserUsingid(@Param("id") int id);
    public User getUserUsingUsername(@Param("username") String username);
    public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(@Param("id") int id);
}
