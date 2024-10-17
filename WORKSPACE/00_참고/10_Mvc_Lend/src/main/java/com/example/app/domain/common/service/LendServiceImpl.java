package com.example.app.domain.common.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.example.app.domain.common.dao.LendDaoImpl;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.LendDto;

public class LendServiceImpl {
	
	private LendDaoImpl dao;
	private ConnectionPool connectionPool; //05-01Day
	
	private static LendServiceImpl instance ;
	public static LendServiceImpl getInstance() throws Exception {
		if(instance==null)
			instance=new LendServiceImpl();
		return instance;
	}
	private LendServiceImpl() throws Exception{	
		dao = LendDaoImpl.getInstance();
		this.connectionPool = ConnectionPool.getInstance();
	}
	
	//유저가 도서 대여 요청
	public boolean requestLendBook(LendDto dto) throws SQLException {
		
		//TX
		connectionPool.txStart();			//05-01 Day TX
		
		//동일 bookCode 가 있는지 확인
		LendDto ExistedLendDto =  dao.Select(0, dto.getBookCode());
		System.out.println("LendServiceImpl's requestLendBook ..ExistedLendDto : " + ExistedLendDto );
		boolean isLended = false;
		
		if(ExistedLendDto==null)
			isLended = dao.Insert(dto);
			
		connectionPool.txCommit();

		return isLended;
	}
	
	public List<LendDto> showReqBorrowBooks(String username) throws SQLException{
		
		connectionPool.txStart();			//05-01 Day TX

		List<LendDto> list = dao.Select(username);
		
		connectionPool.txCommit();

		return list;

	}
	public List<LendDto> showReqLendAllBooks() throws SQLException {
		connectionPool.txStart();			//05-01 Day TX

		List<LendDto> list = dao.SelectAll();
		
		connectionPool.txCommit();
		return list;
	}
	public List<LendDto> showReqLendAllBooks(boolean isAccepted) throws SQLException {
		connectionPool.txStart();			//05-01 Day TX

		List<LendDto> list = dao.SelectAll(isAccepted);
		
		connectionPool.txCommit();
		return list;
	
	}
	public boolean accecptLendBook(Integer lendId,String membername) throws SQLException {
		connectionPool.txStart();			//05-01 Day TX

		// 사서 계정 
		// 승인 날짜
		// 반납 일정(14일후 날짜)
		LendDto dto = new LendDto();
		dto.setMembername(membername);
		dto.setLendId(lendId);
		dto.setAcceptedLend(true);
		dto.setAcceptedDate(LocalDateTime.now());
		dto.setDueDate(LocalDateTime.now().plus(14,ChronoUnit.DAYS));
	
		boolean  isAcceptedLend = dao.Update(dto);
		
		
		connectionPool.txCommit();
		return isAcceptedLend;
	}
	
}



