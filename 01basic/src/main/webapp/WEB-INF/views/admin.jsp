<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Security tag -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
<h1>Admin PAGE</h1>
<h2>INFO</h2>
<p>PRINCIPAL : <sec:authentication property="principal" /></p>
<p>MEMBERDTO : <sec:authentication property="principal.member" /></p>
<p>사용자이름 : <sec:authentication property="principal.member.userName" /></p>
<p>사용자아이디 : <sec:authentication property="principal.member.userid" /></p>
<p>사용자권한리스트 : <sec:authentication property="principal.member.authList" /></p>
<p></p>
<button onclick="logout()">로그아웃</button>

<form action="${pageContext.request.contextPath}/customlogout" method="post" name=logoutfrm>
	<input hidden name="${_csrf.parameterName }" value="${_csrf.token }" />
</form>

<script>
	const logout = function(){
		const logoutfrm = document.logoutfrm;
		logoutfrm.submit();
	}
</script>
</body>
</html>