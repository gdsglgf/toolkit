package com.howtodoinjava.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.howtodoinjava.demo.model.User;
import com.howtodoinjava.demo.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView all(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("account/users");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }
    
    @RequestMapping(value = "{uid}", method = RequestMethod.GET)
    public @ResponseBody User getUserAction(
            @PathVariable("uid") int uid,
            HttpServletRequest request, HttpServletResponse response) {
        return userService.getUserUsingid(uid);
    }
    
    @RequestMapping(value = "user-{username}", method = RequestMethod.GET)
    public @ResponseBody User getUserAction(
            @PathVariable("username") String username,
            HttpServletRequest request, HttpServletResponse response) {
        return userService.getUserUsingUsername(username);
    }
    
    @Autowired
    private UserService userService;
}
