package com.example.app.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.Common.ConnectionPool;
import com.example.app.domain.common.dto.LendDto;
import com.example.app.domain.common.dto.SessionDto;
import com.example.app.domain.common.service.LendServiceImpl;
import com.example.app.domain.common.service.UserService;
import com.example.app.domain.common.service.UserServiceImpl;

public class UserMyinfoController implements SubController {

	private UserService userService;
	private LendServiceImpl lendServiceImpl;
	private ConnectionPool connectionPool;
	
	public UserMyinfoController(){
		try 
		{
			userService = UserServiceImpl.getInstance();
			lendServiceImpl = LendServiceImpl.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	//유저 0 : 도서대여신청(Default) 	1 : 대여예약 				2 : 회원정보 수정
	//사서 0 : 도서대여신청내역 			1 : 미승인 대여 신청내역 	2 : 승인된 대여 신청 내역 	3 : 회원정보 수정
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("UserMyinfoController execute : ");
		// 권한에 맞게 myinfo page 다르게 한다
		
		//1
		String serviceNo =  req.getParameter("serviceNo");
		
		
		HttpSession session =  req.getSession(false);
		System.out.println("UserMyinfoController execute session : " + session);
		SessionDto sessionDto = (SessionDto)session.getAttribute("sessionDto");
		System.out.println("UserMyinfoController execute sessionDto : " + sessionDto);
		
		if(sessionDto==null) {
			System.out.println("UserMyinfoController error .. 로그인이 필요합니다.");
			session.setAttribute("msg", "로그인이 필요합니다.");
		
			try {
				req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
				return ;
			} catch (Exception e) {	
				e.printStackTrace();
			} 
		}	
		String role =  sessionDto.getRole();
		System.out.println("UserMyinfoController execute role : " + role);


			try {
				
				if(role.equals("ROLE_ADMIN")) {
					

					
					req.getRequestDispatcher("/WEB-INF/view/user/admin/myinfo.jsp").forward(req, resp);
					return ;
				}else if(role.equals("ROLE_MEMBER")) {
					if(serviceNo==null || serviceNo.isEmpty() || serviceNo.equals("0"))	//초기값 
					{
						List<LendDto> list = lendServiceImpl.showReqLendAllBooks();
						req.setAttribute("list", list);
						System.out.println("list : " + list);
					}
					else if(serviceNo.equals("1"))	//미승인 
					{
						List<LendDto> list = lendServiceImpl.showReqLendAllBooks(false);
						req.setAttribute("list", list);
						System.out.println("list : " + list);	
					}
					else if(serviceNo.equals("2"))	//승인 
					{
						List<LendDto> list = lendServiceImpl.showReqLendAllBooks(true);
						req.setAttribute("list", list);
						System.out.println("list : " + list);				
					}
					
					req.getRequestDispatcher("/WEB-INF/view/user/member/myinfo.jsp").forward(req, resp);
					return ;
				}else if(role.equals("ROLE_USER")){
					
					if(serviceNo==null || serviceNo.isEmpty())	//초기값 
					{
						List<LendDto> list = lendServiceImpl.showReqBorrowBooks(sessionDto.getUsername());
						req.setAttribute("list", list);
						System.out.println("list : " + list);
					}
					req.getRequestDispatcher("/WEB-INF/view/user/myinfo.jsp").forward(req, resp);
					return ;
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}

		
		return ;
		
		
		
		
		//2
		
		
		//3
		
		//4
		

	}

}
