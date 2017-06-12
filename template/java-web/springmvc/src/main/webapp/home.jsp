<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<div><a href="home.jsp?language=zh_CN"><spring:message code="language.zh_CN" text="Simplified Chinese" /></a> | <a href="home.jsp?language=en_US"><spring:message code="language.en_US" text="English" /></a></div>
	<p><spring:message code="hello.text" text="hello world" /></p>
	<p><spring:message code="message.not.found" /></p>
	<p>Current Locale : ${pageContext.response.locale}</p>
</body>
</html>