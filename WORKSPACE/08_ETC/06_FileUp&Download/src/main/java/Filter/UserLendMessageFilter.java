package Filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Domain.Common.Dao.LendMessageDao;
import Domain.Common.Dao.LendMessageDaoImpl;
import Domain.Common.Dto.LendMessageDto;

public class UserLendMessageFilter implements Filter{
	private LendMessageDao msgDao = LendMessageDaoImpl.getInstance();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("UserLendMessageFilter's doFilter call!");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		String role = (String)session.getAttribute("ROLE");
		String userid=(String)session.getAttribute("ID");
		if(role.equals("ROLE_USER")) {
			try {
				
				
				List<LendMessageDto> list =  msgDao.select(userid);
				if(list.size()!=0) {
					session.setAttribute("alarm_cnt", list.size());
					session.setAttribute("alarm_list", list);
				}
							
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		chain.doFilter(request, response);
		
	}

}
