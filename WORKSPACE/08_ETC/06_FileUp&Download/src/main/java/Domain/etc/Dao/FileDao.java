package Domain.etc.Dao;

import java.util.List;

import Domain.etc.Dto.FileDto;

public interface FileDao {

	int insert(FileDto dto) throws Exception;

	List<FileDto> select(String userid) throws Exception;

	int delete(int id) throws Exception;

}