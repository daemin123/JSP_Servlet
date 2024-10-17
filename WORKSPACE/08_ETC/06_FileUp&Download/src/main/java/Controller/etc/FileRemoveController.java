package Controller.etc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.etc.Service.FileService;
import Domain.etc.Service.FileServiceImpl;

public class FileRemoveController implements SubController {
	
	FileService service = FileServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		try {
			
			boolean isremoved = service.removefile(req,resp);
			
			if(isremoved) {
				req.setAttribute("msg", req.getParameter("id") + "삭제 성공!");
				req.getRequestDispatcher("/etc/fileupload.do").forward(req, resp);
			}else {
				req.setAttribute("msg", req.getParameter("id") + "삭제 실패...");
				req.getRequestDispatcher("/etc/fileupload.do").forward(req, resp);
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
