<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<form action="loginProc.jsp" method="post">
			<input type="text" name="username" 		/> <br>
			<input type="password" name="password" 	/> <br>
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


