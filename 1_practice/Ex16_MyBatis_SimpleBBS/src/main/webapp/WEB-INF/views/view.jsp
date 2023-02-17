<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View</title>
</head>
<body>
내용보기<br>
<hr>
작성자: ${dto.writer }
제목: ${dto.title }
내용: ${dto.content }
<hr>
<br>
<p>
	<a href="list">목록</a>
</p>
</body>
</html>