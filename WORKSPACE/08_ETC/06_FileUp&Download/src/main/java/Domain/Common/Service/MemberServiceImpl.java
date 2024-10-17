package Domain.Common.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Domain.Common.Dao.MemberDao;
import Domain.Common.Dao.MemberDaoImpl;
import Domain.Common.Dto.MemberDto;


public class MemberServiceImpl implements MemberService {

	
	 
	
	private MemberDao dao;
	
	
	
	//싱글톤
	private static MemberService instance;
	public static MemberService getInstance() {
		if(instance==null)
			instance = new MemberServiceImpl();
		return instance;
	}
	//
	
	private MemberServiceImpl() {
		dao=MemberDaoImpl.getInstance();
	
	}
	
	//회원 가입하기
	@Override
	public boolean memberJoin(MemberDto dto) throws Exception {
		
		dto.setRole("ROLE_USER");
		int result = dao.insert(dto);
		
		if(result>0)
			return true;
		return false;
	}
	
	//회원 조회하기(전체) - 사서
	@Override
	public List<MemberDto> memberSearch(String sid) throws Exception{
		
//		String role = this.getRole(sid);
//		
//		if(role.equals("ROLE_MEMBER"))		
//			return dao.select();
		return null;
	}
	//회원 조회하기(한명) - 사서
	@Override
	public MemberDto memberSearchOne(String role,String id) throws Exception{
		if(role.equals("ROLE_MEMBER"))		
			return dao.select(id);
		return null;
	}	
	
	
	//회원 조회하기(한 회원) - 로그인한 회원만 
	@Override
	public MemberDto memberSearch(String id,String sid) throws Exception {
//		Session session = sessionMap.get(sid);
//		
//		if(session!=null && session.getId().equals(id))
//			return dao.select(id);
		
		return null;
	}
	
	
	//회원 수정하기 -- 본인확인
	@Override
	public boolean memberUpdate(MemberDto dto,String sid) throws Exception{
		
//		Session session = sessionMap.get(sid);
//		if(session!=null && session.getId().equals(dto.getId()))
//		{
//			int result = dao.update(dto);
//			if(result>0)
//				return true;
//		}
		
		
		return false;
	}	
	
	//회원 삭제하기
	@Override
	public boolean memberDelete(String id,String sid) throws Exception{
		
//		Session session = sessionMap.get(sid);
//		if(session!=null && session.getId().equals(id))
//		{
//			int result = dao.delete(id);
//			if(result>0)
//				return true;
//		}

		return false;
	}
	
	
	//로그인
	@Override
	public boolean login(HttpServletRequest req) throws Exception{
		
		String id = (String) req.getParameter("id");
		String pw = (String) req.getParameter("pw");
		//1 ID/PW 체크 ->Dao 전달받은 id와 일치하는 정보를 가져와서 Pw일치 확인
		MemberDto dbDto = dao.select(id);
		if(dbDto==null) {
			System.out.println("[ERROR] Login Fail... 아이디가 일치하지 않습니다");
			req.setAttribute("msg", "[ERROR] Login Fail... 아이디가 일치하지 않습니다");
			return false;
		}
		if(!pw.equals(dbDto.getPw())) {
			System.out.println("[ERROR] Login Fail... 패스워드가 일치하지 않습니다");
			req.setAttribute("msg", "[ERROR] Login Fail... 패스워드가 일치하지 않습니다");
			return false;
		}
		System.out.println("login func's dbDto" + dbDto);
		HttpSession session = req.getSession(true);
		System.out.println("login func's session : " + session);
		session.setAttribute("ID", id);
		session.setAttribute("ROLE", dbDto.getRole());
		session.setMaxInactiveInterval(60*30);
		return true;
	}


	


	
	
	
}
