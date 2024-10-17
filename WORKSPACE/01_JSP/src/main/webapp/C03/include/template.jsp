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
			<%@include file="fragments/topHeader.jsp" %>
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