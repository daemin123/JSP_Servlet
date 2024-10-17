<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	 	String cookiename =  request.getParameter("cookiename");
		Cookie cookie = new Cookie(cookiename,"");
		cookie.setMaxAge(0);	
		response.addCookie(cookie);

	%>
	쿠키삭제!!<br>
	<a href="confirmCookie.jsp">쿠키확인</a>
</body>
</html>