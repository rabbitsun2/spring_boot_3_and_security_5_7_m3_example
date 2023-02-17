<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserList</title>
</head>
<body>

<table>
	<tr>
		<td>번호</td>
		<td>작성자</td>
		<td>제목</td>
		<td>삭제</td>
	</tr>
	<c:forEach var="dto" items="${list }">	
	<tr>
		<td>${dto.id }</td>
		<td>${dto.writer }</td>
		<td><a href="view?id=${dto.id }">${dto.title }</a></td>
		<td><a href="delete?id=${dto.id }">X</a></td>
	</tr>
	</c:forEach>
</table>

<p>
	<a href="writeForm">글작성</a>
</p>
</body>
</html>