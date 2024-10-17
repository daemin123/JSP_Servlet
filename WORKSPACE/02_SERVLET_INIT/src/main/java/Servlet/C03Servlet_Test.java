package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login.do")
public class C03Servlet_Test extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet");
		//1 파라미터
		String username = req.getParameter("username");
		String password = req.getParameter("password");
			
		//2 유효성
		if(!isValid(username,password)) {
			return ;
		}
		
		//3 서비스
		HttpSession session =  req.getSession();
		if(!username.equals("user1")) {
			session.setAttribute("msg", "USERID를 올바르게 입력하세요");
			req.getRequestDispatcher("Request_Get.jsp").forward(req, resp);
			
			return ;
		}
		if(!password.equals("1234")) {
			session.setAttribute("msg", "PASSWORD가 잘못입력되었습니다요");
			req.getRequestDispatcher("Request_Get.jsp").forward(req, resp);			
			return ;
		}
		
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		//4 뷰(Forward /Redirect)
		resp.sendRedirect("Main.jsp");
		
		
	}
	
	private boolean isValid(String username, String password) {
		if(username==null)
			return false;
		else if(password==null)
			return false;
		return true;
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost");
		//1 파라미터
		String username = req.getParameter("username");
		String password = req.getParameter("password");
			
		//2 유효성
		if(!isValid(username,password)) {
			return ;
		}
		
		//3 서비스
		HttpSession session =  req.getSession();
		if(!username.equals("user1")) {
			session.setAttribute("msg", "USERID를 올바르게 입력하세요");
			req.getRequestDispatcher("Request_Get.jsp").forward(req, resp);
			
			return ;
		}
		if(!password.equals("1234")) {
			session.setAttribute("msg", "PASSWORD가 잘못입력되었습니다요");
			req.getRequestDispatcher("Request_Get.jsp").forward(req, resp);			
			return ;
		}
		
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		//4 뷰(Forward /Redirect)
		resp.sendRedirect("Main.jsp");
	}

	
//	@Override
//	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//		System.out.println("service");	
//	}
	
	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init");
	}

	
	
	
	
}
