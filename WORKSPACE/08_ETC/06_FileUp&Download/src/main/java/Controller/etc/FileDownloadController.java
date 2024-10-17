package Controller.etc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.etc.Service.FileService;
import Domain.etc.Service.FileServiceImpl;

public class FileDownloadController  implements SubController{
	
	private FileService service = FileServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//01
		
		//02
		
		//03
			try {
				
				boolean isdownload =  service.fileDownload(req,resp);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//04
		
		
	}

}
