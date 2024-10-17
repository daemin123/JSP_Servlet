package Controller.etc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.etc.Service.FileService;
import Domain.etc.Service.FileServiceImpl;

public class FileUploadController implements SubController {
	
	FileService service = FileServiceImpl.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			
			//GET 처리
			if(req.getMethod().equals("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/etc/fileupload.jsp").forward(req, resp);
				return ;
			}
			
			//01 파라미터
			
			//02 입력값 검증(isvalid함수만들어서 처리)
			
			//03 서비스 실행
				
			boolean isupload = service.fileUpload(req, resp);
			
			//04 뷰
			resp.sendRedirect(req.getContextPath()+"/etc/fileupload.do");
			
			
		
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
