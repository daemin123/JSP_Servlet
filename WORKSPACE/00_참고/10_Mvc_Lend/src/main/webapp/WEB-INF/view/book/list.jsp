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
	
	<!-- common.js -->
	<script src="${pageContext.request.contextPath}/resources/static/js/common.js" defer></script>
	
	<!-- book/list.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/book/list.css" />
	
	<!-- book/list.js -->
	<script src="${pageContext.request.contextPath}/resources/static/js/book/list.js" defer></script>
	
</head>

<body>
	
	<div class="wrapper">
		<header>
			
			<!-- top-header -->
			<%@ include file="/resources/static/jsp/topHeader.jsp" %>
			<!-- nav -->
			<%@ include file="/resources/static/jsp/nav.jsp" %>

		</header>
       <main class=layout-150>

            <section class=breadcrum-block>
	            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
				  <ol class="breadcrumb">
				    <li class="breadcrumb-item"><a href="#">HOME</a></li>
				    <li class="breadcrumb-item active" aria-current="page">BOOK(LIST)</li>
				  </ol>
				</nav>
            </section>
            
            <section class="search-block layout-150">
       
            	<form action="${pageContext.request.contextPath}/book/list">          		
            		<div class="m-2">
	            		<select name="type"  class="form-select">
	            			<option value="bookCode" selected>bookCode</option>
	            			<option value="bookName">bookName</option>
	            			<option value="publisher">publisher</option>
	            			<option value="isbn">isbn</option>
	            		</select>
            		</div>
            		<div class="m-2">
            			<input name="keyword" placeholder="KEYWORD" class="form-control">
            		</div>	
            		<input type="hidden" name="pageNo" value="${pageDto.criteria.pageno}" />
            		
            		<div class="m-2">
            			<button class="btn btn-secondary">조회</button>
            		</div>
            		
            	</form>
            </section>

 			<div class="main-block">
 				<aside class=left></aside>
 				
 				<div class=right>
					<section class="show-block">
            	<%-- ${pageDto} --%>
		         		<div>
		         	
		            		<div>TOTAL PAGE: <span> ${pageDto.totalpage}</span> </div>
		            		  
		            		<div>NOW PAGE: <span> ${pageDto.criteria.pageno}</span> </div>
		            	</div> 
		         	</section>
		            
			          <section >
			            	<table class="table">
			            		<thead class="table-secondary">			            		
				            		<tr>
				            			<th scope="col">BOOKCODE</th>
				            			<th scope="col">BOOKNAME</th>
				            			<th scope="col">PUBLISHER</th>
				            			<th scope="col">ISBN</th>
				            		</tr>
			            		</thead>
			            		<c:forEach var="bookDto" items="${list}" varStatus="status" >
				            		<tr>	
										
										<td>
											<a href="${pageContext.request.contextPath}/book/read?bookCode=${bookDto.bookCode}">
												<c:out value="${bookDto.bookCode}" />
											</a>
										</td>
										<td><c:out value="${bookDto.bookName}" /></td>
										<td><c:out value="${bookDto.publisher}" /></td>
										<td><c:out value="${bookDto.isbn}" /></td>
									</tr>						
								</c:forEach>
			            	</table>      
			            </section>
			            
			            <!-- paging -->
			            <section>
				            	<nav aria-label="Page navigation example">
								  <ul class="pagination">
								    <!-- prev -->
								    
								    <c:if test="${pageDto.prev}">
										 <li class="page-item">
										      
										      <!-- keyword 존재유무에 따라 선별하기 -->
										      <a class="page-link" href="${pageContext.request.contextPath}/book/list?pageNo=${pageDto.nowBlock * pageDto.pagePerBlock - pageDto.pagePerBlock*2 + 1}&type=${pageDto.criteria.type}&keyword=${pageDto.criteria.keyword}" aria-label="Previous">
										        <span aria-hidden="true">&laquo;</span>
										      </a>
										      
										      
										  </li>        
								    </c:if>
			
								    
								    <!-- paging -->
									<c:forEach begin="${pageDto.startPage}" end="${pageDto.endPage }" var="pageno" step="1">
										    
										    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/book/list?pageNo=${pageno}&type=${pageDto.criteria.type}&keyword=${pageDto.criteria.keyword}">${pageno}</a></li>
									</c:forEach>
								    
								    <!-- next -->
								   <c:if test="${pageDto.next}">
									    <li class="page-item">
										      <a class="page-link" href="${pageContext.request.contextPath}/book/list?pageNo=${pageDto.nowBlock*pageDto.pagePerBlock+1}&type=${pageDto.criteria.type}&keyword=${pageDto.criteria.keyword}" aria-label="Next">
										        	<span aria-hidden="true">&raquo;</span>
										      </a>
									    </li>
								    </c:if>
								    
								  </ul>
								</nav>
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