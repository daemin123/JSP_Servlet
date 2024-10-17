package com.example.app.domain.common.dao;

import java.util.List;

import com.example.app.domain.common.dto.SessionDto;

public interface SessionDao {

	//SESSION용
	boolean Insert(SessionDto sessionDto) throws Exception;

	SessionDto Select(int sessiondId) throws Exception;

	SessionDto Select(String username) throws Exception;

	boolean Delete(String sessionId) throws Exception;

	//SELECTALL
	List<SessionDto> SelectAll() throws Exception;

}