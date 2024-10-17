<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 
<%
	session.invalidate();
%>
<script>
	alert("로그아웃 성공!.. 로그인페이지로 이동합니다.")
</script>
<%
	String projectPath = pageContext.getServletContext().getContextPath();
	response.sendRedirect("login.jsp");
%>