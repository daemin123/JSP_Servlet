package com.example.app.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.SessionDto;
import com.example.app.domain.common.service.UserService;
import com.example.app.domain.common.service.UserServiceImpl;

public class UserLogoutController implements SubController{

	private UserService userService;
	private ConnectionPool connectionPool;
	
	public UserLogoutController(){
		try 
		{
			userService = UserServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserLogoutController execute : ");
		
		Map<String,Object> result = new HashMap();
		//1
		HttpSession session = req.getSession();
		SessionDto sessionDto = (SessionDto)session.getAttribute("sessionDto");		
		
		//2
		if(!isValid(sessionDto)) {
			session.setAttribute("msg", "로그인 상태가 아닙니다.");
			try {
				req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
			} catch (Exception e) {

				e.printStackTrace();
			} 
			return ;
		}
		
		//3
		Map<String,Object> returnValue = null;
		 try {
			 returnValue = userService.logout(sessionDto,resp);
		
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//4
		 boolean response =  (boolean) returnValue.get("response");
		 String msg = (String)returnValue.get("msg");
		 session.setAttribute("msg",msg);
		 if(response) //로그아웃성공
		 {
			 session.removeAttribute("sessionDto");	//sessionDto 삭제
			 try {
				resp.sendRedirect(req.getContextPath()+"/");		
			 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }		 
		 }
		 else
		 {
			 try {
				req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 }
		
	}

	private boolean isValid(SessionDto sessionDto) {
		// TODO Auto-generated method stub
		return true;
	}

}
