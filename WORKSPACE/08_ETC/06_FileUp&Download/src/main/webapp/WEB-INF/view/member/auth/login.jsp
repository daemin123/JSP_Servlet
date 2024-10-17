<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<!-- bootstrap cdn&bundle -->
<%@ include file="/resources/static/jsp/link.jsp" %>
<style>
	<%@include file="/resources/static/css/login.css" %>

</style>
</head>
<body>

<div class=wrapper>
<div class="msg" >${msg}</div>
<h1>LOGIN</h1>
<form action="" method="post">
	<input name="id" class="form-control" placeholder="Insert Id"><br>
	<input name="pw" type="password" class="form-control" placeholder="Insert Password"><br>
	<input type=submit value="로그인" class="btn btn-primary w-100">
</form>
<div>
	<button class="btn btn-warning w-100"  style="color:white">카카오로그인</button>
</div>
<div>
	<a class="btn btn-secondary w-100"  style="color:white" href="${pageContext.request.contextPath}/member/add.do">회원가입</a>
</div>

</div>



</body>
</html>