package com.example.app.domain.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.dto.Criteria;

public interface BookDao {

	//INSERT
	boolean Insert(BookDto dto) throws Exception;

	//SELECTALL
	List<BookDto> SelectAll() throws Exception;

	//SELECTONE
	BookDto Select(int bookCode) throws Exception;

	int Count() throws Exception;

	List<BookDto> selectAll(int offset, int amount) throws SQLException;

	int Count(Criteria criteria) throws Exception;

	List<BookDto> selectAll(int offset, int amount, Criteria criteria) throws Exception;

}