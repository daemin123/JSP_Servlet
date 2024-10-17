<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="/resources/static/jsp/link.jsp" %>

<style>
	<%@include file="/resources/static/css/common.css" %>
	<%@include file="/resources/static/css/admin.css" %>
</style>

<!-- chart.js -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<!-- drawchart.js  -->
<script src="resources/static/js/drawchart.js" defer></script>
<script src="resources/static/js/TodayChart.js" defer></script>


</head>
<body>

<div class=wrapper>
	
	<header>
		<div class="top--header">
			<div>
				TOTAL : ${todayLog.total}  USER : ${todayLog.user } MEMBER : ${todayLog.member} DATE : ${todayLog.date}  <br/>
			</div>
			<div>
				<a href="javascript:history.go(-1)">이전으로</a>
			</div>
		</div>
		<nav>
			<%@include file="/resources/static/jsp/nav.jsp" %>
		</nav>
	</header>
	<main>
		<section>
			<div>
				<h1>ADMIN DashBoard</h1>
			</div>		
			
			<div class="show_block">
				
				<div class=c_total >
					<canvas id="total"></canvas>
				</div>
				<div class=c_user  >
					<canvas id=user></canvas>
				</div>
				<div class=c_member  >
					<canvas id=member></canvas>
				</div>
			</div>
		</section>
	</main>	
	<<footer>
	</footer>
	

</div>



<script defer>	
	/* 랜더링 될때 Chart표시하기  */
	document.addEventListener("DOMContentLoaded",function(){
		/* today  */
		const todayTotal = '${todayLog.total}';
		const todayUser = '${todayLog.user}';
		const todayMember = '${todayLog.member}';
		const todayDate = '${todayLog.date}';
		const todayobj = { 'total' : todayTotal, 'user' : todayUser,'member':todayMember,'date':todayDate};
		todayChart( todayobj , 'total')
		/*  */
		chartByData('user')
		/*  */
		chartByData('member')
	})
</script>

</body>
</html>