<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<%@ include file="/resources/static/jsp/link.jsp" %>

<style>
	<%@include file="/resources/static/css/common.css" %>
	<%@include file="/resources/static/css/user.css" %>
</style>


</head>
<body>

<div class="wrapper">

		<header>
			<div style="text-align:right;">
					<a href="javascript:history.go(-1)">이전으로</a>
			</div>
			<div class="top--header">
				<%@include file="/resources/static/jsp/top--header.jsp" %>		
			</div>
			<nav>
				<%@include file="/resources/static/jsp/nav.jsp" %>
			</nav>
		</header>
		<main>
			<section>
					<div class="title">
						<h1>${ID} PAGE</h1>
					</div>
		
				<div class="alarm_block">
					<c:if test="${not empty alarm_cnt}">
						<a href="javascript:showMsg()">
							<span class="dotted_bg">${alarm_cnt } </span>
						</a>
					</c:if>
					<span class="material-symbols-outlined">notifications</span>
				</div>	
			</section>

			<section class=second_section>
				<h3>대여 내역 조회(비동기 요청)</h3>
				<button class="lend_btn btn btn-primary" >조회하기</button>
				<div class="mylendList">
				
					<div class=body>
						<table class="table" >
							<thead>
							<tr>
								<th style="width:100px;">대여ID</th>
								<th style="width:100px;">도서코드</th>
								<th style="width:100px;">유저계정</th>
								<th style="width:100px;">대여일자</th>
								<th style="width:100px;">반납일자</th>
							</tr>	
							<thead>
							<tbody >
							
							</tbody>	
						</table>
					</div>
				
				</div>
			</section>
			
		</main>
		<footer>
		
		</footer>
</div>



<hr/>



<!-- axios cdn -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js" integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
const projectPath='${pageContext.request.contextPath}';
const ServerPort = '${pageContext.request.serverPort}';
const ServerHost = '<%=request.getLocalAddr()%>';


	/*  */
	function showMsg(){
		const list = '${alarm_list}';
		console.log("list",list);
		alert(list);

		console.log("serverHost : " , ServerHost);
		const params = {params :{'userid': '${ID}' } }
		axios.post("http://"+ServerHost+':'+ServerPort+projectPath+'/lend/messageRemove.do',null,params)
		.then(response=>{ 
			
			alert("success");
			console.log("response.data : ",typeof response.data);
			if(response.data===true){
				const dotted_bg_el =document.querySelector('.dotted_bg');
				dotted_bg_el.classList.add("hide");	
			}	
		})
		.catch(error=>{alert("error");})
	}
	

	/* 날짜포맷 */
	function formatDate(date) {
		  const year = date.getFullYear();
		  const month = String(date.getMonth() + 1).padStart(2, '0');
		  const day = String(date.getDate()).padStart(2, '0');
		  console.log(year,month,day);
		  
		  return year+"-"+month+"-"+day;
	}
	
	
	const table_el = document.querySelector('.body .table tbody');

	const lend_btn_el = document.querySelector('.lend_btn');
	lend_btn_el.addEventListener('click',function(){
			
		axios.get("http://"+ServerHost+':'+ServerPort+projectPath+'/lend/search.do')
		.then(response=>{
			console.log('response',response);
			
			/* 기존 노드 삭제  */
			while (table_el.hasChildNodes()) {
				table_el.removeChild(table_el.firstChild);
			}
			
			 
			const mylendList_el = document.querySelector('.mylendList');
			const list = response.data;
			list.forEach((dto)=>{
			
				console.log('dto',dto);
				
				
				const tr = document.createElement('tr');
				const td1 = document.createElement('td');
				const td2 = document.createElement('td');
				const td3 = document.createElement('td');
				const td4 = document.createElement('td');
				const td5 = document.createElement('td');

				td1.innerHTML = dto.lendId;
				td2.innerHTML = dto.bookcode;
				td3.innerHTML = dto.id;
				td4.innerHTML = formatDate(new Date(dto.lendDate))
				td5.innerHTML = formatDate(new Date(dto.returnDate))

				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				tr.appendChild(td4);
				tr.appendChild(td5);
				table_el.appendChild(tr);
				
				
				
				
				
					
				
			}) 
			
			
			
		})
		.catch(error=>{console.log('error',error)})
	})

</script>

</body>
</html>