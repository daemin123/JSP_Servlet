package Domain.Common.Dao;

import java.util.List;

import Domain.Common.Dto.LendMessageDto;

public interface LendMessageDao {

	//CRUD
	int insert(LendMessageDto dto) throws Exception;

	List<LendMessageDto> select(String userid) throws Exception;

	int delete(String userid) throws Exception;

}