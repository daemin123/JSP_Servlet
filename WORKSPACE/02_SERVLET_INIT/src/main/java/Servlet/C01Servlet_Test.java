package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet("/Servlet_Test01")
public class C01Servlet_Test extends HttpServlet{

	@Override
	public void init() throws ServletException {
		//최초 1회 실행
		System.out.println("INIT!!!aa");
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		//요청시마다 실행
		System.out.println("SERVICE!!!");
	}

	@Override
	public void destroy() {
		//서블릿 내용 수정(코드수정)
		//종료시,재시작시..!!!!!
		System.out.println("DESTROY!!!");
	}


	
}	
