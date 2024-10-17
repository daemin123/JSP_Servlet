package Domain.Common.Dao;

import java.sql.Date;
import java.util.List;

import Domain.Common.Dto.LogDto;

public interface LogDao {

	int insert() throws Exception;

	List<LogDto> select() throws Exception;

	LogDto select(Date today) throws Exception;

	//	 중요!
	int update() throws Exception;

	int delete(int bookcode) throws Exception;

}