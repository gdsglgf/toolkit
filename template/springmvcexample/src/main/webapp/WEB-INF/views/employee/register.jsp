<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
	<h2>register employee</h2>
	<form onsubmit="onSubmit(); return false;" method="post">
		<table width="405" height="88" style="width: 376px; height: 89px;">
			<tr>
				<td>ID:</td>
				<td><input type="text" id="id" name="id"></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type="text" id="firstName" name="firstName"></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" id="lastName" name="lastName"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Commit"></td>
				<td><input type="reset" value="Reset"></td>
			</tr>
		</table>
	</form>

	<!-- JavaScript -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		function onSubmit() {
			var id = $('#id').val(), 
			firstName = $('#firstName').val(), 
			lastName = $('#lastName').val();

			var postData = {
				'id' : id,
				'firstName' : firstName,
				'lastName' : lastName
			};

			$.ajax({
				type : 'POST',
				url : '<c:url value="register.action" />',
				data : postData,
				dataType : 'JSON',
				success : function(result) {
					alert(result);
				}
			});
		}
	</script>
</body>
</html>