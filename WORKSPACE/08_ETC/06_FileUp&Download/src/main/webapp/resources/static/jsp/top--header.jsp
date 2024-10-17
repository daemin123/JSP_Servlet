<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<span style="font-size : 0.8rem;color : darkgray;"> ${ID}</span> 	
<span style="font-size : 0.8rem;color : darkgray;"> ${ROLE} </span>
<!-- 나의 메뉴이동 -->
<c:if test="${not empty ROLE}">
	<a href="${pageContext.request.contextPath}/mypage.do" > 나의 메뉴</a> 
</c:if>
<!-- 로그인/로그아웃 -->
<c:if test="${empty ROLE}">
	<a href="${pageContext.request.contextPath}/login.do"  >로그인</a>
</c:if>
<c:if test="${not empty ROLE}">
	<a href="${pageContext.request.contextPath}/logout.do" >로그아웃</a>
</c:if>
