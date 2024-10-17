<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	USERNAME : <%=session.getAttribute("ID") %><br>
	ROLE : <%=session.getAttribute("ROLE") %><br>
	
	<h1>MAIN</h1>


	<a href="logoutProc.jsp">로그아웃</a>
</body>
</html>