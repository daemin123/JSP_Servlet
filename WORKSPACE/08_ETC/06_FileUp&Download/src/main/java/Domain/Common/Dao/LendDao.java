package Domain.Common.Dao;

import java.sql.SQLException;
import java.util.List;

import Domain.Common.Dto.LendDto;

public interface LendDao {

	//CRUD
	int insert(LendDto dto) throws Exception;

	List<LendDto> select() throws Exception;

	LendDto select(int bookcode) throws Exception;

	int update(LendDto dto) throws Exception;

	int delete(String id) throws 
	Exception;

	List<LendDto> select(String id) throws SQLException ;

}