package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/join.do")
public class C04Servlet_Test extends HttpServlet{

//	//GET (회원가입 페이지로 이동)
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("GET /Join.do...");
//		req.getRequestDispatcher("/WEB-INF/Join.jsp").forward(req, resp);
//	}
//	
//	//POST(회원가입 처리)
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("POST /Join.do...");
//		
//		//1 파라미터
//		String username = req.getParameter("username");
//		String password = req.getParameter("password");
//			
//		//2 유효성
//		if(!isValid(username,password)) {
//			return ;
//		}
//		
//		//3 서비스
//		HttpSession session =  req.getSession();
//			
//		session.setAttribute("username", username);
//		session.setAttribute("password", password);
//		
//		//4 뷰(Forward /Redirect)
//		resp.sendRedirect("Main.jsp");
//		
//	}

	
	
	
	
	private boolean isValid(String username, String password) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Method 방식
		String method = req.getMethod();
		
		if(method.contains("GET")) {
			System.out.println("GET /join.do...");
			req.getRequestDispatcher("/WEB-INF/Join.jsp").forward(req, resp);
			return ;
		}
		
		//1
		System.out.println("POST /join.do...");
		//2
		
		//3
		
		//4
		resp.sendRedirect("Main.jsp");
		
		
	}
	

	

	
	//
}
