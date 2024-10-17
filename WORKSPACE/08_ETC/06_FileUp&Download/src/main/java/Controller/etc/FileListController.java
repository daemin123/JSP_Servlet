package Controller.etc;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Controller.SubController;
import Domain.Common.Dto.PageDto;
import Domain.etc.Dto.FileDto;
import Domain.etc.Service.FileService;
import Domain.etc.Service.FileServiceImpl;

public class FileListController implements SubController{
	
	private FileService service = FileServiceImpl.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			
			//01
			
			//02
			if(isValid(req.getParameterMap())) {
				
			}
			//03
			HttpSession session =  req.getSession();
			String id = (String)session.getAttribute("ID");
			
			List<FileDto> list =  service.getFiles(id);
			//04
			
			// JAVA -> JSON 변환(List<BookDto)
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonConverted = objectMapper.writeValueAsString(list);
			
	        // 4 View로 전달			
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			//List<BookDto>
			out.print(jsonConverted);
		
	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
//	유효성 체크 함수
	private boolean isValid(Map<String, String[]> parameterMap) {
		 
		return true;
	}

}
