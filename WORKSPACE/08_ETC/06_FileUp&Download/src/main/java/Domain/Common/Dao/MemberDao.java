package Domain.Common.Dao;

import java.util.List;

import Domain.Common.Dto.MemberDto;

public interface MemberDao {

	//CRUD
	int insert(MemberDto dto) throws Exception;

	List<MemberDto> select() throws Exception;

	MemberDto select(String id) throws Exception;

	int update(MemberDto dto) throws Exception;

	int delete(String id) throws Exception;

}