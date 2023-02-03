<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL CORE -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- JSTL SECURITY -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ACCESS ERROR!!</title>
</head>
<body style="color:red; background-color:black;">

<h1>ACCESS Denied..</h1>

<!-- 나중에 확인 -->
<h2><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage()}"></c:out></h2>

<h2><c:out value="${msg}" /> </h2>

<img src="https://ac2-p2.namu.la/20211014s2/f8f88a272299e9f05999cae329d9cdd35099f39970a378d879be1ea84a95214f.png">

</body>
</html>