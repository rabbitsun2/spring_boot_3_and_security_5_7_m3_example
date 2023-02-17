<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WriteForm</title>
</head>
<body>

<form action="write" method="POST">
<table>
	<tr>
		<td>작성자</td>
		<td><input type="text" name="writer" size="100"></td>
	</tr>
	<tr>
		<td>제목</td>
		<td><input type="text" name="title" size="100"></td>
	</tr>
	
	<tr>
		<td>내용</td>
		<td><textarea name="content"></textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="입력">
			&nbsp;&nbsp;
			<a href="list">목록</a>
		</td>
	</tr>
</table>
</form>

</body>
</html>