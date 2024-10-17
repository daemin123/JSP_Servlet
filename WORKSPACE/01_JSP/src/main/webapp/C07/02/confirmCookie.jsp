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
		Cookie[] cookies = 	request.getCookies();
		if(cookies!=null){
			
			for(Cookie c : cookies){
				%>
					<div>
						<a href="removeCookie.jsp?cookiename=<%=c.getName()%>">
							<%=c.getName() %> : <%=c.getValue() %>
						</a>
					</div>
				<%
			}	
		}	
	%>

</body>
</html>