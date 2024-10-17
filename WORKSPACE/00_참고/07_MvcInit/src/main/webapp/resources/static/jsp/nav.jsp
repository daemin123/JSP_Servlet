<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			  <div class="container-fluid">
			    <a class="navbar-brand" href="#">🔶<a>
			    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			      <span class="navbar-toggler-icon"></span>
			   </button>
			    <div class="collapse navbar-collapse" id="navbarSupportedContent">
			      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
			        <li class="nav-item me-5">
			          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/main.do">HOME</a>
			        </li>
			        <li class="nav-item me-5">
			          <a class="nav-link" href="#">공지사항</a>
			        </li>
			        <li class="nav-item me-5">
			          <a class="nav-link" href="#">자유게시판</a>
			        </li>
		 			<li class="nav-item me-5">
			          <a class="nav-link" href="#">이미지게시판</a>
			        </li>
			       	
			        
			                <li class="nav-item dropdown">
					          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
					            기타 테스트
					          </a>
					          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
					            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/etc/fileupload.do">파일업로드</a></li>
					            <li><a class="dropdown-item" href="#">파일다운로드</a></li>
					            <li><a class="dropdown-item" href="#">API사용하기</a></li>
					          </ul>
					        </li>
      
			      </ul>
    
			      
			      <form class="d-flex" onSubmit="return false">
				      <select name="type" class="form-select" style="width : 120px;margin-right:10px;">
						<option value="bookcode" selected>코드</option>
						<option value="bookname">제목</option>
						<option value="publisher">출판사</option>
					</select>
				
			        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
			        <button class="btn btn-outline-success search_btn" type="submit" >Search</button>
			      </form>
			    </div>
			  </div>
 
		</nav>