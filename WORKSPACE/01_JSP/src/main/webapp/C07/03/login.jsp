<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%

	Cookie [] cookies = request.getCookies();
	String username=null;
	String idchk=null;
	for(Cookie cookie : cookies){
		if(cookie.getName().equals("idchk"))
			idchk = cookie.getValue();
		if(cookie.getName().equals("username"))
			username = cookie.getValue();
	}
	
%>

<body>

		<form action="loginProc.jsp" method="post">
			<input type="text" name="username" 	value='<%=(idchk!=null) ? username : "" %>'	/> <br>
			<input type="password" name="password" 	/> <br>
			
			<input type="checkbox" name="idchk"> ID저장 <br>
			<button>로그인</button>
		</form>
		
		
		<script>
		  const message =  "<%=session.getAttribute("msg")%>";
		  console.log(message);
		  console.log(typeof message);
		  if(message!=="null")
				alert(message);		 
		  
		  <% session.removeAttribute("msg"); %>
		  sessionStorage.setItem('msg',message);
		</script>
		
		
</body>
</html>


