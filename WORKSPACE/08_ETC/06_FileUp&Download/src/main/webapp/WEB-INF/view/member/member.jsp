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
	<%@include file="/resources/static/css/member.css" %>
	
	.top--header>*{
		display:block;
		width : 100px;
		height : 30px;
		display : relative;
		text-align:right;
		height : 30px;
		font-size : 0.8rem;
	}
	
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
		
		<style>
			.first_section>*{
				margin-top:10px;
			}
			.first_section form{
				display:flex;
				justify-content : left;
				align-item:left;
			}
			.first_section form input{
				width : 200px;
				height : 40px;
			
			}
			.first_section form>*{
				margin-right : 10px;
			}
		</style>
		<main>
			<section class="first_section">
			<h1>${ID} PAGE</h1>
				<!-- 도서 대여 기능 -->
				<h2>도서 대여 등록하기(비동기 요청)</h2>
				MSG : <span class="add_msg"></span>
				<hr>
				<div>
					<form action="a.jsp" onSubmit="return false" name="lendAddForm">
						<input type="text" name="bookcode" placeholder="bookcode"	class="form-control"			/>
						<input type="text" name="id" 	placeholder="id"	class="form-control"			/>
						<button class="lend_add_btn btn btn-primary">등록요청</button>
					</form>
				</div>
			</section>
			
		</main>
	</div>
	


<!-- axios cdn -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js" integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
const projectPath='${pageContext.request.contextPath}';
const ServerPort = '${pageContext.request.serverPort}';
const ServerHost = '<%=request.getLocalAddr()%>';


	const lend_add_btn_el = document.querySelector('.lend_add_btn');
	
	
	lend_add_btn_el.addEventListener('click',function(){
		
	;
		
		const form=document.lendAddForm;
		const params = {params :{'bookcode':form[0].value,'id':form[1].value } }
		
		axios.post("http://"+ServerHost+':'+ ServerPort + projectPath +"/lend/add.do",null,params)
		.then(response=>{
			console.log('response',response);
			console.log('response',response.data);

			document.querySelector(".add_msg").innerHTML=response.data;
		})
		.catch(error=>{})
		
	})
</script>

</body>
</html>