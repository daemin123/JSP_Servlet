package Domain.Common.Dao;

import java.sql.SQLException;
import java.util.List;

import Domain.Common.Dto.BookDto;

public interface BookDao {

	//CRUD
	int insert(BookDto dto) throws Exception;

	List<BookDto> select() throws Exception;

	BookDto select(int bookcode) throws Exception;

	List<BookDto> select(String keyword);

	List<BookDto> select(int startno, int amount) throws Exception;

	int update(BookDto dto) throws Exception;

	int delete(int bookcode) throws Exception;

	int getAmount() throws Exception;

}