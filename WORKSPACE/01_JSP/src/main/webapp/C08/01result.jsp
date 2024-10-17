<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<%-- 	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	%>
	
	USERNAME : <%=username%><br>
	PASSWORD : <%=password %><br> 
--%>

	<%
		pageContext.setAttribute("P_KEY", "P_VALUE");
		request.setAttribute("R_KEY", "R_VALUE");
		session.setAttribute("S_KEY", "S_VALUE");
		application.setAttribute("A_KEY", "A_VALUE");
		
		//pageContext.setAttribute("DUP", "P_VALUE");
		//request.setAttribute("DUP", "R_VALUE");
		session.setAttribute("DUP", "S_VALUE");
		application.setAttribute("DUP", "A_VALUE");	
	%>
	<hr>
	<!-- parameter  -->
	[PARAM]USERNAME : ${param.username}<br>
	[PARAM]PASSWORD : ${param.password}<br>
	<hr>
	<!-- attribute -->	
	<%= %>
	[ATTR]REQUESET ATTR : ${requestScope.R_KEY} <br>
	[ATTR]REQUESET ATTR : ${R_KEY} <br>
	[ATTR]SESSION ATTR : ${sessionScope.S_KEY} <br>
	[ATTR]SESSION ATTR : ${S_KEY} <br>
	[ATTR]PAGECONTEXT ATTR : ${pageScope.P_KEY} <br>
	[ATTR]PAGECONTEXT ATTR : ${P_KEY} <br>	
	[ATTR]APPLICATION ATTR : ${applicationScope.A_KEY} <br>
	[ATTR]APPLICATION ATTR : ${A_KEY} <br>	
	<hr>
	<!-- attribute key 중첩 -->	
	[ATTR]DUP ATTR : ${DUP}<br>
	
	<!-- object 확인 -->
	<%@page import="C08.*" %>
	<%
		Person ob1 = new Person("hong",55,"대구");
		request.setAttribute("ob1", ob1);
	%>
	[OBJ] OBJ : ${ob1} <br>
	[OBJ] OBJ : ${ob1.name} <br>
	[OBJ] OBJ : ${ob1.age} <br>
	[OBJ] OBJ : ${ob1.addr} <br>
	<hr>
	<!-- object 확인 -->
	[연산] TEST : <%=1 + "1" %><br>
	[연산] TEST : ${1 + "1"}<br>
	
	<hr>	
	<!-- NULL CHECK -->
	[NULL] null : ${null} <br>
	[NULL] empty null : ${empty null} <br>	
	<%
		//request.setAttribute("ATTR1", "VALUE");
	%>
	[NULL] empty ATTR1 : ${empty ATTR1} <br>	
	
</body>
</html>