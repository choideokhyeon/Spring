<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<title>ERROR</title>
</head>
<style>
body{
	background-color:black;
	color:red;
}
</style>
<body>
<h1>ERROR PAGE</h1>

발생예외 : ${ex}<br>
예외메세지 : ${ex.message}<br>
<c:forEach items="${ex.stackTrace}" var="i">
	<li>${i.toString()}</li>
</c:forEach>
</body>
</html>