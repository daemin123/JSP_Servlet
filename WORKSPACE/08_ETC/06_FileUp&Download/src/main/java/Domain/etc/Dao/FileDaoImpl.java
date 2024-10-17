package Domain.etc.Dao;

import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dao.ConnectionPool;
import Domain.etc.Dto.FileDto;

public class FileDaoImpl extends ConnectionPool implements FileDao  {

	
	FileDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static FileDao instance;
	public static FileDao getInstance() {
		if(instance==null)
			instance = new FileDaoImpl();
		return instance;
	}
	
	//CRUD
 
	@Override
	public int insert(FileDto dto) throws Exception{
		pstmt=conn.prepareStatement("insert into tbl_upload_file values(null,?,?,?)");
		pstmt.setString(1, dto.getUser());
		pstmt.setString(2, dto.getDir());
		pstmt.setString(3, dto.getFilename());

		int result=pstmt.executeUpdate();
		pstmt.close();
		return result;	
	}
	
	 
	@Override
	public List<FileDto> select(String userid) throws Exception{
		List<FileDto> list = new ArrayList();
		FileDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_upload_file where user=?");
		pstmt.setString(1, userid);
		rs=pstmt.executeQuery();
		if(rs!=null)
		{
			while(rs.next()) {
				dto=new FileDto();
				dto.setId(rs.getInt("id"));
				dto.setUser(rs.getString("user"));
				dto.setDir(rs.getString("dir"));
				dto.setFilename(rs.getString("filename"));
				list.add(dto);
			}
			rs.close();
		}
		pstmt.close();
			
		return list;
	}
	
	
	
	@Override
	public int delete(int id) throws Exception {
		pstmt=conn.prepareStatement("delete from tbl_upload_file where id=?");
		pstmt.setInt(1, id);
		int result =  pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
	
	
	
}






