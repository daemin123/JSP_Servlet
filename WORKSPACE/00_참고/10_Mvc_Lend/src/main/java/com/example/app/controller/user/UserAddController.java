package com.example.app.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.UserDto;
import com.example.app.domain.common.service.UserService;
import com.example.app.domain.common.service.UserServiceImpl;

public class UserAddController implements SubController{
	
	private UserService userService;
	private ConnectionPool connectionPool;
	
	public UserAddController(){
		try 
		{
			userService = UserServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserAddController execute : ");
		
		//1
		String method = req.getMethod();
		if(method.contains("GET")) {
			try {
				req.getRequestDispatcher("/WEB-INF/view/user/add.jsp").forward(req, resp);
				return ;
			
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		
		//2
		if(!isValid(username,password)) {
			return ;
		}
		
		//3
		boolean isJoined = false;
		UserDto userDto = new UserDto();
		userDto.setUsername(username);
		userDto.setPassword(password);
		userDto.setIslocked(false);
		userDto.setRole("ROLE_USER");
		System.out.println(userDto);
		try {
			isJoined = userService.UserJoin(userDto);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//4
		if(isJoined) {
			try {
				resp.sendRedirect(req.getContextPath() +"/");
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
			try {
				req.getRequestDispatcher("/WEB-INF/view/user/add.jsp").forward(req, resp);	
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
