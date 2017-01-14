package com.example.myproject.binding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SayHelloController {

    @RequestMapping(value="/sayhello", method=RequestMethod.GET)
    public String sayHelloForm(Model model) {
    	System.out.println("in get");
        model.addAttribute("helloMessage", new HelloMessage());
        return "message";
    }

    @RequestMapping(value="/sayhello", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute HelloMessage helloMessage, Model model) {
    	System.out.println("in post");
    	System.out.println(helloMessage);
        model.addAttribute("helloMessage", helloMessage);
        return "message";
    }

}