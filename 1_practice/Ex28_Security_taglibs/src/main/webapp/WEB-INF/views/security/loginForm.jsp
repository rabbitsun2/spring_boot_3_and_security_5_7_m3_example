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

<c:url value="j_spring_security_check" var="loginUrl" />
<form action="${loginUrl }" method="POST">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	<c:if test="${param.error != null }">
	<p>
		Login Error! <br>
		${error_message }
	</p>
	</c:if>
	
	ID : <input type="text" name="j_username"><br>
	PW : <input type="text" name="j_password"><br>
	<input type="submit" value="LOGIN"><br>
</form>

</body>
</html>