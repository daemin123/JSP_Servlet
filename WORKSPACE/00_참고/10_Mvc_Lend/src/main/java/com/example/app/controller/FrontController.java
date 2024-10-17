package com.example.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.book.BookAddController;
import com.example.app.controller.book.BookDeleteController;
import com.example.app.controller.book.BookListController;
import com.example.app.controller.book.BookReadController;
import com.example.app.controller.book.BookUpdateController;
import com.example.app.controller.lend.LendAccecptController;
import com.example.app.controller.lend.LendAddController;
import com.example.app.controller.lend.LendDeleteController;
import com.example.app.controller.lend.LendReadController;
import com.example.app.controller.lend.LendUpdateController;
import com.example.app.controller.user.UserAddController;
import com.example.app.controller.user.UserDeleteController;
import com.example.app.controller.user.UserLoginController;
import com.example.app.controller.user.UserLogoutController;
import com.example.app.controller.user.UserMyinfoController;
import com.example.app.controller.user.UserReadController;
import com.example.app.controller.user.UserUpdateController;


public class FrontController extends HttpServlet{
	public static String path;
	private Map<String,SubController> api;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.path =config.getServletContext().getContextPath(); // /02Mvc2 경로 저장
		
		System.out.println("[FrontController] init's path : " + path);
		
		api = new HashMap();
	
		api.put(path+"/", new HomeController());
		
		// /user
		api.put(path+"/user/add", new UserAddController());
		api.put(path+"/user/read", new UserReadController());
		api.put(path+"/user/update", new UserUpdateController());
		api.put(path+"/user/delete", new UserDeleteController());
		api.put(path+"/user/myinfo", new UserMyinfoController());
		

		api.put(path+"/login", new UserLoginController());
		api.put(path+"/logout", new UserLogoutController());

		
		// /book
		api.put(path+"/book/list", new BookListController());
		api.put(path+"/book/add", new BookAddController());
		api.put(path+"/book/read", new BookReadController());
	
		api.put(path+"/book/update", new BookUpdateController());
		api.put(path+"/book/delete", new BookDeleteController());
		
		// /lend
		api.put(path+"/lend/add", new LendAddController());		
		api.put(path+"/lend/read", new LendReadController());
		api.put(path+"/lend/update", new LendUpdateController());
		api.put(path+"/lend/delete", new LendDeleteController());
		
		api.put(path+"/lend/accept", new LendAccecptController());
	
				
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//문자셋 설정(Filter 이동예정)
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		//SubController실행
		String uri = req.getRequestURI();
		System.out.println("[FrontController] service's uri : " + uri);
		SubController sub = api.get(uri);
		if(sub!=null)
			sub.execute(req, resp);
		else
			req.getRequestDispatcher("/WEB-INF/view/error/common.jsp").forward(req, resp);
		
		
	
		 
	}
}

