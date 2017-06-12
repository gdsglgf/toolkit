package com.examples.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {
	private static final Logger log = LogManager.getLogger(DefaultController.class);
	
	@RequestMapping(value = "/today")
	@ResponseBody
	public Map<String, Object> today() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("uuid", UUID.randomUUID());
		data.put("today", new Date());
		data.put("colors", new String[]{"red", "green", "blue"});
		List<String> weekday = new ArrayList<String>();
		weekday.add("Sunday");
		weekday.add("Monday");
		weekday.add("Tuesday");
		weekday.add("Wednesday");
		weekday.add("Thursday");
		weekday.add("Friday");
		weekday.add("Saturday");
		data.put("weekday", weekday);
		log.debug(data);
		return data;
	}
	
	@RequestMapping(value = "/")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("index");
		return view;
	}
}
