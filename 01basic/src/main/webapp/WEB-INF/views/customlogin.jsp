<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!-- JSTL CORE -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom Login Page</title>

<!-- link -->
<%@include file="/resources/module/link.jsp" %>
<!-- css -->
<link rel="stylesheet" type="text/css" href="resources/css/login.css">


</head>
<body>
<section class="container">
	<div class="msg">
		<c:out value="${error}"></c:out><br>
		<c:out value="${logout}"></c:out><br>
	</div>
	<h1>CUSTOM LOGIN</h1>
	
	<form name="loginfrm" action=<c:url value="/login" /> method="post">
	
		<input type="text" name="username" placeholder="Insert username" class="form-control" />
		<input type="password" name="password"  placeholder="Insert Password" class="form-control" />
		
		<div style="text-align:left; margin:0;">
			<input type="checkbox" name="remember-me" id="chk">
			<label for="chk">자동로그인</label>
		</div>
		
		<button class="btn btn-primary w-100">로그인</button>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</form>
</section>

</body>
</html>