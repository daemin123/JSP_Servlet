<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap cdn&bundle -->
<%@ include file="/resources/static/jsp/link.jsp" %>


<style>
	<%@include file="/resources/static/css/common.css" %>
	<%@include file="/resources/static/css/main.css" %>
</style>

<script src="resources/static/js/main.js" defer></script>

</head>
<body>

<div class="wrapper">
	<div class="title">
		<h1></h1>
	</div>
	<header>
		<div class=msg>${msg}</div>
		<div class="top--header">
			<span style="font-size : 0.8rem;color : darkgray;"> ${ID}</span> 	
			<span style="font-size : 0.8rem;color : darkgray;"> ${ROLE} </span>
			<!-- 나의 메뉴이동 -->
			<c:if test="${not empty ROLE}">
				<a href=<c:url value="/mypage.do" /> >나의 메뉴</a> 
			</c:if>
			<!-- 로그인/로그아웃 -->
			<c:if test="${empty ROLE}">
				<a href="${pageContext.request.contextPath}/login.do"  >로그인</a>
			</c:if>
			<c:if test="${not empty ROLE}">
				<a href="${pageContext.request.contextPath}/logout.do" >로그아웃</a>
			</c:if>
		</div>
		 
		<!-- nav  -->
		<%@include file="/resources/static/jsp/nav.jsp" %>
	</header>
	<main>
		<section class="search">

			<div class="show_block">
				<!-- 헤더 -->
				<div class=header>
					PAGE : <span style=color:red; class="now">${pagedto.criteria.pageno}</span> / <span class="total">${pagedto.totalpage} </span> Page 
				</div>
				
				<!--본문  -->
				<div class=body>
						<table class="table">
							<thead>
								<tr>
									<th style="width:150px;">도서코드</th>
									<th>도서명</th>
									<th style="width:200px;">출판사</th>
									<th style="width:150px;">ISBN</th>
								</tr>	
							</thead>
							<tbody>
							
							</tbody>							
						</table>
				</div>
				
				<!-- 페이징처리 -->
				<div class=footer>
					
					
					<div class=left>
					
						
					</div>
					<div class=right>
						<button class="btn btn-primary">버튼1</button>
						<button class="btn btn-primary">버튼2</button>
					</div>
				</div>
				
			</div>
			
		</section>	
		
		<section></section>	
		 
		
	</main>
	<foooter>
	
	</foooter>

</div>


<hr>



 


<hr/>


EL's Project PATH : ${pageContext.request.contextPath}<br/>
EL's Project ServerPort :${pageContext.request.serverPort}<br/>




<script defer>
const search_btn_el = document.querySelector('.search_btn');
const projectPath = '${pageContext.request.contextPath}';
const ServerPort = '${pageContext.request.serverPort}';
const ServerHost = '<%=request.getLocalAddr()%>';
const table_el = document.querySelector('.show_block .body .table tbody');


const paging_block_el = document.querySelector(".footer .left");
/* paging 요청 함수  */
const req_paging = (pageNo) => {
	const params = { params: { pageno: pageNo } };
	axios.get("http://" + ServerHost + ':' + ServerPort + projectPath + "/book/search.do", params)
		.then(response => {
			console.log("success!", response.data);
			const dataArr = response.data.split('|');
			const list = JSON.parse(dataArr[0]);
			const pagedto = JSON.parse(dataArr[1]);
			console.log('list', list);
			console.log('pagedto', pagedto);

			while (table_el.hasChildNodes()) {
				table_el.removeChild(table_el.firstChild);
			}
			list.forEach((dto) => {

				const tr = document.createElement('tr');
				const td1 = document.createElement('td');
				const td2 = document.createElement('td');
				const td3 = document.createElement('td');
				const td4 = document.createElement('td');

				td1.innerHTML = dto.bookcode;
				td2.innerHTML = dto.bookname;
				td3.innerHTML = dto.publisher;
				td4.innerHTML = dto.isbn;

				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				tr.appendChild(td4);
				table_el.appendChild(tr);


				/* 현재페이지 / 전체페이지 넣기 */
				const nowEl = document.querySelector(".header .now");
				const totalEl = document.querySelector(".header .total");
				nowEl.innerHTML = pagedto.criteria.pageno;
				totalEl.innerHTML = pagedto.totalpage;


			})

			ShowPagingBlock(pagedto);
		})
		.catch(error => { console.log("fail..", error); })	//실패시 처리로직

}


/* paging 보여주기 함수 */
const ShowPagingBlock = (pagedto) => {
	//기존 자식노드 삭제
	while (paging_block_el.hasChildNodes()) {
		paging_block_el.removeChild(paging_block_el.firstChild);
	}

	/* prev */
	if (pagedto.prev === true) {
		const pageno = pagedto.nowBlock * pagedto.pagePerBlock - pagedto.pagePerBlock * 2 + 1;
		console.log("prev pageNo : " + pageno);
		const next_a = document.createElement("a");
		next_a.setAttribute("href", "javascript:req_paging(" + pageno + ")")
		const next = document.createElement("span");
		next.innerHTML = "◀";
		next_a.appendChild(next);

		/* 상위태그에 연결  */
		paging_block_el.appendChild(next_a);
	}


	/* page */
	console.log(pagedto.startPage + " " + pagedto.endPage);
	for (i = pagedto.startPage; i <= pagedto.endPage; i++) {
		const page_a = document.createElement("a");
		page_a.setAttribute("href", "javascript:req_paging(" + i + ")")
		const page_span = document.createElement("span");
		page_span.innerHTML = i;
		page_a.appendChild(page_span);
		paging_block_el.appendChild(page_a);
	}

	/* next */
	if (pagedto.next === true) {
		const pageno = pagedto.nowBlock * pagedto.pagePerBlock + 1;
		console.log("next pageNo : " + pageno);
		const prev_a = document.createElement("a");
		prev_a.setAttribute("href", "javascript:req_paging(" + pageno + ")")
		const prev = document.createElement("span");
		prev.innerHTML = "▶";
		prev_a.appendChild(prev);
		/* 상위태그에 연결  */
		paging_block_el.appendChild(prev_a);
	}

}



 
 
/* Search버튼  눌렀을때  도서 가져오기*/
search_btn_el.addEventListener('click', function() {
	axios.get("http://" + ServerHost + ':' + ServerPort + projectPath + "/book/search.do")
		.then(response => {
			console.log(response.data);

			const dataArr = response.data.split('|');
			const list = JSON.parse(dataArr[0]);
			const pagedto = JSON.parse(dataArr[1]);
			console.log('list', list);
			console.log('pagedto', pagedto);

			while (table_el.hasChildNodes()) {
				table_el.removeChild(table_el.firstChild);
			}

			list.forEach((dto) => {

				const tr = document.createElement('tr');
				const td1 = document.createElement('td');
				const td2 = document.createElement('td');
				const td3 = document.createElement('td');
				const td4 = document.createElement('td');

				td1.innerHTML = dto.bookcode;
				td2.innerHTML = dto.bookname;
				td3.innerHTML = dto.publisher;
				td4.innerHTML = dto.isbn;

				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				tr.appendChild(td4);
				table_el.appendChild(tr);


				/* 현재페이지 / 전체페이지 넣기 */
				const nowEl = document.querySelector(".header .now");
				const totalEl = document.querySelector(".header .total");
				nowEl.innerHTML = pagedto.criteria.pageno;
				totalEl.innerHTML = pagedto.totalpage;

			})
			/* 페이징처리 */
			ShowPagingBlock(pagedto);

		})
		.catch(error => { console.log("fail..", error); })	//실패시 처리로직
})
</script>



</body>
</html>