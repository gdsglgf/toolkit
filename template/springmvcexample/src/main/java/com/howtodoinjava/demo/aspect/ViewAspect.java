package com.howtodoinjava.demo.aspect;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.howtodoinjava.demo.util.LocaleUtils;

@Component
@Aspect
public class ViewAspect {
	
	/**
     * 加载已登录用户的个人信息及答题情况.
     * @param proceedingJoinPoint - ProceedingJoinPoint对象
     * @param request - HttpRequest对象
     * @param response - HttpResponse对象
     * @return 一个包含预期视图的ModelAndView对象
     * @throws Throwable - ResourceNotFound异常
     */
    @Around(value = "execution(* com.howtodoinjava.demo.controller.*.*View(..)) && args(.., request, response)")
    public ModelAndView getUserProfile(ProceedingJoinPoint proceedingJoinPoint, 
            HttpServletRequest request, HttpServletResponse response) throws Throwable {
    	System.out.println("--------ViewAspect----------");
        ModelAndView view = null;
//        HttpSession session = request.getSession();
        
        view = (ModelAndView) proceedingJoinPoint.proceed();
        view.addObject("language", getUserLanguage(request, response));
        
        return view;
    }
	
	/**
     * 获取当前用户的显示语言.
     * @param request - HttpRequest对象
     * @param response - HttpResponse对象
     * @return 当前用户显示语言的唯一英文缩写
     */
    private String getUserLanguage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object languageAttribute = session.getAttribute("language");
        
        if ( languageAttribute == null ) {
            String preferNaturalLanguage = getPreferNaturalLanguage(request, response);
            LocaleUtils.setLocale(request, response, preferNaturalLanguage);
            return preferNaturalLanguage;
        }
        return (String) languageAttribute;
    }
    
    /**
     * 根据用户浏览器语言和系统支持的语言推荐默认语言.
     * @param request - HttpRequest对象
     * @param response - HttpResponse对象
     * @return 推荐语言的代码(例如zh_CN)
     */
    private String getPreferNaturalLanguage(HttpServletRequest request, HttpServletResponse response) {
        final String DEFAULT_LANGUAGE = "en_US";
        final String[] supportedLanguages = { "en_US", "zh_CN" };
        Locale browserLocale = getBrowserLocale(request);
        
        for ( String supportedLanguage : supportedLanguages ) {
            Locale supportLanguageLocale = LocaleUtils.getLocaleOfLanguage(supportedLanguage);
            if ( supportLanguageLocale.getLanguage().equals(browserLocale.getLanguage()) ) {
                return supportedLanguage;
            }
        }
        return DEFAULT_LANGUAGE;
    }
    
    /**
     * 根据浏览器语言获取用户所在地区.
     * @param request - HttpRequest对象
     * @return 一个包含用户所在地区信息的Locale对象
     */
    private Locale getBrowserLocale(HttpServletRequest request) {
        Locale locale = request.getLocale();
        return locale;
    }

}
