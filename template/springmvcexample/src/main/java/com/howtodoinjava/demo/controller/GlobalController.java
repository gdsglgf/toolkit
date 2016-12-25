package com.howtodoinjava.demo.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping(value = "/global")
public class GlobalController {
	@RequestMapping(value = "/testAspect", method = { RequestMethod.GET })
	public String testAspectView(Model model, HttpServletRequest request, HttpServletResponse response) {
		// 从后台代码获取国际化信息
		RequestContext requestContext = new RequestContext(request);
		String hellotext = requestContext.getMessage("hello.text");
		System.out.printf("Aspect global: text[%s]\n", hellotext);
		model.addAttribute("hellotext", hellotext);
		return "global/globaltest";
	}

	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public String test(HttpServletRequest request, Model model) {
		// 从后台代码获取国际化信息
		RequestContext requestContext = new RequestContext(request);
		String hellotext = requestContext.getMessage("hello.text");
		System.out.printf("Browser global: text[%s]\n", hellotext);
		model.addAttribute("hellotext", hellotext);
		return "global/globaltest";
	}
	
	public static Locale getLocaleOfLanguage(String languageName) {
        String[] localeMeta = languageName.split("_");
        if (localeMeta.length != 2) {
        	return LocaleContextHolder.getLocale();
        }
        String language = localeMeta[0];
        String country = localeMeta[1];
        
        return new Locale(language, country);
    }
	
	@RequestMapping(value = "/test2", method = { RequestMethod.GET })
	public String testSession(HttpServletRequest request, 
			@RequestParam(value="language", defaultValue="zh_CN", required=false)String language, 
			Model model) {
		
		Locale locale = getLocaleOfLanguage(language);
		request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		
		// 从后台代码获取国际化信息
		RequestContext requestContext = new RequestContext(request);
		String hellotext = requestContext.getMessage("hello.text");
		System.out.printf("Session global: language:%s, text[%s]\n", language, hellotext);
		model.addAttribute("hellotext", hellotext);
		return "global/globaltest";
	}
	
	@RequestMapping(value = "/test3", method = { RequestMethod.GET })
	public String testCookie(HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(value="language", defaultValue="zh_CN", required=false)String language, 
			Model model) {
		
		Locale locale = getLocaleOfLanguage(language);
		(new CookieLocaleResolver()).setLocale (request, response, locale);
		
		// 从后台代码获取国际化信息
		RequestContext requestContext = new RequestContext(request);
		String hellotext = requestContext.getMessage("hello.text");
		System.out.printf("Cookie global: language:%s, text[%s]\n", language, hellotext);
		model.addAttribute("hellotext", hellotext);
		return "global/globaltest";
	}
	
	

}