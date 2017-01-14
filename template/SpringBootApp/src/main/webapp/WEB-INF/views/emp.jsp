<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>in views/jsp</p>
	<table>
		<tr><th>First name</th><td>${emp.firstName}</td></tr>
		<tr><th>Last name</th><td>${emp.lastName}</td></tr>
		<tr><th>Position</th><td>${emp.position}</td></tr>
		<tr><th>Office</th><td>${emp.office}</td></tr>
		<tr><th>Start date</th><td>${emp.startDate}</td></tr>
		<tr><th>Salary</th><td>${emp.salary}</td></tr>
	</table>
</body>
</html>