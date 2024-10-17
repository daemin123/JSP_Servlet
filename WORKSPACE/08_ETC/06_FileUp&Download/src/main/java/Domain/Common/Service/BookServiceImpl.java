package Domain.Common.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Domain.Common.Dao.BookDao;
import Domain.Common.Dao.BookDaoImpl;
import Domain.Common.Dto.BookDto;
import Domain.Common.Dto.Criteria;
import Domain.Common.Dto.PageDto;

public class BookServiceImpl implements BookService {

	
	
	private BookDao dao;

	//싱글톤
	private static BookService instance;
	public static BookService getInstance() {
		if(instance==null)
			instance = new BookServiceImpl();
		return instance;
	}
	//
	
	//
	private MemberService memberService;
	private BookServiceImpl() {
		dao = BookDaoImpl.getInstance();
		memberService = MemberServiceImpl.getInstance();
	}

	// 도서조회하기(비회원/회원/사서)
	@Override
	public List<BookDto> getAllBook(HttpServletRequest req,Criteria criteria) throws Exception {
		System.out.println("BookService's getAllBook()");
		
		//전체게시물 건수 받기
		int totalcount = dao.getAmount();
		
		//PageDto 만들기
		PageDto pagedto = new PageDto(totalcount,criteria);
		
		//시작 게시물 번호 구하기(수정)
		int startno = criteria.getPageno()*criteria.getAmount()-criteria.getAmount();
		
		//PageDto를 Session에 추가
		HttpSession session = req.getSession(false);
		
		List<BookDto> list = (List<BookDto>)dao.select(startno,criteria.getAmount());
		
		if(list!=null) {
			session.setAttribute("pagedto", pagedto);	
		}
		return list;
	}
	
	@Override
	public BookDto getBook(int bookcode) throws Exception {
		System.out.println("BookService's getBook()");
		return dao.select(bookcode);
	}
	
	
	// 도서등록하기(사서)
	@Override
	public boolean addBook(BookDto dto, String sid) throws Exception {
		System.out.println("BookService's addBook()");
		
//		String role = memberService.getRole(sid);
		
//		if (role.equals("ROLE_MEMBER")) {
//			int result = dao.insert(dto);
//			if (result > 0)
//				return true;
//		}

		return false;
	}

	// 도서수정하기
	@Override
	public boolean updateBook(BookDto dto, String sid) throws Exception {
		
//		System.out.println("BookService's updateBook()");
//		String role = memberService.getRole(sid);
//		
//		if (role.equals("ROLE_MEMBER")) {
//			int result = dao.update(dto);
//			if (result > 0)
//				return true;
//		}

		return false;
	}

	// 도서삭제하기
	@Override
	public boolean removeBook(int bookcode, String sid) throws Exception {
		System.out.println("BookService's removeBook()");
		
//		String role = memberService.getRole(sid);
//		
//		if (role.equals("ROLE_MEMBER")) {
//			int result = dao.delete(bookcode);
//			if (result > 0)
//				return true;
//		}
		return false;
	}
}
