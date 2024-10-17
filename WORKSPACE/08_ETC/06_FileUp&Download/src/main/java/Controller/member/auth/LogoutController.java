package Controller.member.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.SubController;
import Domain.Common.Service.MemberService;
import Domain.Common.Service.MemberServiceImpl;

public class LogoutController  implements SubController{

	private MemberService service= MemberServiceImpl.getInstance();

	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("LogoutController execute");
		try {
			HttpSession session = req.getSession();
			session.invalidate();
			resp.sendRedirect(req.getContextPath()+"/main.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
