<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- BS5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!-- BSICON -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

<!-- JQ -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>

<style>
		<%@include file="/resources/static/css/join.css" %>
</style>


</head>
<body>
<section class="container">
	<div class="msg">${msg}</div>
	<h1>MEMBER JOIN</h1>
	<form id="joinfrm" name="joinfrm" action="${pageContext.request.contextPath}/member/add.do" method="post" onsubmit="return false">
		<input type="text" name="email" placeholder="example@example.com" class="form-control" />
		<input type="password" name="pwd"  placeholder="Insert Password" class="form-control" />
		
		<div class="row" style="margin-bottom:0px;" id="SMSAuth">
			<div class="col-8">
				<input type="text" name="phone"  placeholder="Phone(-없이 입력하세요)" class="form-control" />			
			</div>		
			<div class=col-4>
				<a href=""	class="btn btn-secondary w-100">인증요청</a>
			</div>		
		</div>	
		
		<div class="row" style="margin-bottom:0px;">
			<div class="col-8">
				<input type="text" name="zipcode"  placeholder="우편번호를 입력하세요" class="form-control" />
			</div>
			<div class="col" style="text-align:right">
				<button class="btn btn-secondary w-100" onclick="searchZip()">우편번호 검색</button>	
			</div>	
		</div>
		
		<input type="text" name="addr1"  placeholder="기본주소 입력"  class="form-control" />
		<input type="text" name="addr2"  placeholder="상세주소 입력" class="form-control" />
		<button class="btn btn-secondary" onclick="isValid()">회원가입</button>
		<input type="reset" value="초기화" class="btn btn-danger" />
		<a href="javascript:history.go(-1)" class="btn btn-warning">이전으로</a>
	</form>
</section>




<script defer>
	/* 자바스크립트 수준의 유효성 체크  */
	const isValid=function(){
		const joinfrm = document.joinfrm;
		//alert("[JS] func isValid");
		//email 공백여부 등 Validation Check

		joinfrm.submit();
	}
</script>

<!-- 우편번호 검색 -->
<script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script defer>
	
	
	const searchZip=function()
	{
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            let form = document.joinfrm;
	            var addr='';

	            //사용자가 도로명 주소 선택
	            if(data.userSelectedType==='R')
	            {
	            	addr=data.roadAddress;
	            }
	            else //사용자가 지번 주소 선택 'J'
	            {
	            	addr=data.jibunAddress;
	            }  
	            form.zipcode.value=data.zonecode;
	            form.addr1.value=addr;
	            
	        }
	    }).open();
	}
</script>
<!-- 우편번호 검색 -->


</body>
</html>