<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FileUpload</title>
<meta http-equiv="X-UA-Compatible" content="IE-edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
welcome
<hr>

<form action="uploadOk" method="POST" enctype="multipart/form-data">
	첨부파일 : <input type="file" name="attachFile" /><br>
	<hr>
	이미지 파일들 : <br>
	1번:<input type="file" id="imageFiles" name="imageFiles[]" multiple="multiple" /><br>
	2번:<input type="file" id="imageFiles" name="imageFiles[]" multiple="multiple" /><br>
	<input type="submit" value="File Upload">
</form>

첨부파일 : <br>
* 파일 : <a href="/attach/22e97c36-a853-4908-b103-0d2a33531e2d">다운로드</a><br>
* 이미지 보기 : <br>
<img src="/images/13a99263-6a7c-4814-a3fd-60a3e6a48b58_한글" alt="그림"
	 style="width:120px;height:120px"><br>

</body>
</html>