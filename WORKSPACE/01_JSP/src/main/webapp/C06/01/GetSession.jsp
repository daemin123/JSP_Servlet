<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	Session값 : <%=session.getAttribute("ID") %>  	| 		<%=session.getAttribute("ROLE") %>  	<br>
	Request값 : <%=request.getAttribute("ID2") %>  	| 		<%=request.getAttribute("ROLE2") %>  	<br>

</body>
</html>