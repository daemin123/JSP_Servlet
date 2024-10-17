package Domain.Common.Service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Domain.Common.Dao.ConnectionPool;
import Domain.Common.Dao.LendDao;
import Domain.Common.Dao.LendDaoImpl;
import Domain.Common.Dao.LendMessageDao;
import Domain.Common.Dao.LendMessageDaoImpl;
import Domain.Common.Dto.BookDto;
import Domain.Common.Dto.LendDto;
import Domain.Common.Dto.LendMessageDto;
import Domain.Common.Dto.MemberDto;

public class LendServiceImpl implements LendService {
	
	private MemberService memService;
	private BookService bookService;
	private LendDao dao;
	private LendMessageDao msgDao;
	
	//싱글톤
	private static LendService instance;
	public static LendService getInstance() {
		if(instance==null)
			instance = new LendServiceImpl();
		return instance;
	}
	//
	
	private LendServiceImpl(){
		dao=LendDaoImpl.getInstance();
		memService = MemberServiceImpl.getInstance();
		bookService = BookServiceImpl.getInstance();
		msgDao=LendMessageDaoImpl.getInstance();
	}
	
	//외부로부터 Service받기
	@Override
	public void setMemberService(MemberService memService) {
		this.memService = memService;
	}
	@Override
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	
	
	
	
	@Override
	public boolean reqLend(HttpServletRequest req) throws Exception {
		//req로부터 userid , Session으로 ROLE 꺼내옴
		String userid = req.getParameter("id");
		String bc = req.getParameter("bookcode");
		int bookcode = Integer.parseInt(bc);
		HttpSession session = req.getSession();

		//사서로그인확인,Role받기
		String role = (String)session.getAttribute("ROLE");
		System.out.println("reqLend's ROLE : " + role);
		if(!role.equals("ROLE_MEMBER")) {
			System.out.println("[WARN] 사서만 로그인 할 수 있습니다.");
			req.setAttribute("msg", "[WARN] 사서만 로그인 할 수 있습니다.");
			return false;
		}
		//회원존재유무 확인
		MemberDto dto = memService.memberSearchOne(role, userid);
		
		if(dto!=null)
		{
			//도서존재 유무 확인
			BookDto bdto= bookService.getBook(bookcode);
			if(bdto!=null) {
				//도서가 있다면 대여중인 상태인지 확인(LendDao이용해서 대여중인 책이있는지 조회)
				LendDto ldto=dao.select(bookcode);
				 		
				if(ldto==null) {
					
					
					//commit -false
					ConnectionPool.conn.setAutoCommit(false);
					//도서가 대여가능한 상태라면
					dao.insert(new LendDto(0,bookcode,userid,null,null) );
					//메시지 저장
					msgDao.insert(new LendMessageDto(0,userid,bookcode+" 도서 대여 완료"));
					//commit true
					ConnectionPool.conn.commit();
					ConnectionPool.conn.setAutoCommit(true);
					
					
					
					System.out.println("[INFO] 도서대여 완료되었습니다.");
					req.setAttribute("msg", "[INFO] 도서대여 완료되었습니다.");
					return true;
				}
				System.out.println("[WARN] 요청한 도서는 대여중입니다.");	
				req.setAttribute("msg", "[WARN] 요청한 도서는 대여중입니다.");
				return false;
			}
			System.out.println("[WARN] 해당 도서는 존재하지 않습니다.");
			req.setAttribute("msg", "[WARN] 해당 도서는 존재하지 않습니다.");
			return false;
		}
		System.out.println("[WARN] 해당 회원은 존재하지 않습니다.");
		req.setAttribute("msg", "[WARN] 해당 회원은 존재하지 않습니다.");
		return false;
	}

	@Override
	public List<LendDto> getLendList(HttpServletRequest req) throws SQLException {
		// Session객체안에서 ID를 추출
		// Dao에 Id를 전달해서 id에 맞는 대여정보를 받아오기
		System.out.println("LendServiceImpl's getLendList");
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("ID");
		
		List<LendDto> list = dao.select(id);
		
		return list;
	}

	@Override
	public boolean removeMessage(HttpServletRequest req) throws Exception {
		//user메시지를 테이블 삭제
		System.out.println("removeMessage func : " + req.getParameter("userid"));
		int cnt = msgDao.delete(req.getParameter("userid"));
		
		//session의 alarm관련된 속성 제거
		HttpSession session = req.getSession();
		session.removeAttribute("alarm_cnt");
		session.removeAttribute("alarm_list");
		
		return cnt>0;

	}
	
	
	
	

}
