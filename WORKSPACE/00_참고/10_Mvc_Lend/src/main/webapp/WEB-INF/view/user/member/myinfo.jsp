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
	
	<!-- user/myinfo.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/user/member/myinfo.css" />
	
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
				    <li class="breadcrumb-item active" aria-current="page">MYINFO(ROLE_MEMBER)</li>
				  </ol>
				</nav>
            </section>
            <section>
            	<div class="main-block">
            		<aside class=left>
              			<div>
            				<ul>
            					<li><a href="${pageContext.request.contextPath}/user/myinfo?serviceNo=0"> 전체 대여신청 내역</a></li>
            					<li><a href="${pageContext.request.contextPath}/user/myinfo?serviceNo=1"> 미승인 대여신청 내역</a></li>
	            				<li><a href="${pageContext.request.contextPath}/user/myinfo?serviceNo=2"> 승인된 대여신청 내역</a></li>
	            				<li><a href=""> menu 03</a></li>
	            				<li><a href=""> menu 04</a></li>
	            				<li><a href=""> menu 05</a></li>
            				</ul>
            			
            			</div>          		
            		
            		
            		</aside>
            		<div class=right>
            		            <div class="breadcrum-block mb-3">
						            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
									  <ol class="breadcrumb">
									    <!-- <li class="breadcrumb-item"><a href="#">HOME</a></li> -->
									    <li class="breadcrumb-item active" aria-current="page">도서신청 확인<li>
									  </ol>
									</nav>
					            </div>
          					
            					<div class=body>
            						<div>${list.size()}</div>
	           						<table class="table w-75">
	            							<thead>
	            								<th>대여ID</th>
	            								<th>도서코드</th>
	            								<th>대여상태</th>
	            								<th>반납예정일</th>						
	            								<th>허용/취소</th>	
	            							</thead>
	            							<tbody>
	            				
												<c:forEach var="lendDto" items="${list}">
													<tr>
														<td>${lendDto.lendId}</td>
														<td>${lendDto.bookCode}</td>
														<td>${(lendDto.acceptedLend)?'미승인':'승인'}</td>
														<td>${lendDto.dueDate}</td>
														<td>
													            <c:choose>
													                <c:when test="${!lendDto.acceptedLend}">
													                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/lend/accept?lendId=${lendDto.lendId}">승인</a>
													                </c:when>
													                <c:otherwise>
													                    <a class="btn btn-secondary" href="#">취소(아직..)</a>
													                </c:otherwise>
													            </c:choose>	

														</td>
													</tr>	
												</c:forEach>    
	            							</tbody>
	            						</table>           						
            					
            					</div>
            		
            		</div>	
            	</div>
       		
            </section>
            <section></section>
            <section></section>
            <section></section>
            
        </main>
        
        
        <footer>
        	<!-- footer  -->
			<%@ include file="/resources/static/jsp/footer.jsp" %>
       </footer>
        	
	</div>
	
	
	<!--  
		메시징 처리
	-->
	<script>
	    // 세션 객체의 속성값 가져오기
	    var msg = '<%= session.getAttribute("msg") %>';
		console.log(msg);
	    //메시지가 존재하는 경우에만 alert로 표시
 	    if (msg!='null') {
	        alert(msg);
	    } 
 	   <%session.removeAttribute("msg");%>
	</script>
			
	
	
<!-- 			
		<script>
			const reqLend_btn = document.querySelector('.reqLendAccept_btn');
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
		
		-->
		
</body>	
</html>