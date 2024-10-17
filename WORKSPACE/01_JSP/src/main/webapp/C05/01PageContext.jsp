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
		//Scope확인 : https://zester7.tistory.com/46
		System.out.println("PageContext : " + pageContext);
		System.out.println("PageContext : " + pageContext.getRequest());	//Request객체 꺼내옴
		System.out.println("PageContext : " + pageContext.getResponse());	//Request객체 꺼내옴
		System.out.println("PageContext : " + pageContext.getSession());	//Session객체 꺼내옴
		System.out.println("PageContext : " + pageContext.getServletContext());	//ServletContext객체 꺼내옴
		System.out.println("PageContext : " + pageContext.getServletContext().getContextPath());	//ProjectPath
	%>

	PROJECT PATH : ${pageContext.request.contextPath} <br>
	
	
</body>
</html>