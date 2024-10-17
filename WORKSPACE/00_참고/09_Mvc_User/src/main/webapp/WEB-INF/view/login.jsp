<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- link  -->
	<%@ include file="/resources/static/jsp/link.jsp" %>
	
	<!-- common.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/common.css" />


	<!-- login.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/login.css" />

	
	<!-- common.js -->
	<script src="${pageContext.request.contextPath}/resources/static/js/common.js" defer></script>
</head>

<body>
	
	<div class="wrapper">
		<header>
			
			<!-- top-header -->
			<%@ include file="/resources/static/jsp/topHeader.jsp" %>
			<!-- nav -->
			<%@ include file="/resources/static/jsp/nav.jsp" %>

		</header>
       	
       	
       	<main>
            <section class=login-block>
            	<form action="${pageContext.request.contextPath}/login" method="post">
            			<div>
            				<h2>로그인</h2>
            			</div>
            		  	<div>
            				<input type="text"  class="form-control m-3" name=username placeholder="username"/>
            			</div>
            			<div>
            				<input type="password"  class="form-control m-3" name=password placeholder="password" />
            			</div>
            			<div>
            				<button class="btn btn-secondary m-3">로그인</button>
            			</div>
            	</form>
            </section>
            <section></section>
        </main>
        
        
        <footer>
        	<!-- footer  -->
			<%@ include file="/resources/static/jsp/footer.jsp" %>
       </footer>
        	
	</div>
		
		<script>
	    // 세션 객체의 속성값 가져오기
	    var msg = '<%= session.getAttribute("msg") %>';
		console.log(msg);
	    // 메시지가 존재하는 경우에만 alert로 표시
 	    if (msg!='null') {
	        alert(msg);
	    } 
	    <%session.removeAttribute("msg");%>
	    
	</script>
</body>	
</html>