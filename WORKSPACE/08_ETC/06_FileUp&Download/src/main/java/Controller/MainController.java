package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController implements SubController{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//1 파라미터
		
		//2 입력값 검증
		
		//3 서비스 실행
		
		//4 View 이동
		try {
			req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
		
		
		
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
