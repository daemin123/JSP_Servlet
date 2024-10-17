package com.example.app.domain.common.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.app.domain.common.dao.BookDao;
import com.example.app.domain.common.dao.BookDaoImpl;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.dto.PageDto;

public class BookServiceImpl implements BookService {
	
	private BookDao dao;
	private ConnectionPool connectionPool; //05-01Day
	
	private static BookService instance ;
	public static BookService getInstance() throws Exception {
		if(instance==null)
			instance=new BookServiceImpl();
		return instance;
	}
	
	private BookServiceImpl() throws Exception{
		
		dao = BookDaoImpl.getInstance();
		this.connectionPool = ConnectionPool.getInstance();
	}
	
	@Override
	public boolean bookRegister(BookDto dto) throws Exception {
		
		//TX
		connectionPool.txStart();			//05-01 Day TX
		boolean result = dao.Insert(dto);	//05-01 Day TX
		connectionPool.txCommit();			//05-01 Day TX
		
		return result;
	};
	@Override
	public List<BookDto> getAllBooks() throws Exception {
		
		//TX
		connectionPool.txStart();				//05-01 Day TX
		List<BookDto> list = dao.SelectAll();	//05-01 Day TX
		connectionPool.txCommit();				//05-01 Day TX
		return list;
	}
	
	
	@Override
	public BookDto getBook(int bookCode) throws Exception{
		BookDto dto =  dao.Select(bookCode);
		return dto;
	}
	
	
	//
//	@Override
//	public Map<String,Object>  getAllBooks(Criteria criteria) throws Exception {
//		
//		connectionPool.txStart();				//05-01 Day TX
//
//		
//		Map<String,Object> returnValue = new HashMap();
//		
//		int count =  dao.count();
//		System.out.println("getAllBooks's count : " + count);
//		//pageDto생성
//		PageDto pageDto = new PageDto(count,criteria);
//		
//        //시작 게시물 번호 구하기(수정) - OFFSET
//        int offset =(criteria.getPageno()-1) * criteria.getAmount();    //1page = 0, 2page = 10
//        System.out.println("getAllBooks's offset : " + offset);
//		List<BookDto> list = dao.Select(pageDto,offset);
//		
//		
//		returnValue.put("list",list);
//		returnValue.put("pageDto", pageDto);
//		System.out.println("getAllBooks's list : " + list);
//		
//		connectionPool.txCommit();				//05-01 Day TX
//		
//		return returnValue;
//	}


	//07_Mvc_Book 키워드 조회로 변경
	@Override
	public Map<String,Object>  getAllBooks(Criteria criteria) throws Exception {
		
		connectionPool.txStart(); //TX	START			 
 
		
		Map<String,Object> returnValue = new HashMap();
		
		String type =  criteria.getType();
		String keyword =  criteria.getKeyword();
		
		
		if(type==null || type.isEmpty() ||  keyword == null || keyword.isEmpty())
		{
			int count =  dao.count();
			
			System.out.println("getAllBooks's count : " + count);
			//pageDto생성
			PageDto pageDto = new PageDto(count,criteria);
			
	        //시작 게시물 번호 구하기(수정) - OFFSET
	        int offset =(criteria.getPageno()-1) * criteria.getAmount();     
	        System.out.println("getAllBooks's offset : " + offset);
			List<BookDto> list = dao.Select(pageDto,offset);
			
			
			returnValue.put("list",list);
			returnValue.put("pageDto", pageDto);
			System.out.println("getAllBooks's list : " + list);
		}
		else 		//키워드 조회 
		{
			int count =  dao.count(criteria);
			System.out.println("getAllBooks's count : " + count);
			//pageDto생성
			PageDto pageDto = new PageDto(count,criteria);
			
	        //시작 게시물 번호 구하기(수정) - OFFSET
	        int offset =(criteria.getPageno()-1) * criteria.getAmount();     
	        System.out.println("getAllBooks's offset : " + offset);
			List<BookDto> list = dao.Select(pageDto,offset,type,keyword);
			
			returnValue.put("list",list);
			returnValue.put("pageDto", pageDto);
			System.out.println("getAllBooks's list : " + list);
			
		}

		connectionPool.txCommit(); //TX	END					 
		
		return returnValue;
	}

	@Override
	public boolean updateBook(BookDto dto) throws SQLException {
		// TODO Auto-generated method stub
		connectionPool.txStart(); //TX	START	
		
		boolean isUpdated =  dao.Update(dto);
	
		connectionPool.txCommit();
		
		return isUpdated;
	}

	@Override
	public boolean removeBook(String bookCode) throws Exception {
		
		connectionPool.txStart(); //TX	START	
		
		boolean isDeleted =  dao.Delete(bookCode);
	
		connectionPool.txCommit();
		
		
		return isDeleted;
	}
	
	


}
