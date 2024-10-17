<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		MESSAGE : ${msg} 
	</div>
	<form action="${pageContext.request.contextPath}/login.do" method="post">
		<input type="text" name="username" />
		<input type="text" name="password" />
		<button>요청</button>	
	</form>
	<%
		session.removeAttribute("msg");
	%>
</body>
</html>