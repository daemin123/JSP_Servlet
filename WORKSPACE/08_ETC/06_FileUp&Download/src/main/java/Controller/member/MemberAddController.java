package Controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dto.MemberDto;
import Domain.Common.Service.MemberService;
import Domain.Common.Service.MemberServiceImpl;

public class MemberAddController  implements SubController{

	private MemberService service= MemberServiceImpl.getInstance();

	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("MemberAddController execute");
		
		try {
			if(req.getMethod().equals("GET")) {
				req.setAttribute("msg", "");
				req.getRequestDispatcher("/WEB-INF/view/member/auth/join.jsp").forward(req, resp);
				return ;
			}
			
			//01 파라미터
			String id = req.getParameter("email");
			String pw = req.getParameter("pwd");
			
			
			//02 유효성
			if(!isValid(req.getParameterMap())) {
				req.setAttribute("msg", "유효성체크 오류");
				req.getRequestDispatcher("/WEB-INF/view/member/auth/join.jsp").forward(req, resp);
			}
			//03 서비스
			MemberDto dto = new MemberDto();
			dto.setId(id);
			dto.setPw(pw);
			
			boolean isjoin = service.memberJoin(dto);
			
			//04 뷰로 이동
			if(isjoin) {
				resp.sendRedirect(req.getContextPath()+ "/login.do");
			}else {
				req.setAttribute("msg", "회원가입 실패..");
				req.getRequestDispatcher("/WEB-INF/view/member/auth/join.jsp").forward(req, resp);
			}
			
			
			
		
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private boolean isValid(Map<String, String[]> parameterMap) { 
		return true;
	}

}
