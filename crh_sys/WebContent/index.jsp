<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test page</title>
</head>
<body>
	<h1>Welcome come to test page</h1>
	<h4><i>This page contains 中文测试</i></h4>
	<hr>
	<p>Servlet Context Injection Test : ${str }</p>
	<br>
	<hr>
	<p>MTrain Object Fetch Test:</p>
	<table>
		<tr>
			<td>CRHType</td>
			<td>${trainInfo.crhType }</td>
		</tr>
		<tr>
			<td>CRHModelType</td>
			<td>${trainInfo.crhModelType }</td>
		</tr>
	</table>
</body>
</html>