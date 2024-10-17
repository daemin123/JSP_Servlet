package com.example.app.controller.lend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.LendDto;
import com.example.app.domain.common.dto.SessionDto;
import com.example.app.domain.common.service.LendServiceImpl;

public class LendAddController implements SubController {

	private LendServiceImpl lendServiceImpl;
	private ConnectionPool connectionPool;
	
	public LendAddController(){
		try 
		{
			lendServiceImpl = LendServiceImpl.getInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("LendAddController execute : ");
			
		//01
		String method = req.getMethod();
		String bookCode = req.getParameter("bookCode");
		SessionDto sessionDto = (SessionDto) req.getSession().getAttribute("sessionDto");
		
		//02
		if(!isValid(bookCode,sessionDto)) {
			return ;
		}
		
		//03
		boolean isLended = false;
		try {
			LendDto dto = new LendDto();
			dto.setBookCode(Integer.parseInt(bookCode));
			dto.setUsername(sessionDto.getUsername());
			isLended = lendServiceImpl.requestLendBook(dto);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		//04
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
			PrintWriter out = resp.getWriter();
			if(isLended) {
				//기존
				//out.println("{'response' : 'SUCCESS..'}");
				JSONObject obj = new JSONObject();
				obj.put("response", "success..");
				out.println(obj);
				out.flush();
			}
			else {
				//기존
				//out.println("{'response' : 'FAILED..'}");
				JSONObject obj = new JSONObject();
				obj.put("response", "faild..");
				out.println(obj);
				out.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
	}

	
	private boolean isValid(String bookCode, SessionDto sessionDto) {
		// TODO Auto-generated method stub
		return true;
	}

}
