package com.example.app.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;

public class UserDeleteController implements SubController{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserDeleteController execute : ");
		
	}

}
