package Servlet.page;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Type.Role;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = (String)session.getAttribute("username");
		Role role = (Role)session.getAttribute("role");
	

		
		
		PrintWriter out =  resp.getWriter();
		out.println("<h1>MAIN PAGE</h1>");
		out.println("<div>USERNAME : "+username+"</div>");
		out.println("<div>ROLE :"+ role.name() +" </div>");
	}
}





