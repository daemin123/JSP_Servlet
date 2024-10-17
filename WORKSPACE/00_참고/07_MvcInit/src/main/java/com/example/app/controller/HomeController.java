package com.example.app.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements SubController{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	
			try {
				System.out.println("HomeController execute : ");
				req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}

}
