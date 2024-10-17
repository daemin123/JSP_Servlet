package com.example.app.controller.user;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.service.UserService;
import com.example.app.domain.common.service.UserServiceImpl;

public class UserLoginController implements SubController{

	private UserService userService;
	private ConnectionPool connectionPool;
	
	public UserLoginController(){
		try 
		{
			userService = UserServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserLoginController execute : ");
		
		
		String method = req.getMethod();
		if(method.contains("GET")) {
			try {
				req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
				return ;
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//1
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//2
		if(!isValid(username,password)) {
			return ;
		}
		
		//3
		Map<String,Object> returnValue = null;
		HttpSession session = req.getSession();
		try {
			returnValue = userService.login(username, password, session,resp);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//4
		boolean isLogined = (boolean) returnValue.get("response");
		String msg = (String) returnValue.get("msg");
		session.setAttribute("msg",msg);
		
		if(isLogined)	//로그인성공 
		{
			
			try {
				resp.sendRedirect(req.getContextPath());
				return ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else 
		{
	
			try {
				
				req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
				return ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}

		
	}
	
	private boolean isValid(String username, String password) {
		// TODO Auto-generated method stub
		return true;
	}

}
