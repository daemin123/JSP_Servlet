package Domain.etc.Service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Domain.etc.Dao.FileDao;
import Domain.etc.Dao.FileDaoImpl;
import Domain.etc.Dto.FileDto;

public class FileServiceImpl implements FileService {

	
	private FileDao dao = FileDaoImpl.getInstance();
	private String upload_dir;
	
	private static FileService instance;
	public static FileService getInstance() {
		if(instance==null)
			instance = new FileServiceImpl();
		return instance;
	}
	
	
	//파일업로드 
	@Override
	public boolean fileUpload(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		System.out.println("FileServiceImpl's fileUpload Start===========");
		
		boolean isUpload = false;
		List<String>msg = new ArrayList();
		//업로드 디렉토리 경로설정			
		System.out.println("DIRPATH : " + req.getServletContext().getRealPath("/"));
		upload_dir=req.getServletContext().getRealPath("/")+"upload";
		
		//접속한 Email 계정 확인
		HttpSession session = req.getSession(false);	//없는 경우 새로 Session을 만들지 않는다
		String role = (String)session.getAttribute("ROLE");
		String id = (String)session.getAttribute("ID");
		if(role==null) {
			session.setAttribute("msg", "로그인을 하셔야 업로드 기능을 사용할 수 있습니다.");
			return false;
		}
		//UUID(랜덤값) 폴더생성
		UUID uuid = UUID.randomUUID();
		String path = upload_dir+File.separator+id+File.separator+uuid;

		//추출된 파일정보 저장용 Buffer
		StringBuffer filelist = new StringBuffer();
		StringBuffer filesize= new StringBuffer();
		
		//request로부터 파일정보 추출
		Collection<Part> parts=req.getParts();
		String filename = null;
		for(Part part : parts)
		{
			if(part.getName().equals("files"))
			{
				System.out.println("-------------------------------------------");
				
				System.out.println("PART명 : " + part.getName());
				System.out.println("PART크기 : " + part.getSize());				
				for(String name : part.getHeaderNames()) {
					System.out.println("Header Name : " + name);
					System.out.println("Header Value : " + part.getHeader(name));
				}
				
				//파일명 추출
				System.out.println("파일명 : " + getFileName(part));
				filename=getFileName(part);
				
				if(!filename.equals(""))
				{
					
					//폴더생성
					File dir = new File(path);
					if(!dir.exists()) {
						dir.mkdirs();
					}
					//업로드
					part.write(path+File.separator+filename);
					msg.add(filename + " 업로드 성공!");
					
					//파일명 DB Table에 추가 위한 저장
					filelist.append(filename+";");
					//디렉토리 경로 DB Table에 추가 위한 저장
					filesize.append(part.getSize()+";");
				
					
				}
				
				System.out.println("-------------------------------------------");
			}
			
			dao.insert(new FileDto(0,id,uuid.toString(),filename));
			
		}
		isUpload = true;
			session.setAttribute("msg", msg);
			System.out.println("FileServiceImpl's fileUpload END===========");
			
		return isUpload;
	}
	
		
		
	//파일다운로드
	@Override
	public boolean fileDownload(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		boolean isdownload = false;
		//파라미터
		String filename=req.getParameter("filename");
		String uuid=req.getParameter("uuid");
		System.out.println("filename : " + filename + " uuid : " + uuid);

		//계정정보확인
		HttpSession session = req.getSession(false);
		String userid = (String)session.getAttribute("ID");
		//경로설정
		String path=req.getServletContext().getRealPath("/"); 
		path+="upload"+File.separator+userid+File.separator+uuid;
		System.out.println("path :"  + path );
		//헤더설정
		resp.setHeader("Content-Type","application/octet-stream;charset=utf-8");
		resp.setHeader("Content-Disposition","attachment; filename="+URLEncoder.encode(filename,"utf-8").replaceAll("\\+", ""));
		//스트림형성 
		FileInputStream fin = new FileInputStream(path+File.separator+filename);
		System.out.println("경로확인 : " + path+File.separator+filename);
		ServletOutputStream bout = resp.getOutputStream();
		
		//다운로드 처리
		int read=0;
		byte[] buff = new byte[4096];
		while(true)
		{
			read = fin.read(buff,0,buff.length);
			if(read==-1)
				break;
			bout.write(buff,0,read);		
		}
		bout.flush();
		bout.close();
		fin.close();
		isdownload = true;
		
		return isdownload;
	}
	
	
	//Part의 header정보로 부터 filename 추출하기 함수
	private String getFileName(Part part) {
//		-------------------------------------------
//		PART명 : files
//		PART크기 : 107637
//		Header Name : content-disposition
//		Header Value : form-data; name="files"; filename="aaa.csv"
//		Header Name : content-type
//		Header Value : text/csv
//		-------------------------------------------
		
		String contentDisp = part.getHeader("content-disposition");
		
		String[] tokens = contentDisp.split(";");//[form-data,name="files",filename="aaa.csv"]
		String filename = tokens[tokens.length - 1]; //filename="aaa.csv"
		return filename.substring(11,filename.length()-1);
	}


	public List<FileDto> getFiles(String userid) throws Exception{
		
		return dao.select(userid);
	}


	@Override
	public boolean removefile(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		boolean isremoved = false;	
		boolean flag = false;
		
		//요청 파라미터 추출
		String filename=req.getParameter("filename");
		String uuid=req.getParameter("uuid");
		int id = Integer.parseInt( req.getParameter("id"));
		System.out.println("filename : " + filename + " uuid : " + uuid);
		
		//디렉토리 경로 설정
		String dirpath= req.getServletContext().getRealPath("/")+"upload";
		
		//접속한 Email 계정 확인
		HttpSession session = req.getSession(false);
		String userid =(String)session.getAttribute("ID");
		
		//UUID(랜덤값) 폴더생성
		String path = dirpath+File.separator+userid+File.separator+uuid+File.separator+filename;
		
		
		//파일삭제  
		File file = new File(path);
		boolean isFileDelete =  file.delete();
		int deleted_cnt = 0;
		if(isFileDelete) {
			//파일삭제시 Table 도 삭제 
			deleted_cnt = dao.delete(id);
		}
		
		return deleted_cnt>0;

	}
	
	
	
}
