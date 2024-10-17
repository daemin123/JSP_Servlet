package com.example.app.controller.book;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.service.BookService;
import com.example.app.domain.common.service.BookServiceImpl;

public class BookUpdateController implements SubController {

	private BookService bookService;
	private ConnectionPool connectionPool;
	
	public BookUpdateController(){
		try 
		{
			bookService = BookServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("BookUpdateController execute : ");
		//1
		String bookCode = req.getParameter("bookCode");
		String bookName = req.getParameter("bookName");
		String publisher = req.getParameter("publisher");
		String isbn = req.getParameter("isbn");
		String method = req.getMethod();
		if(method.contains("GET")) {
			try {
				Integer bc = Integer.parseInt(bookCode);
				BookDto dto = new BookDto(bc,bookName,publisher,isbn);
				req.setAttribute("bookDto", dto);
				req.getRequestDispatcher("/WEB-INF/view/book/update.jsp").forward(req, resp);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return ;
		}

		//2
		if(!isValid(bookCode,bookName,publisher,isbn)) {
			return ;
		}
			
		//3
		Integer bc = Integer.parseInt(bookCode);
		BookDto dto = new BookDto(bc,bookName,publisher,isbn);
		
		boolean isUpdated = false;
		try {
			
			isUpdated =  bookService.updateBook(dto);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//4

		if(isUpdated) {
			try {
				
				resp.sendRedirect(req.getContextPath() + "/book/read?bookCode=" + bookCode);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				
				resp.sendRedirect(req.getContextPath() + "/book/read?bookCode=" + bookCode);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
		
		

	}


	private boolean isValid(String bookCode, String bookName, String publisher, String isbn) {
		// TODO Auto-generated method stub
		return true;
	}

}
