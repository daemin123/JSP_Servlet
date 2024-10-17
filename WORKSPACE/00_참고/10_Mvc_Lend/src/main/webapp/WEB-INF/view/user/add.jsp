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

	<!-- user/add.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/user/add.css" />
		
	
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
            <section>
            
                <div class="join-block">
                	<h3>회원가입</h3>
            		<form action="${pageContext.request.contextPath}/user/add" method="post">
            			<div>
            				<input type="text"  class="form-control m-3" name=username placeholder="username"/>
            			</div>
            			<div>
            				<input type="password"  class="form-control m-3" name=password placeholder="password" />
            			</div>
            			<div>
            				<button class="btn btn-secondary m-3">회원가입</button>
            			</div>
            		</form>
            	</div>
            	
            </section>
            <section></section>
        </main>
        
        
        <footer>
        	<!-- footer  -->
			<%@ include file="/resources/static/jsp/footer.jsp" %>
       </footer>
        	
	</div>
		
</body>	
</html>