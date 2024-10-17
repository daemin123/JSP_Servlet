<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@page import="java.sql.*" %>
	<%
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost/bookDb";
		Connection conn =  DriverManager.getConnection(url,"root","1234");
		System.out.println("Connection conn : " + conn);
	
	%>
	Connection : <%=conn %> <br>
</body>
</html>