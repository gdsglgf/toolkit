package com.example.myproject;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController		// 只能返回String或者json, 不能渲染模板
@RequestMapping("/classPath")
public class RestfulController {	
	@RequestMapping("/")
    public String index() {
        return "Index Page";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
	
	@RequestMapping("/methodPath")
    public String method() {
        return "mapping url is /classPath/methodPath";
    }

	@RequestMapping("/users/{username}")
	public String userProfile(@PathVariable("username") String username) {
	    return String.format("user %s", username);
	}

	@RequestMapping("/posts/{id}")
	public String post(@PathVariable("id") int id) {
	    return String.format("post %d", id);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() {
	    return "Login Page";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost() {
	    return "Login Post Request";
	}
	
	@RequestMapping("/json")
    public Map<String,Object> json(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "007");
        map.put("age", "18");
        map.put("sex", "man");
        return map;
    }
}
