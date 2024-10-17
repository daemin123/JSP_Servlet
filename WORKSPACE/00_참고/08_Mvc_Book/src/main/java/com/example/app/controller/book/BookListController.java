package com.example.app.controller.book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.dto.PageDto;
import com.example.app.domain.common.service.BookService;
import com.example.app.domain.common.service.BookServiceImpl;

public class BookListController implements SubController {
	
	private BookService bookService;
	private ConnectionPool connectionPool;
	
	public BookListController(){
		try 
		{
			bookService = BookServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("BookListController execute : ");
		System.out.println("");

		String pageNo  = req.getParameter("pageNo");
		System.out.println("pageNo : " + pageNo);
		String type = req.getParameter("type");
		String keyword = req.getParameter("keyword");
		System.out.println("BookListController execute  type : " + type);
		System.out.println("BookListController execute  keyword : " + keyword);

		Criteria  criteria=null;
		if(pageNo==null) {
			if(type!=null && keyword!=null)
				criteria = new Criteria(type,keyword); //키워드 전체 조회
			else
				criteria = new Criteria(); //키워드x 기본 조회 1페이지 10개
		}
		else {
			if(type!=null && keyword!=null)
				criteria = new Criteria(Integer.parseInt(pageNo),10,type,keyword); //키워드 전체 조회
			else
				criteria = new Criteria(Integer.parseInt(pageNo),10);	 //키워드x 기본 조회 n번째
			
			
		}
        			
		//유효성
		//서비스
		System.out.println("BookController execute criteria : " + criteria);
		Map<String,Object> returnValue = null;
		
		try {
			returnValue = bookService.getAllBooks(criteria);			
			
		} catch (Exception e) {
			e.printStackTrace();
			//05-01 TX
			try {connectionPool.txRollBack();} catch (SQLException e1) {e1.printStackTrace();}
		}
		
		
		
		List<BookDto> list =  (List<BookDto>) returnValue.get("list");
		PageDto pageDto = (PageDto)returnValue.get("pageDto");
			
		//뷰
		req.setAttribute("list", list);
		req.setAttribute("pageDto", pageDto);

		System.out.println("BookController execute list : " + list);
		System.out.println("BookController execute pageDto : " + pageDto);


		System.out.println("");
		try {
			
			req.getRequestDispatcher("/WEB-INF/view/book/list.jsp").forward(req, resp);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
	}
}
