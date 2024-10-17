package com.example.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class UTF_8_EncodingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("UTF_8_EncodingFilter Start-----");
		
		// 요청과 응답의 인코딩을 UTF-8로 설정
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
		
		chain.doFilter(req, resp);
		
		System.out.println("C03UTF_8_EncodingFilter End-----");
		
	}

}
