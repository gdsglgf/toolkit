<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>global message</title>
</head>
<body>
	<a href="test2?language=zh_CN">中文</a> | <a href="test2?language=en_US">英文</a><br/>
	<p>${hellotext} -----  <spring:eval expression="hellotext" /></p>
	<spring:message code="hello.text" text="hello.text" /><br>
	<spring:message code="hello.other" text="hello.other-not-found" /><br>
	<spring:message code="hello.other3" /><br>
</body>
</html>