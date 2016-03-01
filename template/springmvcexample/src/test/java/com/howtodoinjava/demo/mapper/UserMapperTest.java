package com.howtodoinjava.demo.mapper;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.howtodoinjava.demo.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(true)
@ContextConfiguration({"classpath:spring-servlet.xml"})
public class UserMapperTest {
    @Test
    public void testGetAllUsers() {
        Assert.assertEquals(3, userMapper.getAllUsers().size());
    }
    
    @Test
    public void testGetUserUsingid() {
        Assert.assertNotNull(userMapper.getUserUsingid(1));
    }
    
    @Test
    public void testGetUserUsingUsername() {
        Assert.assertNotNull(userMapper.getUserUsingUsername("Jack"));
    }
    
    @Test
    public void testCreateUser() {
        User user = new User("Albert", "ec22");
        userMapper.createUser(user);
        log.debug(user);
    }
    
    @Test
    public void testUpdateUser() {
        User user = userMapper.getUserUsingid(1);
        log.debug(user);
        user.setUsername("Mr. Bean");
        userMapper.updateUser(user);
        log.debug(userMapper.getUserUsingid(1));
    }
    
    @Test
    public void testdeleteUser() {
        userMapper.deleteUser(1);
        Assert.assertNull(userMapper.getUserUsingid(1));
    }

    @Autowired
    private UserMapper userMapper;
    private Logger log = Logger.getLogger(getClass());
}
