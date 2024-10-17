package Domain.Common.Service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Domain.Common.Dto.LendDto;

public interface LendService {

	//외부로부터 Service받기
	void setMemberService(MemberService memService);

	void setBookService(BookService bookService);

	boolean reqLend(HttpServletRequest req) throws Exception;

	List<LendDto> getLendList(HttpServletRequest req) throws SQLException;

	boolean removeMessage(HttpServletRequest req) throws Exception;

}