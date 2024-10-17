package Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dto.MemberDto;
import Domain.Common.Service.MemberService;
import Domain.Common.Service.MemberServiceImpl;

public class templateController  implements SubController{

	private MemberService service= MemberServiceImpl.getInstance();

	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			//01
			
			//02
			
			//03
			
			//04
			
		}catch(Exception e) {
			
		}
		
	}

	private boolean isValid(Map<String, String[]> parameterMap) { 
		return true;
	}

}
