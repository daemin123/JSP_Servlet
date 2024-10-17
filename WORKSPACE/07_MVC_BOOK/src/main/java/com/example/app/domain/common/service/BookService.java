package com.example.app.domain.common.service;

import java.util.List;
import java.util.Map;

import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.dto.Criteria;

public interface BookService {

	boolean bookRegister(BookDto dto) throws Exception;

	List<BookDto> getAllBooks() throws Exception;

	BookDto getBook(int bookCode) throws Exception;

	public Map<String,Object> getAllBooks(Criteria criteria)  throws Exception;
	
}