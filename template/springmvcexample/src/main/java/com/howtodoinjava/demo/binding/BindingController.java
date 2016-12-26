package com.howtodoinjava.demo.binding;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/binding")
public class BindingController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllEmployees(Model model) {
        return "binding/index";
    }
	
	@RequestMapping("/test1.do")
	public void test1(int count) {
		System.out.println(String.format("test1:%d", count));
	}

	@RequestMapping("/test2.do")
	public void test2(Integer count) {
		System.out.println(String.format("test2:%d", count));
	}

	@RequestMapping("/test3.do")
	public void test3(User user) {
		System.out.println(String.format("test3:%s", user));
	}

	@RequestMapping("/test4.do")
	public void test4(User user) {
		System.out.println(String.format("test4:%s", user));
	}

	@RequestMapping("/test5.do")
	public void test5(UserListForm userForm) {
		System.out.println("/test5.do");
		for (User user : userForm.getUsers()) {
			System.out.println(user.getFirstName() + " - " + user.getLastName());
		}
	}
	
	@RequestMapping("/test6.do")
	public void test6(UserSetForm userForm) {
		System.out.println("/test6.do");
	    for (User user : userForm.getUsers()) {
	        System.out.println(user.getFirstName() + " - " + user.getLastName());
	    }
	}
	
	@RequestMapping("/test7.do")
	public void test7(UserMapForm userForm) {
		System.out.println("/test7.do");
	    for (Map.Entry<String, User> entry : userForm.getUsers().entrySet()) {
	        System.out.println(entry.getKey() + ": " + entry.getValue().getFirstName() + " - " +
	        entry.getValue().getLastName());
	    }
	}
}
