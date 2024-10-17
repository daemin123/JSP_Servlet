<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@page import="java.io.*" %>
	<%
 		//디렉토리 경로 확인
		String dirPath = pageContext.getServletContext().getRealPath("/") + "C05\\download\\";
		System.out.println("PATH : " + dirPath);
		String filepath = dirPath+"test1.txt";
		System.out.println("PATHfilepath : " + filepath);
		try{
			
			//in
			FileInputStream in = new FileInputStream(dirPath+"test1.txt");
			
			//[오류해결] 이 응답을 위해 getOutputStream()이 이미 호출되었습니다.]을(를) 발생시켰습니다.
			out.clear();					//	response outstream을 닫고 버퍼를 비움
			out = pageContext.pushBody();	//	현재페이지에 body 연결
			
			//out
			ServletOutputStream bout = response.getOutputStream();
			
			//respone Header 변경(★)
			response.setHeader("Content-Type","application/octet-stream;charset-utf-8");
			response.setHeader("Content-Disposition","attachment; filename=TEST1.txt");
			
			
			while(true){
				
				int data = in.read();
				if(data==-1)
					break;
				bout.write((byte)data);
				bout.flush();
			}
			bout.close();
			in.close();
			
		}catch(Exception e){
			e.printStackTrace();
		} 
		
		
		
	%>

</body>
</html>