package com.example.app.controller.lend;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.SessionDto;
import com.example.app.domain.common.service.LendServiceImpl;

public class LendAccecptController implements SubController {

	private LendServiceImpl lendServiceImpl;
	private ConnectionPool connectionPool;
	
	public LendAccecptController(){
		try 
		{
			lendServiceImpl = LendServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("LendAccecptController execute : ");
			
		//01
	
		String lendId = req.getParameter("lendId");
		SessionDto sessionDto = (SessionDto) req.getSession().getAttribute("sessionDto");
		
		//02
		if(!isValid(lendId)) {
			return ;
		}
		
		//03
		boolean isAccepted = false;
		try {
			
			isAccepted = lendServiceImpl.accecptLendBook(Integer.parseInt(lendId),sessionDto.getUsername());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		//04
		HttpSession session =  req.getSession();
		session.setAttribute("msg", "대여성공");
		
        try {
			resp.sendRedirect(req.getContextPath() + "/user/myinfo?serviceNo=0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

		
		
	}

	
	private boolean isValid(String lendId) {
		// TODO Auto-generated method stub
		return true;
	}

}
