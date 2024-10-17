package com.example.app.controller.book;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.service.BookService;
import com.example.app.domain.common.service.BookServiceImpl;

public class BookAddController implements SubController {

	private BookService bookService;
	private ConnectionPool connectionPool;
	
	public BookAddController(){
		try 
		{
			bookService = BookServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("BookAddController execute : ");
		//1
		String bookCode = req.getParameter("bookCode");
		String bookName = req.getParameter("bookName");
		String publisher = req.getParameter("publisher");
		String isbn = req.getParameter("isbn");
		String method =  req.getMethod();
		System.out.println("BookAddController execute method : " + method);
		

		
		//GET요청
		if(method.contains("GET")) {
			try {
				req.getRequestDispatcher("/WEB-INF/view/book/add.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		
		//2
		if( !isValid(bookCode,bookName,publisher,isbn) ) {
			return;
		}
			
		
		
		//3 서비스 실행
		Integer bookCode_re = Integer.parseInt(bookCode);
		BookDto dto =new BookDto(bookCode_re,bookName,publisher,isbn);
		System.out.println("BookAddController execute dto : " + dto);
		
		boolean isRegistred=false;
		try {		
			
			isRegistred =  bookService.bookRegister(dto);		
		
		} catch (Exception e) {
			
			e.printStackTrace();
			//05-01 TX
			try {connectionPool.txRollBack();} catch (SQLException e1) {e1.printStackTrace();}
		}
		
		
		//4 뷰로 전달 or 이동
		try {
			
			resp.sendRedirect(req.getContextPath()+"/book/list");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	private boolean isValid(String bookCode, String bookName, String publisher, String isbn) {
			
		if(bookCode==null)
			return false;	//null 인지 or 숫자인지 or 앞뒤공백여부.
		else if(bookName==null)
			return false;
		else if(publisher==null)
			return false;
		else if(isbn==null)
			return false;
		
		return true;
	}

	

}
