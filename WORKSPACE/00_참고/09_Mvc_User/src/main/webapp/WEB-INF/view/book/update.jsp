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
	
	<!-- update.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/book/update.css" />
		
		
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
       	
       	
       	<main class="layout-150">
       	    <section class=breadcrum-block>
	            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
				  <ol class="breadcrumb">
				    <li class="breadcrumb-item"><a href="#">HOME</a></li>
				    <li class="breadcrumb-item"><a href="#">BOOK(LIST)</a></li>
					<li class="breadcrumb-item"><a href="#">READ</a></li>
				    <li class="breadcrumb-item active" aria-current="page">UPDATE</li>
				  </ol>
				</nav>
            </section>
            
            
            <div class="main-block">
				<aside class="left"></aside>
				<div class="right">
				           ${bookDto}<br>
		            <section>
		            	<form action="${pageContext.request.contextPath}/book/update" method="post" class="w-100 m-3">
		            		<div>
		            			<input type="text" name="bookCode"  value="${bookDto.bookCode}" placeholder="bookCode"  class="form-control m-3"  readonly />
		            		</div>  		
		            		<div>
		            			<input type="text" name="bookName" value="${bookDto.bookName}" placeholder="bookName" class="form-control m-3" />
		            		</div>
		            		<div>
		            			<input type="text" name="publisher" value="${bookDto.publisher}" placeholder="publisher" class="form-control m-3" />
		            		</div>
		             		<div>
		            			<input type="text" name="isbn" value="${bookDto.isbn}" placeholder="isbn" class="form-control m-3" />
		            		</div>           		
		             		
		             		<div>
		            			<button class="btn btn-secondary m-3">도서수정</button>			
		            			<a class="btn btn-secondary m-3" href="${pageContext.request.contextPath}/book/read?bookCode=${bookDto.bookCode}">이전으로</a>
		            		</div>            				
		            	</form>
		            </section>

				</div>            
            </div>
 
        </main>
        
        
        <footer>
        	<!-- footer  -->
			<%@ include file="/resources/static/jsp/footer.jsp" %>
       </footer>
        	
	</div>
		
</body>	
</html>