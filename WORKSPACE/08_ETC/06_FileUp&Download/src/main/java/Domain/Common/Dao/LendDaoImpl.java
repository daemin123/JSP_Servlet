package Domain.Common.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 
import Domain.Common.Dto.LendDto;
import Domain.Common.Dto.MemberDto;

public class LendDaoImpl extends ConnectionPool implements LendDao{
	
	
	private static LendDao instance;
	public static LendDao getInstance() {
		if(instance==null)
			instance=new LendDaoImpl();
		return instance;
	}
	
	private LendDaoImpl(){
		
	}
	
	//CRUD
	@Override
	public int insert(LendDto dto) throws Exception{
		pstmt=conn.prepareStatement("insert into tbl_lend values(null,?,?,curdate(),DATE_ADD(CURDATE(), INTERVAL 7 DAY))");
		pstmt.setInt(1, dto.getBookcode());
		pstmt.setString(2,dto.getId());
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}
	
	@Override
	public List<LendDto> select() throws Exception{
		List<LendDto> list = new ArrayList();
		LendDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_lend");
		rs=pstmt.executeQuery();
		if(rs!=null)
		{
			while(rs.next()) {
				dto=new LendDto();
				dto.setLendId(rs.getInt("lendId"));
				dto.setBookcode(rs.getInt("bookcode"));
				dto.setId(rs.getString("id"));
				dto.setLendDate(rs.getDate("lendDate"));
				dto.setReturnDate(rs.getDate("returnDate"));
				list.add(dto);
			}
			rs.close();
		}
		
		pstmt.close();
			
		return list;
	}
	
	
	@Override
	public LendDto select(int bookcode) throws Exception{
		
		LendDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_lend where bookcode=?");
		pstmt.setInt(1, bookcode);
		rs=pstmt.executeQuery();
		if(rs!=null && rs.isBeforeFirst())
		{
			rs.next();
			dto=new LendDto();
			dto.setLendId(rs.getInt("lendId"));
			dto.setBookcode(rs.getInt("bookcode"));
			dto.setId(rs.getString("id"));
			dto.setLendDate(rs.getDate("lendDate"));
			dto.setReturnDate(rs.getDate("returnDate"));	 		
			rs.close();
		}
		pstmt.close();
			
		return dto;
	}	
	

	
	@Override
	public int update(LendDto dto) throws Exception{
		pstmt=conn.prepareStatement("update tbl_lend set bookcode=?,id=?,lendDate=curdate(),returnDate=DATE_ADD(CURDATE(), INTERVAL 7 DAY) where lendId=?");
		pstmt.setInt(1, dto.getBookcode());
		pstmt.setString(2, dto.getId());
		pstmt.setInt(3, dto.getLendId());
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}
	@Override
	public int delete(String id)  throws Exception{
		pstmt=conn.prepareStatement("delete from tbl_member where id=?");
		pstmt.setString(1, id);	
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}

	
	
	@Override
	public List<LendDto> select(String id) throws SQLException  {
		List<LendDto> list = new ArrayList();
		LendDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_lend where id=?");
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();
		if(rs!=null)
		{
			while(rs.next()) {
				dto=new LendDto();
				dto.setLendId(rs.getInt("lendId"));
				dto.setBookcode(rs.getInt("bookcode"));
				dto.setId(rs.getString("id"));
				dto.setLendDate(rs.getDate("lendDate"));
				dto.setReturnDate(rs.getDate("returnDate"));
				list.add(dto);
			}
			rs.close();
		}
		
		pstmt.close();
			
		return list;
	}
	
	
	
	
	
}
