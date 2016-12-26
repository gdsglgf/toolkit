<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Binding Test</title>
</head>
<body>
	<div>
		<h1>Binding int</h1>
		<form action="test1.do" method="post">
			<input name="count" value="10" type="text" />
			<input type="submit" value="Save" />
		</form>
	</div>

	<div>
		<h1>Binding Integer</h1>
		<form action="test2.do" method="post">
			<input name="count" value="10" type="text" />
			<input type="submit" value="Save" />
		</form>
	</div>

	<div>
		<h1>Binding simple bean</h1>
		<form action="test3.do" method="post">
			<input name="firstName" value="张" type="text" />
			<input name="lastName" value="三" type="text" />
			<input type="submit" value="Save" />
		</form>
	</div>

	<div>
		<h1>Binding complex bean</h1>
		<form action="test4.do" method="post">
			<input name="firstName" value="张" /><br>
			<input name="lastName" value="三" /><br>
			<input name="contactInfo.tel" value="1234567890" /><br>
			<input name="contactInfo.address" value="北京海淀" /><br>
			<input type="submit" value="Save" />
		</form>
	</div>

	<div>
		<h1>Binding List1</h1>
		<form action="test5.do" method="post">
			<table>
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="2"><input type="submit" value="Save" /></td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<td><input name="users[0].firstName" value="aaa" /></td>
						<td><input name="users[0].lastName" value="bbb" /></td>
					</tr>
					<tr>
						<td><input name="users[1].firstName" value="ccc" /></td>
						<td><input name="users[1].lastName" value="ddd" /></td>
					</tr>
					<tr>
						<td><input name="users[2].firstName" value="eee" /></td>
						<td><input name="users[2].lastName" value="fff" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div>
		<h1>Binding List2</h1>
		<form action="test5.do" method="post">
			<table>
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="2"><input type="submit" value="Save" /></td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<td><input name="users[0].firstName" value="aaa" /></td>
						<td><input name="users[0].lastName" value="bbb" /></td>
					</tr>
					<tr>
						<td><input name="users[1].firstName" value="ccc" /></td>
						<td><input name="users[1].lastName" value="ddd" /></td>
					</tr>
					<tr>
						<td><input name="users[5].firstName" value="eee" /></td>
						<td><input name="users[5].lastName" value="fff" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div>
		<h1>Binding Set</h1>
		<form action="test6.do" method="post">
			<table>
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="2"><input type="submit" value="Save" /></td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<td><input name="users[0].firstName" value="aaa" /></td>
						<td><input name="users[0].lastName" value="bbb" /></td>
					</tr>
					<tr>
						<td><input name="users[1].firstName" value="ccc" /></td>
						<td><input name="users[1].lastName" value="ddd" /></td>
					</tr>
					<tr>
						<td><input name="users[2].firstName" value="eee" /></td>
						<td><input name="users[2].lastName" value="fff" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div>
		<h1>Binding Map</h1>
		<form action="test7.do" method="post">
			<table>
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="2"><input type="submit" value="Save" /></td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<td><input name="users['x'].firstName" value="aaa" /></td>
						<td><input name="users['x'].lastName" value="bbb" /></td>
					</tr>
					<tr>
						<td><input name="users['y'].firstName" value="ccc" /></td>
						<td><input name="users['y'].lastName" value="ddd" /></td>
					</tr>
					<tr>
						<td><input name="users['z'].firstName" value="eee" /></td>
						<td><input name="users['z'].lastName" value="fff" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>