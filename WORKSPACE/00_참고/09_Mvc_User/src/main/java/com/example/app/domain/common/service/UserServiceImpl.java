package com.example.app.domain.common.service;

 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.app.domain.common.dao.SessionDao;
import com.example.app.domain.common.dao.SessionDaoImpl;
import com.example.app.domain.common.dao.UserDao;
import com.example.app.domain.common.dao.UserDaoImpl;
import com.example.app.domain.common.dao.Common.ConnectionPool_ByHikari;
import com.example.app.domain.common.dto.SessionDto;
import com.example.app.domain.common.dto.UserDto;

 
public class UserServiceImpl implements UserService  {
	
	private List<Integer> SessionIdList;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserDao userDao;
	private SessionDao sessionDao;
	private ConnectionPool_ByHikari connectionPool_ByHikari;//05-02 Hikari

	
	private static UserService instance ;
	public static UserService getInstance() throws Exception {
		if(instance==null)
			instance=new UserServiceImpl();
		return instance;
	}
	
	private UserServiceImpl() throws Exception {
		System.out.println("UserServiceImpl's UserServiceImpl()");
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		userDao = UserDaoImpl.getInstance();
		sessionDao = SessionDaoImpl.getInstance();
		
		//SessionIdList=new ArrayList();
		
		//접속중인 sessionid를 session테이블에서 list로 저장
		List<SessionDto> tmpList =sessionDao.SelectAll();
		for(SessionDto dto : tmpList) {
			//SessionIdList.add(dto.getSessionId());
		}
		
		//05-02 Hikari
		this.connectionPool_ByHikari = ConnectionPool_ByHikari.getInstance();
		
		
		
		
	}
	
	//회원가입
	@Override
	public boolean UserJoin(UserDto dto) throws Exception {
		//+ 비즈니스 유효성 체크
		//입력패스워드 + re패스워드 일치여부
		//패스워드 정책(정규표현식)
		//현재 상태가 로그인 된 상태인지..
		//..
		//패스워드 암호화
		
		
		//-------------------------------
		//TX
		//-------------------------------		
		connectionPool_ByHikari.txStart();//TX 05-02 Hikari
		//-------------------------------
		
		String encrypt= bCryptPasswordEncoder.encode(dto.getPassword());
		dto.setPassword(encrypt);
		boolean isJoined =  userDao.Insert(dto);
		
		//-------------------------------
		connectionPool_ByHikari.txCommit(); //TX 05-02 Hikari
		//-------------------------------
		
		
	 	return isJoined;
	}
	
	//로그인
	@Override
	public Map<String,Object> login(String username,String password,HttpSession session,HttpServletResponse response) throws Exception {
		
		
		
		//TX 05-02
		connectionPool_ByHikari.txStart();
		
		Map<String,Object> result=new HashMap();
		
		//1 SessionList에 동일한 세션정보가 있는지 확인(->Session객체로 대체)
		
//		for(int id : SessionIdList) {
//			if(SessiondId==id) {
//				result.put("response", false);
//				result.put("msg", "이미 해당 계정은 로그인한 상태입니다.");
//				return result;
//			}
//		}
		
		SessionDto loginedSessionDto = (SessionDto)session.getAttribute("sessionDto");
		if(loginedSessionDto!=null) {
			result.put("response", false);
			result.put("msg", "이미 해당 계정은 로그인한 상태입니다.");
			return result;
		}
		
		
		//2 로그인 상태가 아니라면 user테이블로부터 동일한 이름의 user정보를 가져오기(getUser())
		UserDto savedUser =  getUser(username);
		if(savedUser==null) {
			result.put("response", false);
			result.put("msg", "동일 계정이 존재하지 않습니다.");
			return result;
		}
		
		//3 pw일치여부 확인
		if(!bCryptPasswordEncoder.matches(password, savedUser.getPassword())) {
			result.put("response", false);
			result.put("msg", "Password가 일치하지 않습니다.");
			return result;
		}
		
		//4 PW일치한다면 session테이블에 세션정보 저장
		SessionDto sessionDto = new SessionDto();
		String randUUID =  UUID.randomUUID().toString();
		sessionDto.setSessionId(randUUID);
		sessionDto.setUsername(savedUser.getUsername());
		sessionDto.setRole(savedUser.getRole());
		
		boolean isSessionSaved =  sessionDao.Insert(sessionDto);
		if(!isSessionSaved) {
			result.put("response", false);
			result.put("msg", "로그인 처리중 오류가 발생하였습니다.Session생성 실패..");
			return result;
		}
		
		
		
		//5 PW일치한다면 sessionList에 sessionId값 저장
		//Integer id =  sessionDao.Select(sessionDto.getUsername()).getSessionId();
		result.put("response", true);
		result.put("msg", "로그인 성공!");

		//sessionDto.setSessionId(id);
		session.setAttribute("sessionDto", sessionDto);
		
//		result.put("sessionId", id);
//		result.put("role", sessionDto.getRole());
//		SessionIdList.add(id);
		
		//sessionId 쿠키 암호화 해서 전달
		String enCryptedSessionId = bCryptPasswordEncoder.encode(randUUID);
		Cookie cookie = new Cookie("sessionId",enCryptedSessionId);
		cookie.setMaxAge(30*30); //3600초 1시간
		cookie.setPath("/");	//모든 path 에 적용
		response.addCookie(cookie);
		
		
		connectionPool_ByHikari.txCommit();
		
		return result;
		
	}
	//로그아웃
	@Override
	public Map<String,Object> logout(SessionDto sessionDto,HttpServletResponse resp) throws Exception {
		
		String SessionId = sessionDto.getSessionId();
		
		Map<String,Object> response = new HashMap();
		
		//1 sessionList에 sessionId 있는지 확인
//		boolean isExisted =  SessionIdList.contains(SessionId);	
//		//System.out.println("isExised : " + isExised);
//		if(!isExisted) {
//			response.put("response", false);
//			response.put("msg", "현재 로그인된 상태가 아닙니다.");
//			return response;
//		}
		

		//2 Session테이블에서 dto 삭제
		boolean isremoved =  sessionDao.Delete(SessionId);
		if(!isremoved) {
			response.put("response", false);
			response.put("msg","시스템 상의 문제로 세션삭제가 불가합니다.관리자에게 문의해주세요");
			return response;
		}
		
		//3 List에서 sessionId 제거
		//boolean isremoved2 =  SessionIdList.remove(new Integer(SessionId));
		
		
		//sessionId 쿠키 제거
		Cookie cookie = new Cookie("sessionId",null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		//4 로그아웃 성공
		response.put("response", true);
		response.put("msg", "로그아웃성공!");
		
		
		
		return response;
	}
	
	//유저정보 가져오기
	@Override
	public UserDto getUser(String username) throws Exception {
		return userDao.Select(username);
	}
	
	
	//현재 접속중인 세션Id list 리턴
	@Override
	public List<Integer> getSessionIdList(){
		return SessionIdList;
	}
	
	
	
	
}
