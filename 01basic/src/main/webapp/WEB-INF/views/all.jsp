<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ALL</title>
</head>
<body>
<h1>ALL PAGE</h1>
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