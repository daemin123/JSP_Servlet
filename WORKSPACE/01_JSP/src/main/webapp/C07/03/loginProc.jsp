<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	//파라미터받기
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String idchk = request.getParameter("idchk");
	System.out.println("idchk : " + idchk);
	//유효성체크
	if(username==null || username.isEmpty()){
		session.setAttribute("msg","ID입력은 필수 사항입니다.");
		response.sendRedirect("login.jsp");
		return ;
	}
	if(password==null || password.isEmpty()){
		session.setAttribute("msg","PW입력은 필수 사항입니다.");
		response.sendRedirect("login.jsp");
		return ;
	}
	
	if(!username.equals("USER1") && !username.equals("USER2")){
		session.setAttribute("msg","ID가 일치하지 않습니다.");
		response.sendRedirect("login.jsp");
		return ;
	}
	if(!password.equals("1234")){
		session.setAttribute("msg","PW가 일치하지 않습니다.");
		response.sendRedirect("login.jsp");
		return ;
	}
	
	
	// 세션에 개인정보 저장
	session.setAttribute("ROLE", "ROLE_USER");
	session.setAttribute("ID", username);
	session.setMaxInactiveInterval(60*60);	//초단위 기본 30분(1800초)
	
	
	//Idchk 확인
	if(idchk!=null)	//id체크 on
	{
		Cookie idchkOn = new Cookie("idchk","true");
		Cookie usernameCookie = new Cookie("username",username);
		idchkOn.setPath("./");
		usernameCookie.setPath("./");
		response.addCookie(idchkOn);
		response.addCookie(usernameCookie);
	}
	else 
	{
		Cookie idchkOn = new Cookie("idchk","false");
		Cookie usernameCookie = new Cookie("username","");
		idchkOn.setMaxAge(0);
		usernameCookie.setMaxAge(0);
		response.addCookie(idchkOn);
		response.addCookie(usernameCookie);
	}
	
	
	
	response.sendRedirect("main.jsp");
	
	
%>



