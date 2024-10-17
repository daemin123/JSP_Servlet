<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 변수선언  -->
	<c:set var="num" value="123" />
	<!-- 변수출력 -->
	<c:out value="${num}" /> <br>
	NUM : ${num} <br>
	<hr />
	<!-- 배열지정 -->
	<c:set var="arr" value="${[10,20,30,40,50]}" /> 
	<c:out value="${arr}" /> <br>
	<c:out value="${arr[1]}" /> <br>
	<hr />
	
	<!-- if -->
	<%-- <c:set var="flag" value="true" /> --%>
	<c:if test="${empty flag}">
		<c:out value="flag false!!" />
	</c:if>
	<c:if test="${not empty flag}">
		<c:out value="flag true!!" />
	</c:if>	
	
	<hr />
	<!-- choose when -->
	<c:set var="str" value="d" />
	<c:choose>
		<c:when test="${str eq 'a' }">
			<c:out value="[CHOOSE] A" />
		</c:when>
		<c:when test="${str eq 'b' }">
			<c:out value="[CHOOSE] B" />
		</c:when>	
		<c:when test="${str eq 'c' }">
			<c:out value="[CHOOSE] C" />
		</c:when>
		<c:otherwise>
			<c:out value="[CHOOSE] ETC" />
		</c:otherwise>
	</c:choose>
	
	<hr/>
	
	<!-- forEach -->
	<c:set var="end" value="15" />
	<c:forEach var="i" begin="1" end="${end}" step="2">
		<c:out value="${i}"/> |
	</c:forEach>
	
	<hr />

	<!--  
	참고 https://baessi.tistory.com/144
	-->
	<%@page import="java.util.*" %>
	<%
		List<String> list = new ArrayList();
		list.add("JAVA");
		list.add("JSP");
		list.add("SERVLET");
		list.add("STS3 SPRINGFRAMEWORK");
		list.add("SPRING BOOT");
		request.setAttribute("list",list);
	%>
	<%-- <c:set var="arr2" value="${['aaa','bbb','ccc','ddd','eee']}" /> --%>
	
	Total : ${fn:length(list)} <br>
	
	<c:forEach var="item"  items="${list}" varStatus="status">
		${status.index} . ${item} | count : ${status.count}<br>
	</c:forEach>
	
	
	
	
	
	
	
	
	
	
</body>
</html>

