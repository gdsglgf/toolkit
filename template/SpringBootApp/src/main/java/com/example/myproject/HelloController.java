package com.example.myproject;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller		// 可以返回String或者json（添加注解@ResponseBody）, 渲染模板（返回模板名称即可）
public class HelloController {
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello World!";
	}
	
	@RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
	
	@RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

	@RequestMapping("/posts/{id}")
	@ResponseBody
	// url: http://localhost:8080/posts/1000
	public String post(@PathVariable("id") int id) {
		return String.format("post %d", id);
	}
	
	@RequestMapping("/get")
	@ResponseBody
	// url: http://localhost:8080/get?name=007
	public String get(@RequestParam("name") String name) {
		return String.format("get name=%s", name);
	}
	
	@RequestMapping("/json")
    @ResponseBody
    public Map<String,Object> json(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "007");
        map.put("age", "18");
        map.put("sex", "man");
        return map;
    }
}
