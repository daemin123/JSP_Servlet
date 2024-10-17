package com.example.app.domain.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.dto.PageDto;

public interface BookDao {

	//INSERT
	boolean Insert(BookDto dto) throws Exception;

	//SELECTALL
	List<BookDto> SelectAll() throws Exception;

	//SELECTONE
	BookDto Select(int bookCode) throws Exception;

	List<BookDto> SelectAll(Integer pageNo) throws SQLException;
	
	//COUNT
	int count() throws Exception;

	List<BookDto> Select(PageDto pageDto, int offset) throws SQLException;

	
	List<BookDto> Select(PageDto pageDto, int offset, String keyfield, String keyword) throws SQLException;

	//07
	int count(Criteria criteria) throws Exception;

	boolean Update(BookDto dto) throws SQLException;

	boolean Delete(String bookCode) throws Exception;

}