package Domain.etc.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.etc.Dto.FileDto;

public interface FileService {

	//파일업로드 
	boolean fileUpload(HttpServletRequest req, HttpServletResponse resp) throws Exception;

	//파일다운로드
	boolean fileDownload(HttpServletRequest req, HttpServletResponse resp) throws Exception;

	public List<FileDto> getFiles(String userid) throws Exception;

	boolean removefile(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}