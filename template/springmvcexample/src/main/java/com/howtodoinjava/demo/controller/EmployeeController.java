package com.howtodoinjava.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.howtodoinjava.demo.model.EmployeeVO;
import com.howtodoinjava.demo.service.EmployeeManager;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeManager manager;

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", manager.getAllEmployees());
        return "employee/employeesListDisplay";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView show1(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("employee/employeesListDisplay");
        mav.addObject("employees", manager.getAllEmployees());
        return mav;
    }
    
    @RequestMapping(value = "/getEmployee.action", method = RequestMethod.GET)
    public @ResponseBody List<EmployeeVO> getEmployeeAction(HttpServletRequest request) {
        return manager.getAllEmployees();
    }
    
    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
    public @ResponseBody EmployeeVO getEmployeeAction(
            @PathVariable("employeeId") int employeeId,
            HttpServletRequest request, HttpServletResponse response) {
        return manager.getAllEmployees().get(employeeId - 1);
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView() {
        return "employee/register";
    }
    
    @RequestMapping(value = "/register.action", method = RequestMethod.POST)
    public @ResponseBody boolean registerAction(
            @RequestParam(value = "id", required = true) int id,
            @RequestParam(value = "firstName", required = true) String firstName,
            @RequestParam(value = "lastName", required = true) String lastName,
            HttpServletRequest request) {
        List<EmployeeVO> list = manager.getAllEmployees();
        boolean exists = false;
        for (EmployeeVO e : list) {
            if (e.getId().intValue() == id) {
                exists = true;
                break;
            }
        }
        
        return exists;
    }
}