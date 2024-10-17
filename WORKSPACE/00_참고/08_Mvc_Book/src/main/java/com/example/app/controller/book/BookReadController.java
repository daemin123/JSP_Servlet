package com.example.app.controller.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.service.BookService;
import com.example.app.domain.common.service.BookServiceImpl;

public class BookReadController implements SubController {
	
	private BookService bookService;
	private ConnectionPool connectionPool;
	
	public BookReadController(){
		try 
		{
			bookService = BookServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("BookReadController execute : ");
		
		//1
		String bookCode = req.getParameter("bookCode");
		//2
		if(!isValid(bookCode))
			return ;
		//3
		Integer bc = Integer.parseInt(bookCode);
		BookDto bookDto = null;
		try {
			bookDto = bookService.getBook(bc);
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
		//4
		req.setAttribute("bookDto", bookDto);
		try {
			req.getRequestDispatcher("/WEB-INF/view/book/read.jsp").forward(req, resp);
			return ;
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}


	private boolean isValid(String bookCode) {
		// TODO Auto-generated method stub
		return true;
	}

}
