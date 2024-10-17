package Controller.book;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Controller.SubController;
import Domain.Common.Dto.BookDto;
import Domain.Common.Dto.Criteria;
import Domain.Common.Dto.PageDto;
import Domain.Common.Service.BookService;
import Domain.Common.Service.BookServiceImpl;

public class BookSearchController implements SubController {

	private BookService service = BookServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("BookSearchController execute");
		
		// 1 파라미터 추출(keyfield,keyword,criteria)
		Map<String,String[]> params =  req.getParameterMap();
		
		
		// 2 입력값 검증(생략)
		if(!isValid(params)) {
			
		}
		
		// 3 서비스 실행(서비스모듈작업 이후 처리)
		
		List<BookDto> list = null;
		try {
		
			//가져오는 게시물 단위 / Type/Keyword 저장
			Criteria criteria = null;
			
			if(params.get("pageno")==null) //최초 /board/list.do 접근
			{
				criteria = new Criteria(); //pageno=1 , amount=10
			}
			else
			{
				int pageno = Integer.parseInt(params.get("pageno")[0]);
				criteria = new Criteria(pageno,10);
			}
			list = service.getAllBook(req,criteria);
			
			
			// JAVA -> JSON 변환(List<BookDto)
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonConverted = objectMapper.writeValueAsString(list);
			
	        // JAVA -> JSON 변환(pageDto)
	        HttpSession session = req.getSession();
	        PageDto pageDto = (PageDto)session.getAttribute("pagedto");
			ObjectMapper objectMapper2 = new ObjectMapper();
	        String jsonConverted2 = objectMapper2.writeValueAsString(pageDto);
			
	        // 4 View로 전달			
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			//List<BookDto>
			out.print(jsonConverted);
			//pageDto
			out.print("|"+jsonConverted2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//	입력값 검증 함수
	private boolean isValid(Map<String, String[]> params) {
		
		return true;
	}

}
