package Domain.Common.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 
import Domain.Common.Dto.LendMessageDto;
import Domain.Common.Dto.MemberDto;

public class LendMessageDaoImpl extends ConnectionPool implements LendMessageDao {
	
	
	private static LendMessageDao instance;
	public static LendMessageDao getInstance() {
		if(instance==null)
			instance=new LendMessageDaoImpl();
		return instance;
	}
	
	private LendMessageDaoImpl(){
		
	}
	
	//CRD
	@Override
	public int insert(LendMessageDto dto) throws Exception{
		pstmt=conn.prepareStatement("insert into tbl_lend_message values(null,?,?)");
		pstmt.setString(1, dto.getUserId());
		pstmt.setString(2,dto.getMsg());
		int result=pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
	
	@Override
	public List<LendMessageDto> select(String userid) throws Exception{
		List<LendMessageDto> list = new ArrayList();
		LendMessageDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_lend_message where userId=?");
		pstmt.setString(1, userid);
		rs=pstmt.executeQuery();
		if(rs!=null)
		{
			while(rs.next()) {
				dto=new LendMessageDto();
				dto.setMsgId(rs.getInt("msgId"));
				dto.setUserId(rs.getString("userid"));
				dto.setMsg(rs.getString("msg"));	
				list.add(dto);
			}
			rs.close();
		}
		
		pstmt.close();
			
		return list;
	}
		

	@Override
	public int delete(String userid)  throws Exception{
		System.out.println("LendMessageDaoImpl's delete : " + userid);
		pstmt=conn.prepareStatement("delete from tbl_lend_message where userId=?");
		pstmt.setString(1, userid);	
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}

	
	
	
	
	
	
	
	
}
