package Domain.Common.Dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dto.LogDto;
import Listener.log.State;
 

public class LogDaoImpl extends ConnectionPool implements LogDao  {
	
	//싱글톤 패턴
	private static LogDao instance; 
	public static LogDao getInstance() {
		if(instance==null)
			instance=new LogDaoImpl();
		return instance;
	}
	//
	
	private LogDaoImpl(){
		
	}
	
	
	//CRUD

	@Override
	public int insert() throws Exception{
		//ServletContext()를 확인해서 오늘날짜의 값이 tbl_log에 없으면 자동 생성
		pstmt=conn.prepareStatement("insert into tbl_log values(null,0,0,0,curdate())");
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}
	
	@Override
	public List<LogDto> select() throws Exception{
		List<LogDto> list = new ArrayList();
		LogDto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_log");
		rs=pstmt.executeQuery();
		if(rs!=null)
		{
			while(rs.next()) {
				dto=new LogDto();
				dto.setId(rs.getInt("id"));
				dto.setTotal(rs.getInt("total"));
				dto.setUser(rs.getInt("user"));
				dto.setMember(rs.getInt("member"));
				dto.setDate(rs.getDate("date"));
				list.add(dto);
			}
			rs.close();
		}
		pstmt.close();
			
		return list;
	}
	 
	@Override
	public LogDto select(Date today) throws Exception{
		System.out.println("today : " +today);
		pstmt=conn.prepareStatement("select * from tbl_log where date=?");
		pstmt.setDate(1, today);
		rs=pstmt.executeQuery();
		LogDto dto = new LogDto();
		if(rs.next())
		{
			dto.setId(rs.getInt("id"));
			dto.setTotal(rs.getInt("total"));
			dto.setUser(rs.getInt("user"));
			dto.setMember(rs.getInt("member"));
			dto.setDate(rs.getDate("date"));
		}
		System.out.println("LOGDTO : " + dto);
		
		pstmt.close();
		return dto;
	}	
	
//	 중요!
	@Override
	public int update() throws Exception{
		pstmt=conn.prepareStatement("update tbl_log set total=total+?,user=user+?,member=member+? where date=?");
		pstmt.setInt(1, State.total);
		pstmt.setInt(2,State.user);
		pstmt.setInt(3,State.member);
		pstmt.setDate(4, State.today);
	
		int result=pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
	 
	
	
	@Override
	public int delete(int bookcode)  throws Exception{
		 
		pstmt=conn.prepareStatement("delete from tbl_book where bookcode=?");
		pstmt.setInt(1, bookcode);
		int result=pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
	
	
	
	
	
}
