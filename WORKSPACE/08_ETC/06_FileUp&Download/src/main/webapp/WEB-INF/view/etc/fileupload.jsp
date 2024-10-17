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
	
.top--header{
	height :58px;	 
	display:flex;
	justify-content: right;
	align-items: end;
	position:relative;	
}
.top--header div{
	display:flex;
	align-items: end;
	position:absolute;
	top:35px;
	z-index:3;
	height : 25px;	
}
main{
	
}
main section{
	width : 80%;
	height : 100px;
	border : 1px solid gray;
	margin: 30px auto;
}
main section>*{
	margin : 10px;
	
}
/* UPLOAD BLOCK */
main section:nth-child(2){
	height : 500px;
}
main section:nth-child(2) .showfile_block{
	border : 1px solid gray;
	height : 400px;
	overflow : auto;
}


</style>

</head>
<body>
	
	<div class="wrapper">
		<header>
			<div style="height:25px;text-align:right;">
				<span class="msg">${msg}</span>
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
				<div>
					
				</div>
				<h3>FILE UPLOAD</h3>
				<form  name=uploadform action="${pageContext.request.contextPath}/etc/fileupload.do" method="post" onSubmit="return false" enctype="multipart/form-data">
					<div style="display:flex;justify-content :left;">
						<input type="file" name="files"  class="form-control w-25"  multiple>
						<button  class="btn btn-primary" onclick="isValid()" style="width:100px;margin-left:10px;">UPLOAD</button>
					</div>
				</form>
			</section>
			
			<section>
				<h3>SHOW FILE</h3>
				<div>
					<a  class="btn btn-primary showfile_btn" href="javascript:void(0)" >SHOW FILE</a>
				</div>
				<div class=showfile_block>
					<table class="table w-100">
						<thead>
							<tr>
								<th style="width:50px;">ID</th>
								<th style="width:150px;">USER</th>
								<th style="width:350px;">DIRECTORY</th>
								<th>FILENAME</th>
								<th style="width:50px;" >DOWNLOAD</th>	
								<th style="width:50px;" >DELETE</th>	
							</tr>
						</thead>
						<tbody>
						
						</tbody>
					</table>
				</div>
			</section>
		</main>
		<footer></footer>
	</div>
	
	<script>
		
		/* 자바스크립트 유효성 체크  */
		const isValid = ()=>{
			const form = document.uploadform;
			/* 요소에 값이 제대로 들어왔는지 체크해보기 */
			
			
			/* submit */
			form.submit();
		}
		
		
		/* ShowFile Btn */
		
		/* Search버튼  눌렀을때  도서 가져오기*/
		const showfile_btn_el = document.querySelector(".showfile_btn");
		
		const projectPath = '${pageContext.request.contextPath}';
		const ServerPort = '${pageContext.request.serverPort}';
		const ServerHost = '<%=request.getLocalAddr()%>';
		
		const table_el = document.querySelector('.table tbody');
		
		

		showfile_btn_el.addEventListener('click', function() {
		axios.get("http://" + ServerHost + ':' + ServerPort + projectPath + "/etc/filelist.do")
		.then(response => {
			console.log(response.data);

			const list = response.data;
			console.log('list', list);  

			while (table_el.hasChildNodes()) {
				table_el.removeChild(table_el.firstChild);
			}

			list.forEach((dto) => {

				const tr = document.createElement('tr');
				const td1 = document.createElement('td');
				const td2 = document.createElement('td');
				const td3 = document.createElement('td');
				const td4 = document.createElement('td');
				const td5 = document.createElement('td');
				const a5 = document.createElement('a');
				const td6 = document.createElement('td');
				const a6 = document.createElement('a');

				td1.innerHTML = dto.id;
				td2.innerHTML = dto.user;
				td3.innerHTML = dto.dir;
				td4.innerHTML = dto.filename;
				a5.innerHTML='<span class="material-symbols-outlined">download</span>';
				a5.setAttribute('href',projectPath +"/etc/filedownload.do?filename="+dto.filename+"+&uuid="+dto.dir);
				td5.appendChild(a5);
				
				
				a6.innerHTML='<span class="material-symbols-outlined">cancel</span>';
				a6.setAttribute('href',projectPath +"/etc/fileremove.do?filename="+dto.filename+"+&uuid="+dto.dir+"&id="+dto.id);
				td6.appendChild(a6);
				
				
					
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
				tr.appendChild(td4);
				tr.appendChild(td5);
				tr.appendChild(td6);
				table_el.appendChild(tr);


				/* 현재페이지 / 전체페이지 넣기 */
				
			})
			
		})
		.catch(error => { console.log("fail..", error); })	//실패시 처리로직
})


	</script>
</body>
</html>