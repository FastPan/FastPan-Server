<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="http://127.0.0.1:8080/fastpan/user/uploadImage" enctype="multipart/form-data" >
<input type ="file" name ="file" id = "file" multiple="multiple" >
<input type ="submit" value = "提交"/>
<img src="D:/apache-tomcat-7.0.42/wtpwebapps/fastpan-web/img/2017/03/22/2.png" /> 
</form>
</body>
</html>