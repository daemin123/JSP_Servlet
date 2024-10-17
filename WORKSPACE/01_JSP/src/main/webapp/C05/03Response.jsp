<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@page import="java.io.*" %>
	<%
		//Redirect 
		//response.sendRedirect("02Request.jsp");
	
		//새로고침
		//response.setIntHeader("Refresh",3);

		//OutStream추출
/* 		ServletOutputStream bout =  response.getOutputStream();
		bout.write(97);
		bout.flush();
		bout.close(); */
		
		//PrintWriter 사용(Out내장객체와 유사)
		PrintWriter o =  response.getWriter();
		
		out.println("<h1>TEST</h1>");
		o.println("<h2>TEST2</h2>");
	%>
	
	
	<%@page import="java.util.*" %>
	<%=new Date() %>
</body>
</html>