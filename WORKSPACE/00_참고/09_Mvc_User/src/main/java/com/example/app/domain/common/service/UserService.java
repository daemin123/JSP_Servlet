package com.example.app.domain.common.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.domain.common.dto.SessionDto;
import com.example.app.domain.common.dto.UserDto;



public interface UserService {

	//회원가입
	boolean UserJoin(UserDto dto) throws Exception;

	//로그인
	public Map<String,Object> login(String username,String password,HttpSession session,HttpServletResponse response) throws Exception;

	//로그아웃
	Map<String, Object> logout(SessionDto sessionDto,HttpServletResponse response) throws Exception;

	//유저정보 가져오기
	UserDto getUser(String username) throws Exception;

	//현재 접속중인 세션Id list 리턴
	List<Integer> getSessionIdList();



}