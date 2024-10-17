package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Test2")
public class C02Servlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		req.getServletContext().setAttribute("CTX_ATTR2", "VAL2");
		
		String val = (String)req.getServletContext().getAttribute("CTX_ATTR1");
		String val2 = (String)req.getServletContext().getAttribute("CTX_ATTR2");
		
		out.println("<h1>ServletContextListner TEST2</h1>");
		out.println("CTX_ATTR1 : " + val);
		out.println("CTX_ATTR2 : " + val2);
	}

}
