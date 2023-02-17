<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginForm</title>
</head>
<body>
<h1>LoginForm</h1>

<form action="<c:url value="j_spring_security_check" />" method="POST">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	ID : <input type="text" name="j_username"><br>
	PW : <input type="text" name="j_password"><br>
	<input type="submit" value="LOGIN"><br>
</form>

</body>
</html>