package com.example.app.controller.book;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.service.BookService;
import com.example.app.domain.common.service.BookServiceImpl;

public class BookDeleteController implements SubController {

	
	private BookService bookService;
	private ConnectionPool connectionPool;
	
	public BookDeleteController(){
		try 
		{
			bookService = BookServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("BookDeleteController execute : ");
		
		//1
		String bookCode = req.getParameter("bookCode");
		
		//2
		if(!isValid(bookCode)) {
			return ;
		}
		
		//3
		
		boolean isRemoved=false;
		try {
			isRemoved = bookService.removeBook(bookCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//4
		if(isRemoved) {
			try {
				resp.sendRedirect(req.getContextPath() +"/book/list");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				resp.sendRedirect(req.getContextPath() +"/book/read?bookCode="+bookCode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	
	
	private boolean isValid(String bookCode) {
		// TODO Auto-generated method stub
		return true;
	}

}
