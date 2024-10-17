package com.example.app.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.domain.common.dto.SessionDto;

public class PermissionFilter implements Filter{
	
	//grade 
	//0 - 익명계정
	//1 - 유저계정
	//2 - 사서계정
	//3 - 관리자계정
	
	// /book/list - grade 0 이상
	
	// /book/read - grade 1 이상

	// /book/update - grade 2 이상
	// /book/add	 - grade 2 이상
	// /book/delete - grade 2 이상

	

	
	Map<String, Integer>pageGradeMap =new HashMap();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		String path = filterConfig.getServletContext().getContextPath();
		
		pageGradeMap.put(path+"/book/list", 0);
		pageGradeMap.put(path+"/book/read", 1);	
		pageGradeMap.put(path+"/book/add", 2);
		pageGradeMap.put(path+"/book/update", 2);
		pageGradeMap.put(path+"/book/delete", 2);


	}
	
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//Request요청 전처리
		System.out.println("Permission Filter Start!");
		
		//HttpServletRequest,Response 캐스팅
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		//Uri 꺼내오기
		String uri = request.getRequestURI();
		System.out.println("Permission URI : " + uri);
		
		//Session으로부터 authdto꺼내오기
		HttpSession session = request.getSession(false);
		SessionDto adto =(SessionDto)session.getAttribute("sessionDto");
		
		
		int pageGrade=0;	
		//페이지 권한 가져오기	
		pageGrade = pageGradeMap.get(uri);
		//유저 권한 가져오기
		int userGrade=0;
		if(adto!=null)	//로그인된 상태
		{

			if(adto.getRole().equals("ROLE_USER")) // 일반유저
			{
				userGrade=1;
			}
			else if(adto.getRole().equals("ROLE_MEMBER")) // 사서
			{
				userGrade=2;
			}
			else if(adto.getRole().equals("ROLE_ADMIN"))	//관리자
			{
				userGrade=3;
			}
		}
		else {
			userGrade=0;
		}
		System.out.println("Permission Filter USERGrade : " + userGrade);
		System.out.println("Permission Filter PAGEGrade : " + pageGrade);
			
		//익명계정(0) 이 로그인이필요한(1이상)페이지로 접근요청한경우
		if(pageGrade>=1 && userGrade==0)
		{
				throw new ServletException("로그인이 필요한 페이지입니다..");
		}
		if(pageGrade>=2 && userGrade<=1)
		{
				throw new ServletException("사서 권한이 필요한 페이지입니다..");
		}
		if(pageGrade>=3 && userGrade<=2) {
				throw new ServletException("관리자 권한이 필요한 페이지입니다..");
		}
			

		chain.doFilter(req, resp);
	
		System.out.println("Permission Filter End!");
		
	}

}
