package com.example.app.domain.common.dao;

import com.example.app.domain.common.dto.UserDto;

public interface UserDao {

	//INSERT
	boolean Insert(UserDto dto) throws Exception;

	UserDto Select(String username) throws Exception;

}