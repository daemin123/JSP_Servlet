package Controller.lend;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dao.ConnectionPool;
import Domain.Common.Dto.LendDto;
import Domain.Common.Service.LendService;
import Domain.Common.Service.LendServiceImpl;

public class LendAddController  implements SubController{
	private LendService service= LendServiceImpl.getInstance();

	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("LendAddController execute");
		try {
			
			PrintWriter out = resp.getWriter();
			//01 파라미터 받기
			String bookcode = req.getParameter("bookcode");
			String id = req.getParameter("id");
			
			//02 입력값 확인(Data 유효성 검사)
			if(bookcode.isEmpty() || id.isEmpty()) {
				System.out.println("[ERROR] Data Validation Check Error");
				out.print("[ERROR] Data Validation Check Error");
				return ;
			}
			
			//03 서비스 실행
			LendDto dto = new LendDto(0, Integer.parseInt(bookcode),id,null,null);
			System.out.println("Dto : " + dto);
			
			
			Boolean isLend=false;
		
			System.out.println("LendAddController'Session role : " + req.getSession().getAttribute("ROLE") );
			isLend = service.reqLend(req);
		
	
			
			//04 View 이동(전달)
			
			if(isLend) {
				out.print("도서대여가 완료되었습니다.");
			}else {
				out.print(req.getAttribute("msg"));
			}
			
		} catch (Exception e) {
			try {
			
				ConnectionPool.conn.rollback();
				System.out.println("[Exception] Lend_Add서비스에 대한 Rollback..");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
