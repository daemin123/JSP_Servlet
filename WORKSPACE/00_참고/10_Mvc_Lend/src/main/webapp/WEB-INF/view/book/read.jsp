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
	
	<!-- read.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/book/read.css" />
		
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
						    <li class="breadcrumb-item active" aria-current="page">READ</li>
						  </ol>
						</nav>
			</section>
            
       		<div class="main-block">
       			<aside class="left"></aside>
       		
	 			<div class=right>

		            
		            ${bookDto}<br>
		            <section>
		                       
		      	
		            	<form action="${pageContext.request.contextPath}/book/update" method="get" class="w-100 m-3">
		            		<div>
		            			<input type="text" name="bookCode"  value="${bookDto.bookCode}" placeholder="bookCode"  class="form-control m-3"  readonly />
		            		</div>  		
		            		<div>
		            			<input type="text" name="bookName" value="${bookDto.bookName}" placeholder="bookName" class="form-control m-3" readonly/>
		            		</div>
		            		<div>
		            			<input type="text" name="publisher" value="${bookDto.publisher}" placeholder="publisher" class="form-control m-3" readonly/>
		            		</div>
		             		<div>
		            			<input type="text" name="isbn" value="${bookDto.isbn}" placeholder="isbn" class="form-control m-3" readonly/>
		            		</div>           		
		             		
		             		<div>
		             			<div>${sessionDto}</div>
			             		<c:if test="${sessionDto.role eq 'ROLE_USER'}">
										<a href="javascript:void(0)" class="btn btn-secondary m-3 reqLend_btn">대여신청</a>
			             		</c:if>
		             			<c:if test="${sessionDto.role eq 'ROLE_MEMBER'}">
			             			<button class="btn btn-secondary m-3">도서수정</button>
									<a href="${pageContext.request.contextPath}/book/delete?bookCode=${bookDto.bookCode}"  class="btn btn-secondary m-3">도서삭제</a>
		             			</c:if>
		            			
		            			<a class="btn btn-secondary m-3" href="${pageContext.request.contextPath}/book/list">이전으로</a>
		            		</div>            				
		            	</form>
		            </section>
		            
		            </section>
		            <section></section>
	 			</div>
	 		</div>
        </main>
        
        
        <footer>
        	<!-- footer  -->
			<%@ include file="/resources/static/jsp/footer.jsp" %>
       </footer>
        	
	</div>
		
		<script>
			const reqLend_btn = document.querySelector('.reqLend_btn');
			reqLend_btn.addEventListener('click',function(){
				
				const bookCode = ${bookDto.bookCode};
				const path = '${pageContext.request.contextPath}';
				
				console.log("clicked....bookCode : " + bookCode);
				axios.get(path+"/lend/add?bookCode=" + bookCode)
				.then(resp=>{
					console.log(resp);
					alert(resp.data.response);
				})
				.catch(err=>{
					console.log(err);
					alert(resp.data.response);
				});
				
			})
		</script>
</body>	
</html>

