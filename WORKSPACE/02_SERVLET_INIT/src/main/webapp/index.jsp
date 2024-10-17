<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<style>
		*{margin :5px;}
		.wrapper>header{
			height : 150px; border:1px solid;
		}
		.wrapper>header>.top-header{
			height:45px;
			border : 1px solid;	
			
		}
		.wrapper>header>.top-header>ul{
			display:flex;
			justify-content: right;
			align-items : center;
			list-style:none;
			padding : 0px;
		}
		.wrapper>header>.top-header>ul>li{
			width : 120px;
			height : 100%;
			border : 1px solid;
		}
		.wrapper>header>nav{
			height:75px;
			border : 1px solid;
		}
		.wrapper>main{
			height:500px; border : 1px solid;
		}
		.wrapper>footer{
			height : 300px; border : 1px solid;
		}
	</style>
<body>

	<div class="wrapper">
		<header>
			<div class="top-header">
				<ul>
					<li><a href="${pageContext.request.contextPath}/join">회원가입</a></li>
					<li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
				</ul>
			</div>
			<%@include file="fragments/nav.jsp" %>
		</header>
		<main>
			<section></section>
			<section></section>
			<section></section>
		</main>
		<footer>
			<%@include file="fragments/footer.jsp" %>
		</footer>
	</div>

</body>
</html>