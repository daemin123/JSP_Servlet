package com.example.app.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.app.domain.common.dao.SessionDao;
import com.example.app.domain.common.dao.SessionDaoImpl;
import com.example.app.domain.common.dto.SessionDto;

public class RememberMeFilter implements Filter{

	private SessionDao sessionDao;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			
			sessionDao = SessionDaoImpl.getInstance();
			bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		// 들어오기전 제일먼저 처리
//		- 만약 HttpSession안에 sessionDto가 있으면 통과
//		- 만약 HttpSession안에 sessionDto가 없으면 sessionid쿠키를 사용하여 session테이블에 동일 내용이있는지 확인
//			- 동일 내용있으면 HttpSession 에 sessionDto 생성 해서 넣기
//			- 동일 내용없으면 그냥 넘기기(익명로그인)
		System.out.println("RememberMeFilter start------");
		
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		Cookie[] cookies =  req.getCookies();
		Cookie sessionIdCookie = null;
		System.out.println("RememberMeFilter start------cookies : " + cookies);

		if(cookies!=null)
		{
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("sessionId")) {
					sessionIdCookie = cookie;
				}
					
			}
		}

		System.out.println("RememberMeFilter sessionIdCookie "  + sessionIdCookie);
		if(sessionIdCookie !=null)	//sessionId 쿠키가 있는경우 
		{
				HttpSession session =  req.getSession(false);	//
				if(session!=null) {
					
					SessionDto sessionDto = (SessionDto)session.getAttribute("sessionDto");
					System.out.println("RememberMeFilter sessionDto : " + sessionDto);
					if(sessionDto==null)	//sessionDto가 없다? 로그인x 
					{
						
						//db에서 비교
						List<SessionDto> list = null;
						try {
							
							list =  sessionDao.SelectAll();
							if(list!=null) 
							{
								
								for(SessionDto dto  : list) {
									String rawSessionId  = dto.getSessionId();
									
									if(bCryptPasswordEncoder.matches(rawSessionId, sessionIdCookie.getValue()))//매칭되면 
									{
										System.out.println("session 테이블의 sessionId 와 sessionIdCooke의 sessionId일치확인");
										session.setAttribute("sessionDto", dto);
										break;
									}
								}
								
								
							}
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					else
					{
						//sessionDto가 있다 로그인 상태....그냥 넘어감..
						;
					}
					
				}		
				
		}		

	
		
		//sessionId 쿠키가 없으면 그냥 다시 로그인 해야됨..
		
		chain.doFilter(request, response);
		
		System.out.println("RememberMeFilter end------");
	}

}
