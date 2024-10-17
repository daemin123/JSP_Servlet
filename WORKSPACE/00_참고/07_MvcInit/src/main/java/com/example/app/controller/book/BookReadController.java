package com.example.app.controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;

public class BookReadController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("BookReadController execute : ");

	}

}
